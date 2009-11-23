package compiler;

import java.io.*;

public class Ast {

	private AstType type;
	private String value;
	private Ast [] children;

	private Ast (AstType type, String value, Ast [] children) {
		this.type = type;
		this.value = value;
		this.children = children;
	}

	public static Ast parseAst (Reader sourceReader) throws IOException {
		TokenSequence ts = new TokenSequence(sourceReader);
		Ast ast =  readValue(ts);

		// Detect junk at the end of a file
		Token token = ts.next();
		if (token != null) {
			throw new RuntimeException("Found token '" + token.getValue() + "' but expected end of file.");
		}

		return ast; 
	}
	
	private static Ast readValue (TokenSequence ts) throws IOException {
		Token token = ts.next();
		assertNotNull(token);
		// If integer return integer node
		if (token.getType() == TokenType.INTEGER) {
			return new Ast(AstType.INTEGER, token.getValue(), null);
		}
		// If open paren readApplication node
		if (token.getType() == TokenType.OPEN_PAREN) {
			return readApplication(ts);
		}

		unexpectedToken(token);

		// Shouldn't get here 
		return null;
	}

	private static Ast readApplication (TokenSequence ts) throws IOException {
		// Read in operation
		Token operationToken = ts.next();
		assertNotNull(operationToken);

		Ast [] children = new Ast[2];
		// Read in values
		children[0] = readValue(ts);
		children[1] = readValue(ts);

		// Read in close paren
		Token closeToken = ts.next();
		assertTokenType(closeToken, TokenType.CLOSE_PAREN);

		return new Ast(AstType.APPLICATION, operationToken.getValue(), children);
	}

	public enum AstType {
		INTEGER, APPLICATION
	}

	private static void assertNotNull(Token token) {
		if (token == null) {
			throw new RuntimeException("Unexpected end of file");
		}
	}

	private static void assertTokenType(Token token, TokenType checkType) {
		assertNotNull(token);
		if (token.getType() != checkType) {
			throw new RuntimeException(
					"Was expecting a token of type '" + checkType + "' but got type '" + token.getType()  + "' with value '" + token.getValue() + "'.");
		}
	}

	private static void unexpectedToken(Token token) {
		throw new RuntimeException("Got unexpected token '" + token.getValue()  + "'.");
	}

	public String getValue () {
		return value;
	}

	public AstType getType () {
		return type;
	}

	public Ast getChild (int index) {
			return children[index];
	}

	private enum TokenType {
		INTEGER, OPEN_PAREN, CLOSE_PAREN, OPERATOR
	}

	private static class Token {
		private final TokenType type;
		private final String value;

		public Token (TokenType type, String value) {
			this.type = type;
			this.value = value;
		}

		public TokenType getType () {
			return type;
		}

		public String getValue () {
			return value;
		}
	}

	private static class TokenSequence {
		private final Reader sourceReader;
		private Token nextToken;
		private int remainder = -2;
		
		public TokenSequence (Reader sourceReader) {
			this.sourceReader = sourceReader;
		}

		public Token next () throws IOException {
			TokenType type = null;
			StringBuilder value = new StringBuilder();
			int charInt;
			while ((charInt = ((remainder != -2) ? remainder : sourceReader.read())) != -1) {
				// Clear remainder
				remainder = -2;
				char parseChar = (char) charInt;

				// Deal w/ whitespace
				if (Character.isWhitespace(parseChar)) {
					if (type == null) {
						// Drop leading whitespace
						continue;
					}
					else {
						// Terminate token on whitespace
						return new Token(type, value.toString());
					}
				}
				
				// Deal w/ open parens
				if (parseChar == '(') {
					if (type != null) {
						throw new RuntimeException("Parse Error: '(' where unexpected");
					}
					return new Token (TokenType.OPEN_PAREN, "(");
				}

				// Deal w/ close parens
				if (parseChar == ')') {
					if (type != null) {
						remainder = charInt;
						return new Token(type, value.toString());
					}
					return new Token(TokenType.CLOSE_PAREN, ")");
				}

				// Deal w/ integers
				if (Character.isDigit(parseChar)) {
					if ((type != null) && type != TokenType.INTEGER) {
						throw new RuntimeException("Parse Error: digit where unexpected");
					}
					type = TokenType.INTEGER;
					value.append(parseChar);
					continue;
				}

				// Deal w/ operators
				if ((parseChar == '+') || (parseChar == '-')) {
					if (type != null) {
						throw new RuntimeException("Parse Error: operator '" + parseChar  + "' where unexpected");
					}
					return new Token(TokenType.OPERATOR, "" + parseChar);
				}

				// Else Error
				throw new RuntimeException("Parse Error: invalid character '" + parseChar + "'");
			}
			// No more tokens
			return null;
		}
	}

}

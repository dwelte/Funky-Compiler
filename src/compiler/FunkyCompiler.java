package compiler;

import java.io.*;
import java.util.*;

public class FunkyCompiler {

	private static int BASE_POOL_SIZE = 27;

	public static void main (String [] args) {
		if (args.length != 2) {
			System.err.println("Invalid number of arguments");
		}
		String inputFilename = args[0];
		String outputFilename = args[1];
		Reader ir = null;
		Ast ast = null;
		try {
			ir = new FileReader(inputFilename);
			ast = Ast.parseAst(ir);
		}
		catch (IOException e) {
			System.err.println("Error parsing file: " + e);
			e.printStackTrace();
			return;
		}
		finally {
			close(ir);
		}
		
		OutputStream os = null;
		try {
			os = new FileOutputStream(outputFilename);
			os.write(getClassBytes(ast));
		}
		catch (IOException e) {
			System.err.println("Error creating files: " + e);
			e.printStackTrace();
			return;
		}
		finally {
			close(os);
		}
	}

	private static void close (Closeable closee) {
		if (closee != null) {
			try {closee.close();} catch (Exception e) {}
		}
	}

	private static byte [] getClassBytes (Ast ast)  throws IOException {
		ByteArrayOutputStream classBytes = new ByteArrayOutputStream();
		ByteArrayOutputStream codeBytes = new ByteArrayOutputStream();
		ConstantPool constants = new ConstantPool();
		compile(ast, codeBytes, constants);
		printHex(classBytes, PREFIX);
		constants.writeBytes(classBytes);
		printHex(classBytes, MIDDLE);
		classBytes.write(codeBytes.toByteArray());
		printHex(classBytes, POSTFIX);
		return classBytes.toByteArray();
	}

	private static void compile (Ast ast, ByteArrayOutputStream codeBlockBytes, ConstantPool constants) throws IOException {
		ByteArrayOutputStream codeBytes = new ByteArrayOutputStream();
		// Get System.out
		codeBytes.write(VmConst.GETSTATIC);
		writeIntPair(codeBytes, 2);
		// Compile the AST
		int stackSize = compileDw(ast, codeBytes, constants, new StackSize());
		// Invoke println
		codeBytes.write(VmConst.INVOKEVIRTUAL);
		writeIntPair(codeBytes, 3);
		// Return
		codeBytes.write(VmConst.RETURN);
		byte [] codeByteArray = codeBytes.toByteArray();
		// Write the name index
		printHex(codeBlockBytes, "0008");
		// Write the attribute length
		writeIntQuad(codeBlockBytes, 12 + codeByteArray.length);
		// Write the max stack size
		writeIntPair(codeBlockBytes, 1 + stackSize);
		// Write the max locals
		printHex(codeBlockBytes, "0001");
		// Write the code length
		writeIntQuad(codeBlockBytes, codeByteArray.length);
		// Write out the code array
		codeBlockBytes.write(codeByteArray);
		// Write exceptions and attribute blocks
		printHex(codeBlockBytes, "0000 0000");
	}

	private static int compileDw (Ast ast, ByteArrayOutputStream codeBytes, ConstantPool constants, StackSize stackSize) {
		if (ast.getType() == Ast.AstType.APPLICATION) {
			compileDw(ast.getChild(0), codeBytes, constants, stackSize);
			compileDw(ast.getChild(1), codeBytes, constants, stackSize);
			writeOperatorBytes(codeBytes, ast.getValue());
			stackSize.current--;
		}
		else if (ast.getType() == Ast.AstType.INTEGER) {
			int constPosition = constants.add(ast.getValue());
			writeConstantBytes(codeBytes, constPosition);
			stackSize.current++;
			if (stackSize.current > stackSize.max) {
				stackSize.max = stackSize.current;
			}
		}
		return stackSize.max;
	}

	private static class StackSize {
		public int max = 0;
		public int current = 0;
	}

	private static void writeOperatorBytes (ByteArrayOutputStream bytes, String operator) {
		if (operator.equals("+")) {
			bytes.write(VmConst.IADD);
		}
		else if (operator.equals("-")) {
			bytes.write(VmConst.ISUB);
		}
		else if (operator.equals("*")) {
			bytes.write(VmConst.IMUL);
		}
		else if (operator.equals("%")) {
			bytes.write(VmConst.IREM);
		}
		else if (operator.equals("/")) {
			bytes.write(VmConst.IDIV);
		}
	}

	private static void writeConstantBytes (ByteArrayOutputStream bytes, int constantPosition) {
		bytes.write(VmConst.LDC);
		bytes.write(constantPosition);
	}

	private static class ConstantPool {
		private int index = BASE_POOL_SIZE - 1;
		private ArrayList<Integer> values = new ArrayList<Integer>();
		
		public ConstantPool () {
		}

		public int add (String valueStr) {
			int value = Integer.parseInt(valueStr);
			values.add(value);
			index++;
			return index;
		}

		public void writeBytes (ByteArrayOutputStream bytes) {
			// Get the pool size
			int poolSize = index + 1;
			writeIntPair(bytes, poolSize);
			printHex(bytes, STATIC_POOL_ENTRIES);
			for (int value : values) {
				// Tag byte
				bytes.write(3);
				// Value bytes
				writeIntQuad(bytes, value);
			}
		}
	}

	private static void printHex (ByteArrayOutputStream os, String hexString) {
		String currentByteString = "";
		for (int i = 0; i < hexString.length(); i++) {
			if (hexString.charAt(i) == ' ') continue;
			currentByteString = currentByteString + hexString.charAt(i);
			if (currentByteString.length() == 2) {
				os.write(Integer.parseInt(currentByteString, 16));
				currentByteString = "";
			}
		}
	}

	private static void printString (ByteArrayOutputStream os, String outputString) throws IOException, UnsupportedEncodingException {
		byte [] output = outputString.getBytes("UTF-8");
		int length = output.length;
		writeIntPair(os, length);
		os.write(output);
	}

	private static void writeIntPair (ByteArrayOutputStream bytes, int value) {
		// TODO: size check
		bytes.write(value >>> 8);
		value &= 0x00FF;
		bytes.write(value);
	}

	private static void writeIntQuad (ByteArrayOutputStream bytes, int value) {
		bytes.write(value >>> 24);
		value &= 0x00FFFFFF;
		bytes.write(value >>> 16);
		value &= 0x0000FFFF;
		bytes.write(value >>> 8);
		value &= 0x000000FF;
		bytes.write(value);
	}

	private static final String PREFIX =
"cafe babe 0000 0031";

	private static final String STATIC_POOL_ENTRIES = 
"                         0a00 0500 0e09" +
"000f 0010 0a00 1100 1207 0013 0700 1401" +
"0006 3c69 6e69 743e 0100 0328 2956 0100" +
"0443 6f64 6501 000f 4c69 6e65 4e75 6d62" +
"6572 5461 626c 6501 0004 6d61 696e 0100" +
"1628 5b4c 6a61 7661 2f6c 616e 672f 5374" +
"7269 6e67 3b29 5601 000a 536f 7572 6365" +
"4669 6c65 0100 0842 6f62 2e6a 6176 610c" +
"0006 0007 0700 150c 0016 0017 0700 180c" +
"0019 001a 0100 0342 6f62 0100 106a 6176" +
"612f 6c61 6e67 2f4f 626a 6563 7401 0010" +
"6a61 7661 2f6c 616e 672f 5379 7374 656d" +
"0100 036f 7574 0100 154c 6a61 7661 2f69" +
"6f2f 5072 696e 7453 7472 6561 6d3b 0100" +
"136a 6176 612f 696f 2f50 7269 6e74 5374" +
"7265 616d 0100 0770 7269 6e74 6c6e 0100" +
"0428 4929 56";

	private static final String MIDDLE = 
"            00 2100 0400 0500 0000 0000" +
"0200 0100 0600 0700 0100 0800 0000 1d00" +
"0100 0100 0000 052a b700 01b1 0000 0001" +
"0009 0000 0006 0001 0000 0001 0009 000a" +
"000b 0001";


	private static final String POSTFIX = 
"0001 000c 0000 0002 000d";

}

package compiler;

public class VmConst {
	public final static int NOP = 0x00;
	public final static int ACONST_NULL = 0x01;
	public final static int ICONST_M1 = 0x02;
	public final static int ICONST_0 = 0x03;
	public final static int ICONST_1 = 0x04;
	public final static int ICONST_2 = 0x05;
	public final static int ICONST_3 = 0x06;
	public final static int ICONST_4 = 0x07;
	public final static int ICONST_5 = 0x08;
	public final static int LCONST_0 = 0x09;
	public final static int LCONST_1 = 0x0a;
	public final static int FCONST_0 = 0x0b;
	public final static int FCONST_1 = 0x0c;
	public final static int FCONST_2 = 0x0d;
	public final static int DCONST_0 = 0x0e;
	public final static int DCONST_1 = 0x0f;
	public final static int BIPUSH = 0x10;
	public final static int SIPUSH = 0x11;
	public final static int LDC = 0x12;
	public final static int LDC_W = 0x13;
	public final static int LDC2_W = 0x14;
	public final static int ILOAD = 0x15;
	public final static int LLOAD = 0x16;
	public final static int FLOAD = 0x17;
	public final static int DLOAD = 0x18;
	public final static int ALOAD = 0x19;
	public final static int ILOAD_0 = 0x1a;
	public final static int ILOAD_1 = 0x1b;
	public final static int ILOAD_2 = 0x1c;
	public final static int ILOAD_3 = 0x1d;
	public final static int LLOAD_0 = 0x1e;
	public final static int LLOAD_1 = 0x1f;
	public final static int LLOAD_2 = 0x20;
	public final static int LLOAD_3 = 0x21;
	public final static int FLOAD_0 = 0x22;
	public final static int FLOAD_1 = 0x23;
	public final static int FLOAD_2 = 0x24;
	public final static int FLOAD_3 = 0x25;
	public final static int DLOAD_0 = 0x26;
	public final static int DLOAD_1 = 0x27;
	public final static int DLOAD_2 = 0x28;
	public final static int DLOAD_3 = 0x29;
	public final static int ALOAD_0 = 0x2a;
	public final static int ALOAD_1 = 0x2b;
	public final static int ALOAD_2 = 0x2c;
	public final static int ALOAD_3 = 0x2d;
	public final static int IALOAD = 0x2e;
	public final static int LALOAD = 0x2f;
	public final static int FALOAD = 0x30;
	public final static int DALOAD = 0x31;
	public final static int AALOAD = 0x32;
	public final static int BALOAD = 0x33;
	public final static int CALOAD = 0x34;
	public final static int SALOAD = 0x35;
	public final static int ISTORE = 0x36;
	public final static int LSTORE = 0x37;
	public final static int FSTORE = 0x38;
	public final static int DSTORE = 0x39;
	public final static int ASTORE = 0x3a;
	public final static int ISTORE_0 = 0x3b;
	public final static int ISTORE_1 = 0x3c;
	public final static int ISTORE_2 = 0x3d;
	public final static int ISTORE_3 = 0x3e;
	public final static int LSTORE_0 = 0x3f;
	public final static int LSTORE_1 = 0x40;
	public final static int LSTORE_2 = 0x41;
	public final static int LSTORE_3 = 0x42;
	public final static int FSTORE_0 = 0x43;
	public final static int FSTORE_1 = 0x44;
	public final static int FSTORE_2 = 0x45;
	public final static int FSTORE_3 = 0x46;
	public final static int DSTORE_0 = 0x47;
	public final static int DSTORE_1 = 0x48;
	public final static int DSTORE_2 = 0x49;
	public final static int DSTORE_3 = 0x4a;
	public final static int ASTORE_0 = 0x4b;
	public final static int ASTORE_1 = 0x4c;
	public final static int ASTORE_2 = 0x4d;
	public final static int ASTORE_3 = 0x4e;
	public final static int IASTORE = 0x4f;
	public final static int LASTORE = 0x50;
	public final static int FASTORE = 0x51;
	public final static int DASTORE = 0x52;
	public final static int AASTORE = 0x53;
	public final static int BASTORE = 0x54;
	public final static int CASTORE = 0x55;
	public final static int SASTORE = 0x56;
	public final static int POP = 0x57;
	public final static int POP2 = 0x58;
	public final static int DUP = 0x59;
	public final static int DUP_X1 = 0x5a;
	public final static int DUP_X2 = 0x5b;
	public final static int DUP2 = 0x5c;
	public final static int DUP2_X1 = 0x5d;
	public final static int DUP2_X2 = 0x5e;
	public final static int SWAP = 0x5f;
	public final static int IADD = 0x60;
	public final static int LADD = 0x61;
	public final static int FADD = 0x62;
	public final static int DADD = 0x63;
	public final static int ISUB = 0x64;
	public final static int LSUB = 0x65;
	public final static int FSUB = 0x66;
	public final static int DSUB = 0x67;
	public final static int IMUL = 0x68;
	public final static int LMUL = 0x69;
	public final static int FMUL = 0x6a;
	public final static int DMUL = 0x6b;
	public final static int IDIV = 0x6c;
	public final static int LDIV = 0x6d;
	public final static int FDIV = 0x6e;
	public final static int DDIV = 0x6f;
	public final static int IREM = 0x70;
	public final static int LREM = 0x71;
	public final static int FREM = 0x72;
	public final static int DREM = 0x73;
	public final static int INEG = 0x74;
	public final static int LNEG = 0x75;
	public final static int FNEG = 0x76;
	public final static int DNEG = 0x77;
	public final static int ISHL = 0x78;
	public final static int LSHL = 0x79;
	public final static int ISHR = 0x7a;
	public final static int LSHR = 0x7b;
	public final static int IUSHR = 0x7c;
	public final static int LUSHR = 0x7d;
	public final static int IAND = 0x7e;
	public final static int LAND = 0x7f;
	public final static int IOR = 0x80;
	public final static int LOR = 0x81;
	public final static int IXOR = 0x82;
	public final static int LXOR = 0x83;
	public final static int IINC = 0x84;
	public final static int I2L = 0x85;
	public final static int I2F = 0x86;
	public final static int I2D = 0x87;
	public final static int L2I = 0x88;
	public final static int L2F = 0x89;
	public final static int L2D = 0x8a;
	public final static int F2I = 0x8b;
	public final static int F2L = 0x8c;
	public final static int F2D = 0x8d;
	public final static int D2I = 0x8e;
	public final static int D2L = 0x8f;
	public final static int D2F = 0x90;
	public final static int I2B = 0x91;
	public final static int I2C = 0x92;
	public final static int I2S = 0x93;
	public final static int LCMP = 0x94;
	public final static int FCMPL = 0x95;
	public final static int FCMPG = 0x96;
	public final static int DCMPL = 0x97;
	public final static int DCMPG = 0x98;
	public final static int IFEQ = 0x99;
	public final static int IFNE = 0x9a;
	public final static int IFLT = 0x9b;
	public final static int IFGE = 0x9c;
	public final static int IFGT = 0x9d;
	public final static int IFLE = 0x9e;
	public final static int IF_ICMPEQ = 0x9f;
	public final static int IF_ICMPNE = 0xa0;
	public final static int IF_ICMPLT = 0xa1;
	public final static int IF_ICMPGE = 0xa2;
	public final static int IF_ICMPGT = 0xa3;
	public final static int IF_ICMPLE = 0xa4;
	public final static int IF_ACMPEQ = 0xa5;
	public final static int IF_ACMPNE = 0xa6;
	public final static int GOTO = 0xa7;
	public final static int JSR = 0xa8;
	public final static int RET = 0xa9;
	public final static int TABLESWITCH = 0xaa;
	public final static int LOOKUPSWITCH = 0xab;
	public final static int IRETURN = 0xac;
	public final static int LRETURN = 0xad;
	public final static int FRETURN = 0xae;
	public final static int DRETURN = 0xaf;
	public final static int ARETURN = 0xb0;
	public final static int RETURN = 0xb1;
	public final static int GETSTATIC = 0xb2;
	public final static int PUTSTATIC = 0xb3;
	public final static int GETFIELD = 0xb4;
	public final static int PUTFIELD = 0xb5;
	public final static int INVOKEVIRTUAL = 0xb6;
	public final static int INVOKESPECIAL = 0xb7;
	public final static int INVOKESTATIC = 0xb8;
	public final static int INVOKEINTERFACE = 0xb9;
	public final static int UNUSED_1 = 0xba;
	public final static int NEW = 0xbb;
	public final static int NEWARRAY = 0xbc;
	public final static int ANEWARRAY = 0xbd;
	public final static int ARRAYLENGTH = 0xbe;
	public final static int ATHROW = 0xbf;
	public final static int CHECKCAST = 0xc0;
	public final static int INSTANCEOF = 0xc1;
	public final static int MONITORENTER = 0xc2;
	public final static int MONITOREXIT = 0xc3;
	public final static int WIDE = 0xc4;
	public final static int MULTIANEWARRAY = 0xc5;
	public final static int IFNULL = 0xc6;
	public final static int IFNONNULL = 0xc7;
	public final static int GOTO_W = 0xc8;
	public final static int JSR_W = 0xc9;
	public final static int BREAKPOINT = 0xca;
	public final static int IMPDEP1 = 0xfe;
	public final static int IMPDEP2 = 0xff;
}

package binaryArithmetics.javaVersion.bitwise;

public class BitwiseLogic { 

    public static short NAND(short bitA, short bitB) {
        return ((short) ((bitA == 1 && bitB == 1) ?  0 : 1));
    }

    public static short NOT(short bit) {
        return (NAND(bit, bit));
    }

    public static short AND(short bitA, short bitB) {
        return NOT(NAND(bitA, bitB));
    }

    public static short OR(short bitA, short bitB){ 
        return NAND(NOT(bitA), NOT(bitB));
    }

    public static short XOR(short bitA, short bitB) {
        return NAND( NAND(NOT(bitA), bitB), NAND(bitA, NOT(bitB)));
    }
}

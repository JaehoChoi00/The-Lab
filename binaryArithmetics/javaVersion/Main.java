package binaryArithmetics.javaVersion;

import binaryArithmetics.javaVersion.arithmetics.ArithmeticsTest;
import binaryArithmetics.javaVersion.binary.BinaryTest;
import binaryArithmetics.javaVersion.bitwise.BitwiseTest;

public class Main {
    public static void main(String[] args) {
        BitwiseTest bitwiseTest = new BitwiseTest();
        BinaryTest binaryTest = new BinaryTest();
        ArithmeticsTest arithmeticsTest = new ArithmeticsTest();

        bitwiseTest.run();
        binaryTest.run();
        arithmeticsTest.run();
    }
}

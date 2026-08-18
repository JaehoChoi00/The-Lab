package binaryArithmetics.javaVersion.arithmetics;

import static binaryArithmetics.javaVersion.binary.BinaryFunctions.binaryToVariable;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.flip;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.shiftLeft;
import static binaryArithmetics.javaVersion.bitwise.BitwiseLogic.AND;
import static binaryArithmetics.javaVersion.bitwise.BitwiseLogic.XOR;

public class Arithmetics {

    public static String alu_add(String binaryA, String binaryB) {
        int bitsCount = binaryA.length();
        char[] tempA = binaryA.toCharArray();
        char[] tempB = binaryB.toCharArray();

        if (binaryToVariable(binaryB) == 0) {
            return binaryA;
        }

        while(binaryToVariable(String.valueOf(tempB)) != 0) {
            String carryAnd = "";
            String temporarySum = "";
            
            for (int index = bitsCount - 1; index >= 0; index--) {
                int bitA = tempA[index] - '0';
                int bitB = tempB[index] - '0';

                carryAnd = AND((short) bitA, (short) bitB) + carryAnd;
                temporarySum = XOR((short) bitA, (short) bitB) + temporarySum;
            }

            tempA = temporarySum.toCharArray();

            carryAnd = shiftLeft(carryAnd);
            tempB = carryAnd.toCharArray();
        }

        return new String(tempA);
    }

    public static String alu_sub(String binaryA, String binaryB) {
        int bitsCount = binaryA.length();
        char[] tempB = binaryB.toCharArray();

        char[] onePadded = new char[bitsCount];

        java.util.Arrays.fill(onePadded, '0');
        onePadded[bitsCount-1] = '1';

        String twosCompliment = alu_add(flip(String.valueOf(tempB)), String.valueOf(onePadded));

        return alu_add(binaryA, twosCompliment);
    }
}

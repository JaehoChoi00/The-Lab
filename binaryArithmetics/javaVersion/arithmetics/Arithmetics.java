package binaryArithmetics.javaVersion.arithmetics;

import static binaryArithmetics.javaVersion.binary.BinaryFunctions.binaryToVariable;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.flip;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.shiftLeft;
import static binaryArithmetics.javaVersion.bitwise.BitwiseLogic.AND;
import static binaryArithmetics.javaVersion.bitwise.BitwiseLogic.XOR;
import shared.javaUtil.Exposure;
import shared.javaUtil.enums.ExposureCategory;
import shared.javaUtil.enums.ExposureLevel;

public class Arithmetics {

    public static String alu_add(String binaryA, String binaryB) {
        // Pad both inputs to match the same bit width framework
        while (binaryB.length() < binaryA.length()) {
            binaryB = "0" + binaryB;
        }
        while (binaryA.length() < binaryB.length()) {
            binaryA = "0" + binaryA;
        }
        
        int bitsCount = binaryA.length();
        char[] tempA = binaryA.toCharArray();
        char[] tempB = binaryB.toCharArray();

        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL1, "ALU ADDITION INITIALIZED%nInput_1 = %s%nInput_2 = %s%n", binaryA, binaryB);
        Exposure.NEWLINE(ExposureLevel.LEVEL1);

        if (binaryToVariable(binaryB) == 0) {
            Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL5, "Output  = " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET + "%n", binaryA);
            return binaryA;
        }

        int cycle = 1;
        while (binaryToVariable(String.valueOf(tempB)) != 0) {
            Exposure.printf(ExposureLevel.LEVEL3, "--- Addition Accumulation Cycle %d ---%n", cycle);
            
            String carryAnd = "";
            String temporarySum = "";
            
            for (int index = bitsCount - 1; index >= 0; index--) {
                int bitA = tempA[index] - '0';
                int bitB = tempB[index] - '0';

                short stepCarry = AND((short) bitA, (short) bitB);
                short stepSum = XOR((short) bitA, (short) bitB);

                carryAnd = stepCarry + carryAnd;
                temporarySum = stepSum + temporarySum;

                Exposure.printf(
                    ExposureLevel.LEVEL4, 
                    "Bit %d:%n  A: %d%n  B: %d%n  Step Carry: %d%n  Step Sum: %d%n%n", 
                    (bitsCount - 1 - index), bitA, bitB, stepCarry, stepSum
                );
            }

            tempA = temporarySum.toCharArray();
            
            String shiftedCarry = shiftLeft(carryAnd);
            tempB = shiftedCarry.toCharArray();

            Exposure.printf(
                ExposureLevel.LEVEL3, 
                "  Accumulated Sum  : %s%n  Shifted Carry Out: %s%n%n", 
                temporarySum, shiftedCarry
            );
            cycle++;
        }
        String result = new String(tempA);
        
        Exposure.printf(ExposureLevel.LEVEL2, "Output  = " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET + "%n", result);
        return result;
    }

    public static String alu_sub(String binaryA, String binaryB) {
        Exposure.printf(ExposureLevel.LEVEL2, "ALU SUBTRACTION INITIALIZED%nMinuend    (A): %s%nSubtrahend (B): %s%n", binaryA, binaryB);
        Exposure.NEWLINE(ExposureLevel.LEVEL2);

        int bitsCount = binaryA.length();
        char[] tempB = binaryB.toCharArray();

        char[] onePadded = new char[bitsCount];
        for (int i = 0; i < bitsCount; i++) {
            onePadded[i] = '0';
        }
        onePadded[bitsCount - 1] = '1';

        Exposure.printf(ExposureLevel.LEVEL3, " -> Transforming Subtrahend using Two's Complement...%n");
        String twosComplement = alu_add(flip(String.valueOf(tempB)), String.valueOf(onePadded));
        
        Exposure.printf(ExposureLevel.LEVEL3, " -> Performing binary addition: A + (Two's Complement of B)%n");
        String result = alu_add(binaryA, twosComplement);

        Exposure.printf(ExposureLevel.LEVEL2, "Output  = " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET + "%n", result);
        return result;
    }
}

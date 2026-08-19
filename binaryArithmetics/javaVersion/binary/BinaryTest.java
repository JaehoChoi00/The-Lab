package binaryArithmetics.javaVersion.binary;

import static binaryArithmetics.javaVersion.binary.BinaryFunctions.binaryToVariable;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.convertToBinary;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.convertToByteUTF8;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.convertToHex;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.flip;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.hexToBinary;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.shiftLeft;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.shiftRight;
import static shared.javaUtil.VariableConstants.BOLD;
import static shared.javaUtil.VariableConstants.FG_COLOR;
import static shared.javaUtil.VariableConstants.FG_GREEN;
import static shared.javaUtil.VariableConstants.LINEBREAK;
import static shared.javaUtil.VariableConstants.LINEFEED;
import static shared.javaUtil.VariableConstants.NEWLINE;
import static shared.javaUtil.VariableConstants.RESET;
import static shared.javaUtil.VariableConstants.UNDERLINE;

public class BinaryTest {

    public void run() {
        // Binary Operations
        System.out.printf(BOLD + UNDERLINE + "%c[Binary Operations]" +  RESET, (char) LINEFEED);
        NEWLINE();

        String testBinary = "111100111";

        System.out.printf("Original binary: " + BOLD + FG_GREEN + "%s" + RESET + " Value: " + BOLD + FG_COLOR(50) + "%d" + RESET, testBinary, binaryToVariable(testBinary));
        NEWLINE();

        String shiftLeftBinary = shiftLeft(testBinary);

        System.out.printf("Binary after shift Left: " + BOLD + FG_GREEN + "%s" + RESET + " Value: " + BOLD + FG_COLOR(50) + "%d" + RESET, shiftLeftBinary, binaryToVariable(shiftLeftBinary));
        NEWLINE();

        String shiftLeftBinary5 = shiftLeft(testBinary, 2);

        System.out.printf("Binary after shift Left by 2: " + BOLD + FG_GREEN + "%s" + RESET + " Value: " + BOLD + FG_COLOR(50) + "%d" + RESET, shiftLeftBinary5, binaryToVariable(shiftLeftBinary5));
        NEWLINE();

        String shiftRightBinary = shiftRight(testBinary);

        System.out.printf("Binary after shift Right: " + BOLD + FG_GREEN + "%s" + RESET + " Value: " + BOLD + FG_COLOR(50) + "%d" + RESET, shiftRightBinary, binaryToVariable(shiftRightBinary));
        NEWLINE();

        String shiftRightBinary5 = shiftRight(testBinary, 2);

        System.out.printf("Binary after shift Right by 2: " + BOLD + FG_GREEN + "%s" + RESET + " Value: " + BOLD + FG_COLOR(50) + "%d" + RESET, shiftRightBinary5, binaryToVariable(shiftRightBinary5));
        NEWLINE();

        String flippedBinary = flip(testBinary);

        System.out.printf("Binary after flipping: " + BOLD + FG_GREEN + "%s" + RESET + " Value: " + BOLD + FG_COLOR(50) + "%d" + RESET, flippedBinary, binaryToVariable(flippedBinary));
        NEWLINE();

        int variableToConvert = 0xbb67ae85;

        System.out.printf("Variable to convert: " + BOLD + FG_GREEN + "%d" + RESET + " Binary: " + BOLD + FG_COLOR(50) + "%s" + RESET, variableToConvert, convertToBinary(variableToConvert));
        NEWLINE();

        String binaryToConvert = "001100011111";

        System.out.printf("Binary to convert: " + BOLD + FG_GREEN + "%s" + RESET + " Hex: " + BOLD + FG_COLOR(50) + "%s" + RESET, binaryToConvert, convertToHex(binaryToConvert));
        NEWLINE();

        String hexToConvert = "0xbb67ae85";

        System.out.printf("Hex to convert: " + BOLD + FG_GREEN + "%s" + RESET + " Binary: " + BOLD + FG_COLOR(50) + "%s" + RESET, hexToConvert, hexToBinary(hexToConvert));
        NEWLINE();

        String stringToConvert = "Hello 안녕";

        System.out.printf("String to convert: " + BOLD + FG_GREEN + "%s" + RESET + " Byte: " + BOLD + FG_COLOR(50) + "%s" + RESET, stringToConvert, java.util.Arrays.toString(convertToByteUTF8(stringToConvert)));
        NEWLINE();

        LINEBREAK();
    }
    
}


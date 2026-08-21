package binaryArithmetics.javaVersion.binary;

import static binaryArithmetics.javaVersion.binary.BinaryFunctions.binaryToVariable;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.convertToBinary;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.convertToByteUTF8;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.convertToHex;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.flip;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.hexToBinary;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.shiftLeft;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.shiftRight;
import shared.javaUtil.Exposure;
import shared.javaUtil.enums.ExposureCategory;
import shared.javaUtil.enums.ExposureLevel;

public class BinaryTest {

    public void run() {
        // Main Category Header
        Exposure.printf(
            ExposureCategory.TEST, ExposureLevel.LEVEL1, 
            Exposure.BOLD + Exposure.UNDERLINE + "%c[Binary Operations]" + Exposure.RESET, 
            (char) Exposure.LINEFEED
        );
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);

        String testBinary = "111100111";

        // --- 1. Binary to Variable ---
        int varValue = binaryToVariable(testBinary);
        Exposure.printf(
            ExposureCategory.TEST, ExposureLevel.LEVEL1, 
            Exposure.BOLD + "Binary to Variable ->" + Exposure.RESET 
            + " Input: " + Exposure.FG_CYAN + "%s" + Exposure.RESET 
            + " Result: " + Exposure.BOLD + Exposure.FG_GREEN + "%d" + Exposure.RESET, 
            testBinary, varValue
        );
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);

        // --- 2. Shift Left (1) ---
        String shiftLeft1 = shiftLeft(testBinary);
        Exposure.printf(
            ExposureCategory.TEST, ExposureLevel.LEVEL1, 
            Exposure.BOLD + "Shift Left (1) ->" + Exposure.RESET 
            + " Input: " + Exposure.FG_CYAN + "%s" + Exposure.RESET 
            + " Result: " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET, 
            testBinary, shiftLeft1
        );
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);

        // --- 3. Shift Left (2) ---
        String shiftLeft2 = shiftLeft(testBinary, 2);
        Exposure.printf(
            ExposureCategory.TEST, ExposureLevel.LEVEL1, 
            Exposure.BOLD + "Shift Left (2) ->" + Exposure.RESET 
            + " Input: " + Exposure.FG_CYAN + "%s" + Exposure.RESET 
            + " Result: " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET, 
            testBinary, shiftLeft2
        );
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);

        // --- 4. Shift Right (1) ---
        String shiftRight1 = shiftRight(testBinary);
        Exposure.printf(
            ExposureCategory.TEST, ExposureLevel.LEVEL1, 
            Exposure.BOLD + "Shift Right (1) ->" + Exposure.RESET 
            + " Input: " + Exposure.FG_CYAN + "%s" + Exposure.RESET 
            + " Result: " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET, 
            testBinary, shiftRight1
        );
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);

        // --- 5. Shift Right (2) ---
        String shiftRight2 = shiftRight(testBinary, 2);
        Exposure.printf(
            ExposureCategory.TEST, ExposureLevel.LEVEL1, 
            Exposure.BOLD + "Shift Right (2) ->" + Exposure.RESET 
            + " Input: " + Exposure.FG_CYAN + "%s" + Exposure.RESET 
            + " Result: " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET, 
            testBinary, shiftRight2
        );
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);

        // --- 6. Flip Bits ---
        String flipped = flip(testBinary);
        Exposure.printf(
            ExposureCategory.TEST, ExposureLevel.LEVEL1, 
            Exposure.BOLD + "Flip Bits ->" + Exposure.RESET 
            + " Input: " + Exposure.FG_CYAN + "%s" + Exposure.RESET 
            + " Result: " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET, 
            testBinary, flipped
        );
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);

        // --- 7. Decimal to Binary ---
        int variableToConvert = 0xbb67ae85;
        String convertedBinary = convertToBinary(variableToConvert);
        Exposure.printf(
            ExposureCategory.TEST, ExposureLevel.LEVEL1, 
            Exposure.BOLD + "Decimal to Binary ->" + Exposure.RESET 
            + " Input: " + Exposure.FG_CYAN + "%d" + Exposure.RESET 
            + " Result: " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET, 
            variableToConvert, convertedBinary
        );
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);

        // --- 8. Binary to Hex ---
        String binaryToConvert = "001100011111";
        String convertedHex = convertToHex(binaryToConvert);
        Exposure.printf(
            ExposureCategory.TEST, ExposureLevel.LEVEL1, 
            Exposure.BOLD + "Binary to Hex ->" + Exposure.RESET 
            + " Input: " + Exposure.FG_CYAN + "%s" + Exposure.RESET 
            + " Result: " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET, 
            binaryToConvert, convertedHex
        );
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);

        // --- 9. Hex to Binary ---
        String hexToConvert = "0xbb67ae85";
        String convertedHexBinary = hexToBinary(hexToConvert);
        Exposure.printf(
            ExposureCategory.TEST, ExposureLevel.LEVEL1, 
            Exposure.BOLD + "Hex to Binary ->" + Exposure.RESET 
            + " Input: " + Exposure.FG_CYAN + "%s" + Exposure.RESET 
            + " Result: " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET, 
            hexToConvert, convertedHexBinary
        );
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);

        // --- 10. String to UTF-8 Bytes ---
        String stringToConvert = "Hello 안녕";
        int[] convertedBytes = convertToByteUTF8(stringToConvert);
        Exposure.printf(
            ExposureCategory.TEST, ExposureLevel.LEVEL1, 
            Exposure.BOLD + "String to UTF-8 Bytes ->" + Exposure.RESET 
            + " Input: " + Exposure.FG_CYAN + "\"%s\"" + Exposure.RESET 
            + " Result: " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET, 
            stringToConvert, java.util.Arrays.toString(convertedBytes)
        );
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);

        // Final layout separator
        Exposure.LINEBREAK(ExposureCategory.TEST, ExposureLevel.LEVEL1);
    }
}

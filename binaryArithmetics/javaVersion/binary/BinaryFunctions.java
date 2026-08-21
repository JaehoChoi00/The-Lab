package binaryArithmetics.javaVersion.binary;

import shared.javaUtil.Exposure;
import shared.javaUtil.enums.ExposureCategory;
import shared.javaUtil.enums.ExposureLevel;

public class BinaryFunctions {
    public static char[] hexMap = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        
    public static String shiftLeft(String binary) {
        return shiftLeft(binary, 1);
    }

    public static String shiftLeft(String binary, int number) {
        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, "%n" + Exposure.BOLD + Exposure.FG_CYAN + "[Shift Left]" + Exposure.RESET + " Input: " + Exposure.BOLD + "%s" + Exposure.RESET + " | Shifts: " + Exposure.BOLD + "%d" + Exposure.RESET + "%n", binary, number);
        
        int binaryLength = binary.length();
        char[] binaryChars = binary.toCharArray();
        int reformatBinaryLength = ((binaryLength + 7) / 8) * 8;

        Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL4, " -> " + Exposure.FG_YELLOW + "Padded:" + Exposure.RESET + " %d bits -> %d bits%n", binaryLength, reformatBinaryLength);

        char[] shiftLeftBinary = new char[reformatBinaryLength];
        for (int i = 0; i < reformatBinaryLength; i++) {
            shiftLeftBinary[i] = '0';
        }

        for (int bit = binaryLength - 1; bit >= 0; bit--) {
            shiftLeftBinary[(reformatBinaryLength - 1) - (binaryLength - 1 - bit)] = binaryChars[bit];
        }

        Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL3, " -> " + Exposure.FG_YELLOW + "Padded State:" + Exposure.RESET + " %s%n", new String(shiftLeftBinary));

        for (int loop = 0; loop < number; loop++) {
            for (int bit = 0; bit < reformatBinaryLength - 1; bit++) {
                shiftLeftBinary[bit] = shiftLeftBinary[bit + 1];
            }
            shiftLeftBinary[reformatBinaryLength - 1] = '0';

            Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL4, "   " + Exposure.BOLD + "Shift %d:" + Exposure.RESET + " %s%n", (loop + 1), new String(shiftLeftBinary));
        }

        String result = new String(shiftLeftBinary);
        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, " Output: " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET + "%n", result);
        return result;
    }

    public static String shiftRight(String binary) {
        return shiftRight(binary, 1);
    }

    public static String shiftRight(String binary, int number) {
        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, "%n" + Exposure.BOLD + Exposure.FG_CYAN + "[Shift Right]" + Exposure.RESET + " Input: " + Exposure.BOLD + "%s" + Exposure.RESET + " | Shifts: " + Exposure.BOLD + "%d" + Exposure.RESET + "%n", binary, number);

        int binaryLength = binary.length();
        char[] binaryChars = binary.toCharArray();
        int reformatBinaryLength = ((binaryLength + 7) / 8) * 8;

        Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL4, " -> " + Exposure.FG_YELLOW + "Padded:" + Exposure.RESET + " %d bits -> %d bits%n", binaryLength, reformatBinaryLength);

        char[] shiftRightBinary = new char[reformatBinaryLength];
        for (int i = 0; i < reformatBinaryLength; i++) {
            shiftRightBinary[i] = '0';
        }

        for (int bit = binaryLength - 1; bit >= 0; bit--) {
            shiftRightBinary[(reformatBinaryLength - 1) - (binaryLength - 1 - bit)] = binaryChars[bit];
        }

        Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL3, " -> " + Exposure.FG_YELLOW + "Padded State:" + Exposure.RESET + " %s%n", new String(shiftRightBinary));

        for (int loop = 0; loop < number; loop++) {
            for (int bit = reformatBinaryLength - 1; bit > 0; bit--) {
                shiftRightBinary[bit] = shiftRightBinary[bit - 1];
            }
            shiftRightBinary[0] = '0';
            
            Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL4, "   " + Exposure.BOLD + "Shift %d:" + Exposure.RESET + " %s%n", (loop + 1), new String(shiftRightBinary));
        }

        String result = new String(shiftRightBinary);
        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, " Output: " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET + "%n", result);
        return result;
    }
    
    public static String flip(String binary) {
        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, "%n" + Exposure.BOLD + Exposure.FG_CYAN + "[Flip Bits]" + Exposure.RESET + " Input: " + Exposure.BOLD + "%s" + Exposure.RESET + "%n", binary);

        int binaryLength = binary.length();
        char[] binaryChars = binary.toCharArray();
        int reformatBinaryLength = ((binaryLength + 7) / 8) * 8;

        char[] flippedBinary = new char[reformatBinaryLength];
        for (int i = 0; i < reformatBinaryLength; i++) {
            flippedBinary[i] = '0';
        }

        for (int bit = binaryLength - 1; bit >= 0; bit--) {
            flippedBinary[(reformatBinaryLength - 1) - (binaryLength - 1 - bit)] = binaryChars[bit];
        }
        
        Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL3, " -> " + Exposure.FG_YELLOW + "Normalized:" + Exposure.RESET + " %s%n", new String(flippedBinary));

        for (int bit = 0; bit < reformatBinaryLength; bit++) {
            int bitValue = flippedBinary[bit] - '0';
            short flippedValue = (short)(1 - bitValue);
            flippedBinary[bit] = (char) (flippedValue + '0');
            
            Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL4, "   Bit [%d]: " + Exposure.BOLD + "%d" + Exposure.RESET + " -> " + Exposure.BOLD + Exposure.FG_YELLOW + "%d" + Exposure.RESET + "%n", bit, bitValue, flippedValue);
        }
        
        String result = new String(flippedBinary);
        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, " Output: " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET + "%n", result);
        return result;
    }

    public static int binaryToVariable(String binary) {
        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, "%n" + Exposure.BOLD + Exposure.FG_CYAN + "[Binary -> Decimal]" + Exposure.RESET + " Input: " + Exposure.BOLD + "%s" + Exposure.RESET + "%n", binary);

        int binaryLength = binary.length();
        char[] binaryChars = binary.toCharArray();
        int[] conversionGrid = new int[binaryLength];
        int variable = 0;

        for (int index = 0; index < binaryLength; index++) {
            conversionGrid[binaryLength - 1 - index] = (1 << index);
            Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5, "   Bit [%d] weight: 2^%d = %d%n", (binaryLength - 1 - index), index, conversionGrid[binaryLength - 1 - index]);
        }

        Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL3, " -> Grid size: " + Exposure.BOLD + "%d bits" + Exposure.RESET + "%n", binaryLength);

        for (int bit = binaryLength - 1; bit >= 0; bit--) {
            if (binaryChars[bit] == '1') {
                variable = variable + conversionGrid[bit];
                Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL4, "   Bit [%d] = '1' " + Exposure.FG_YELLOW + "(+%d)" + Exposure.RESET + " -> Total: " + Exposure.BOLD + "%d" + Exposure.RESET + "%n", bit, conversionGrid[bit], variable);
            } else {
                Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5, "   Bit [%d] = '0' (skip)%n", bit);
            }
        }

        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, " Output: " + Exposure.BOLD + Exposure.FG_GREEN + "%d" + Exposure.RESET + "%n", variable);
        return variable;
    }

    public static String convertToBinary(int variable) {
        int rawLength = Integer.toBinaryString(variable).length();
        
        if (variable < 0) rawLength = 8;

        int reformatBinaryLength = ((rawLength + 7) / 8) * 8;
        return convertToBinary(variable, reformatBinaryLength);
    }

    public static String convertToBinary(int variable, int targetLength) {
        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, "%n" + Exposure.BOLD + Exposure.FG_CYAN + "[Decimal -> Binary]" + Exposure.RESET + " Input: " + Exposure.BOLD + "%d" + Exposure.RESET + " (Length: %d bits)%n", variable, targetLength);

        long unsignedValue = variable;

        if (unsignedValue < 0) {
            unsignedValue = 4294967296L + unsignedValue; 
            Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL3, " -> " + Exposure.FG_YELLOW + "Two's Complement Applied:" + Exposure.RESET + " %d%n", unsignedValue);
        }

        char[] binaryBits = new char[targetLength];

        for (int i = targetLength - 1; i >= 0; i--) {
            if (unsignedValue > 0) {
                long rem = unsignedValue % 2;
                binaryBits[i] = (rem == 1) ? '1' : '0';
                Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5, "   Bit [%d]: %d %% 2 = %d -> '%c'%n", i, unsignedValue, rem, binaryBits[i]);
                unsignedValue = unsignedValue / 2;
                Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL4, "   Bit [%d] = " + Exposure.BOLD + "'%c'" + Exposure.RESET + " | Remainder: %d%n", i, binaryBits[i], unsignedValue);
            } else {
                binaryBits[i] = '0';
                Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5, "   Bit [%d] = '0'%n", i);
            }
        }

        String result = new String(binaryBits);
        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, " Output: " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET + "%n", result);
        return result;
    }

    public static String convertToHex(String binary) {
        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, "%n" + Exposure.BOLD + Exposure.FG_CYAN + "[Binary -> Hex]" + Exposure.RESET + " Input: " + Exposure.BOLD + "%s" + Exposure.RESET + "%n", binary);
        int binaryLength = binary.length();
        
        int reformatBinaryLength = ((binaryLength + 3) / 4) * 4;
        Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL4, " -> Aligned to nibble boundary: %d bits -> %d bits%n", binaryLength, reformatBinaryLength);

        char[] binaryArray = new char[reformatBinaryLength];
        for (int i = 0; i < reformatBinaryLength; i++) {
            binaryArray[i] = '0';
        }

        for (int bit = binaryLength - 1; bit >= 0; bit--) {
            int targetIndex = (reformatBinaryLength - 1) - (binaryLength - 1 - bit);
            binaryArray[targetIndex] = binary.charAt(bit); 
        }

        String hex = "";

        for (int loop = 0; loop < reformatBinaryLength; loop += 4) {
            String nibble = "" + binaryArray[loop] + binaryArray[loop + 1] + binaryArray[loop + 2] + binaryArray[loop + 3];
            int decimalValue = binaryToVariable(nibble);
            char hexChar = hexMap[decimalValue];
            hex = hex + hexChar;

            Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL3, " -> Nibble [%d..%d]: " + Exposure.BOLD + "%s" + Exposure.RESET + "%n", loop, loop + 3, nibble);
            Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL4, "   Mapped %s -> Dec: %d -> Hex: " + Exposure.BOLD + Exposure.FG_YELLOW + "'%c'" + Exposure.RESET + "%n", nibble, decimalValue, hexChar);
            Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5, "     hexMap[%d] = '%c'%n", decimalValue, hexChar);
        }

        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, " Output: " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET + "%n", hex);
        return hex;
    }

    public static String hexToBinary(String hex) {
        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, "%n" + Exposure.BOLD + Exposure.FG_CYAN + "[Hex -> Binary]" + Exposure.RESET + " Input: " + Exposure.BOLD + "%s" + Exposure.RESET + "%n", hex);

        if (hex.contains("0x")){
            hex = hex.substring(2);
            Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL4, " -> Stripped '0x' prefix: %s%n", hex);
        }
        
        int hexLength = hex.length();
        char[] hexArray = hex.toCharArray();

        String binary = "";

        for (int hexIndex = 0; hexIndex < hexLength; hexIndex++) {
            for (int index = 0; index < hexMap.length; index++) {
                if (hexArray[hexIndex] == hexMap[index]) {
                    String nibbleBin = convertToBinary(index).substring(4);
                    binary = binary + nibbleBin;

                    Exposure.printf(ExposureLevel.LEVEL3, " -> Hex digit " + Exposure.BOLD + "'%c'" + Exposure.RESET + " mapped%n", hexArray[hexIndex]);
                    Exposure.printf(ExposureLevel.LEVEL4, "   Hex '%c' -> Dec: %d -> Binary: " + Exposure.BOLD + Exposure.FG_YELLOW + "%s" + Exposure.RESET + "%n", hexArray[hexIndex], index, nibbleBin);
                    Exposure.printf(ExposureLevel.LEVEL5, "     Match at hexMap[%d]%n", index);
                    break;
                }
            }
        }

        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, " Output: " + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET + "%n", binary);
        return binary;
    }

    public static int[] convertToByteUTF8(String textToConvert) {
        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, "%n" + Exposure.BOLD + Exposure.FG_CYAN + "[UTF-8 Encoding]" + Exposure.RESET + " Input Text: " + Exposure.BOLD + "\"%s\"" + Exposure.RESET + "%n", textToConvert);

        int textLength = textToConvert.length();
        int totalBytesNeeded = 0;

        for (int index = 0; index < textLength;) {
            int codePoint = textToConvert.codePointAt(index);
            if (codePoint >= 0 && codePoint <= 127) totalBytesNeeded += 1;
            else if (codePoint <= 2047) totalBytesNeeded += 2;
            else if (codePoint <= 65535) totalBytesNeeded += 3;
            else if (codePoint <= 1114111) totalBytesNeeded += 4;

            Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5, "   U+%04X scan byte requirements%n", codePoint);
            index += Character.charCount(codePoint); 
        }

        Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL3, " -> Total Bytes Required: " + Exposure.BOLD + "%d" + Exposure.RESET + "%n", totalBytesNeeded);

        int[] intBytes = new int[totalBytesNeeded];
        int byteIndex = 0;

        for (int index = 0; index < textLength;) {
            int conversion = textToConvert.codePointAt(index);
            Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL4, " Encoding U+%04X at index %d...%n", conversion, index);

            if (conversion >= 0 && conversion <= 127) {
                intBytes[byteIndex] = conversion;
                Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL4, "   [ASCII] Value: " + Exposure.BOLD + Exposure.FG_YELLOW + "%d" + Exposure.RESET + "%n", conversion);
                byteIndex++;
            }
            else if (conversion >= 128 && conversion <= 2047) {
                String binaryConversion = convertToBinary(conversion, 11);
                String byte1 = "110" + binaryConversion.substring(0, 5);
                String byte2 = "10" + binaryConversion.substring(5, 11);

                intBytes[byteIndex] = binaryToVariable(byte1);
                intBytes[byteIndex+1] = binaryToVariable(byte2);

                Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL4, "   [2-Byte UTF-8] Bytes: " + Exposure.BOLD + Exposure.FG_YELLOW + "%d, %d" + Exposure.RESET + "%n", intBytes[byteIndex], intBytes[byteIndex+1]);
                Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5, "     Byte1: %s, Byte2: %s%n", byte1, byte2);
                byteIndex += 2;
            }
            else if (conversion >= 2048 && conversion <= 65535) {
                String binaryConversion = convertToBinary(conversion, 16);
                String byte1 = "1110" + binaryConversion.substring(0, 4);
                String byte2 = "10"   + binaryConversion.substring(4, 10);
                String byte3 = "10"   + binaryConversion.substring(10, 16);

                intBytes[byteIndex]   = binaryToVariable(byte1);
                intBytes[byteIndex+1] = binaryToVariable(byte2);
                intBytes[byteIndex+2] = binaryToVariable(byte3);

                Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL4, "   [3-Byte UTF-8] Bytes: " + Exposure.BOLD + Exposure.FG_YELLOW + "%d, %d, %d" + Exposure.RESET + "%n", intBytes[byteIndex], intBytes[byteIndex+1], intBytes[byteIndex+2]);
                Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5, "     Byte1: %s, Byte2: %s, Byte3: %s%n", byte1, byte2, byte3);
                byteIndex += 3;
            }
            else if (conversion >= 65536 && conversion <= 1114111) {
                String binaryConversion = convertToBinary(conversion, 21);
                String byte1 = "11110" + binaryConversion.substring(0, 3);
                String byte2 = "10"    + binaryConversion.substring(3, 9);
                String byte3 = "10"    + binaryConversion.substring(9, 15);
                String byte4 = "10"    + binaryConversion.substring(15, 21);

                intBytes[byteIndex]   = binaryToVariable(byte1);
                intBytes[byteIndex+1] = binaryToVariable(byte2);
                intBytes[byteIndex+2] = binaryToVariable(byte3);
                intBytes[byteIndex+3] = binaryToVariable(byte4);

                Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL4, "   [4-Byte UTF-8] Bytes: " + Exposure.BOLD + Exposure.FG_YELLOW + "%d, %d, %d, %d" + Exposure.RESET + "%n", intBytes[byteIndex], intBytes[byteIndex+1], intBytes[byteIndex+2], intBytes[byteIndex+3]);
                Exposure.printf(ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5, "     Byte1: %s, Byte2: %s, Byte3: %s, Byte4: %s%n", byte1, byte2, byte3, byte4);
                byteIndex += 4;
            }
            
            index += Character.charCount(conversion);
        }

        Exposure.printf(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, " Output: " + Exposure.BOLD + Exposure.FG_GREEN + "Byte Array length %d generated successfully." + Exposure.RESET + "%n", intBytes.length);
        return intBytes;
    }
}
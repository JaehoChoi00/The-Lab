package binaryArithmetics.javaVersion.binary;

public class BinaryFunctions {
    public static char[] hexMap = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        
    public static String shiftLeft(String binary) {
        return shiftLeft(binary, 1);
    }

    public static String shiftLeft(String binary, int number) {
        int binaryLength = binary.length();
        char[] binaryChars = binary.toCharArray();

        int reformatBinaryLength = ((binaryLength + 7) / 8) * 8;

        char[] shiftLeftBinary = new char[reformatBinaryLength];

        for (int i = 0; i < reformatBinaryLength; i++) {
            shiftLeftBinary[i] = '0';
        }

        for (int bit = binaryLength - 1; bit >= 0; bit--) {
            shiftLeftBinary[(reformatBinaryLength - 1) - (binaryLength - 1 - bit)] = binaryChars[bit];
        }

        for (int loop = 0; loop < number; loop++) {
            
            for (int bit = 0; bit < reformatBinaryLength - 1; bit++) {
                shiftLeftBinary[bit] = shiftLeftBinary[bit + 1];
            }
            
            shiftLeftBinary[reformatBinaryLength - 1] = '0';
        }

        return new String(shiftLeftBinary);
    }

    public static String shiftRight(String binary) {
        return shiftRight(binary, 1);
    }

    public static String shiftRight(String binary, int number) {
        int binaryLength = binary.length();
        char[] binaryChars = binary.toCharArray();

        int reformatBinaryLength = ((binaryLength + 7) / 8) * 8;

        char[] shiftRightBinary = new char[reformatBinaryLength];

        for (int i = 0; i < reformatBinaryLength; i++) {
            shiftRightBinary[i] = '0';
        }

        for (int bit = binaryLength - 1; bit >= 0; bit--) {
            shiftRightBinary[(reformatBinaryLength - 1) - (binaryLength - 1 - bit)] = binaryChars[bit];
        }

        for (int loop = 0; loop < number; loop++) {
            
            for (int bit = reformatBinaryLength - 1; bit > 0; bit--) {
                shiftRightBinary[bit] = shiftRightBinary[bit - 1];
            }
            
            shiftRightBinary[0] = '0';
        }

        return new String(shiftRightBinary);
    }
    
    public static String flip(String binary) {
        
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
        
        for (int bit = 0; bit < reformatBinaryLength; bit++) {
            int bitValue = flippedBinary[bit] - '0';
            short flippedValue = (short)(1 - bitValue);
            flippedBinary[bit] = (char) (flippedValue + '0');
        }
        
        return new String(flippedBinary);
    }

    public static int binaryToVariable(String binary) {

        int binaryLength = binary.length();
        char[] binaryChars = binary.toCharArray();
        int[] conversionGrid = new int[binaryLength];
        int variable = 0;

        for (int index = 0; index < binaryLength; index++) {
            conversionGrid[binaryLength - 1 - index] = (1 << index);
        }

        for (int bit = binaryLength - 1; bit >= 0; bit--) {
            if (binaryChars[bit] == '1') {
                variable = variable + conversionGrid[bit];
            }
        }
        
        return variable;
    }

    public static String convertToBinary(int variable) {
        int rawLength = Integer.toBinaryString(variable).length();
        
        if (variable < 0) rawLength = 8;

        int reformatBinaryLength = ((rawLength + 7) / 8) * 8;
        return convertToBinary(variable, reformatBinaryLength);
    }

    public static String convertToBinary(int variable, int targetLength) {
        long unsignedValue = variable;

        // Negative numbers using Two's Complement (32-bit unsigned offset)
        if (unsignedValue < 0) {
            unsignedValue = 4294967296L + unsignedValue; 
        }

        char[] binaryBits = new char[targetLength];

        for (int i = targetLength - 1; i >= 0; i--) {
            if (unsignedValue > 0) {
                binaryBits[i] = (unsignedValue % 2 == 1) ? '1' : '0';
                unsignedValue = unsignedValue / 2;
            } else {
                binaryBits[i] = '0'; 
            }
        }

        return new String(binaryBits);
    }

    public static String convertToHex(String binary) {
        int binaryLength = binary.length();
        
        int reformatBinaryLength = ((binaryLength + 3) / 4) * 4;

        char[] binaryArray = new char[reformatBinaryLength];
        for (int i = 0; i < reformatBinaryLength; i++) {
            binaryArray[i] = '0';
        }

        for (int bit = binaryLength - 1; bit >= 0; bit--) {
            int targetIndex = (reformatBinaryLength - 1) - (binaryLength - 1 - bit);
            binaryArray[targetIndex] = binary.charAt(bit); 
        }

        String hex = "";

        for (int loop = 0; loop < binaryLength; loop+=4) {
            String nibble = "";
            nibble = nibble + binaryArray[loop] + binaryArray[loop + 1] + binaryArray[loop + 2] + binaryArray[loop + 3];
            
            hex = hex + hexMap[binaryToVariable(nibble)];
        }

        
        return hex;
    }

    public static String hexToBinary(String hex) {
        
        if (hex.contains("0x")){
            hex = hex.substring(2);
        }
        
        int hexLength = hex.length();
        char[] hexArray = hex.toCharArray();

        String binary = "";

        for (int hexIndex = 0; hexIndex < hexLength; hexIndex++) {
            for (int index = 0; index < hexMap.length; index++) {
                if (hexArray[hexIndex] == hexMap[index]) {
                    binary = binary + convertToBinary(index).substring(4);
                    break;
                }
            }
        }

        return binary;
    }

    public static int[] convertToByteUTF8(String textToConvert) {
        int textLength = textToConvert.length();

        int totalBytesNeeded = 0;
        for (int index = 0; index < textLength;) {
            int codePoint = textToConvert.codePointAt(index);
            if (codePoint >= 0 && codePoint <= 127) totalBytesNeeded += 1;
            else if (codePoint <= 2047) totalBytesNeeded += 2;
            else if (codePoint <= 65535) totalBytesNeeded += 3;
            else if (codePoint <= 1114111) totalBytesNeeded += 4;

            index += Character.charCount(codePoint); 
        }

        int[] intBytes = new int[totalBytesNeeded];
        int byteIndex = 0;
        for (int index = 0; index < textLength;) {
            int conversion = textToConvert.codePointAt(index);

            if (conversion >= 0 && conversion <= 127) {
                intBytes[byteIndex] = conversion;
                byteIndex++;
            }
            // Byte 2
            else if (conversion >= 128 && conversion <= 2047) {
                String binaryConversion = convertToBinary(conversion, 11);
                String byte1 = "110" + binaryConversion.substring(0, 5);
                String byte2 = "10" + binaryConversion.substring(5, 11);
                intBytes[byteIndex] = binaryToVariable(byte1);
                intBytes[byteIndex+1] = binaryToVariable(byte2);
                byteIndex += 2;
            }
            // Byte 3
            else if (conversion >= 2048 && conversion <= 65535) {
                String binaryConversion = convertToBinary(conversion, 16);
                String byte1 = "1110" + binaryConversion.substring(0, 4);
                String byte2 = "10"   + binaryConversion.substring(4, 10);
                String byte3 = "10"   + binaryConversion.substring(10, 16);
                intBytes[byteIndex] = binaryToVariable(byte1);
                intBytes[byteIndex+1] = binaryToVariable(byte2);
                intBytes[byteIndex+2] = binaryToVariable(byte3);
                byteIndex += 3;
            }
            // Byte 4
            else if (conversion >= 65536 && conversion <= 1114111) {
                String binaryConversion = convertToBinary(conversion, 21);
                String byte1 = "11110" + binaryConversion.substring(0, 3);
                String byte2 = "10"    + binaryConversion.substring(3, 9);
                String byte3 = "10"    + binaryConversion.substring(9, 15);
                String byte4 = "10"    + binaryConversion.substring(15, 21);
                intBytes[byteIndex] = binaryToVariable(byte1);
                intBytes[byteIndex+1] = binaryToVariable(byte2);
                intBytes[byteIndex+2] = binaryToVariable(byte3);
                intBytes[byteIndex+3] = binaryToVariable(byte4);
                byteIndex += 4;
            }
            
            index += Character.charCount(conversion);
        }
        // for (int i : intBytes) {
        //     System.out.println(i);
        // }

        return intBytes;
    }

}
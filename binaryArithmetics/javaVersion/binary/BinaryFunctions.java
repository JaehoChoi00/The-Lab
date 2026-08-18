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

        java.util.Arrays.fill(shiftLeftBinary, '0');

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

        java.util.Arrays.fill(shiftRightBinary, '0');

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
        java.util.Arrays.fill(flippedBinary, '0');

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

        long unsignedValue = variable;

        if (unsignedValue < 0) {
            unsignedValue = 4294967296L + unsignedValue; 
        }

        if (unsignedValue == 0) {
            return "00000000";
        }
        
        String binary = "";

        while (unsignedValue > 0) {
            binary = unsignedValue%2 + binary;
            unsignedValue = unsignedValue/2;
        }

        int binaryLength = binary.length();
        int reformatBinaryLength = ((binaryLength + 7) / 8) * 8;

        char[] inverseBinary = binary.toCharArray();
        char[] binaryBits = new char[reformatBinaryLength];

        java.util.Arrays.fill(binaryBits, '0');

        for (int bit = binaryLength - 1; bit >= 0; bit--) {
            binaryBits[bit + (reformatBinaryLength - binaryLength)] = inverseBinary[bit];
        }

        return new String(binaryBits);
    }

    public static String convertToHex(String binary) {
        int binaryLength = binary.length();
        
        int reformatBinaryLength = ((binaryLength + 3) / 4) * 4;

        char[] binaryArray = new char[reformatBinaryLength];
        java.util.Arrays.fill(binaryArray, '0');

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

}
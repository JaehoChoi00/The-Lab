package cypherEncryptions.sha_256.javaVersion;

import java.util.Arrays;

import static binaryArithmetics.javaVersion.arithmetics.Arithmetics.alu_add;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.binaryToVariable;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.convertToBinary;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.convertToByteUTF8;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.convertToHex;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.shiftRight;
import static binaryArithmetics.javaVersion.bitwise.BitwiseLogic.XOR;

public class SHA_256 {

    // constants
    private static final int[] K = {
            0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
            0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
            0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
            0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
            0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
            0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
            0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
            0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2 };

    // initial hash value
    private static final int[] H = {
            0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a, 0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19 };

    public static String SHA256(String textToHash) {
        int[] message = convertToByteUTF8(textToHash);
        long bitLength = (long) message.length * 8;

        int[] paddedBinary = new int[message.length + 1];

        int index = 0;
        for (int binary : message) {
            //System.out.print(binary + " "); 
            paddedBinary[index] = binary;
            index++;
        }

        paddedBinary[message.length] = 128;

        int messageLengthBytes = message.length;
        int totalBytesNeeded = ((messageLengthBytes + 1 + 8 + 63) / 64) * 64;

        int[] bytes64 = new int[totalBytesNeeded];

        for (index = 0; index < paddedBinary.length; index++) { bytes64[index] = paddedBinary[index]; }
        for (index = paddedBinary.length; index < totalBytesNeeded - 8; index++) { bytes64[index] = 0; }

        String bitToBinaryLength = convertToBinary((int) bitLength, 64);

        int endOfBytes64 = totalBytesNeeded - 8;
        for (index = 0; index < 8; index++) {
            String eightBit = bitToBinaryLength.substring(index * 8, (index + 1) * 8);
            bytes64[endOfBytes64 + index] = binaryToVariable(eightBit);
        }

        // 32 bits
        int totalWordsCount = bytes64.length / 4;

        String[] bits32 = new String[totalWordsCount];
        Arrays.fill(bits32, "");
        
        for (index = 0; index < totalWordsCount; index++) {
            for (int internal = 0; internal < 4; internal++) {
                bits32[index] = bits32[index] + convertToBinary(bytes64[(index*4) + internal]);
            }

            if (bits32[index].length() > 32) {
                int extraChars = bits32[index].length() - 32;
                bits32[index] = bits32[index].substring(extraChars);
            }
        }            

        String cupA = convertToBinary(H[0], 32);
        String cupB = convertToBinary(H[1], 32);
        String cupC = convertToBinary(H[2], 32);
        String cupD = convertToBinary(H[3], 32);
        String cupE = convertToBinary(H[4], 32);
        String cupF = convertToBinary(H[5], 32);
        String cupG = convertToBinary(H[6], 32);
        String cupH = convertToBinary(H[7], 32);

        for (int blockOffset = 0; blockOffset < totalWordsCount; blockOffset += 16) {

            // Back to 64 bytes
            String[] words64 = new String[64];
            java.util.Arrays.fill(words64, "00000000000000000000000000000000");
            
            for (index = 0; index < 16; index++) { words64[index] = bits32[blockOffset + index]; }

            for (index = 16; index < 64; index++) {
                String wordMinus2  = words64[index - 2];
                String wordMinus7  = words64[index - 7];
                String wordMinus15 = words64[index - 15];
                String wordMinus16 = words64[index - 16];
                String s0 = smallSigma(wordMinus15, 7, 18, 3);
                String s1 = smallSigma(wordMinus2, 17, 19, 10);
                
                words64[index] = binaryAddition(wordMinus16, s0, wordMinus7, s1);
            }

            for (int loop = 0; loop < 64; loop++) {
                String filter0 = bigSigma(cupA, 2, 13, 22);
                String filter1 = bigSigma(cupE, 6, 11, 25);

                int binaryLength = cupA.length();
                char[] chooseArray = new char[binaryLength];
                char[] majorityArray = new char[binaryLength];

                char[] cupEchars = cupE.toCharArray();
                char[] cupFchars = cupF.toCharArray();
                char[] cupGchars = cupG.toCharArray();

                char[] cupAchars = cupA.toCharArray();
                char[] cupBchars = cupB.toCharArray();
                char[] cupCchars = cupC.toCharArray();

                for (int bit = 0; bit < binaryLength; bit++) {
                    chooseArray[bit] =  cupEchars[bit] == '1' ? cupFchars[bit] : cupGchars[bit];
                    majorityArray[bit] = ((cupAchars[bit] - '0') + (cupBchars[bit] - '0') + (cupCchars[bit] - '0')) > 1 ? '1' : '0';
                }

                String chooseFilter = new String(chooseArray);
                String majorityFilter = new String(majorityArray);
                String temp1 = alu_add(alu_add(alu_add(alu_add(words64[loop], convertToBinary(K[loop], 32)), chooseFilter), filter1), cupH);
                String temp2 = alu_add(filter0, majorityFilter);

                String copyG = cupG;
                String copyF = cupF;
                String copyE = cupE;
                String copyC = cupC;
                String copyB = cupB;
                String copyA = cupA;

                cupH = copyG;
                cupG = copyF;
                cupF = copyE;
                cupE = alu_add(cupD, temp1);
                cupD = copyC;
                cupC = copyB;
                cupB = copyA;
                cupA = alu_add(temp1, temp2);
            }
            
            String oldH0 = convertToBinary(H[0], 32);
            String oldH1 = convertToBinary(H[1], 32);
            String oldH2 = convertToBinary(H[2], 32);
            String oldH3 = convertToBinary(H[3], 32);
            String oldH4 = convertToBinary(H[4], 32);
            String oldH5 = convertToBinary(H[5], 32);
            String oldH6 = convertToBinary(H[6], 32);
            String oldH7 = convertToBinary(H[7], 32);

            H[0] = binaryToVariable(alu_add(cupA, oldH0));
            H[1] = binaryToVariable(alu_add(cupB, oldH1));
            H[2] = binaryToVariable(alu_add(cupC, oldH2));
            H[3] = binaryToVariable(alu_add(cupD, oldH3));
            H[4] = binaryToVariable(alu_add(cupE, oldH4));
            H[5] = binaryToVariable(alu_add(cupF, oldH5));
            H[6] = binaryToVariable(alu_add(cupG, oldH6));
            H[7] = binaryToVariable(alu_add(cupH, oldH7));

            cupA = convertToBinary(H[0], 32);
            cupB = convertToBinary(H[1], 32);
            cupC = convertToBinary(H[2], 32);
            cupD = convertToBinary(H[3], 32);
            cupE = convertToBinary(H[4], 32);
            cupF = convertToBinary(H[5], 32);
            cupG = convertToBinary(H[6], 32);
            cupH = convertToBinary(H[7], 32);

    }

        // Stage 5: Final Output

        String finalA = convertToHex(convertToBinary(H[0], 32));
        String finalB = convertToHex(convertToBinary(H[1], 32));
        String finalC = convertToHex(convertToBinary(H[2], 32));
        String finalD = convertToHex(convertToBinary(H[3], 32));
        String finalE = convertToHex(convertToBinary(H[4], 32));
        String finalF = convertToHex(convertToBinary(H[5], 32));
        String finalG = convertToHex(convertToBinary(H[6], 32));
        String finalH = convertToHex(convertToBinary(H[7], 32));

        String finalOutput = finalA + finalB + finalC + finalD + finalE + finalF + finalG + finalH;

        return finalOutput;
    }
    
    public static String smallSigma(String binary, int clone1Setting, int clone2Setting, int clone3Setting) {
        int binaryLength = binary.length();
        char[] binaryChar = binary.toCharArray();
        char[] clone1;
        char[] clone2;
        char[] clone3;

        String charactersRight = binary;
        charactersRight = shiftRight(charactersRight, clone1Setting);
        clone1 = charactersRight.toCharArray();

        for (int i = 0; i < clone1Setting; i++) {
            clone1[i] = binaryChar[(binaryLength - clone1Setting) + i];
        }

        charactersRight = binary;
        charactersRight = shiftRight(charactersRight, clone2Setting);
        clone2 = charactersRight.toCharArray();

        for (int i = 0; i < clone2Setting; i++) {
            clone2[i] = binaryChar[(binaryLength - clone2Setting) + i];
        }

        charactersRight = binary;
        charactersRight = shiftRight(charactersRight, clone3Setting);
        clone3 = charactersRight.toCharArray();

        char[] MergedBinary = new char[binaryLength];

        for (int bit = 0; bit < binaryLength; bit++) {
            int bit1 = clone1[bit] - '0';
            int bit2 = clone2[bit] - '0';
            int bit3 = clone3[bit] - '0';
            
            int result = XOR(XOR((short) (bit1), (short) (bit2)), (short) (bit3));
            MergedBinary[bit] = (char) (result + '0');
        }

        return new String(MergedBinary);
    }

    public static String bigSigma(String binary, int clone1Setting, int clone2Setting, int clone3Setting) {
        int binaryLength = binary.length();
        char[] binaryChar = binary.toCharArray();
        char[] clone1;
        char[] clone2;
        char[] clone3;

        String charactersRight = binary;
        charactersRight = shiftRight(charactersRight, clone1Setting);
        clone1 = charactersRight.toCharArray();

        for (int i = 0; i < clone1Setting; i++) {
            clone1[i] = binaryChar[(binaryLength - clone1Setting) + i];
        }

        charactersRight = binary;
        charactersRight = shiftRight(charactersRight, clone2Setting);
        clone2 = charactersRight.toCharArray();

        for (int i = 0; i < clone2Setting; i++) {
            clone2[i] = binaryChar[(binaryLength - clone2Setting) + i];
        }

        charactersRight = binary;
        charactersRight = shiftRight(charactersRight, clone3Setting);
        clone3 = charactersRight.toCharArray();

        for (int i = 0; i < clone3Setting; i++) {
            clone3[i] = binaryChar[(binaryLength - clone3Setting) + i];
        }

        char[] MergedBinary = new char[binaryLength];

        for (int bit = 0; bit < binaryLength; bit++) {
            int bit1 = clone1[bit] - '0';
            int bit2 = clone2[bit] - '0';
            int bit3 = clone3[bit] - '0';
            
            int result = XOR(XOR((short) (bit1), (short) (bit2)), (short) (bit3));
            MergedBinary[bit] = (char) (result + '0');
        }
        
        return new String(MergedBinary);
    }

    public static String binaryAddition(String wordMinus16, String s0, String wordMinus7, String s1) {
        return alu_add(alu_add(alu_add(wordMinus16, s0), wordMinus7), s1);
    }

}

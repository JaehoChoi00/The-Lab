package cypherEncryptions.sha_256.javaVersion;

import java.util.Arrays;

import static binaryArithmetics.javaVersion.arithmetics.Arithmetics.alu_add;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.binaryToVariable;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.convertToBinary;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.convertToByteUTF8;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.convertToHex;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.shiftRight;
import static binaryArithmetics.javaVersion.bitwise.BitwiseLogic.XOR;
import static shared.javaUtil.Exposure.BOLD;
import static shared.javaUtil.Exposure.FG_COLOR;
import static shared.javaUtil.Exposure.FG_CYAN;
import static shared.javaUtil.Exposure.FG_GREEN;
import static shared.javaUtil.Exposure.LINEBREAK;
import static shared.javaUtil.Exposure.LINEFEED;
import static shared.javaUtil.Exposure.NEWLINE;
import static shared.javaUtil.Exposure.RESET;
import static shared.javaUtil.Exposure.UNDERLINE;
import static shared.javaUtil.Exposure.printf;
import static shared.javaUtil.enums.ExposureLevel.LEVEL1;
import static shared.javaUtil.enums.ExposureLevel.LEVEL2;
import static shared.javaUtil.enums.ExposureLevel.LEVEL3;
import static shared.javaUtil.enums.ExposureLevel.LEVEL4;

public class SHA_256_INTERNAL {

    // constants
    private final int[] K = {
            0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
            0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
            0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
            0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
            0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
            0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
            0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
            0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2 };

    // initial hash value
    private final int[] H = {
            0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a, 0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19 };

    // Initial single bit padding
    private final int singleBitPadding = 128;


    public String run(String textToHash) {
        printf(LEVEL1, "Input: " + BOLD + FG_GREEN + "%s\n" + RESET, textToHash);
        printf(LEVEL2, "Input length: " + BOLD + FG_GREEN + "%d\n" + RESET, textToHash.length());
        
        // STEP 1: PADDING
        printf(LEVEL2, BOLD + UNDERLINE + "%c[Step 1: Padding]" + BOLD + UNDERLINE + RESET, (char) LINEFEED);
        NEWLINE(LEVEL2);

        int[] message = convertToByteUTF8(textToHash);
        long bitLength = (long) message.length * 8;

        printf(LEVEL3, "bit length: " + BOLD + FG_GREEN + "%d\n" + RESET, bitLength);
        printf(LEVEL3, "Input converted to bytes: " + BOLD + FG_GREEN + "\n%s" + RESET, java.util.Arrays.toString(message));
        LINEBREAK(LEVEL3);

        // Step 1: Padding
        
        int[] paddedBinary = new int[message.length + 1];
        printf(LEVEL3, "Padded binary: Size: " + BOLD + FG_COLOR(150) + "%d" + RESET + BOLD + FG_GREEN + "\n%s" + RESET, message.length + 1, java.util.Arrays.toString(paddedBinary));
        NEWLINE(LEVEL3);

        int index = 0;
        for (int binary : message) {
            //System.out.print(binary + " "); 
            paddedBinary[index] = binary;
            index++;
        }
        // System.out.println("");
        printf(LEVEL3, "Convert input to decimals: " + BOLD + FG_GREEN + "\n%s" + RESET, java.util.Arrays.toString(paddedBinary));
        NEWLINE(LEVEL3);

        paddedBinary[message.length] = singleBitPadding;

        printf(LEVEL3, "Single byte padding added at the end: " + BOLD + FG_GREEN + "\n%s" + RESET, java.util.Arrays.toString(paddedBinary));
        NEWLINE(LEVEL3);

        int messageLengthBytes = message.length;
        int totalBytesNeeded = ((messageLengthBytes + 1 + 8 + 63) / 64) * 64;

        printf(LEVEL3, "Calculated total bytes needed: (messageLengthBytes + 1 + 8 + 63) / 64) * 64 = " + BOLD + FG_COLOR(150) + "%d" + RESET, totalBytesNeeded);
        NEWLINE(LEVEL3);

        int[] bytes64 = new int[totalBytesNeeded];
        printf(LEVEL3, "Bytes64 array made: Size: " + BOLD + FG_COLOR(150) + "%d" + RESET + BOLD + FG_GREEN + "\n%s" + RESET, message.length + 1, java.util.Arrays.toString(bytes64));
        NEWLINE(LEVEL3);

        for (index = 0; index < paddedBinary.length; index++) {
            bytes64[index] = paddedBinary[index];
        }

        printf(LEVEL3, "Filling up the space with input: " + BOLD + FG_GREEN + "\n%s" + RESET, java.util.Arrays.toString(bytes64));
        NEWLINE(LEVEL3);

        for (index = paddedBinary.length; index < totalBytesNeeded - 8; index++) {
            bytes64[index] = 0;
        }

        printf(LEVEL3, "Padding remaining space: " + BOLD + FG_GREEN + "\n%s" + RESET, java.util.Arrays.toString(bytes64));
        NEWLINE(LEVEL3);

        String bitToBinaryLength = convertToBinary((int) bitLength, 64);
        printf(LEVEL3, "Convert Input bit length to binary: " + BOLD + FG_GREEN + "%s\n" + RESET, bitToBinaryLength);
        
        int endOfBytes64 = totalBytesNeeded - 8;
        for (index = 0; index < 8; index++) {
            String eightBit = bitToBinaryLength.substring(index * 8, (index + 1) * 8);
            bytes64[endOfBytes64 + index] = binaryToVariable(eightBit);
            printf(LEVEL4, BOLD + FG_COLOR(150) + "Converting 8bit index" + BOLD + FG_CYAN + "[%d]" + RESET + ": " + BOLD + FG_GREEN + "%s" + RESET + " to variable: " + BOLD + FG_COLOR(150) + "%d\n" + RESET, index, eightBit, bytes64[endOfBytes64 + index]);
        }
        NEWLINE(LEVEL4);

        printf(LEVEL3, "Imprinting byte size of input: " + BOLD + FG_GREEN + "\n%s" + RESET, java.util.Arrays.toString(bytes64));
        NEWLINE(LEVEL3);

        printf(LEVEL2, BOLD + UNDERLINE +"%c[Step 2: 32 bits]" + BOLD + UNDERLINE + RESET, (char) LINEFEED);
        NEWLINE(LEVEL2);

        // 32 bits
        int totalWordsCount = bytes64.length / 4;

        printf(LEVEL3, "total word count: " + BOLD + FG_GREEN + "%d" + RESET, totalWordsCount);
        NEWLINE(LEVEL3);

        String[] bits32 = new String[totalWordsCount];
        Arrays.fill(bits32, "");
        
        printf(LEVEL3, "Empty bits32 array made: " + BOLD + FG_GREEN + "\n%s" + RESET, java.util.Arrays.toString(bits32));
        NEWLINE(LEVEL3);

        for (index = 0; index < totalWordsCount; index++) {
            for (int internal = 0; internal < 4; internal++) {
                bits32[index] = bits32[index] + convertToBinary(bytes64[(index*4) + internal]);
            }

            if (bits32[index].length() > 32) {
                int extraChars = bits32[index].length() - 32;
                bits32[index] = bits32[index].substring(extraChars);
            }
        }            
        
        printf(LEVEL3, "Converting bytes64 to binary bits32: " + BOLD + FG_GREEN + "\n%s" + RESET, java.util.Arrays.toString(bits32));
        NEWLINE(LEVEL3);

        String cupA = convertToBinary(H[0], 32);
        String cupB = convertToBinary(H[1], 32);
        String cupC = convertToBinary(H[2], 32);
        String cupD = convertToBinary(H[3], 32);
        String cupE = convertToBinary(H[4], 32);
        String cupF = convertToBinary(H[5], 32);
        String cupG = convertToBinary(H[6], 32);
        String cupH = convertToBinary(H[7], 32);

        printf(LEVEL3, "Converting Hash CupA hex: " + BOLD + FG_GREEN + "%x" + RESET + " to binary: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, H[0], cupA);
        printf(LEVEL3, "Converting Hash CupB hex: " + BOLD + FG_GREEN + "%x" + RESET + " to binary: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, H[1], cupB);
        printf(LEVEL3, "Converting Hash CupC hex: " + BOLD + FG_GREEN + "%x" + RESET + " to binary: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, H[2], cupC);
        printf(LEVEL3, "Converting Hash CupD hex: " + BOLD + FG_GREEN + "%x" + RESET + " to binary: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, H[3], cupD);
        printf(LEVEL3, "Converting Hash CupE hex: " + BOLD + FG_GREEN + "%x" + RESET + " to binary: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, H[4], cupE);
        printf(LEVEL3, "Converting Hash CupF hex: " + BOLD + FG_GREEN + "%x" + RESET + " to binary: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, H[5], cupF);
        printf(LEVEL3, "Converting Hash CupG hex: " + BOLD + FG_GREEN + "%x" + RESET + " to binary: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, H[6], cupG);
        printf(LEVEL3, "Converting Hash CupH hex: " + BOLD + FG_GREEN + "%x" + RESET + " to binary: " + BOLD + FG_COLOR(150) + "%s" + RESET, H[7], cupH);
        NEWLINE(LEVEL3);

        for (int blockOffset = 0; blockOffset < totalWordsCount; blockOffset += 16) {
            printf(LEVEL2, BOLD + "Block " + RESET + BOLD + FG_COLOR(150) + "[%d]:" + RESET, blockOffset);
            NEWLINE(LEVEL2);

            // Back to 64 bytes
            String[] words64 = new String[64];
            java.util.Arrays.fill(words64, "00000000000000000000000000000000");
            
            for (index = 0; index < 16; index++) {
                words64[index] = bits32[blockOffset + index];
            }
            printf(LEVEL3, "Filling first 16 with bits32: " + BOLD + FG_GREEN + "\n%s" + RESET, java.util.Arrays.toString(words64));

            for (index = 16; index < 64; index++) {
                // System.out.println("Index" + index);
                
                String wordMinus2  = words64[index - 2];
                String wordMinus7  = words64[index - 7];
                String wordMinus15 = words64[index - 15];
                String wordMinus16 = words64[index - 16];
                String s0 = smallSigma(wordMinus15, 7, 18, 3);
                String s1 = smallSigma(wordMinus2, 17, 19, 10);
                
                words64[index] = binaryAddition(wordMinus16, s0, wordMinus7, s1);

                printf(LEVEL4, BOLD + FG_COLOR(150) + "words64 index" + BOLD + FG_CYAN + "[%d]" + RESET + ": " + BOLD + FG_GREEN + "%s\n" + RESET, index, words64[index]);
                printf(LEVEL4, BOLD + FG_COLOR(150) + "words64 look back " + BOLD + FG_CYAN + "[%d]" + RESET + ": " + BOLD + FG_GREEN + "%s\n" + RESET, index - 2, wordMinus2);
                printf(LEVEL4, BOLD + FG_COLOR(150) + "words64 look back " + BOLD + FG_CYAN + "[%d]" + RESET + ": " + BOLD + FG_GREEN + "%s\n" + RESET, index - 7, wordMinus7);
                printf(LEVEL4, BOLD + FG_COLOR(150) + "words64 look back " + BOLD + FG_CYAN + "[%d]" + RESET + ": " + BOLD + FG_GREEN + "%s\n" + RESET, index - 15, wordMinus15);
                printf(LEVEL4, BOLD + FG_COLOR(150) + "words64 look back " + BOLD + FG_CYAN + "[%d]" + RESET + ": " + BOLD + FG_GREEN + "%s\n" + RESET, index - 16, wordMinus16);
                printf(LEVEL4, BOLD + FG_COLOR(150) + "small sigma0: " + BOLD + FG_GREEN + "%s\n" + RESET, s0);
                printf(LEVEL4, BOLD + FG_COLOR(150) + "small sigma1: " + BOLD + FG_GREEN + "%s\n" + RESET, s1);
                printf(LEVEL4, BOLD + FG_COLOR(150) + "binaryAddition Result: " + BOLD + FG_COLOR(13) + "%s" + RESET, words64[index]);
                LINEBREAK(LEVEL4);
            }
            NEWLINE(LEVEL3);

            printf(LEVEL3, "Using small sigma functions to stretch after 16: " + BOLD + FG_GREEN + "\n%s" + RESET, java.util.Arrays.toString(words64));
            NEWLINE(LEVEL3);

            // Stage 4: The 64-Round Compression Loop
            printf(LEVEL2, BOLD + UNDERLINE +"%c[Step 3: The 64-Round Compression Loop]" + BOLD + UNDERLINE + RESET, (char) LINEFEED);
            NEWLINE(LEVEL2);

            for (int loop = 0; loop < 64; loop++) {

                String filter0 = bigSigma(cupA, 2, 13, 22);
                String filter1 = bigSigma(cupE, 6, 11, 25);

                // Logic Filters

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

                // Temp 1 and Temp 2

                String temp1 = alu_add(alu_add(alu_add(alu_add(words64[loop], convertToBinary(K[loop], 32)), chooseFilter), filter1), cupH);
                String temp2 = alu_add(filter0, majorityFilter);

                String copyH = cupH;
                String copyG = cupG;
                String copyF = cupF;
                String copyE = cupE;
                String copyD = cupD;
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

                printf(LEVEL4, BOLD + FG_COLOR(150) + "\nwords64 index" + BOLD + FG_CYAN + "[%d]" + RESET + ": " + BOLD + FG_GREEN + "%s\n" + RESET, loop, words64[loop]);
                printf(LEVEL4, BOLD + FG_COLOR(150) + "bigSigma filter 0: " +  BOLD + FG_GREEN + "%s\n" + RESET, filter0);
                printf(LEVEL4, BOLD + FG_COLOR(150) + "bigSigma filter 1: " +  BOLD + FG_GREEN + "%s\n" + RESET, filter1);
                printf(LEVEL4, BOLD + FG_COLOR(150) + "choose filter " + BOLD + FG_CYAN + "[%s]\n" + RESET, chooseFilter);
                printf(LEVEL4, BOLD + FG_COLOR(150) + "choose filter " + BOLD + FG_CYAN + "[%s]\n" + RESET, majorityFilter);
                printf(LEVEL4, BOLD + FG_COLOR(150) + "Temp1 -> words64[loop] + convertToBinary(K[loop], 32) + chooseFilter + bigSigma filter1 + cupH = " + BOLD + FG_CYAN + "\n[%s]\n" + RESET, temp1);
                printf(LEVEL4, BOLD + FG_COLOR(150) + "Temp2 -> filter0 + majorityFilter = " + BOLD + FG_CYAN + "[%s]" + RESET, temp2);
                NEWLINE(LEVEL4);
                printf(LEVEL4, "old CupA: " + BOLD + FG_GREEN + "%s" + RESET + " new CupA: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, copyA, cupA);
                printf(LEVEL4, "old CupB: " + BOLD + FG_GREEN + "%s" + RESET + " new CupB: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, copyB, cupB);
                printf(LEVEL4, "old CupC: " + BOLD + FG_GREEN + "%s" + RESET + " new CupC: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, copyC, cupC);
                printf(LEVEL4, "old CupD: " + BOLD + FG_GREEN + "%s" + RESET + " new CupD: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, copyD, cupD);
                printf(LEVEL4, "old CupE: " + BOLD + FG_GREEN + "%s" + RESET + " new CupE: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, copyE, cupE);
                printf(LEVEL4, "old CupF: " + BOLD + FG_GREEN + "%s" + RESET + " new CupF: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, copyF, cupF);
                printf(LEVEL4, "old CupG: " + BOLD + FG_GREEN + "%s" + RESET + " new CupG: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, copyG, cupG);
                printf(LEVEL4, "old CupH: " + BOLD + FG_GREEN + "%s" + RESET + " new CupH: " + BOLD + FG_COLOR(150) + "%s" + RESET, copyH, cupH);
                LINEBREAK(LEVEL4);

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

            NEWLINE(LEVEL2);
            printf(LEVEL2, BOLD + UNDERLINE + "[Step 4: Block State Accumulation Update]" + RESET);
            NEWLINE(LEVEL2);
            printf(LEVEL3, "old H[0]: " + BOLD + FG_GREEN + "%s" + RESET + "  +  CupA: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, oldH0, cupA);
            printf(LEVEL3, "old H[1]: " + BOLD + FG_GREEN + "%s" + RESET + "  +  CupB: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, oldH1, cupB);
            printf(LEVEL3, "old H[2]: " + BOLD + FG_GREEN + "%s" + RESET + "  +  CupC: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, oldH2, cupC);
            printf(LEVEL3, "old H[3]: " + BOLD + FG_GREEN + "%s" + RESET + "  +  CupD: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, oldH3, cupD);
            printf(LEVEL3, "old H[4]: " + BOLD + FG_GREEN + "%s" + RESET + "  +  CupE: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, oldH4, cupE);
            printf(LEVEL3, "old H[5]: " + BOLD + FG_GREEN + "%s" + RESET + "  +  CupF: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, oldH5, cupF);
            printf(LEVEL3, "old H[6]: " + BOLD + FG_GREEN + "%s" + RESET + "  +  CupG: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, oldH6, cupG);
            printf(LEVEL3, "old H[7]: " + BOLD + FG_GREEN + "%s" + RESET + "  +  CupH: " + BOLD + FG_COLOR(150) + "%s\n" + RESET, oldH7, cupH);
            LINEBREAK(LEVEL3);

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

        printf(LEVEL2, BOLD + UNDERLINE + "[Stage 5: Final Hexadecimal Conversion]" + RESET);
        NEWLINE(LEVEL2);
        printf(LEVEL3, "H[0] Binary -> Hex: " + BOLD + FG_GREEN + "%s" + RESET + "  ->  " + BOLD + FG_COLOR(150) + "%s\n" + RESET, convertToBinary(H[0], 32), finalA);
        printf(LEVEL3, "H[1] Binary -> Hex: " + BOLD + FG_GREEN + "%s" + RESET + "  ->  " + BOLD + FG_COLOR(150) + "%s\n" + RESET, convertToBinary(H[1], 32), finalB);
        printf(LEVEL3, "H[2] Binary -> Hex: " + BOLD + FG_GREEN + "%s" + RESET + "  ->  " + BOLD + FG_COLOR(150) + "%s\n" + RESET, convertToBinary(H[2], 32), finalC);
        printf(LEVEL3, "H[3] Binary -> Hex: " + BOLD + FG_GREEN + "%s" + RESET + "  ->  " + BOLD + FG_COLOR(150) + "%s\n" + RESET, convertToBinary(H[3], 32), finalD);
        printf(LEVEL3, "H[4] Binary -> Hex: " + BOLD + FG_GREEN + "%s" + RESET + "  ->  " + BOLD + FG_COLOR(150) + "%s\n" + RESET, convertToBinary(H[4], 32), finalE);
        printf(LEVEL3, "H[5] Binary -> Hex: " + BOLD + FG_GREEN + "%s" + RESET + "  ->  " + BOLD + FG_COLOR(150) + "%s\n" + RESET, convertToBinary(H[5], 32), finalF);
        printf(LEVEL3, "H[6] Binary -> Hex: " + BOLD + FG_GREEN + "%s" + RESET + "  ->  " + BOLD + FG_COLOR(150) + "%s\n" + RESET, convertToBinary(H[6], 32), finalG);
        printf(LEVEL3, "H[7] Binary -> Hex: " + BOLD + FG_GREEN + "%s" + RESET + "  ->  " + BOLD + FG_COLOR(150) + "%s\n" + RESET, convertToBinary(H[7], 32), finalH);
        NEWLINE(LEVEL3);

        String finalOutput = finalA + finalB + finalC + finalD + finalE + finalF + finalG + finalH;
        printf(LEVEL1, BOLD + FG_GREEN + "FINAL COMBINED OUTPUT" + RESET + ": " + BOLD + FG_COLOR(150) + "%s" + RESET, finalOutput);
        LINEBREAK(LEVEL1);

        return finalOutput;
    }
    
    public String smallSigma(String binary, int clone1Setting, int clone2Setting, int clone3Setting) {
        // System.out.println("Before anyting: " + binary);
        int binaryLength = binary.length();
        char[] binaryChar = binary.toCharArray();
        char[] clone1;
        char[] clone2;
        char[] clone3;

        // Clone 1
        String charactersRight = binary;
        charactersRight = shiftRight(charactersRight, clone1Setting);
        clone1 = charactersRight.toCharArray();

        for (int i = 0; i < clone1Setting; i++) {
            clone1[i] = binaryChar[(binaryLength - clone1Setting) + i];
        }

        // Clone 2
        charactersRight = binary;
        charactersRight = shiftRight(charactersRight, clone2Setting);
        clone2 = charactersRight.toCharArray();

        for (int i = 0; i < clone2Setting; i++) {
            clone2[i] = binaryChar[(binaryLength - clone2Setting) + i];
        }

        // Clone 3
        charactersRight = binary;
        charactersRight = shiftRight(charactersRight, clone3Setting);
        clone3 = charactersRight.toCharArray();

        // System.out.println("Clone 1:");
        // System.out.println(clone1);
        // System.out.println("Clone 2:");
        // System.out.println(clone2);
        // System.out.println("Clone 3:");
        // System.out.println(clone3);

        // Step 2 Merge XOR
        char[] MergedBinary = new char[binaryLength];

        for (int bit = 0; bit < binaryLength; bit++) {
            int bit1 = clone1[bit] - '0';
            int bit2 = clone2[bit] - '0';
            int bit3 = clone3[bit] - '0';
            
            int result = XOR(XOR((short) (bit1), (short) (bit2)), (short) (bit3));
            MergedBinary[bit] = (char) (result + '0');
        }

        // System.out.println("MergedBinary Sigma0:");
        // System.out.println(MergedBinary);

        return new String(MergedBinary);
    }

    public String bigSigma(String binary, int clone1Setting, int clone2Setting, int clone3Setting) {
        //System.out.println("Before anyting: " + binary);
        int binaryLength = binary.length();
        char[] binaryChar = binary.toCharArray();
        char[] clone1;
        char[] clone2;
        char[] clone3;

        // Clone 1
        String charactersRight = binary;
        charactersRight = shiftRight(charactersRight, clone1Setting);
        clone1 = charactersRight.toCharArray();

        for (int i = 0; i < clone1Setting; i++) {
            clone1[i] = binaryChar[(binaryLength - clone1Setting) + i];
        }

        // Clone 2
        charactersRight = binary;
        charactersRight = shiftRight(charactersRight, clone2Setting);
        clone2 = charactersRight.toCharArray();

        for (int i = 0; i < clone2Setting; i++) {
            clone2[i] = binaryChar[(binaryLength - clone2Setting) + i];
        }

        // Clone 3
        charactersRight = binary;
        charactersRight = shiftRight(charactersRight, clone3Setting);
        clone3 = charactersRight.toCharArray();

        for (int i = 0; i < clone3Setting; i++) {
            clone3[i] = binaryChar[(binaryLength - clone3Setting) + i];
        }

        // System.out.println("Clone 1:");
        // System.out.println(clone1);
        // System.out.println("Clone 2:");
        // System.out.println(clone2);
        // System.out.println("Clone 3:");
        // System.out.println(clone3);

        // Step 2 Merge XOR
        char[] MergedBinary = new char[binaryLength];

        for (int bit = 0; bit < binaryLength; bit++) {
            int bit1 = clone1[bit] - '0';
            int bit2 = clone2[bit] - '0';
            int bit3 = clone3[bit] - '0';
            
            int result = XOR(XOR((short) (bit1), (short) (bit2)), (short) (bit3));
            MergedBinary[bit] = (char) (result + '0');
        }

        // System.out.println("MergedBinary Sigma0:");
        // System.out.println(MergedBinary);

        return new String(MergedBinary);
    }

    public String binaryAddition(String wordMinus16, String s0, String wordMinus7, String s1) {
        return alu_add(alu_add(alu_add(wordMinus16, s0), wordMinus7), s1);
    }

}

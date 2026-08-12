package cypherEncryptions.sha_256;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SHA_256 {

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
    private int singleBitPadding = 128;

    // Final Result
    private int[] finalOutput = new int[64];

    public void run() {
        String textToEncrypt = "hello";

        int bitLength = textToEncrypt.length() * 8;

        int[] binaryConverted = convertToByte(textToEncrypt);

        // Step 1: Padding
        int[] paddedBinary = new int[binaryConverted.length + 1];

        System.out.print("Convert text to binary: ");
        int index = 0;
        for (int binary : binaryConverted) {
            System.out.print(binary + " "); 
            paddedBinary[index] = binary;
            index++;
        }
        System.out.println("");

        paddedBinary[binaryConverted.length] = singleBitPadding;

        System.out.print("Added padding: ");
        for (int padded : paddedBinary) { System.out.print(padded + " "); }
        System.out.println("");

        // 64 bytes

        int[] bytes64 = new int[64];

        for (index = 0; index < paddedBinary.length; index++) {
            bytes64[index] = paddedBinary[index];
        }
        for (index = paddedBinary.length; index < 63; index++) {
            bytes64[index] = 0;
        }
        bytes64[63] = bitLength;

        System.out.print("bytes64: ");
        for (int theByte : bytes64) { System.out.print(theByte + " "); }
        System.out.println("");

        // 32 bits

        String[] bits32 = new String[16];
        Arrays.fill(bits32, "");

        for (index = 0; index < 16; index++) {
            for (int internal = 0; internal < 4; internal++) {
                bits32[index] = bits32[index] + convertToBinary(bytes64[(index*4) + internal]);
            }
        }                                                                 
        System.out.print("bits32: ");
        for (String theBit : bits32) { System.out.print(theBit + " "); }
        System.out.println("");

        // Back to 64 bytesf
    }

    public int[] convertToByte(String textToConvert) {
        byte[] stringBytes = textToConvert.getBytes(StandardCharsets.UTF_8);

        int[] intBytes = new int[stringBytes.length];
        
        for (int i = 0; i < stringBytes.length; i++) {
            // Mask with 0xFF to remove the negative sign and get a clean 0-255 integer
            intBytes[i] = stringBytes[i] & 0xFF;
        }
        
        return intBytes;
    }

    public String convertToBinary(int byteToConvert) {
        String binary = Integer.toBinaryString(byteToConvert & 0xFF);
        while (binary.length() < 8) {
            binary = "0" + binary;
        }
        return binary;
    }
}

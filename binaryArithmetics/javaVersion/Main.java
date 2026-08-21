package binaryArithmetics.javaVersion;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import binaryArithmetics.javaVersion.arithmetics.ArithmeticsTest;
import binaryArithmetics.javaVersion.binary.BinaryTest;
import binaryArithmetics.javaVersion.bitwise.BitwiseTest;
import shared.javaUtil.Exposure;
import shared.javaUtil.enums.ExposureLevel;

public class Main {
    public static void main(String[] args) {
        
        Exposure.setLevel(ExposureLevel.LEVEL5);
        Exposure.setBridge(null);

        // BitwiseTest bitwiseTest = new BitwiseTest();
        // BinaryTest binaryTest = new BinaryTest();
        // ArithmeticsTest arithmeticsTest = new ArithmeticsTest();

        //bitwiseTest.run();
        //binaryTest.run();
        // arithmeticsTest.run();

        String logFileName = "testOutput.txt";

        try (PrintWriter fileWriter = new PrintWriter(new FileWriter(logFileName))) {
            
            Exposure.setBridge((String rawText) -> {
                String cleanText = rawText.replaceAll("\u001B\\[[;\\d]*m", "");
                
                fileWriter.print(cleanText);
                fileWriter.flush(); 
            });

            BitwiseTest bitwiseTest = new BitwiseTest();
            BinaryTest binaryTest = new BinaryTest();
            ArithmeticsTest arithmeticsTest = new ArithmeticsTest();

            // bitwiseTest.run();
            // binaryTest.run();
            arithmeticsTest.run();

            Exposure.setBridge(null);

        } catch (IOException e) {
            System.err.println("Fatal Error: Could not write simulation trace to file.");
            e.printStackTrace();
        }
    }
}

package cypherEncryptions.sha_256.javaVersion;

import static cypherEncryptions.sha_256.javaVersion.SHA_256.SHA256;
import shared.javaUtil.Exposure;
import static shared.javaUtil.Exposure.BOLD;
import static shared.javaUtil.Exposure.FG_COLOR;
import static shared.javaUtil.Exposure.FG_GREEN;
import static shared.javaUtil.Exposure.RESET;
import static shared.javaUtil.Exposure.printf;
import static shared.javaUtil.enums.ExposureLevel.LEVEL1;

public class Main {
    public static void main(String[] args) {
        Exposure.setLevel(LEVEL1);
        Exposure.setBridge(null);

        SHA_256_INTERNAL sha_256 = new SHA_256_INTERNAL();
        sha_256.run("Hello");
        
        // String logFileName = "SHA256.txt";

        // try (PrintWriter fileWriter = new PrintWriter(new FileWriter(logFileName))) {
            
        //     Exposure.setBridge(rawText -> {
        //         String cleanText = rawText.replaceAll("\u001B\\[[;\\d]*m", "");
                
        //         fileWriter.print(cleanText);
        //         fileWriter.flush(); 
        //     });

        //     sha_256.run("Hello");

        //     Exposure.setBridge(null);

        // } catch (IOException e) {
        //     System.err.println("Fatal Error: Could not write simulation trace to file.");
        //     e.printStackTrace();
        // }

        printf(LEVEL1, BOLD + FG_GREEN + "OUTPUT" + RESET + ": " + BOLD + FG_COLOR(150) + "%s" + RESET, SHA256("Hello"));
    }
}

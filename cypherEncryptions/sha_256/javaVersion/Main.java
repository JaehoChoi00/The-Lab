package cypherEncryptions.sha_256.javaVersion;

import static cypherEncryptions.sha_256.javaVersion.SHA_256.SHA256;
import static shared.javaUtil.VariableConstants.BOLD;
import static shared.javaUtil.VariableConstants.FG_COLOR;
import static shared.javaUtil.VariableConstants.FG_GREEN;
import static shared.javaUtil.VariableConstants.RESET;

public class Main {
    public static void main(String[] args) {
        SHA_256_INTERNAL sha_256 = new SHA_256_INTERNAL();
        sha_256.run("SHA-256 engine built from scratch!");

        System.out.printf(BOLD + FG_GREEN + "OUTPUT" + RESET + ": " + BOLD + FG_COLOR(150) + "%s" + RESET, SHA256("SHA-256 engine built from scratch!"));
    }
}

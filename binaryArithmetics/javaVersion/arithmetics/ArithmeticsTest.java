package binaryArithmetics.javaVersion.arithmetics;
import static binaryArithmetics.javaVersion.arithmetics.Arithmetics.alu_add;
import static binaryArithmetics.javaVersion.arithmetics.Arithmetics.alu_sub;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.binaryToVariable;
import static shared.javaUtil.VariableConstants.BOLD;
import static shared.javaUtil.VariableConstants.FG_COLOR;
import static shared.javaUtil.VariableConstants.FG_GREEN;
import static shared.javaUtil.VariableConstants.LINEFEED;
import static shared.javaUtil.VariableConstants.NEWLINE;
import static shared.javaUtil.VariableConstants.RESET;
import static shared.javaUtil.VariableConstants.UNDERLINE;

public class ArithmeticsTest {
    
    public void run() {
        // Binary Arithmetics
        System.out.printf(BOLD + UNDERLINE + "%c[Binary Arithmetics]" + RESET, (char) LINEFEED);
        NEWLINE();

        String testA = "11111111";
        String testB = "11111111";

        System.out.printf("Test numbers: A " + BOLD + FG_GREEN + "%d" + RESET + " B " + BOLD + FG_COLOR(50) + "%d" + RESET, binaryToVariable(testA), binaryToVariable(testB));
        NEWLINE();

        System.out.printf("Add " + BOLD + FG_GREEN + "%s" + RESET + " + " + BOLD + FG_COLOR(50) + "%s" + RESET + " = %s", testA, testB, alu_add(testA, testB));
        NEWLINE();

        System.out.printf("%d", binaryToVariable(alu_add(testA, testB)));
        NEWLINE();

        System.out.printf("Sub " + BOLD + FG_GREEN + "%s" + RESET + " - " + BOLD + FG_COLOR(50) + "%s" + RESET + " = %s", testA, testB, alu_sub(testA, testB));
        NEWLINE();
        System.out.printf("%d", binaryToVariable(alu_sub(testA, testB)));
        NEWLINE();
    }
}

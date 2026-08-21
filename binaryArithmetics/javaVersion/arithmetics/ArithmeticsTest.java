package binaryArithmetics.javaVersion.arithmetics;

import static binaryArithmetics.javaVersion.arithmetics.Arithmetics.alu_add;
import static binaryArithmetics.javaVersion.arithmetics.Arithmetics.alu_sub;
import static binaryArithmetics.javaVersion.binary.BinaryFunctions.binaryToVariable;
import shared.javaUtil.Exposure;
import shared.javaUtil.enums.ExposureCategory;
import shared.javaUtil.enums.ExposureLevel;

public class ArithmeticsTest {
    
    public void run() {
        Exposure.printf(
            ExposureCategory.TEST, ExposureLevel.LEVEL1, 
            Exposure.BOLD + Exposure.UNDERLINE + "%c[Binary Arithmetics]" + Exposure.RESET, 
            (char) Exposure.LINEFEED
        );
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);

        String testA = "00000001";
        String testB = "00000001";

        // Display decimal representation of the test numbers
        Exposure.printf(
            ExposureCategory.TEST, ExposureLevel.LEVEL1,
            Exposure.BOLD + "Test Numbers ->" + Exposure.RESET 
            + " A: " + Exposure.FG_CYAN + "%d" + Exposure.RESET 
            + " B: " + Exposure.FG_CYAN + "%d" + Exposure.RESET, 
            binaryToVariable(testA), binaryToVariable(testB)
        );
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);

        // ALU Addition Execution
        String addResultBinary = alu_add(testA, testB);
        int addResultDecimal = binaryToVariable(addResultBinary);
        
        Exposure.printf(
            ExposureCategory.TEST, ExposureLevel.LEVEL1,
            Exposure.BOLD + "ALU Add ->" + Exposure.RESET 
            + " " + Exposure.FG_CYAN + "%s" + Exposure.RESET 
            + " + " 
            + Exposure.FG_CYAN + "%s" + Exposure.RESET 
            + " = " 
            + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET 
            + " (Decimal: " + Exposure.BOLD + Exposure.FG_GREEN + "%d" + Exposure.RESET + ")", 
            testA, testB, addResultBinary, addResultDecimal
        );
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);

        // ALU Subtraction Execution
        String subResultBinary = alu_sub(testA, testB);
        int subResultDecimal = binaryToVariable(subResultBinary);

        Exposure.printf(
            ExposureCategory.TEST, ExposureLevel.LEVEL1,
            Exposure.BOLD + "ALU Sub ->" + Exposure.RESET 
            + " " + Exposure.FG_CYAN + "%s" + Exposure.RESET 
            + " - " 
            + Exposure.FG_CYAN + "%s" + Exposure.RESET 
            + " = " 
            + Exposure.BOLD + Exposure.FG_GREEN + "%s" + Exposure.RESET 
            + " (Decimal: " + Exposure.BOLD + Exposure.FG_GREEN + "%d" + Exposure.RESET + ")", 
            testA, testB, subResultBinary, subResultDecimal
        );
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);

        // Closing boundary spacer
        Exposure.LINEBREAK(ExposureCategory.TEST, ExposureLevel.LEVEL1);
    }
}

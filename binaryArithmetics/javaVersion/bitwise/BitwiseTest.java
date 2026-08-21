package binaryArithmetics.javaVersion.bitwise;

import java.util.Map;

import shared.javaUtil.Exposure;
import shared.javaUtil.enums.ExposureCategory;
import shared.javaUtil.enums.ExposureLevel;

@FunctionalInterface
interface TwoBitOperation {
    int apply(short a, short b);
}

@FunctionalInterface
interface SingleBitOperation {
    int apply(short a);
}

public class BitwiseTest {

    private static final Map<String, TwoBitOperation> TWOBITOPERATIONS = Map.of(
        "AND",  BitwiseLogic::AND,
        "OR",   BitwiseLogic::OR,
        "XOR",  BitwiseLogic::XOR,
        "NAND", BitwiseLogic::NAND
    );

    private static final Map<String, SingleBitOperation> SINGLEBITOPERATIONS = Map.of(
        "NOT",  BitwiseLogic::NOT
    );

    public void run() {
        // Category Header Block
        Exposure.printf(
            ExposureCategory.TEST, ExposureLevel.LEVEL1, 
            Exposure.BOLD + Exposure.UNDERLINE + "%c[Bit Operations]" + Exposure.RESET, 
            (char) Exposure.LINEFEED
        );
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);

        testLogicDoubleInput("NAND");
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);
        
        testLogicDoubleInput("AND");
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);
        
        testLogicDoubleInput("OR");
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);
        
        testLogicDoubleInput("XOR");
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);
        
        testLogicSingleInput("NOT");
        Exposure.NEWLINE(ExposureCategory.TEST, ExposureLevel.LEVEL1);
        
        Exposure.LINEBREAK(ExposureCategory.TEST, ExposureLevel.LEVEL1);
    }

    public void testLogicDoubleInput(String logicType) {
        short[] bitsA = {0, 1, 0, 1};
        short[] bitsB = {0, 0, 1, 1};

        TwoBitOperation operator = TWOBITOPERATIONS.get(logicType.toUpperCase());

        for (int index = 0; index < 4; index++) {
            int result = operator.apply(bitsA[index], bitsB[index]);
            
            // Format: [Label] -> Cyan Input 1 [Op] Cyan Input 2 = Bold Green Result
            Exposure.printf(
                ExposureCategory.TEST, ExposureLevel.LEVEL1, 
                Exposure.BOLD + "%s Test -> " + Exposure.RESET
                + Exposure.FG_CYAN + "%d" + Exposure.RESET 
                + " %s " 
                + Exposure.FG_CYAN + "%d" + Exposure.RESET 
                + " = " 
                + Exposure.BOLD + Exposure.FG_GREEN + "%d" + Exposure.RESET + "%n", 
                logicType, bitsA[index], logicType, bitsB[index], result
            );

        }
    }

    public void testLogicSingleInput(String logicType) {
        short[] bits = {0, 1};

        if (!logicType.equals("NOT")) {
            TwoBitOperation operator = TWOBITOPERATIONS.get(logicType.toUpperCase());
            for (int index = 0; index < 2; index++) {
                int result = operator.apply(bits[index], bits[index]);
                
                Exposure.printf(
                    ExposureCategory.TEST, ExposureLevel.LEVEL1, 
                    Exposure.BOLD + "%s Test -> " + Exposure.RESET
                    + Exposure.FG_CYAN + "%d" + Exposure.RESET 
                    + " %s = " 
                    + Exposure.BOLD + Exposure.FG_GREEN + "%d" + Exposure.RESET + "%n", 
                    logicType, bits[index], logicType, result
                );
            }
        } else {
            SingleBitOperation NOT = SINGLEBITOPERATIONS.get(logicType.toUpperCase());
            for (int index = 0; index < 2; index++) {
                int result = NOT.apply(bits[index]);
                
                Exposure.printf(
                    ExposureCategory.TEST, ExposureLevel.LEVEL1, 
                    Exposure.BOLD + "%s Test -> " + Exposure.RESET
                    + Exposure.FG_CYAN + "%d" + Exposure.RESET 
                    + " %s = " 
                    + Exposure.BOLD + Exposure.FG_GREEN + "%d" + Exposure.RESET + "%n", 
                    logicType, bits[index], logicType, result
                );
            }
        }
    }
}

package binaryArithmetics.javaVersion.bitwise;

import java.util.Map;
import static shared.javaUtil.VariableConstants.*;

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

        // Bit Operations
        System.out.printf(BOLD + UNDERLINE + "%c[Bit Operations]" +  RESET, (char) LINEFEED);
        NEWLINE();

        testLogicDoubleInput("NAND");
        NEWLINE();
        testLogicDoubleInput("AND");
        NEWLINE();
        testLogicDoubleInput("OR");
        NEWLINE();
        testLogicDoubleInput("XOR");
        NEWLINE();
        testLogicSingleInput("NOT");
        NEWLINE();

    }

    public void testLogicDoubleInput(String logicType) {
        short[] bitsA = {0, 1, 0, 1};
        short[] bitsB = {0, 0, 1, 1};

        TwoBitOperation operator = TWOBITOPERATIONS.get(logicType.toUpperCase());

        for (int index = 0; index < 4; index++) {
            System.out.printf( BOLD + FG_GREEN + "%h" + RESET + " %s " + BOLD + FG_COLOR(50) + "%h" + RESET + " = " + BOLD + "%h %c" +  RESET, bitsA[index], logicType, bitsB[index], operator.apply(bitsA[index], bitsB[index]), LINEFEED);
        }
    }

    public void testLogicSingleInput(String logicType) {
        short[] bits = {0, 1};

        if (!logicType.equals("NOT")) {
            TwoBitOperation operator = TWOBITOPERATIONS.get(logicType.toUpperCase());
            for (int index = 0; index < 2; index++) {
                System.out.printf( BOLD + FG_GREEN + "%h" + RESET + " %s = " + BOLD + "%h%c" +  RESET, bits[index], logicType, operator.apply(bits[index], bits[index]), (char) LINEFEED);
            }
        }
        else {
            SingleBitOperation NOT = SINGLEBITOPERATIONS.get(logicType.toUpperCase());
            for (int index = 0; index < 2; index++) {
                System.out.printf( BOLD + FG_GREEN + "%h" + RESET + " %s = " + BOLD + "%h%c" +  RESET, bits[index], logicType, NOT.apply(bits[index]), (char) LINEFEED);
            }
        }
    }
}

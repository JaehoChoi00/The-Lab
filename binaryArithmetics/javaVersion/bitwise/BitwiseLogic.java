package binaryArithmetics.javaVersion.bitwise;

import shared.javaUtil.Exposure;
import shared.javaUtil.enums.ExposureCategory;
import shared.javaUtil.enums.ExposureLevel;
public class BitwiseLogic { 
    

    public static short NAND(short bitA, short bitB) {
        short result = (short) ((bitA == 1 && bitB == 1) ? 0 : 1);

        Exposure.printf(
            ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5,
            Exposure.FG_CYAN + "%d" + Exposure.RESET +
            " NAND " +
            Exposure.FG_CYAN + "%d" + Exposure.RESET +
            " = " +
            Exposure.BOLD + Exposure.FG_GREEN + "%d" + Exposure.RESET + "%n",
            bitA, bitB, result
        );

        return result;
    }

    public static short NOT(short bit) {

        short result = NAND(bit, bit);

        Exposure.printf(
            ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5,
            " NOT " +
            Exposure.FG_CYAN + "%d" + Exposure.RESET +
            " = " +
            Exposure.BOLD + Exposure.FG_GREEN + "%d" + Exposure.RESET + "%n",
            bit, result
        );

        return result;
    }

    public static short AND(short bitA, short bitB) {
        short result = NOT(NAND(bitA, bitB));

        Exposure.printf(
            ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5,
            Exposure.FG_CYAN + "%d" + Exposure.RESET +
            " AND " +
            Exposure.FG_CYAN + "%d" + Exposure.RESET +
            " = " +
            Exposure.BOLD + Exposure.FG_GREEN + "%d" + Exposure.RESET + "%n",
            bitA, bitB, result
        );

        return result;
    }

    public static short OR(short bitA, short bitB){ 
        short result = NAND(NOT(bitA), NOT(bitB));

        Exposure.printf(
            ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5,
            Exposure.FG_CYAN + "%d" + Exposure.RESET +
            " OR " +
            Exposure.FG_CYAN + "%d" + Exposure.RESET +
            " = " +
            Exposure.BOLD + Exposure.FG_GREEN + "%d" + Exposure.RESET + "%n",
            bitA, bitB, result
        );

        return result;
    }

    public static short XOR(short bitA, short bitB) {

        short result = NAND(NAND(NOT(bitA), bitB), NAND(bitA, NOT(bitB)));

        Exposure.printf(
            ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5,
            Exposure.FG_CYAN + "%d" + Exposure.RESET +
            " NAND " +
            Exposure.FG_CYAN + "%d" + Exposure.RESET +
            " = " +
            Exposure.BOLD + Exposure.FG_GREEN + "%d" + Exposure.RESET + "%n",
            bitA, bitB, result
        );

        return result;
    }
}

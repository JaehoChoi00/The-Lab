#include "bitwiseLogic.h"
#include "Exposure.h"
#include "ExposureLevel.h"

short NAND(short bitA, short bitB) {
    short result = !(bitA && bitB);

    ExposurePrintf(
        LEVEL5,  
        "NAND: " 
        BOLD FG_GREEN "%d" RESET 
        " NAND " 
        BOLD FG_YELLOW "%d" RESET 
        " = " 
        BOLD "%d\n" RESET,  
        bitA, bitB, result
    );

    return result;
}

short NOT(short bit) {
    short result = NAND(bit, bit);

    ExposurePrintf(
        LEVEL5, 
        "NOT: " 
        BOLD FG_GREEN "%d" RESET 
        " = " 
        BOLD "%d\n" RESET, 
        bit, result
    );

    return result;
}

short AND(short bitA, short bitB) {
    short result = NOT(NAND(bitA, bitB));

    ExposurePrintf(
        LEVEL5, 
        "AND: " 
        BOLD FG_GREEN "%d" RESET 
        " AND " 
        BOLD FG_YELLOW "%d" RESET 
        " = " 
        BOLD "%d\n" RESET, 
        bitA, bitB, result
    );

    return result;
}

short OR(short bitA, short bitB) { 
    short result = NAND(NOT(bitA), NOT(bitB));

    ExposurePrintf(
        LEVEL5, 
        "OR : " 
        BOLD FG_GREEN "%d" RESET 
        " OR " 
        BOLD FG_YELLOW "%d" RESET 
        " = " 
        BOLD "%d\n" RESET, 
        bitA, bitB, result
    );

    return result;
}

short XOR(short bitA, short bitB) {
    short result = NAND(NAND(NOT(bitA), bitB), NAND(bitA, NOT(bitB)));

    ExposurePrintf(
        LEVEL5, 
        "XOR: " 
        BOLD FG_GREEN "%d" RESET 
        " XOR " 
        BOLD FG_YELLOW "%d" RESET 
        " = " 
        BOLD "%d\n" RESET, 
        bitA, bitB, result
    );

    return result;
}
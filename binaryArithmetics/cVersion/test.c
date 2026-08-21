#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "Exposure.h"
#include "ExposureLevel.h"
#include "binary/binary.h"
#include "bitwise/bitwiseLogic.h"
#include "arithmetics/arithmetics.h"

void testLogicDoubleInput(char logicType[], short (*logic)(short, short));
void testLogicSingleInput(char logicType[], short (*logic)(short));

void bitwiseTest();
void binaryTest();
void arithmeticTest();

// argv[1] = binary
// argv[2], argv[3] = bitA, bitB
int main(int argc, char* argv[]) {

    ExposureLevel level = LEVEL1;

    // 1. Set Exposure Level from argv[1]
    if (argc >= 2) {
        int parsedLevel = atoi(argv[1]);
        if (parsedLevel >= LEVEL1 && parsedLevel <= LEVEL5) {
            level = (ExposureLevel)parsedLevel;
        }
    }
    setExposureLevel(level);

    bitwiseTest();

    binaryTest();

    arithmeticTest();
    
    return 0;
}

void bitwiseTest() {
    ExposurePrintf(
        LEVEL1, 
        BOLD UNDERLINE "%c[Bitwise Operations]" RESET, 
        LINEFEED
    );
    ExposureNewline(LEVEL1);

    testLogicDoubleInput("NAND", NAND);
    ExposureNewline(LEVEL1);

    testLogicSingleInput("NOT", NOT);
    ExposureNewline(LEVEL1);

    testLogicDoubleInput("AND", AND);
    ExposureNewline(LEVEL1);

    testLogicDoubleInput("OR", OR);
    ExposureNewline(LEVEL1);

    testLogicDoubleInput("XOR", XOR);
    ExposureNewline(LEVEL1);

    ExposureLinebreak(LEVEL1);
}

void binaryTest() {
    ExposurePrintf(
        LEVEL1, 
        BOLD UNDERLINE "%c[Binary Operations]" RESET, 
        LINEFEED
    );
    ExposureNewline(LEVEL1);

    char testBinary[] = "111100111";

    int varValue = binaryToVariable(testBinary);
    ExposurePrintf(
        LEVEL1, 
        BOLD "Binary to Variable ->" RESET 
        " Input: " FG_CYAN "%s" RESET 
        " Result: " BOLD FG_GREEN "%d" RESET, 
        testBinary, varValue
    );
    ExposureNewline(LEVEL1);

    int binaryLength = strlen(testBinary) + 1;
    char shiftleftBinary[binaryLength];
    strcpy(shiftleftBinary, testBinary);
    shiftLeft(shiftleftBinary);

    ExposurePrintf(
        LEVEL1, 
        BOLD "Shift Left ->" RESET 
        " Input: " FG_CYAN "%s" RESET 
        " Result: " BOLD FG_GREEN "%s" RESET, 
        testBinary, shiftleftBinary
    );
    ExposureNewline(LEVEL1);

    char flipBinary[binaryLength];
    strcpy(flipBinary, testBinary);
    flip(flipBinary);

    ExposurePrintf(
        LEVEL1, 
        BOLD "Flip Bits ->" RESET 
        " Input: " FG_CYAN "%s" RESET 
        " Result: " BOLD FG_GREEN "%s" RESET, 
        testBinary, flipBinary
    );
    ExposureNewline(LEVEL1);

    ExposureLinebreak(LEVEL1);
}

void arithmeticTest() {
    // Main Category Header Block
    ExposurePrintf(
        LEVEL1, 
        "%s%s%c[Binary Arithmetics]%s", 
        BOLD, UNDERLINE, (char)LINEFEED, RESET
    );
    ExposureNewline(LEVEL1);

    // Initial testing input frameworks (8-bit width allocation matching Java test profiles)
    char testA[9] = "11111111";
    char testB[9] = "11111111";

    // Trace out decimal translation summaries of incoming test parameters
    ExposurePrintf(
        LEVEL1,
        "%sTest Numbers ->%s A: %s%d%s B: %s%d%s\n", 
        BOLD, RESET, 
        FG_CYAN, binaryToVariable(testA), RESET, 
        FG_CYAN, binaryToVariable(testB), RESET
    );
    ExposureNewline(LEVEL1);

    // --- 1. ALU Addition Evaluation ---
    char* addResultBinary = alu_add(testA, testB);
    int addResultDecimal = binaryToVariable(addResultBinary);
    
    ExposurePrintf(
        LEVEL1,
        "%sALU Add ->%s %s%s%s + %s%s%s = %s%s%s%s (Decimal: %s%s%d%s)\n", 
        BOLD, RESET, 
        FG_CYAN, testA, RESET, 
        FG_CYAN, testB, RESET, 
        BOLD, FG_GREEN, addResultBinary, RESET, 
        BOLD, FG_GREEN, addResultDecimal, RESET
    );
    ExposureNewline(LEVEL1);
    free(addResultBinary); // Clean up the heap memory dynamically allocated by alu_add

    // --- 2. ALU Subtraction Evaluation ---
    char* subResultBinary = alu_sub(testA, testB);
    int subResultDecimal = binaryToVariable(subResultBinary);

    ExposurePrintf(
        LEVEL1,
        "%sALU Sub ->%s %s%s%s - %s%s%s = %s%s%s%s (Decimal: %s%s%d%s)\n", 
        BOLD, RESET, 
        FG_CYAN, testA, RESET, 
        FG_CYAN, testB, RESET, 
        BOLD, FG_GREEN, subResultBinary, RESET, 
        BOLD, FG_GREEN, subResultDecimal, RESET
    );
    ExposureNewline(LEVEL1);
    free(subResultBinary); // Clean up the heap memory dynamically allocated by alu_sub

    // Closing category framework boundary splitter
    ExposureLinebreak(LEVEL1);
}

void testLogicDoubleInput(char logicType[], short (*logic)(short, short)) {
    short bitA[] = {0, 1, 0, 1};
    short bitB[] = {0, 0, 1, 1};

    for (int index = 0; index < 4; index++) {
        ExposurePrintf(
            LEVEL1,
            BOLD "%s" RESET
            " Test -> "
            BOLD FG_GREEN "%d" RESET
            " %s "
            BOLD FG_YELLOW "%d" RESET
            " Result = "
            BOLD "%d\n" RESET,
            logicType, bitA[index], logicType, bitB[index], logic(bitA[index], bitB[index])
        );
        ExposureNewline(LEVEL1);
    }

}

void testLogicSingleInput(char logicType[], short (*logic)(short)) {
    short bits[] = {0, 1};

    if (strcmp(logicType, "NOT") == 0) {
        for (int index = 0; index < 2; index++) {
            ExposurePrintf(
                LEVEL1,
                BOLD "NOT" RESET
                " Test -> "
                BOLD FG_GREEN "%d" RESET
                " NOT Result = "
                BOLD "%d\n" RESET,
                bits[index], logic(bits[index])
            );
            ExposureNewline(LEVEL1);
        }
    }
}
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "../shared/cHeaders/VariableConstants.h"
#include "cVersion/binary/binary.h"
#include "cVersion/bitwise/bitwiseLogic.h"
#include "cVersion/arithmetics/arithmetics.h"

void testLogicDoubleInput(char logicType[], short (*logic)(short, short));
void testLogicSingleInput(char logicType[], short (*logic)(short));

// argv[1] = binary
// argv[2], argv[3] = bitA, bitB
int main(int argc, char* argv[]) {

    // Binary Operations
    printf(BOLD UNDERLINE "%c[Binary Operations]" RESET, LINEFEED);
    NEWLINE;

    char *inputString = "00000000";

    if (argc >= 2) {
        inputString = argv[1];
    }
    
    int binaryLength = strlen(inputString) + 1;

    char originalBinary[binaryLength];
    strcpy(originalBinary, inputString);
    
    printf("Original binary: " BOLD FG_GREEN "%s" RESET " Value: " BOLD FG_COLOR(50) "%d" RESET, originalBinary, binaryToVariable(originalBinary));
    NEWLINE;

    char shiftleftBinary[binaryLength];
    strcpy(shiftleftBinary, originalBinary);
    shiftLeft(shiftleftBinary);

    printf("Binary after shift Left: " BOLD FG_GREEN "%s" RESET " Value: " BOLD FG_COLOR(50) "%d" RESET, shiftleftBinary, binaryToVariable(shiftleftBinary));
    NEWLINE;

    char flipBinary[binaryLength];
    strcpy(flipBinary, shiftleftBinary);
    flip(flipBinary);

    printf("Binary after flipping: " BOLD FG_GREEN "%s" RESET " Value: " BOLD FG_COLOR(50) "%d" RESET, flipBinary, binaryToVariable(flipBinary));
    NEWLINE;
    LINEBREAK;

    // Bitwise Logic
    printf(BOLD UNDERLINE "%c[Bitwise Operations]" RESET, LINEFEED);
    NEWLINE;

    testLogicDoubleInput("AND", AND);
    NEWLINE;
    testLogicDoubleInput("OR", OR);
    NEWLINE;
    testLogicDoubleInput("XOR", XOR);
    NEWLINE;
    testLogicDoubleInput("NAND", NAND);
    NEWLINE;
    testLogicSingleInput("NOT", NOT);
    NEWLINE;

    // Binary Arithmetics
    printf(BOLD UNDERLINE "%c[Binary Arithmetics]" RESET, LINEFEED);
    NEWLINE;

    char testA[] = "00001111";
    char testB[] = "00000011";

    printf("Add = %s", alu_add(testA, testB));
    NEWLINE;
    printf("%d", binaryToVariable(alu_add(testA, testB)));
    NEWLINE;

    printf("Sub = %s", alu_sub(testA, testB));
    NEWLINE;
    printf("%d", binaryToVariable(alu_sub(testA, testB)));
    NEWLINE;

    return 0;
}

void testLogicDoubleInput(char logicType[], short (*logic)(short, short)) {
    short bitA[] = {0, 1, 0, 1};
    short bitB[] = {0, 0, 1, 1};

    for (int index = 0; index < 4; index++) {
        printf(BOLD FG_GREEN "%hd" RESET " %s " BOLD FG_COLOR(50) "%hd" RESET " = " BOLD "%hd%c" RESET, bitA[index], logicType, bitB[index], logic(bitA[index], bitB[index]), LINEFEED);
    }
}

void testLogicSingleInput(char logicType[], short (*logic)(short)) {
    short bits[] = {0, 1};

    if (strcmp(logicType, "NOT") == 0) {
        for (int index = 0; index < 2; index++) {
            printf("NOT " BOLD FG_GREEN "%hd" RESET " = " BOLD "%hd%c" RESET, bits[index], logic(bits[index]), LINEFEED);
        }
    }

}
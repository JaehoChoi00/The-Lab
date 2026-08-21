#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "arithmetics.h"
#include "bitwise/bitwiseLogic.h"
#include "binary/binary.h"
#include "Exposure.h"
#include "ExposureLevel.h"

char* alu_add(char binaryA[], char binaryB[]) {
    int bitsCount = strlen(binaryA); 
    int binarySize = bitsCount + 1;

    char *tempA = malloc(binarySize * sizeof(char));
    char tempB[binarySize];
    
    strcpy(tempA, binaryA);
    strcpy(tempB, binaryB);
    
    ExposurePrintf(
        LEVEL2, 
        "ALU ADDITION INITIALIZED\n"
        "Input_1 = %s%s%s\n"
        "Input_2 = %s%s%s\n", 
        FG_CYAN, binaryA, RESET, 
        FG_CYAN, binaryB, RESET
    );
    ExposureNewline(LEVEL2);

    if (binaryToVariable(tempB) == 0) {
        ExposurePrintf(LEVEL2, "Output  = %s%s%s%s\n", BOLD, FG_GREEN, tempA, RESET);
        return tempA;
    }

    int cycle = 1;
    while (binaryToVariable(tempB) != 0) {
        ExposurePrintf(LEVEL3, "--- Addition Accumulation Cycle %d ---\n", cycle);

        char carryAnd[binarySize];
        char temporarySum[binarySize];

        carryAnd[bitsCount] = '\0';
        temporarySum[bitsCount] = '\0';

        for (int index = bitsCount - 1; index >= 0; index--) {
            short bitA = tempA[index] - '0';
            short bitB = tempB[index] - '0';
            
            short stepCarry = AND(bitA, bitB);
            short stepSum = XOR(bitA, bitB);

            carryAnd[index] = stepCarry + '0'; 
            temporarySum[index] = stepSum + '0';

            ExposurePrintf(
                LEVEL4, 
                "Bit %d:\n  A: %d\n  B: %d\n  Step Carry: %d\n  Step Sum: %d\n\n", 
                (bitsCount - 1 - index), bitA, bitB, stepCarry, stepSum
            );
        }

        strcpy(tempA, temporarySum);
        shiftLeft(carryAnd);
        strcpy(tempB, carryAnd);

        ExposurePrintf(
            LEVEL3, 
            "  Accumulated Sum  : %s%s%s\n"
            "  Shifted Carry Out: %s%s%s\n\n", 
            FG_YELLOW, tempA, RESET, 
            FG_YELLOW, tempB, RESET
        );
        cycle++;
    }

    ExposurePrintf(LEVEL2, "Output  = %s%s%s%s\n", BOLD, FG_GREEN, tempA, RESET);
    return tempA;
}

char* alu_sub(char binaryA[], char binaryB[]) {
    ExposurePrintf(
        LEVEL2, 
        "ALU SUBTRACTION INITIALIZED\n"
        "Minuend    (A): %s%s%s\n"
        "Subtrahend (B): %s%s%s\n", 
        FG_CYAN, binaryA, RESET, 
        FG_CYAN, binaryB, RESET
    );
    ExposureNewline(LEVEL2);

    int bitsCount = strlen(binaryA); 
    int binarySize = bitsCount + 1;

    char tempB[binarySize];
    strcpy(tempB, binaryB);

    char onePadded[binarySize];
    onePadded[bitsCount] = '\0';

    for (int index = 0; index < bitsCount; index++) {
        onePadded[index] = '0';
    }
    onePadded[bitsCount - 1] = '1';

    char twosCompliment[binarySize];

    ExposurePrintf(LEVEL3, " -> Transforming Subtrahend using Two's Complement...\n");
    char *tempAddResult = alu_add(flip(tempB), onePadded);
    strcpy(twosCompliment, tempAddResult);
    free(tempAddResult);

    ExposurePrintf(LEVEL3, " -> Performing binary addition: A + (Two's Complement of B)\n");
    char *result = alu_add(binaryA, twosCompliment);

    return result;
}

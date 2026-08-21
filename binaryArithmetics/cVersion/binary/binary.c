#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#include "binary.h"
#include "../../cVersion/bitwise/bitwiseLogic.h"
#include "Exposure.h"
#include "ExposureLevel.h"

char* shiftLeft(char binary[]) {
    int binaryLength = strlen(binary);
    
    ExposurePrintf(LEVEL2, "Operation: Shift Left (1 position)\nInput binary: %s%s%s\n", FG_CYAN, binary, RESET);

    if (binaryLength <= 1) {
        binary[0] = '0';
        binary[1] = '\0';

        ExposurePrintf(LEVEL2, "Output binary: %s%s%s%s\n", BOLD, FG_GREEN, binary, RESET);
        return binary;
    }

    for (int bit = 0; bit < binaryLength - 1; bit++) {
        binary[bit] = binary[bit + 1];
    }
    binary[binaryLength - 1] = '0';

    ExposurePrintf(LEVEL4, "   [Cycle 1] result: %s%s%s\n", FG_YELLOW, binary, RESET);
    ExposurePrintf(LEVEL2, "Output binary: %s%s%s%s\n", BOLD, FG_GREEN, binary, RESET);
    return binary;
}

char* flip(char binary[]) {
    ExposurePrintf(LEVEL2, "Operation: Flip Bits (NOT inversion)\nInput binary: %s%s%s\n", FG_CYAN, binary, RESET);

    int binaryLength = strlen(binary);
    for (int bit = 0; bit < binaryLength; bit++) {
        short convertedBit = binary[bit] - '0';
        short flippedBit = NOT(convertedBit);
        binary[bit] = flippedBit + '0';

        ExposurePrintf(
            LEVEL4, 
            "   Flipping index %d: %s%d%s -> %s%d%s\n", 
            bit, FG_CYAN, convertedBit, RESET, FG_YELLOW, flippedBit, RESET
        );
    }

    ExposurePrintf(LEVEL2, "Output binary: %s%s%s%s\n", BOLD, FG_GREEN, binary, RESET);
    return binary;
}

int binaryToVariable(char binary[]) {
    ExposurePrintf(LEVEL2, "Operation: Binary To Variable Conversion\nInput binary: %s%s%s\n", FG_CYAN, binary, RESET);

    int binaryLength = strlen(binary);
    int* conversionGrid = (int*)malloc(binaryLength * sizeof(int));
    if (conversionGrid == NULL) {
        return 0;
    }
    int variable = 0;

    for (int index = 0; index < binaryLength; index++) {
        conversionGrid[binaryLength - 1 - index] = (1 << index);
    }

    ExposurePrintf(LEVEL4, " -> Conversion grid initialized for %s%d%s bits\n", FG_YELLOW, binaryLength, RESET);
    
    for (int bit = binaryLength - 1; bit >= 0; bit--) {
        if (binary[bit] == '1') {
            variable = variable + conversionGrid[bit];
            
            ExposurePrintf(
                LEVEL4, 
                "   Accumulating bit position %d value (+%s%d%s) -> Total: %s%d%s\n", 
                bit, FG_YELLOW, conversionGrid[bit], RESET, BOLD, variable, RESET
            );
        }
    }

    ExposurePrintf(LEVEL2, "Output: %s%s%d%s\n", BOLD, FG_GREEN, variable, RESET);
    free(conversionGrid);
    return variable;
}

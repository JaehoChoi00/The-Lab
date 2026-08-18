#include <string.h>

#include "binary.h"
#include "../../cVersion/bitwise/bitwiseLogic.h"

char* shiftLeft(char binary[]) {
    int binaryLength = strlen(binary);
    if (binaryLength <= 1) {
        binary[0] = '0';
        binary[1] = '\0';
        return binary;
    }

    for (int bit = 0; bit < binaryLength - 1; bit++) {
        binary[bit] = binary[bit + 1];
    }
    binary[binaryLength - 1] = '0';
    return binary;
}

char* flip(char binary[]) {
    int binaryLength = strlen(binary);
    for (int bit = 0; bit < binaryLength; bit++) {
        short convertedBit = binary[bit] - '0';
        short flippedBit = NOT(convertedBit);
        binary[bit] = flippedBit + '0';
    }
    return binary;
}

int binaryToVariable(char binary[]) {
    int binaryLength = strlen(binary);
    int conversionGrid[binaryLength];
    int variable = 0;

    for (int index = 0; index < binaryLength; index++) {
        conversionGrid[binaryLength - 1 - index] = (1 << index);
    }

    for (int bit = binaryLength - 1; bit >= 0; bit--) {
        if (binary[bit] == '1') {
            variable = variable + conversionGrid[bit];
        }
    }

    return variable;
}
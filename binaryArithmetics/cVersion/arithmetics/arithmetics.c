#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "arithmetics.h"
#include "cVersion/bitwise/bitwiseLogic.h"
#include "cVersion/binary/binary.h"å

char* alu_add(char binaryA[], char binaryB[]) {

    int bitsCount = strlen(binaryA); 
    int binarySize = bitsCount + 1;

    char *tempA = malloc(binarySize * sizeof(char));
    char tempB[binarySize];
    
    strcpy(tempA, binaryA);
    strcpy(tempB, binaryB);
    
    if (binaryToVariable(tempB) == 0){
        return tempA;
    }

    while (binaryToVariable(tempB) != 0) {
        char carryAnd[binarySize];
        char temporarySum[binarySize];

        carryAnd[bitsCount] = '\0';
        temporarySum[bitsCount] = '\0';

        for (int index = bitsCount - 1; index >= 0; index--) {
            short bitA = tempA[index] - '0';
            short bitB = tempB[index] - '0';
            carryAnd[index] = AND(bitA, bitB) + '0'; 
            temporarySum[index] = XOR(bitA, bitB) + '0';
        }

        strcpy(tempA, temporarySum);
        shiftLeft(carryAnd);
        strcpy(tempB, carryAnd);
    }

    return tempA;
}

char* alu_sub(char binaryA[], char binaryB[]) {
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

    char *tempAddResult = alu_add(flip(tempB), onePadded);
    strcpy(twosCompliment, tempAddResult);
    free(tempAddResult);

    return alu_add(binaryA, twosCompliment);
}


#include "bitwiseLogic.h"

short NAND(short bitA, short bitB) {
    return !(bitA && bitB);
}

short NOT(short bit) {
    return (NAND(bit, bit));
}

short AND(short bitA, short bitB) {
    return NOT(NAND(bitA, bitB));
}

short OR(short bitA, short bitB){ 
    return NAND(NOT(bitA), NOT(bitB));
}

short XOR(short bitA, short bitB) {
    return NAND( NAND(NOT(bitA), bitB), NAND(bitA, NOT(bitB)));
}


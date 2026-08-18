#include <stdio.h>

#include "../../../cHeaders/VariableConstants.h"

void systemPrint(char systemPrint[]);



int main() {

    return 0;
}

void systemPrint(char stringToPrint[]) {
    printf("%s%c", stringToPrint, LINEFEED);
}
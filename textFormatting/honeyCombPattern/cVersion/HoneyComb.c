#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>

#include "../../../cHeaders/VariableConstants.h"

// gcc HoneyComb.c -o HoneyComb && ./HoneyComb

int main(int argc, char *argv[]) {
    char firstPattern[] = "/ \\_";
    char secondPattern[] = "\\_/ ";

    int toggle = 0;
    int width = 10;
    int height = 20;

    if (argc >= 3) {
        width = atoi(argv[1]);
        height = atoi(argv[2]);
    }

    for (int i = 0; i < height * 2; i++) {
        printf("%c", LINEFEED);
        for (int j = 0; j < width; j++) {
            switch (toggle) {
                case 0 : 
                    printf("%s", firstPattern);
                    break;
                case 1 : 
                    printf("%s", secondPattern);
                    break;
            }
        }
        toggle = 1 - toggle;
    }
}
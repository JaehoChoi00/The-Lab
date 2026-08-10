#include <stdio.h>
#include "../../../cHeaders/VariableConstants.h"

// gcc HoneyComb.c -o HoneyComb && ./HoneyComb

int main() {
    char firstPattern[] = "/ \\_";
    char secondPattern[] = "\\_/ ";

    int toggle = 0;

    int width = 10;
    int height = 10;

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
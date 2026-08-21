#include "Exposure.h"
#include <stdio.h>
#include <stdarg.h>

// Encapsulated Module State using your camelCase convention
static ExposureLevel currentExposureLevel = LEVEL1;
static ExposureBridge externalExposureBridge = NULL;

void setExposureLevel(ExposureLevel newLevel) { currentExposureLevel = newLevel; }

ExposureLevel getExposureLevel(void) { return currentExposureLevel; }

void setExposureBridge(ExposureBridge outsideSystem) {
    externalExposureBridge = outsideSystem;
}

int showExposure(ExposureLevel requiredLevel) {
    return (int)currentExposureLevel >= (int)requiredLevel;
}

void ExposurePrintf(ExposureLevel requiredLevel, const char* format, ...) {
    if (!showExposure(requiredLevel)) { return; }

    va_list args;
    va_start(args, format);

    if (externalExposureBridge != NULL) {
        char buffer[2048];
        vsnprintf(buffer, sizeof(buffer), format, args);
        externalExposureBridge(buffer);
    } else {
        vprintf(format, args);
    }

    va_end(args);
}

void ExposureNewline(ExposureLevel requiredLevel) {
    if (showExposure(requiredLevel)) {
        if (externalExposureBridge != NULL) {
            externalExposureBridge("\n\n");
        } else {
            // Safe native call unaffected by standard library conflicts
            printf("\n\n");
        }
    }
}

void ExposureLinebreak(ExposureLevel requiredLevel) {
    if (showExposure(requiredLevel)) {
        if (externalExposureBridge != NULL) {
            externalExposureBridge("\n--------------------------------\n");
        } else {
            printf("\n--------------------------------\n");
        }
    }
}

#ifndef EXPOSURE_H
#define EXPOSURE_H

#include "ExposureLevel.h"
#include "VariableConstants.h"

typedef void (*ExposureBridge)(const char* text);

// Configuration Methods
void setExposureLevel(ExposureLevel newLevel);
ExposureLevel getExposureLevel(void);
void setExposureBridge(ExposureBridge outsideSystem);
int showExposure(ExposureLevel requiredLevel);

// Formatting Actions (No underscores used)
void ExposurePrintf(ExposureLevel requiredLevel, const char* format, ...);
void ExposureNewline(ExposureLevel requiredLevel);
void ExposureLinebreak(ExposureLevel requiredLevel);

#endif // EXPOSURE_H

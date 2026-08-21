# Exposure

[:arrow_left: Return to Hub](/shared/SharedUtility.md)  
[:arrow_right: Understand `Exposure Level & Exposure Category`](/shared/javaUtil/enums/ExposureLevel.md)

# The :coffee: EXPRESSO Framework

> Building the intuition from the ground up through 2D matrix filtering.

> * [`Exposure.java`](/shared/javaUtil/Exposure.java) 

## Sections

> * [`Overview`](#overview)
> * [`API Fields & Styles`](#api-fields--styles)
> * [`Configuration Methods`](#configuration-methods)
> * [`Dual-Mode Evaluation Logic`](#dual-mode-evaluation-logic)
> * [`Bridges & Pipelines`](#bridges--pipelines)
> * [`Formatting Actions`](#formatting-actions)
> * [`Core API Integration`](#core-api-integration)

---

### [`Overview`](#sections)

```txt
Exposure (2D Matrix Architecture)
│
├── Category Domain Set (Horizontal Filter)
│   │
│   └── Set<ExposureCategory> (VANILLA, SYSTEMLOG, DEBUG, COMPONENTIAL, LOWERLEVEL, TEST)
│
├── Depth Level Set (Vertical Filter)
│   │
│   └── Set<ExposureLevel> (LEVEL1, LEVEL2, LEVEL3, LEVEL4, LEVEL5)
│
├── Dual-Mode Filtering Engine
│   │
│   ├── Single Level  -> Cascading Floor Threshold (ActiveLevel >= RequiredLevel)
│   │
│   └── Multi Levels  -> Explicit Hand-Picked Isolation (EnabledLevels.contains(RequiredLevel))
│
├── Interceptor Hook (Bridge)
│   │
│   └── Consumer<String> (Null = Native System Terminal Output)
│   
└── Style Mappings
    │
    └── VariableConstants (ANSI Colors, Fonts, Formatting)
```

---

### [`API Fields & Styles`](#sections)

| Text Formatting Type | API Variable Alias Reference | Escape Sequence Target |
| :--- | :--- | :--- |
| **Reset Code** | `Exposure.RESET` | `VariableConstants.RESET` |
| **Font Adjustments** | `Exposure.BOLD`, `Exposure.DIM`, `Exposure.UNDERLINE` | `VariableConstants.*` |
| **Line Controls** | `Exposure.LINEFEED` | `VariableConstants.LINEFEED` |
| **Text Foreground Palette** | `Exposure.FG_BLACK` to `Exposure.FG_WHITE` | `VariableConstants.FG_*` |
| **Background Palette** | `Exposure.BG_BLACK` to `Exposure.BG_WHITE` | `VariableConstants.BG_*` |

***Dynamic Colors***
* `Exposure.FG_COLOR(int id)`: Fetches ANSI indexed 256-color foreground string.
* `Exposure.BG_COLOR(int id)`: Fetches ANSI indexed 256-color background string.

---

### [`Configuration Methods`](#sections)

```java
// Level Filtering Controls
public static void setLevel(ExposureLevel newLevel);             // Single level (Cascading Mode)
public static void setLevels(ExposureLevel... handPickedLevels); // Multi level (Explicit Mode)
public static Set<ExposureLevel> getLevels();

// Category Domain Controls
public static void setCategory(ExposureCategory category);
public static void setCategories(ExposureCategory... handPickedCategories);
public static void enableCategory(ExposureCategory category);
public static void disableCategory(ExposureCategory category);
public static void clearAllCategories();                         // Resets to VANILLA safety fallback
public static Set<ExposureCategory> getCategories();
```

---

### [`Dual-Mode Evaluation Logic`](#sections)

```java
public static boolean show(ExposureLevel requiredLevel);

public static boolean show(ExposureCategory category, ExposureLevel requiredLevel) {
    if (!enabledCategories.contains(category)) return false;
    
    if (enabledLevels.size() == 1) {
        ExposureLevel activeLevel = enabledLevels.iterator().next();
        return activeLevel.getLevel() >= requiredLevel.getLevel();
    }
    
    return enabledLevels.contains(requiredLevel);
}
```

---

### [`Bridges & Pipelines`](#sections)

```java
public static void setBridge(Consumer<String> outsideSystem);
```

***How Routing Operates Inside***

```txt
               [Exposure.printf() Invoked]
                           │
             Is Category Enabled in Set?
              /                         \
         (No) │                         │ (Yes)
              ▼                         ▼
        [Abort Stream]      Check Enabled Level Count
                             /                     \
            (Size == 1)     │                       │ (Size > 1)
                            ▼                       ▼
                 Active >= Required?       Set.contains(Required)?
                  /               \         /               \
             (No) │         (Yes) │    (No) │         (Yes) │
                  ▼               └────┬────┘               ▼
            [Abort Stream]             ▼              [Abort Stream]
                              [Format Text String]
                                       │
                           Is externalBridge active?
                            /                     \
                       (Yes) │                     │ (No)
                             ▼                     ▼
               [Forward raw text string]    [System.out.print]
                             │                     │
                             ▼                     ▼
                     (UI / Log Files)      (Native Console)
```

---

### [`Formatting Actions`](#sections)

```java
public static void printf(ExposureCategory category, ExposureLevel requiredLevel, String format, Object... args);
public static void NEWLINE(ExposureCategory category, ExposureLevel requiredLevel);
public static void LINEBREAK(ExposureCategory category, ExposureLevel requiredLevel);

public static void printf(ExposureLevel requiredLevel, String format, Object... args);
public static void NEWLINE(ExposureLevel requiredLevel);
public static void LINEBREAK(ExposureLevel requiredLevel);
```

---

### [`Core API Integration`](#sections)

***Example Code***

```java
Exposure.setCategories(ExposureCategory.COMPONENTIAL, ExposureCategory.LOWERLEVEL);
Exposure.setLevels(ExposureLevel.LEVEL1, ExposureLevel.LEVEL5);

Exposure.setBridge(rawText -> {
    String cleanText = rawText.replaceAll("\u001B\\[[;\\d]*m", "");
    System.out.print("[BRIDGE PIPELINE LOG]: " + cleanText);
});

Exposure.printf(
    ExposureCategory.COMPONENTIAL,
    ExposureLevel.LEVEL1, 
    Exposure.BOLD + "ALU Unit Initialized -> " + Exposure.RESET + Exposure.FG_GREEN + "READY" + Exposure.RESET + "%n"
);

Exposure.printf(
    ExposureCategory.COMPONENTIAL,
    ExposureLevel.LEVEL3, 
    "Intermediate Bus Clock: 400MHz%n"
);

Exposure.printf(
    ExposureCategory.LOWERLEVEL,
    ExposureLevel.LEVEL5, 
    "NAND Gate Transistor Logic Validated.%n"
);

Exposure.LINEBREAK(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL1);
```

***Output***

```txt
[BRIDGE PIPELINE LOG]: ALU Unit Initialized -> READY
[BRIDGE PIPELINE LOG]: NAND Gate Transistor Logic Validated.
[BRIDGE PIPELINE LOG]: 
--------------------------------
```
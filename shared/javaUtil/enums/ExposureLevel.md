# Exposure Matrix Filter

[:arrow_left: Return to Hub](/shared/SharedUtility.md)  
[:arrow_left: Return to Understanding Exposure](/shared/javaUtil/Exposure.md)  

> What type of coffee bean and intensity?

> * [`ExposureLevel.java`](/shared/javaUtil/enums/ExposureLevel.java) 
> * [`ExposureCategory.java`](/shared/javaUtil/enums/ExposureCategory.java) 

## Sections

> * [`Overview`](#overview)
> * [`ExposureCategory`](#exposurecategory)
> * [`LEVEL1`](#level1)
> * [`LEVEL2`](#level2)
> * [`LEVEL3`](#level3)
> * [`LEVEL4`](#level4)
> * [`LEVEL5`](#level5)

---

### [`Overview`](#sections)

```txt
LEVEL 1 (ESSENTIAL)
└── VANILLA / TEST
    └── Final / essential result

LEVEL 2 (COMPONENT)
└── COMPONENTIAL
    └── Major operation being performed

LEVEL 3 (INTERMEDIATE)
└── COMPONENTIAL / DEBUG
    └── Important intermediate values

LEVEL 4 (INTERNAL)
└── COMPONENTIAL / DEBUG
    └── Internal operations of the component

LEVEL 5 (PRIMITIVE)
└── LOWERLEVEL
    └── Lowest-level operations / individual transformations
```

```java
package shared.javaUtil.enums;

public enum ExposureLevel {
    LEVEL1(1), // Final & essential result
    LEVEL2(2), // Major operation being performed
    LEVEL3(3), // Important intermediate values
    LEVEL4(4), // Internal operations of the component
    LEVEL5(5)  // Lowest-level operations & individual transformations
}
```

### [`ExposureCategory`](#sections)

***Example***

```java
package shared.javaUtil.enums;

public enum ExposureCategory {
    /** Regular unfiltered mode */
    VANILLA,        
    /** System log or system related calls */
    SYSTEMLOG, 
    /** Regular Debug mode */
    DEBUG,          
    /** Major system building blocks (ALU, Registers) */
    COMPONENTIAL,   
    /** Low-level diagnostic tracing (NAND gates, variables) */
    LOWERLEVEL,     
    /** For anything testing related. */
    TEST
}
```

```java
// Hand-pick exactly which horizontal domains are allowed to express themselves
Exposure.setCategories(ExposureCategory.VANILLA, ExposureCategory.COMPONENTIAL);

Exposure.printf(
                ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, 
                "ALU ADD REGISTERS LOADED\n"
                ); // Shown
Exposure.printf(
                ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5, 
                "NAND: 1 NAND 1 = 0\n"
                );      // Blocked
```

### [`LEVEL1`](#sections)

***Example***

```java
Exposure.setCategories(ExposureCategory.VANILLA);
Exposure.setLevel(ExposureLevel.LEVEL1);

Exposure.printf(
                ExposureLevel.LEVEL1, 
                "Output  = 01010100\n"
                );
Exposure.printf(
                ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5, 
                "XOR: 0 XOR 0 = 0\n"
                ); // Hidden
```

```txt
Output  = 01010100
```

### [`LEVEL2`](#sections)

***Example***

```java
Exposure.setCategories(ExposureCategory.COMPONENTIAL);
Exposure.setLevel(ExposureLevel.LEVEL2);

Exposure.printf(
                ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, 
                "Input_1 = 00111000\n"
                );
Exposure.printf(
                ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, 
                "Input_2 = 00011100\n"
                );
Exposure.printf(
                ExposureLevel.LEVEL1, 
                "Output  = 01010100\n"
                );
```

```txt
Input_1 = 00111000
Input_2 = 00011100
Output  = 01010100
```

### [`LEVEL3`](#sections)

***Example***

```java
Exposure.setCategories(ExposureCategory.COMPONENTIAL);
Exposure.setLevel(ExposureLevel.LEVEL3);

Exposure.printf(
                ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, 
                "Input_1 = 00111000\n"
                );
Exposure.printf(
                ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL2, 
                "Input_2 = 00011100\n"
                );
Exposure.NEWLINE(ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL3);
Exposure.printf(
                ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL3, 
                "Binary Addition:\n11110000\n+\n00001111\n────────\n11111111\n
                ");
```

```txt
Input_1 = 00111000
Input_2 = 00011100

Binary Addition:
11110000
+
00001111
────────
11111111
```

### [`LEVEL4`](#sections)

***Example***

```java
Exposure.setCategories(ExposureCategory.COMPONENTIAL);
Exposure.setLevel(ExposureLevel.LEVEL4);

Exposure.printf(
                ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL4, 
                "Bit 8:\nA: 0\nB: 0\nCarry: 0\nResult: 0\n"
                );
Exposure.printf(
                ExposureCategory.COMPONENTIAL, ExposureLevel.LEVEL4, 
                "Bit 7:\nA: 0\nB: 0\nCarry: 0\nResult: 0\n"
                );
```

```txt
Bit 8:
A: 0
B: 0
Carry: 0
Result: 0

Bit 7:
A: 0
B: 0
Carry: 0
Result: 0
```

### [`LEVEL5`](#sections)

***Example***

```java
Exposure.setCategories(ExposureCategory.LOWERLEVEL);
Exposure.setLevel(ExposureLevel.LEVEL5);

Exposure.printf(
                ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5, 
                "Bit 7\n────────────────────────\n\n"
                );
Exposure.printf(
                ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5, 
                "A = 0\nB = 0\nCarry In = 0\n\n"
                );
Exposure.printf(
                ExposureCategory.LOWERLEVEL, ExposureLevel.LEVEL5, 
                "XOR:\n0 XOR 0 = 0\n\nAND:\n0 AND 0 = 0\n"
                );
```

```txt
Bit 7
────────────────────────

A = 0
B = 0
Carry In = 0

XOR:
0 XOR 0 = 0

AND:
0 AND 0 = 0
```

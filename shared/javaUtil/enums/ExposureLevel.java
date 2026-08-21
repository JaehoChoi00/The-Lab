package shared.javaUtil.enums;

/**
 * ExposureLevel
 * @LEVEL1
 */
public enum ExposureLevel {
    /** Final & essential result */
    LEVEL1(1), 
    /**  Major operation being performed */
    LEVEL2(2), 
    /** Important intermediate values */
    LEVEL3(3),
    /** Internal operations of the component */
    LEVEL4(4), 
    /** Lowest-level operations & individual transformations */
    LEVEL5(5); 
    // Potentially pending as more complicated projects emerge. 

    private final int level;

    ExposureLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}

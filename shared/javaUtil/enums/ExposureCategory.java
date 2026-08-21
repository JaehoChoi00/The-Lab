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

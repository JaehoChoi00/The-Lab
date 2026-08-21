package shared.javaUtil;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.Consumer;

import shared.javaUtil.enums.ExposureCategory;
import shared.javaUtil.enums.ExposureLevel;

public class Exposure {

    private static final Set<ExposureCategory> enabledCategories = EnumSet.allOf(ExposureCategory.class);

    private static final Set<ExposureLevel> enabledLevels = EnumSet.of(ExposureLevel.LEVEL1);

    // The bridge hook to route text to outside systems like UI or Files
    private static Consumer<String> externalBridge = null;

    // Direct alias mappings from the absolute source of truth
    public static final String RESET = VariableConstants.RESET;
    public static final String BOLD = VariableConstants.BOLD;
    public static final String DIM = VariableConstants.DIM;
    public static final String UNDERLINE = VariableConstants.UNDERLINE;
    public static final int LINEFEED = VariableConstants.LINEFEED;

    public static final String FG_BLACK = VariableConstants.FG_BLACK;
    public static final String FG_RED = VariableConstants.FG_RED;
    public static final String FG_GREEN = VariableConstants.FG_GREEN;
    public static final String FG_YELLOW = VariableConstants.FG_YELLOW;
    public static final String FG_BLUE = VariableConstants.FG_BLUE;
    public static final String FG_MAGENTA = VariableConstants.FG_MAGENTA;
    public static final String FG_CYAN = VariableConstants.FG_CYAN;
    public static final String FG_WHITE = VariableConstants.FG_WHITE;

    public static final String BG_BLACK = VariableConstants.BG_BLACK;
    public static final String BG_RED = VariableConstants.BG_RED;
    public static final String BG_GREEN = VariableConstants.BG_GREEN;
    public static final String BG_YELLOW = VariableConstants.BG_YELLOW;
    public static final String BG_BLUE = VariableConstants.BG_BLUE;
    public static final String BG_MAGENTA = VariableConstants.BG_MAGENTA;
    public static final String BG_CYAN = VariableConstants.BG_CYAN;
    public static final String BG_WHITE = VariableConstants.BG_WHITE;
    public static String FG_COLOR(int id) { return VariableConstants.FG_COLOR(id); }
    public static String BG_COLOR(int id) { return VariableConstants.BG_COLOR(id); }

    public static void clearAllCategories() {
        enabledCategories.clear();
        enabledCategories.add(ExposureCategory.VANILLA); // Always keep your fallback safe
    }

    // Category Toggle Controls
    public static void enableCategory(ExposureCategory category) { enabledCategories.add(category); }
    public static void disableCategory(ExposureCategory category) { enabledCategories.remove(category); }

    public static void NEWLINE(ExposureLevel requiredLevel) { 
        NEWLINE(ExposureCategory.VANILLA, requiredLevel); 
    }
    
    public static void LINEBREAK(ExposureLevel requiredLevel) { 
        LINEBREAK(ExposureCategory.VANILLA, requiredLevel); 
    }

    public static void NEWLINE(ExposureCategory category, ExposureLevel requiredLevel) { 
        if (show(category, requiredLevel)) {
            emit("\n\n"); 
        }
    }
    
    public static void LINEBREAK(ExposureCategory category, ExposureLevel requiredLevel) { 
        if (show(category, requiredLevel)) {
            emit("\n--------------------------------\n"); 
        }
    }

    // Exposure configuration methods

    public static void setLevel(ExposureLevel newLevel) { 
        enabledLevels.clear();
        if (newLevel != null) {
            enabledLevels.add(newLevel);
        }
    }
    
    public static void setLevels(ExposureLevel... handPickedLevels) {
        enabledLevels.clear();
        if (handPickedLevels != null) {
            for (ExposureLevel level : handPickedLevels) {
                if (level != null) {
                    enabledLevels.add(level);
                }
            }
        }
    }

    public static void setCategory(ExposureCategory category) { 
        enabledCategories.clear();
        if (category != null) {
            enabledCategories.add(category);
        }
    }
    

    public static void setCategories(ExposureCategory... handPickedCategories) {
        enabledCategories.clear();
        if (handPickedCategories != null) {
            for (ExposureCategory category : handPickedCategories) {
                if (category != null) {
                    enabledCategories.add(category);
                }
            }
        }
    }

    public static Set<ExposureCategory> getCategories() {
        return Collections.unmodifiableSet(enabledCategories);
    }
    public static Set<ExposureLevel> getLevels() {
        return Collections.unmodifiableSet(enabledLevels);
    }

    public static void setBridge(Consumer<String> outsideSystem) { externalBridge = outsideSystem; }

    public static boolean show(ExposureLevel requiredLevel) {
        if (enabledLevels.size() == 1) {
            ExposureLevel activeLevel = enabledLevels.iterator().next();
            return activeLevel.getLevel() >= requiredLevel.getLevel();
        }
        return enabledLevels.contains(requiredLevel);
    }

    public static boolean show(ExposureCategory category, ExposureLevel requiredLevel) {
        if (!enabledCategories.contains(category)) return false;
        
        if (enabledLevels.size() == 1) {
            ExposureLevel activeLevel = enabledLevels.iterator().next();
            return activeLevel.getLevel() >= requiredLevel.getLevel();
        }
        return enabledLevels.contains(requiredLevel);
    }

    public static void printf(ExposureLevel requiredLevel, String format, Object... args) {
        if (show(requiredLevel)) {
            emit(String.format(format, args));
        }
    }

    public static void printf(ExposureCategory category, ExposureLevel requiredLevel, String format, Object... args) {
        if (show(category, requiredLevel)) {
            emit(String.format(format, args));
        }
    }

    private static void emit(String text) {
        if (externalBridge != null) {
            externalBridge.accept(text);
        } else {
            System.out.print(text);
        }
    }

}
package gameExperiments.intentionSystem.javaVersion;

import shared.javaUtil.Exposure;
import shared.javaUtil.enums.ExposureCategory;
import static shared.javaUtil.enums.ExposureCategory.COMPONENTIAL;
import static shared.javaUtil.enums.ExposureCategory.SYSTEMLOG;
import shared.javaUtil.enums.ExposureLevel;
import static shared.javaUtil.enums.ExposureLevel.LEVEL1;
import static shared.javaUtil.enums.ExposureLevel.LEVEL2;

public class Main {
    public static void main(String[] args) {
        Exposure.setBridge(System.err::print);
        Exposure.setCategories(ExposureCategory.values());
        Exposure.setLevel(ExposureLevel.LEVEL5);

        Exposure.printf(SYSTEMLOG, LEVEL1, "[Boot Sequence]: Launching Intention System Engine...%n");
        
        IntentionSystem intentionSystem = new IntentionSystem();
        Exposure.printf(COMPONENTIAL, LEVEL2, "[System Core]: IntentionSystem module instantiated.%n");
        
        intentionSystem.run();
        
        Exposure.printf(SYSTEMLOG, LEVEL1, "[Boot Sequence]: Intention System Engine terminated cleanly.%n");
    }
}

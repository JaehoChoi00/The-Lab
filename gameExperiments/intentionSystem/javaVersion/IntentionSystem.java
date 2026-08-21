// IntentionSystem.java
package gameExperiments.intentionSystem.javaVersion;

import java.util.Scanner;

import shared.javaUtil.Exposure;
import static shared.javaUtil.enums.ExposureCategory.COMPONENTIAL;
import static shared.javaUtil.enums.ExposureCategory.DEBUG;
import static shared.javaUtil.enums.ExposureCategory.LOWERLEVEL;
import static shared.javaUtil.enums.ExposureCategory.SYSTEMLOG;
import static shared.javaUtil.enums.ExposureLevel.LEVEL1;
import static shared.javaUtil.enums.ExposureLevel.LEVEL2;
import static shared.javaUtil.enums.ExposureLevel.LEVEL3;
import static shared.javaUtil.enums.ExposureLevel.LEVEL4;
import static shared.javaUtil.enums.ExposureLevel.LEVEL5;

public class IntentionSystem {

    private Scanner scanner;
    private Player bob;
    private World world;

    public void run() {
        init();
        loop();
        terminate();
    }

    public void init() {
        Exposure.printf(SYSTEMLOG, LEVEL1, "Initializing Intention System...%n");
        scanner = new Scanner(System.in);

        bob = generatePlayer("Bob", 100, 0, 0, 'B');
        world = generateWorld("World", 10, 10); 
        world.addPlayer(bob);

        world.renderPlayers();
        world.printGameStats();
        
        Exposure.LINEBREAK(SYSTEMLOG, LEVEL1);
    }

    public void loop() {
        String intent = scanner.nextLine();

        while (!(intent.equals("end"))) {
            if (intent.length() < 2 && bob.controller(intent)) {
                Exposure.printf(DEBUG, LEVEL2, "[Executing Direct Intent]: %s%n", intent);
                world.renderPlayers();
                world.printGameStats();
            }
            else if (intent.equals("scaleDown")) {
                Exposure.printf(COMPONENTIAL, LEVEL2, "[World Scaled Down]: %dx%d%n", world.getMatrixSizeX() - 1, world.getMatrixSizeY() - 1);
                world.modifyWorld(world.getMatrixSizeX() - 1, world.getMatrixSizeY() - 1);
                world.renderPlayers();
                world.printGameStats();
            }
            else if (intent.equals("scaleUp")) {
                Exposure.printf(COMPONENTIAL, LEVEL2, "[World Scaled Up]: %dx%d%n", world.getMatrixSizeX() + 1, world.getMatrixSizeY() + 1);
                world.modifyWorld(world.getMatrixSizeX() + 1, world.getMatrixSizeY() + 1);
                world.renderPlayers();
                world.printGameStats();
            }
            else if (intent.length() > 1) {
                Exposure.printf(DEBUG, LEVEL2, "[Processing Multi-Instruction Intent]: %s%n", intent);
                for (char instruction : intent.toCharArray()) {
                    if (bob.controller(String.valueOf(instruction))) {
                        Exposure.printf(LOWERLEVEL, LEVEL4, "  └─ [Sub-Step Executed]: %c%n", instruction);
                        world.renderPlayers();
                        world.printGameStats();
                    }
                    sleep(300);
                }
            }

            intent = scanner.nextLine();
        }
        
        Exposure.NEWLINE(SYSTEMLOG, LEVEL1);
    }

    public void terminate() {
        Exposure.printf(SYSTEMLOG, LEVEL1, "Terminating Intention System resources.%n");
        scanner.close();
    }

    public Player generatePlayer(String name, int hitpoint, int positionX, int positionY, char symbol) {
        Exposure.printf(COMPONENTIAL, LEVEL3, "[Player Created]: Name=%s, HP=%d, Pos=(%d, %d), Symbol='%c'%n", 
            name, hitpoint, positionX, positionY, symbol);
        return new Player(name, hitpoint, positionX, positionY, symbol);
    }

    public World generateWorld(String name, int matrixSizeX, int matrixSizeY) {
        Exposure.printf(COMPONENTIAL, LEVEL3, "[World Created]: Name=%s, Grid=(%dx%d)%n", 
            name, matrixSizeX, matrixSizeY);
        return new World(name, matrixSizeX, matrixSizeY);
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Exposure.printf(SYSTEMLOG, LEVEL5, "[Thread Interrupt]: Sleep interrupted during delay.%n");
            Thread.currentThread().interrupt();
        }
    }
}
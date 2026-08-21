// World.java
package gameExperiments.intentionSystem.javaVersion;

import java.util.HashSet;
import java.util.Set;

import shared.javaUtil.Exposure;
import static shared.javaUtil.enums.ExposureCategory.COMPONENTIAL;
import static shared.javaUtil.enums.ExposureCategory.DEBUG;
import static shared.javaUtil.enums.ExposureCategory.LOWERLEVEL;
import static shared.javaUtil.enums.ExposureCategory.SYSTEMLOG;
import static shared.javaUtil.enums.ExposureCategory.VANILLA;
import static shared.javaUtil.enums.ExposureLevel.LEVEL1;
import static shared.javaUtil.enums.ExposureLevel.LEVEL2;
import static shared.javaUtil.enums.ExposureLevel.LEVEL3;
import static shared.javaUtil.enums.ExposureLevel.LEVEL4;
import static shared.javaUtil.enums.ExposureLevel.LEVEL5;

public class World {
    private String name;
    private int matrixSizeX;
    private int matrixSizeY;
    final Set<Player> players = new HashSet<>();

    private String emptyMap[][];
    private String renderedMap[][];

    public World(String name, int matrixSizeX, int matrixSizeY) {
        this.name = name;
        this.matrixSizeX = matrixSizeX;
        this.matrixSizeY = matrixSizeY;
        this.emptyMap = new String[matrixSizeY][matrixSizeX];
        
        Exposure.printf(COMPONENTIAL, LEVEL2, "[World Allocation]: Creating grid matrix %dx%d for '%s'%n", matrixSizeX, matrixSizeY, name);

        for (int i = 0; i < matrixSizeY; i++) { 
            for (int j = 0; j < matrixSizeX; j++) { 
                emptyMap[i][j] = "0";
            }
        }

        this.renderedMap = new String[matrixSizeY][matrixSizeX];
        refreshWorldRender();
    }

    public void addPlayer(Player playerToAdd) {
        playerToAdd.registerWorld(this);
        players.add(playerToAdd);
        Exposure.printf(COMPONENTIAL, LEVEL2, "[World Registry]: Added Player '%s' to '%s'%n", playerToAdd.getName(), this.name);
    }

    public void removePlayer(Player playerToRemove) {
        playerToRemove.unregisterWorld();
        players.remove(playerToRemove);
        Exposure.printf(COMPONENTIAL, LEVEL2, "[World Registry]: Removed Player '%s' from '%s'%n", playerToRemove.getName(), this.name);
    }

    public void modifyWorld(int newSizeX, int newSizeY) {
        if (newSizeX < 1 || newSizeY < 1) {
            Exposure.printf(SYSTEMLOG, LEVEL2, "[World Resize Error]: Invalid matrix dimensions (%dx%d)%n", newSizeX, newSizeY);
            return;
        }

        int oldSizeX = matrixSizeX;
        int oldSizeY = matrixSizeY;

        Exposure.printf(COMPONENTIAL, LEVEL2, "[World Resizing]: %dx%d -> %dx%d%n", oldSizeX, oldSizeY, newSizeX, newSizeY);

        setMatrixSizeX(newSizeX);
        setMatrixSizeY(newSizeY);
        this.emptyMap = new String[newSizeY][newSizeX];
        for (int i = 0; i < matrixSizeY; i++) { 
            for (int j = 0; j < matrixSizeX; j++) { 
                emptyMap[i][j] = "0";
            }
        }
        
        for (Player player : players) {
            if (player.getPositionX() >= newSizeX) {
                int adjustedX = player.getPositionX() - Math.abs(oldSizeX - newSizeX);
                Exposure.printf(LOWERLEVEL, LEVEL4, "[World Re-clamp]: Clamping Player '%s' X to %d%n", player.getName(), adjustedX);
                player.setPositionX(adjustedX);
            }
            if (player.getPositionY() >= newSizeY) {
                int adjustedY = player.getPositionY() - Math.abs(oldSizeY - newSizeY);
                Exposure.printf(LOWERLEVEL, LEVEL4, "[World Re-clamp]: Clamping Player '%s' Y to %d%n", player.getName(), adjustedY);
                player.setPositionY(adjustedY);
            }
        }
        refreshWorldRender();
    }

    public void refreshWorldRender() { 
        this.renderedMap = new String[matrixSizeY][matrixSizeX];
        for (int i = 0; i < emptyMap.length; i++) {
            this.renderedMap[i] = this.emptyMap[i].clone();
        }
        Exposure.printf(LOWERLEVEL, LEVEL5, "[Grid Pipeline]: Cleaned and cloned background frame buffer%n");
    }

    public void renderPlayers() {
        refreshWorldRender();

        for (Player player : players) {
            renderedMap[player.getPositionY()][player.getPositionX()] = String.valueOf(player.getSymbol());
            Exposure.printf(LOWERLEVEL, LEVEL5, "[Grid Render]: Projected symbol '%c' at (%d, %d)%n", 
                player.getSymbol(), player.getPositionX(), player.getPositionY());
        }
    }

    public void printPlayers() {
        for (Player player : players) {
            player.printPlayerStats();
        }
    }

    private void clearScreen() {
        // \033[H moves cursor to top-left; \033[J clears down to erase lingering characters
        System.out.print("\033[H\033[J");
        System.out.flush();
    }

    public void printWorldStats() {
        clearScreen();

        Exposure.printf(VANILLA, LEVEL1, "--- WORLD STATE: %s ---%n", this.name);
        Exposure.LINEBREAK(VANILLA, LEVEL1);

        for (var row : this.renderedMap) {
            Exposure.printf(VANILLA, LEVEL1, "%s%n", String.join(" ", row)); 
        }
        Exposure.LINEBREAK(VANILLA, LEVEL1);
    }

    public void printGameStats() {
        clearScreen();
        
        Exposure.printf(VANILLA, LEVEL1, "--- PLAYER STATS: Count [%d] ---%n", this.players.size());
        printPlayers();
        Exposure.printf(VANILLA, LEVEL1, "--- WORLD STATE: %s ---%n", this.name);
        Exposure.LINEBREAK(VANILLA, LEVEL1);

        for (var row : this.renderedMap) {
            Exposure.printf(VANILLA, LEVEL1, "%s%n", String.join(" ", row)); 
        }
        Exposure.LINEBREAK(VANILLA, LEVEL1);
    }

    public boolean setName(String name) { 
        if ((this.name.equals(name))) {
            Exposure.printf(DEBUG, LEVEL3, "[World Update Bypassed]: Identical name '%s'%n", name);
            return false;
        }
        Exposure.printf(COMPONENTIAL, LEVEL3, "[World Update]: Name %s -> %s%n", this.name, name);
        this.name = name;
        return true;
    }

    public boolean setMatrixSizeX(int matrixSizeX) { 
        if ((this.matrixSizeX == matrixSizeX)) {
            Exposure.printf(DEBUG, LEVEL3, "[World Update Bypassed]: Identical matrix size X %d%n", matrixSizeX);
            return false;
        }
        this.matrixSizeX = matrixSizeX;
        return true;
    }

    public boolean setMatrixSizeY(int matrixSizeY) { 
        if ((this.matrixSizeY == matrixSizeY)) {
            Exposure.printf(DEBUG, LEVEL3, "[World Update Bypassed]: Identical matrix size Y %d%n", matrixSizeY);
            return false;
        }
        this.matrixSizeY = matrixSizeY;
        return true;
    }
    
    public String getName() { return name; }
    public int getMatrixSizeX() { return matrixSizeX; }
    public int getMatrixSizeY() { return matrixSizeY; }
}
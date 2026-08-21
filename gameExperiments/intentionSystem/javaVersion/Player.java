// Player.java
package gameExperiments.intentionSystem.javaVersion;

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

public class Player {
    private String name;
    private int hitpoint;
    private int positionX;
    private int positionY;
    private char symbol;

    private World world;

    public Player(String name, int hitpoint, int positionX, int positionY, char symbol) {
        this.name = name;
        this.hitpoint = hitpoint;
        this.positionX = positionX;
        this.positionY = positionY;
        this.symbol = symbol;
        
        Exposure.printf(COMPONENTIAL, LEVEL3, "[Player Instantiated]: %s | HP: %d | Pos: (%d, %d) | Symbol: '%c'%n",
                name, hitpoint, positionX, positionY, symbol);
    }

    public void registerWorld(World world) { 
        this.world = world; 
        Exposure.printf(COMPONENTIAL, LEVEL4, "[Player Binding]: Player '%s' registered to World%n", name);
    }
    
    public void unregisterWorld() { 
        Exposure.printf(COMPONENTIAL, LEVEL4, "[Player Unbinding]: Player '%s' detached from World%n", name);
        this.world = null; 
    }

    public boolean changePlayerPositionX(int newPlayerPositionX) {
        if ((0 <= newPlayerPositionX) && newPlayerPositionX < world.getMatrixSizeX()) {
            Exposure.printf(LOWERLEVEL, LEVEL5, "[Pos Shift X]: Player '%s' X: %d -> %d%n", name, this.positionX, newPlayerPositionX);
            setPositionX(newPlayerPositionX);
            return true;
        } else {
            Exposure.printf(SYSTEMLOG, LEVEL2, "[Boundary Error]: Player '%s' hit X boundary at %d%n", name, newPlayerPositionX);
            return false;
        }
    }

    public boolean changePlayerPositionY(int newPlayerPositionY) {
        if ((0 <= newPlayerPositionY) && newPlayerPositionY < world.getMatrixSizeY()) {
            Exposure.printf(LOWERLEVEL, LEVEL5, "[Pos Shift Y]: Player '%s' Y: %d -> %d%n", name, this.positionY, newPlayerPositionY);
            setPositionY(newPlayerPositionY);
            return true;
        } else {
            Exposure.printf(SYSTEMLOG, LEVEL2, "[Boundary Error]: Player '%s' hit Y boundary at %d%n", name, newPlayerPositionY);
            return false;
        }
    }

    public boolean controller(String intent) {
        boolean moved = false;
        switch (intent) {
            case "d" -> moved = changePlayerPositionX(this.positionX + 1);
            case "a" -> moved = changePlayerPositionX(this.positionX - 1);
            case "w" -> moved = changePlayerPositionY(this.positionY - 1);
            case "s" -> moved = changePlayerPositionY(this.positionY + 1);
        }
        Exposure.printf(COMPONENTIAL, LEVEL3, "[Controller Trigger]: Intent '%s' -> Action Resolved: %b%n", intent, moved);
        return moved;
    }

    public String getName() { return name; }
    public int getHitpoint() { return hitpoint; }
    public int getPositionX() { return positionX; }
    public int getPositionY() { return positionY; }
    public char getSymbol() { return symbol; }

    public boolean setName(String name) { 
        if ((this.name.equals(name))) {
            Exposure.printf(DEBUG, LEVEL3, "[Player Update Bypassed]: Identical name '%s'%n", name);
            return false;
        }
        Exposure.printf(COMPONENTIAL, LEVEL3, "[Player Update]: Name %s -> %s%n", this.name, name);
        this.name = name;
        return true;
    }

    public boolean setHitpoint(int hitpoint) { 
        if ((this.hitpoint == hitpoint)) {
            Exposure.printf(DEBUG, LEVEL3, "[Player Update Bypassed]: Identical hitpoint %d%n", hitpoint);
            return false;
        }
        Exposure.printf(COMPONENTIAL, LEVEL3, "[Player Update]: HP %d -> %d%n", this.hitpoint, hitpoint);
        this.hitpoint = hitpoint;
        return true;
    }
    
    public void setPositionX(int positionX) { this.positionX = positionX; }
    public void setPositionY(int positionY) { this.positionY = positionY; }
    
    public boolean setSymbol(char symbol) { 
        if ((this.symbol == symbol)) {
            Exposure.printf(DEBUG, LEVEL3, "[Player Update Bypassed]: Identical symbol '%c'%n", symbol);
            return false;
        }
        Exposure.printf(COMPONENTIAL, LEVEL3, "[Player Update]: Symbol '%c' -> '%c'%n", this.symbol, symbol);
        this.symbol = symbol;
        return true;
    }

    public void printPlayerStats() {
        Exposure.printf(VANILLA, LEVEL1, "Player name: %s%n", this.name);
        Exposure.printf(VANILLA, LEVEL1, "Player hitpoint: %d%n", this.hitpoint);
        Exposure.printf(VANILLA, LEVEL1, "Player positionX: %d%n", this.positionX);
        Exposure.printf(VANILLA, LEVEL1, "Player positionY: %d%n", this.positionY);
        Exposure.printf(VANILLA, LEVEL1, "Player symbol: %c%n", this.symbol);
        Exposure.LINEBREAK(VANILLA, LEVEL1);
    }
}

import util.SystemPrint;

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
    }

    public void registerWorld(World world) { this.world = world; }
    public void unregisterWorld() { this.world = null; }

    public boolean changePlayerPositionX(int newPlayerPositionX) {
        if ((0 <= newPlayerPositionX) && newPlayerPositionX < world.getMatrixSizeX()) {
            setPositionX(newPlayerPositionX);
            return true;
        }
        else {
            SystemPrint.terminalDisplay("[System Report]: Boundary Hit on X movement. Cannot move outside map.\n");
            return false;
        }
    }

    public boolean changePlayerPositionY(int newPlayerPositionY) {
        if ((0 <= newPlayerPositionY) && newPlayerPositionY < world.getMatrixSizeY()) {
            setPositionY(newPlayerPositionY);
            return true;
        }
        else {
            SystemPrint.terminalDisplay("[System Report]: Boundary Hit on Y movement. Cannot move outside map.\n");
            return false;
        }
    }

    public boolean controller(String intent) {
        boolean moved = false;
        switch (intent) {
            case "d" ->  moved = changePlayerPositionX(this.positionX + 1);
            case "a" ->  moved = changePlayerPositionX(this.positionX - 1);
            case "w" ->  moved = changePlayerPositionY(this.positionY - 1);
            case "s" ->  moved = changePlayerPositionY(this.positionY + 1);
        }
        return moved;
    }

    public String getName() { return name;}
    public int getHitpoint() { return hitpoint; }
    public int getPositionX() { return positionX; }
    public int getPositionY() { return positionY; }
    public char getSymbol() { return symbol; }

    public boolean setName(String name) { 
        if ((this.name.equals(name))) {
            SystemPrint.terminalDisplay("[System Report]: Same as previous player name. No changes\n");
            return false;
        }
        this.name = name;
        return true;
    }

    public boolean setHitpoint(int hitpoint) { 
        if ((this.hitpoint == hitpoint)) {
            SystemPrint.terminalDisplay("[System Report]: Same as previous player name. No changes\n");
            return false;
        }
        this.hitpoint = hitpoint;
        return true;
    }
    public void setPositionX(int positionX) { this.positionX = positionX; }
    public void setPositionY(int positionY) { this.positionY = positionY; }
    public boolean setSymbol(char symbol) { 
        if ((this.symbol == symbol)) {
            SystemPrint.terminalDisplay("[System Report]: Same as previous player name. No changes\n");
            return false;
        }
        this.symbol = symbol;
        return true;
    }

    public void printPlayerStats() {
        System.out.println("Player name: " + this.name);
        System.out.println("Player hitpoint: " + this.hitpoint);
        System.out.println("Player positionX: " + this.positionX);
        System.out.println("Player positionY: " + this.positionY);
        System.out.println("Player symbol: " + this.symbol) ;
        System.out.println("-------------");
    }
}

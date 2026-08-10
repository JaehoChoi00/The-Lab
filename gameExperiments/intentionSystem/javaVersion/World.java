import java.util.HashSet;
import java.util.Set;
import util.SystemPrint;
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
        
        renderedMap = emptyMap.clone();
        for (int i = 0; i < matrixSizeY; i++) { 
            for (int j = 0; j < matrixSizeX; j++) { 
                emptyMap[i][j] = "0";
            }
        }

        for (int i = 0; i < emptyMap.length; i++) {
            this.renderedMap[i] = this.emptyMap[i].clone();
        }
    }

    public void addPlayer(Player playerToAdd) {
        playerToAdd.registerWorld(this);
        players.add(playerToAdd);
    }

    public void removePlayer(Player playerToRemove) {
        playerToRemove.unregisterWorld();
        players.remove(playerToRemove);
    }

    public void modifyWorld(int newSizeX, int newSizeY) {
        if (newSizeX < 1 || newSizeY < 1) {
            return;
        }

        int oldSizeX = matrixSizeX;
        int oldSizeY = matrixSizeY;

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
                player.setPositionX(player.getPositionX() - Math.abs(oldSizeX - newSizeX));
            }
            if (player.getPositionY() >= newSizeY) {
                player.setPositionY(player.getPositionY() - Math.abs(oldSizeY - newSizeY));
            }
        }
        refreshWorldRender();
    }

    public void refreshWorldRender() { 
        renderedMap = emptyMap.clone();
        for (int i = 0; i < emptyMap.length; i++) {
            this.renderedMap[i] = this.emptyMap[i].clone();
        }
    }

    public void renderPlayers() {
        refreshWorldRender();

        for (Player player : players) {
            renderedMap[player.getPositionY()][player.getPositionX()] = String.valueOf(player.getSymbol());
        }
    }

    public void printPlayers() {
        for (Player player : players) {
            player.printPlayerStats();
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void printWorldStats() {
        clearScreen();

        System.out.println("--- WORLD STATE: " + this.name + " ---");
        System.out.println("-------------");

        for (var row : this.renderedMap) {
            System.out.println(String.join(" ", row)); 
        }
        System.out.println("-------------");
    }

    public void printGameStats() {
        clearScreen();
        
        System.out.println("--- PLAYER STATS: Count [" + this.players.size() + "] ---");
        printPlayers();
        System.out.println("--- WORLD STATE: " + this.name + " ---");
        System.out.println("-------------");

        for (var row : this.renderedMap) {
            System.out.println(String.join(" ", row)); 
        }
        System.out.println("-------------");
    }

    public boolean setName(String name) { 
        if ((this.name.equals(name))) {
            SystemPrint.terminalDisplay("[System Report]: Same as previous player name. No changes\n");
            return false;
        }
        this.name = name;
        return true;
    }
    public boolean setMatrixSizeX(int matrixSizeX) { 
        if ((this.matrixSizeX == matrixSizeX)) {
            SystemPrint.terminalDisplay("[System Report]: Same as previous player name. No changes\n");
            return false;
        }
        this.matrixSizeX = matrixSizeX;
        return true;
    }
    public boolean setMatrixSizeY(int matrixSizeY) { 
        if ((this.matrixSizeY == matrixSizeY)) {
            SystemPrint.terminalDisplay("[System Report]: Same as previous player name. No changes\n");
            return false;
        }
        this.matrixSizeY = matrixSizeY;
        return true;
    }
    
    public String getName() { return name; }
    public int getMatrixSizeX() { return matrixSizeX; }
    public int getMatrixSizeY() { return matrixSizeY; }

}

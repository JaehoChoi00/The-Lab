import java.util.Scanner;
import util.SystemPrint;

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
        scanner = new Scanner(System.in);

        bob = generatePlayer("Bob", 100, 0, 0, 'B');
        world = generateWorld("World", 10, 10); 
        world.addPlayer(bob);

        world.renderPlayers();
        world.printGameStats();
    }

    public void loop() {
        String intent = scanner.nextLine();

        while(!(intent.equals("end"))) {
            if (intent.length() < 2 && bob.controller(intent)) {
                world.renderPlayers();
                world.printGameStats();
                SystemPrint.terminalDisplay("[Executing]: " + intent + "\n");
            }
            else if (intent.equals("scaleUp")) {
                world.modifyWorld(world.getMatrixSizeX()+1, world.getMatrixSizeY()+1);
                world.renderPlayers();
                world.printGameStats();
            }
            else if (intent.equals("scaleDown")) {
                world.modifyWorld(world.getMatrixSizeX()-1, world.getMatrixSizeY()-1);
                world.renderPlayers();
                world.printGameStats();
            }
            else if (intent.length() > 1) {
                for (char instruction : intent.toCharArray()) {
                    if(bob.controller(String.valueOf(instruction))){
                        world.renderPlayers();
                        world.printGameStats();
                        SystemPrint.terminalDisplay("[Executing]: " + intent + "\n");
                    }
                    sleep(300);
                }
            }

            intent = scanner.nextLine();
        }
        SystemPrint.terminalDisplay("\n");
    }

    public void terminate() {
        scanner.close();
    }

    public Player generatePlayer(String name, int hitpoint, int positionX, int positionY, char Symbol) {
        return (new Player(name, hitpoint, positionX, positionY, Symbol));
    }

    public World generateWorld(String name, int matrixSizeX, int matrixSizeY) {
        return (new World(name, matrixSizeX, matrixSizeY));
    }

    public static void sleep(int milliseconds) {
    try {
        Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Cleanly handle system interruptions
    }
}
}
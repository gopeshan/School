import java.util.Scanner;
import java.util.Random;

public class GuessMaster {

    private Entity[] entities;
    private static final String EXIT_COMMAND = "exit";

    public GuessMaster(Entity[] entities) {
        validateEntities(entities);
        this.entities = entities.clone();
        System.out.println("Welcome to GuessMaster!");
    }

    public GuessMaster() {
        this.entities = new Entity[0];
        System.out.println("Welcome to GuessMaster!");
    }

    public void addEntity(Entity entity) {
        if (entity != null) {
            Entity[] temp = new Entity[this.entities.length + 1];
            System.arraycopy(this.entities, 0, temp, 0, this.entities.length);
            temp[this.entities.length] = entity;
            this.entities = temp;
        } else {
            System.out.println("Invalid Entity. Major Error");
            System.exit(0);
        }
    }

    public void playGame(Entity entity) {
        if (entity == null) {
            System.out.println("Invalid entity. Major Error");
            System.exit(0);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please guess the birthday of " + entity.getName() +
                ". Enter the date in the format of mm/dd/yyyy");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase(EXIT_COMMAND)) {
                System.out.println("Exiting...");
                scanner.close();
                System.exit(0);
            }

            try {
                Date userDate = new Date(userInput);
                handleUserGuess(entity, userDate);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
    }

    public void playGame(int entityIndex) {
        if (this.entities.length == 0) {
            System.out.println("No entities available.");
            return;
        }

        if (isValidEntityIndex(entityIndex)) {
            playGame(this.entities[entityIndex]);
        } else {
            System.out.println("Invalid Entity Index. Choosing from available entities....");
            playGame(getRandomEntity());
        }
    }

    public void playGame() {
        playGame(getRandomEntity());
    }

    private void handleUserGuess(Entity entity, Date userDate) {
        if (entity.getBirthDate().equals(userDate)) {
            System.out.println("BINGO! You got it!");
            playGame(getRandomEntity());
            System.exit(0);
        } else if (entity.getBirthDate().precedes(userDate)) {
            System.out.println("Incorrect, try an earlier date.");
        } else {
            System.out.println("Incorrect, try a later date.");
        }
    }

    private Entity getRandomEntity() {
        Random random = new Random();
        return this.entities[random.nextInt(this.entities.length)];
    }

    private void validateEntities(Entity[] entities) {
        if (entities == null || entities.length == 0) {
            System.out.println("Invalid Entities. Major Error");
            System.exit(0);
        }
    }

    private boolean isValidEntityIndex(int entityIndex) {
        return entityIndex >= 0 && entityIndex < this.entities.length;
    }

    public static void main(String[] args) {
        Entity trudeau = new Entity("Justin Trudeau", new Date("December", 25, 1971));
        Entity dion = new Entity("Celine Dion", new Date("March", 30, 1968));
        Entity usa = new Entity("United States", new Date("July", 4, 1776));

        GuessMaster guessMaster = new GuessMaster();
        guessMaster.addEntity(trudeau);
        guessMaster.addEntity(dion);
        guessMaster.addEntity(usa);

        guessMaster.playGame();
    }
}

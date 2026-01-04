import java.util.Scanner;

public class AdventureGame {

    // Scanner for user input
    private static Scanner scanner = new Scanner(System.in);

    // Player attributes
    private static int health = 100;
    private static boolean hasKey = false;

    public static void main(String[] args) {
        // Game introduction
        System.out.println("Welcome to the Adventure Game!");
        System.out.println("You wake up in a mysterious room. Your goal is to escape the dungeon.");

        // Start the game with the first room
        room1();
    }

    public static void room1() {
        System.out.println("\nYou are in Room 1. It's a small, dark room with a locked door.");
        System.out.println("There's a table with a note on it. What do you want to do?");
        System.out.println("1. Read the note.");
        System.out.println("2. Look around for a key.");
        System.out.println("3. Try to open the door.");
        
        String choice = getUserInput();

        switch (choice) {
            case "1":
                readNote();
                break;
            case "2":
                lookForKey();
                break;
            case "3":
                openDoor();
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
                room1();
        }
    }

    public static void readNote() {
        System.out.println("\nThe note reads: 'The key is hidden in the room. Look carefully.'");
        room1();
    }

    public static void lookForKey() {
        if (hasKey) {
            System.out.println("\nYou already have the key! Try something else.");
            room1();
        } else {
            System.out.println("\nYou search the room and find a small key hidden under the table.");
            hasKey = true;
            room1();
        }
    }

    public static void openDoor() {
        if (hasKey) {
            System.out.println("\nYou use the key to unlock the door and step into the next room.");
            room2();
        } else {
            System.out.println("\nThe door is locked! You need a key to open it.");
            room1();
        }
    }

    public static void room2() {
        System.out.println("\nYou enter Room 2. It's dark, but you can faintly see a shadow of a figure.");
        System.out.println("The figure turns out to be a guard with a sword!");
        System.out.println("1. Fight the guard.");
        System.out.println("2. Try to sneak past the guard.");
        System.out.println("3. Talk to the guard.");
        
        String choice = getUserInput();

        switch (choice) {
            case "1":
                fightGuard();
                break;
            case "2":
                sneakPast();
                break;
            case "3":
                talkToGuard();
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
                room2();
        }
    }

    public static void fightGuard() {
        System.out.println("\nYou decide to fight the guard!");
        if (Math.random() > 0.5) {
            System.out.println("You defeat the guard and continue on your journey.");
            room3();
        } else {
            System.out.println("The guard overpowers you. You lose 30 health.");
            health -= 30;
            if (health <= 0) {
                System.out.println("You have died. Game over!");
            } else {
                room2();
            }
        }
    }

    public static void sneakPast() {
        System.out.println("\nYou attempt to sneak past the guard...");
        if (Math.random() > 0.3) {
            System.out.println("You successfully sneak past the guard and move to the next room.");
            room3();
        } else {
            System.out.println("The guard spots you! You take 20 damage.");
            health -= 20;
            if (health <= 0) {
                System.out.println("You have died. Game over!");
            } else {
                room2();
            }
        }
    }

    public static void talkToGuard() {
        System.out.println("\nThe guard says: 'You must answer my riddle to pass.'");
        System.out.println("What is always in front of you but can't be seen?");
        System.out.println("1. The future.");
        System.out.println("2. Air.");
        System.out.println("3. Darkness.");

        String choice = getUserInput();

        if (choice.equals("1")) {
            System.out.println("\nCorrect! The guard steps aside, allowing you to pass.");
            room3();
        } else {
            System.out.println("\nIncorrect! The guard strikes you for 25 damage.");
            health -= 25;
            if (health <= 0) {
                System.out.println("You have died. Game over!");
            } else {
                room2();
            }
        }
    }

    public static void room3() {
        System.out.println("\nYou enter Room 3. There is a chest in the center of the room.");
        System.out.println("1. Open the chest.");
        System.out.println("2. Leave the chest alone.");
        
        String choice = getUserInput();

        switch (choice) {
            case "1":
                openChest();
                break;
            case "2":
                System.out.println("You decide to leave the chest alone and exit the room.");
                endGame();
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
                room3();
        }
    }

    public static void openChest() {
        System.out.println("\nYou open the chest and find a treasure! Congratulations, you win!");
        endGame();
    }

    public static void endGame() {
        System.out.println("\nThe game has ended. Thank you for playing!");
    }

    public static String getUserInput() {
        System.out.print("Choose an option: ");
        return scanner.nextLine().toLowerCase();
    }
}

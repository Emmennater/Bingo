package ui;

import java.util.Scanner;

/**
 * A class that handles the user interface for the Bingo game
 */
public class BingoInterface {
    private Scanner scanner;

    /**
     * Creates a new BingoInterface object
     */
    public BingoInterface() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message
     */
    public void displayWelcomeMessage() {
        System.out.println("Welcome to Bingo!");
    }

    /**
     * Prompts the user to choose the number of cards to play with
     * @param maxCards The maximum number of cards available
     * @return The number of cards to play with
     */
    public int getDesiredNumberOfCards(int maxCards) {
        System.out.println("How many cards would you like to play with? (1-" + maxCards + ")");
        int numCardsToPlay = scanner.nextInt();

        // Check if the number of cards to play is valid
        while (numCardsToPlay < 1 || numCardsToPlay > maxCards) {
            System.out.println("Invalid input. Please enter a number in the range 1-" + maxCards + ".");
            numCardsToPlay = scanner.nextInt();
        }

        return numCardsToPlay;
    }

    /**
     * Prompts the user to choose a play mode
     * @return The chosen play mode (R)andom or (M)anual
     */
    public String getPlayMode() {
        System.out.println("Please choose a play mode (R)andom or (M)anual:");
        String playMode = scanner.next().toUpperCase();
        scanner.nextLine();

        // Check if the play mode is valid
        while (!playMode.equals("R") && !playMode.equals("M")) {
            System.out.println("Invalid input. Enter 'R' for Random or 'M' for Manual.");
            playMode = scanner.next().toUpperCase();
            scanner.nextLine();
        }

        return playMode;
    }
}

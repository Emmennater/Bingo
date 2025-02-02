package bingo;

import java.util.List;
import java.util.Scanner;
import ui.BingoInterface;
import helper.Print;

/**
 * The main game class that handles the game logic
 */
public class Game {
    private CardManager cardManager;
    private BingoInterface bingoInterface;

    /**
     * Constructs a new Game object
     * @param cards the list of all Bingo cards
     */
    public Game(List<BingoCard> cards) {
        this.cardManager = new CardManager(cards);
        this.bingoInterface = new BingoInterface();
        start();
    }

    /**
     * Starts the game
     */
    private void start() {
        int maxCards = cardManager.getMaxChosenCards();

        // Display the welcome message
        bingoInterface.displayWelcomeMessage();

        // Ask the user how many cards they want to play with
        Scanner scanner = new Scanner(System.in);
        int numCardsToPlay = bingoInterface.getDesiredNumberOfCards(maxCards);

        // Ask the user if they want to play in random or manual mode
        String playMode = bingoInterface.getPlayMode();

        cardManager.reset();

        if (playMode.equals("R")) {
            cardManager.randomSelect(numCardsToPlay);
        } else {
            cardManager.manualSelect(scanner, numCardsToPlay);
        }

        // Display the chosen cards to the user
        String[] cardStrings = new String[cardManager.getChosenCards().size()];
        for (int i = 0; i < cardManager.getChosenCards().size(); i++) {
            cardStrings[i] = "Card " + (i + 1) + ":\n" + cardManager.getChosenCards().get(i);
        }

        // Print the chosen cards in a horizontal format
        Print.printHorizontal(List.of(cardStrings));

        scanner.close();
    }
}

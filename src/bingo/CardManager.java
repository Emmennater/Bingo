package bingo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import helper.Print;

/**
 * Manages the selection of Bingo cards
 * 
 * Refactoring prompt:
 * https://chatgpt.com/share/679fe3b1-64b8-8004-bbd5-65cecf70b881
 */
public class CardManager {
    public static final int MAX_CARDS = 4;
    private List<BingoCard> cards;
    private List<BingoCard> chosenCards;

    /**
     * Constructs a CardManager with a list of Bingo cards
     * 
     * @param cards The list of all Bingo cards
     */
    public CardManager(List<BingoCard> cards) {
        this.cards = cards;
        this.chosenCards = new ArrayList<>();
    }

    /**
     * Resets the chosen cards list
     */
    public void reset() {
        chosenCards.clear();
    }

    /**
     * Gets the chosen cards
     */
    public List<BingoCard> getChosenCards() {
        return chosenCards;
    }

    /**
     * Gets all the cards
     * 
     * @return The list of all Bingo cards
     */
    public List<BingoCard> getCards() {
        return cards;
    }

    /**
     * Gets the maximum number of cards that can be chosen
     * 
     * @return The maximum number of cards
     */
    public int getMaxChosenCards() {
        return Math.min(MAX_CARDS, cards.size());
    }

    /**
     * Randomly selects cards to play
     * 
     * @param numCardsToPlay The number of cards to select
     */
    public void randomSelect(int numCardsToPlay) {
        List<Integer> cardIndices = new ArrayList<>();
        for (int i = 1; i <= cards.size(); i++) {
            cardIndices.add(i);
        }

        for (int i = cardIndices.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int temp = cardIndices.get(i);
            cardIndices.set(i, cardIndices.get(j));
            cardIndices.set(j, temp);
        }

        for (int i = 0; i < numCardsToPlay; i++) {
            addChosenCard(cardIndices.get(i));
        }
    }

    /**
     * Allows manual selection of cards
     * 
     * @param scanner        The scanner to read user input
     * @param numCardsToPlay The number of cards to select
     */
    public void manualSelect(Scanner scanner, int numCardsToPlay) {
        displayCards();
        System.out.println("Please choose " + numCardsToPlay + " different card(s) to play (1-" + cards.size() + ", separated by spaces):");
        String[] chosenCardIndices = scanner.nextLine().split(" ");

        while (chosenCards.size() < numCardsToPlay) {
            for (String index : chosenCardIndices) {
                try {
                    addChosenCard(Integer.parseInt(index));
                } catch (Exception e) {
                    System.out.println("Invalid input: " + index);
                }
            }

            if (chosenCards.size() < numCardsToPlay) {
                System.out.println("Please choose " + (numCardsToPlay - chosenCards.size()) + " more card(s):");
                chosenCardIndices = scanner.nextLine().split(" ");
            }
        }
    }

    /**
     * Displays the chosen cards
     */
    private void displayCards() {
        // Use the horizontal print function to display the cards four per row
        String[] cardStrings = new String[cards.size()];
        
        for (int i = 0; i < cards.size(); i++) {
            cardStrings[i] = "Card " + (i + 1) + ":\n" + cards.get(i);
        }

        for (int i = 0; i < cardStrings.length; i += 4) {
            List<String> cardStringsToPrint = new ArrayList<>();
            for (int j = i; j < Math.min(i + 4, cardStrings.length); j++) {
                cardStringsToPrint.add(cardStrings[j]);
            }
            Print.printHorizontal(cardStringsToPrint);
            System.out.println();
        }
    }

    /**
     * Adds a card to the chosen cards list
     * 
     * @param cardIndex The index of the card to add
     */
    private void addChosenCard(int cardIndex) {
        if (cardIndex < 1 || cardIndex > cards.size()) {
            throw new IllegalArgumentException("Invalid card index: " + cardIndex);
        }
        if (chosenCards.contains(cards.get(cardIndex - 1))) {
            throw new IllegalArgumentException("Card " + cardIndex + " has already been chosen.");
        }
        chosenCards.add(cards.get(cardIndex - 1));
    }
}
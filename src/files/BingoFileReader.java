package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import bingo.BingoCard;

import java.util.List;

/**
 * Bingo card file reader
 */
public class BingoFileReader {
    
    /**
     * Reads a list of Bingo cards from a file
     * @param fileName
     * @return the list of Bingo cards
     * @throws FileNotFoundException
     */
    public static List<BingoCard> readBingoCards(String fileName) throws FileNotFoundException {
        List<BingoCard> cards = new ArrayList<>();
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                // Consume empty lines and card id
                String line = scanner.nextLine().trim();

                if (line.isEmpty())
                    continue; // Skip empty lines

                try {
                    cards.add(readSingleCard(scanner));
                } catch (RuntimeException e) {
                    // Ignore invalid cards
                }
            }
        }

        return cards;
    }

    /**
     * Reads a single Bingo card
     * @param scanner
     * @return the Bingo card
     */
    private static BingoCard readSingleCard(Scanner scanner) {
        int[] numbers = new int[BingoCard.SIZE * BingoCard.SIZE];

        // Read the numbers separated by commas and new lines every 5 numbers
        for (int i = 0; i < BingoCard.SIZE; i++) {
            if (!scanner.hasNextLine()) {
                throw new RuntimeException("Unexpected end of file while reading a Bingo card.");
            }

            String[] numberStrings = scanner.nextLine().trim().split(",");

            if (numberStrings.length != BingoCard.SIZE) {
                discardRemainingLines(scanner, i);
                throw new RuntimeException("Invalid line format: Expected " + BingoCard.SIZE + " numbers, but got "
                        + numberStrings.length);
            }

            for (int j = 0; j < BingoCard.SIZE; j++) {
                try {
                    numbers[i * BingoCard.SIZE + j] = Integer.parseInt(numberStrings[j]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid number format in Bingo card: " + numberStrings[j]);
                }
            }
        }

        return new BingoCard(numbers);
    }

    /**
     * Discards all remaining lines (up to BingoCard.SIZE) from the scanner
     * This is to ensure that the scanner is not left in an invalid state
     * after reading an invalid card
     * @param scanner
     * @param currentLine
     */
    private static void discardRemainingLines(Scanner scanner, int currentLine) {
        for (int j = currentLine + 1; j < BingoCard.SIZE; j++) {
            if (!scanner.hasNextLine())
                break;
            scanner.nextLine();
        }
    }
}

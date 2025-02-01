package bingo;

/**
 * Bingo card class to store information about a Bingo card
 */
public class BingoCard {
    public static final int SIZE = 5;
    private int[] numbers = new int[SIZE * SIZE];

    /**
     * Creates a new Bingo card
     * @param numbers the numbers on the card
     */
    public BingoCard(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Gets the number at the specified row and column
     * @param row the row
     * @param col the column
     * @return the number
     */
    public int getNumber(int row, int col) {
        return numbers[row * SIZE + col];
    }

    /**
     * Returns a string representation of the card
     * @return the string representation
     */
    public String toString() {
        String result = "";

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result += getNumber(i, j) + " ";
            }

            result += "\n";
        }

        return result.substring(0, result.length() - 1);
    }
}

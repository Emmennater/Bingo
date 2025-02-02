package bingo;

/**
 * Bingo card class to store information about a Bingo card
 */
public class BingoCard {
    public static final int SIZE = 5;
    private int[] numbers = new int[SIZE * SIZE];
    private String id;

    /**
     * Creates a new Bingo card
     * 
     * @param numbers the numbers on the card
     */
    public BingoCard(int[] numbers, String id) {
        this.numbers = numbers;
        this.id = id;
    }

    /**
     * Gets the number at the specified row and column
     * 
     * @param row the row
     * @param col the column
     * @return the number
     */
    public int getNumber(int row, int col) {
        return numbers[row * SIZE + col];
    }

    /**
     * Returns the id of the card
     * 
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns a string representation of the card
     * Prompt: https://chatgpt.com/share/679fdf77-76c8-8004-a9c7-57911ed56355
     * 
     * @return the string representation
     */
    public String toString() {
        StringBuilder result = new StringBuilder();

        // Column headers
        result.append("    B   I   N   G   O\n");
        result.append("  ---------------------\n");

        for (int i = 0; i < SIZE; i++) {
            result.append("BINGO".charAt(i)).append(" |"); // Row headers

            for (int j = 0; j < SIZE; j++) {
                int num = getNumber(i, j);
                if (num == 0) { // Assuming 0 represents "Free" space
                    result.append(" F |");
                } else {
                    result.append(String.format("%2d |", num));
                }
            }

            result.append("\n");
            result.append("  ---------------------\n");
        }

        return result.toString();
    }

}

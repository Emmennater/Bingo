package helper;

/**
 * A class that handles different types of printing
 */
public class Print {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * Prints an error message in red
     * @param message
     */
    public static void error(String message) {
        System.err.println(ANSI_RED + message + ANSI_RESET);
    }
}

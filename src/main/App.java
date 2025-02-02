/**
 * @author Emmett Jaakkola
 * @version 1.0
 * @since 2025-01-02
 */

package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import files.BingoFileReader;
import bingo.BingoCard;
import bingo.Game;

/**
 * The entry point of the application
 */
public class App {
    public static void main(String[] args) {
        List<BingoCard> cards = new ArrayList<>();
        
        try {
            cards = BingoFileReader.readBingoCards("BingoCards.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Bingo cards file not found: 'BingoCards.txt'");
            e.printStackTrace();
        }

        new Game(cards);
    }
}
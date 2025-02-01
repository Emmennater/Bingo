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

public class App {
    public static void main(String[] args) {
        List<BingoCard> cards = new ArrayList<>();
        
        try {
            cards = BingoFileReader.readBingoCards("BingoCards.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (BingoCard card : cards) {
            System.out.println(card.toString() + "\n");
        }
    }
}
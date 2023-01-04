package flashcards.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class DeckManager extends Observable {

    private ArrayList<Deck> deckManager;

    public DeckManager() {
        this.deckManager = new ArrayList<Deck>();
    }

    /**
     * Adds a deck to the deck manager.
     *
     * @param Deck the deck to be added to the deck manager
     */
    public void addDeck(Deck Deck) {
        deckManager.add(Deck);
    }

    /**
     * Exports a deck to a file specified by a path.
     *
     * @param deck the deck to be exported
     * @param path the path specifying the file to which the deck is exported
     * @throws IOException if an error occurs while writing to the file
     */
    public void exportdeckManager(Deck deck, Path path) throws IOException {
        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(deck, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Imports a deck from a file specified by a path.
     *
     * @param filename the path specifying the file from which the deck is imported
     * @return the imported deck
     * @throws JsonSyntaxException   if the JSON file is not properly formatted
     * @throws JsonIOException       if an error occurs while reading the JSON file
     * @throws FileNotFoundException if the file specified by the path is not found
     */
    public Deck importdeckManager(String filename)
            throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        Gson gson = new Gson();
        Deck newDeck = gson.fromJson(new FileReader(filename), Deck.class);
        return newDeck;
    }

    /**
     * Saves the deck manager to a file.
     */
    public void save() {
        String filename = "decks.json";
        Path path = Paths.get(filename);
        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(deckManager, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a deck at a specified index in the deck manager.
     *
     * @param index the index of the deck to retrieve
     * @return the deck at the specified index
     */
    public Deck getDeck(int index) {
        return deckManager.get(index);
    }

    /**
     * Gets a card at a specified deck in the deck manager.
     *
     * @param index the index of the deck to retrieve
     * @return the deck at the specified index
     */
    public Card getCard(int index, int cardIndex) {
        return deckManager.get(index).getCard(cardIndex);
    }

    /**
     * Removes a deck at a specified index in the deck manager.
     *
     * @param index the index of the deck to remove
     */
    public void removeDeck(int index) {
        deckManager.remove(index);
    }

    /**
     * Returns the size of the deckManager.
     * 
     * @return the size of the deckManager
     */
    public int getDeckManagerSize() {
        return deckManager.size();
    }

    /**
     * Sets the value of the deckManager to the given ArrayList of Decks.
     * 
     * @param deckManager the ArrayList of Decks to set as the new deckManager
     */
    public void setDeckManager(ArrayList<Deck> deckManager) {
        this.deckManager = deckManager;
    }

}

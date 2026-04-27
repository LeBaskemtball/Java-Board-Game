package MODEL.Table;

import MODEL.Cards.Card;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * The Board class represents the game board, which consists of multiple paths
 * and a deck of cards. It provides methods for managing paths, cards, and
 * shuffling the deck.
 */
public class Board{
    private List<Path> paths;   //List of the paths
    private List<Card> deck;    //List of the card deck

    /**
     * Constructor for creating a new board with empty paths and deck.
     */
    public Board(){
        paths = new ArrayList<>();
        deck = new ArrayList<>();
    }

    public List<Path> getPaths(){
        return paths;
    }
    public List<Card> getDeck(){
        return deck;
    }

    /**
     * Adds a path to the board.
     *
     * @param path The path to be added to the board.
     */
    public void addPath(Path path){
        paths.add(path);
    }

    /**
     * Adds a card to the deck.
     *
     * @param card The card to be added to the deck.
     */
    public void addCard(Card card){
        deck.add(card);
    }

    /**
     * Gets the size of the deck.
     *
     * @return The size of the deck.
     */
    public int getDeckSize(){
        return deck.size() - 1;
    }

    /**
     * Shuffles the deck of cards.
     */
    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    /**
     * Draws a card from the deck.
     *
     * @return The drawn card, or null if the deck is empty.
     */
    public Card drawCard(){
        if(!deck.isEmpty()){
            return deck.remove(0);
        }
        return null;
    }
}

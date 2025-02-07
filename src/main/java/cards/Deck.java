package cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> cards;

    // Constructs a Deck object with 52 cards
    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String value : values) {
                cards.add(new Card(suit, value));
            }
        }
    }

    // Shuffles the deck of cards
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Deals a card from the deck
    public Card dealCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        } else {
            return null;
        }
    }
}

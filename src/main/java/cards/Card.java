package cards;

/**
 * This class represents a playing card with a suit and a value.
 */
public class Card {
    private final String suit;
    private final String value;

    // Constructs a Card with specific suit and value
    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    // Returns the suit of the card
    public String getSuit() {
        return suit;
    }

    // Returns the value of the card
    public String getValue() {
        return value;
    }

    // Returns a string representation of the card
    @Override
    public String toString() {
        return value + " of " + suit;
    }
}
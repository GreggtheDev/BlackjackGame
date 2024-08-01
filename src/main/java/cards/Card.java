package blackjack.cards;

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
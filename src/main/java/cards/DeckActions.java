package blackjack.cards;

/**
 * This class demonstrates basic deck operations such as shuffling and dealing cards.
 */
public class DeckActions {
    public static void main(String[] args) {
        // Create and shuffle a deck
        Deck deck = new Deck();
        deck.shuffle();

        // Deal and display five cards
        for (int i = 0; i < 5; i++) {
            Card card = deck.dealCard();
            System.out.println(card);
        }
    }
}


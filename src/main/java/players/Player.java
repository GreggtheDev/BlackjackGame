package blackjack.players;

import blackjack.cards.Card;
import java.util.ArrayList;

public class Player {
    private final String name;
    private int balance;
    private final ArrayList<Card> hand;

    // Constructs a Player object with a specified name and balance
    public Player(String name, int balance) {
        this.name = name;
        this.balance = balance;
        this.hand = new ArrayList<>();
    }

    // Getter to return name
    public String getName() {
        return name;
    }

    // Getter to return balance
    public int getBalance() {
        return balance;
    }

    // Decreases Player balance by specified amount
    public void decreaseBalance(int amount) {
        balance -= amount;
    }

    // Increases Player balance by specified amount
    public void increaseBalance(int amount) {
        balance += amount;
    }

    // Adds a card to Player's hand
    public void addCardToHand(Card card) {
        hand.add(card);
    }

    // Returns Player's current hand
    public ArrayList<Card> getHand() {
        return hand;
    }

    // Calculates the value of Player's hand
    public int calculateHandValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            String cardValue = card.getValue();
            if (cardValue.equals("Jack") || cardValue.equals("Queen") || cardValue.equals("King")) {
                value += 10;
            } else if (cardValue.equals("Ace")) {
                aceCount++;
                value += 11;
            } else {
                value += Integer.parseInt(cardValue);
            }
        }

        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        return value;
    }

    // Clears Player's hand of cards
    public void clearHand() {
        hand.clear();
    }
}

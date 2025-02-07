package game;

import game.Deck;
import game.Player;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class contains the main method to run the game of Blackjack.
 * It manages the game loop, player interactions, and game logic.
 */
public class GameRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Blackjack!");

        // Play background music
        blackjack.game.PlayMusic.playMusic("CasinoJazz.wav");

        // Initialize player and dealer
        Player player = new Player("Player 1", 100);
        Player dealer = new Player("Dealer", 0);
        Deck deck = new Deck();
        deck.shuffle();

        // Set the default bet amount
        final int DEFAULT_BET = 10;

        while (true) {
            player.clearHand();
            dealer.clearHand();

            System.out.println("Your current balance: $" + player.getBalance());
            int bet = DEFAULT_BET;

            // Ensure the player has enough balance to place the default bet
            if (player.getBalance() < bet) {
                System.out.println("You don't have enough balance to place the default bet.");
                break;
            }

            player.decreaseBalance(bet);
            System.out.println("Dealing cards...");

            // Deal initial cards
            player.addCardToHand(deck.dealCard());
            player.addCardToHand(deck.dealCard());
            dealer.addCardToHand(deck.dealCard());
            dealer.addCardToHand(deck.dealCard());

            // Show initial hands
            System.out.println("Your hand: " + player.getHand() + " (value: " + player.calculateHandValue() + ")");
            System.out.println("Dealer's visible card: " + dealer.getHand().get(0));

            // Player's turn
            boolean playerBust = false;
            boolean doubledDown = false;
            while (true) {
                if (player.calculateHandValue() > 21) {
                    System.out.println("You bust!");
                    playerBust = true;
                    break;
                }
                System.out.print("Do you want to hit, stand, or double down? (hit/stand/double): ");
                String action = scanner.next();
                if (action.equalsIgnoreCase("stand")) {
                    break;
                } else if (action.equalsIgnoreCase("hit")) {
                    player.addCardToHand(deck.dealCard());
                    System.out.println("Your hand: " + player.getHand() + " (value: " + player.calculateHandValue() + ")");
                } else if (action.equalsIgnoreCase("double") && player.getBalance() >= bet) {
                    player.decreaseBalance(bet);
                    bet *= 2;
                    player.addCardToHand(deck.dealCard());
                    System.out.println("Your hand: " + player.getHand() + " (value: " + player.calculateHandValue() + ")");
                    doubledDown = true;
                    break;
                } else {
                    System.out.println("Invalid action or insufficient balance for double down. Please enter 'hit', 'stand', or 'double'.");
                }
            }

            // Dealer's turn
            if (!playerBust) {
                System.out.println("Dealer's turn.");
                System.out.println("Dealer's hand: " + dealer.getHand() + " (value: " + dealer.calculateHandValue() + ")");
                while (dealer.calculateHandValue() < 17) {
                    dealer.addCardToHand(deck.dealCard());
                    System.out.println("Dealer's hand: " + dealer.getHand() + " (value: " + dealer.calculateHandValue() + ")");
                }
            }

            // Determine the winner
            int playerHandValue = player.calculateHandValue();
            int dealerHandValue = dealer.calculateHandValue();
            if (playerBust || (dealerHandValue <= 21 && dealerHandValue > playerHandValue)) {
                System.out.println("Dealer wins!");
            } else if (dealerHandValue > 21 || playerHandValue > dealerHandValue) {
                System.out.println("You win!");
                player.increaseBalance(bet * 2); // Player wins double the bet amount
            } else {
                System.out.println("It's a tie!");
                player.increaseBalance(bet);
            }

            // Show final balances
            System.out.println("Your current balance: $" + player.getBalance());

            // Ask if the player wants to play again
            while (true) {
                System.out.print("Do you want to play again? (yes/no): ");
                String response = scanner.next();
                if (response.equalsIgnoreCase("yes")) {
                    deck.shuffle();
                    break;
                } else if (response.equalsIgnoreCase("no")) {
                    System.out.println("Thanks for playing! Your final balance: $" + player.getBalance());
                    scanner.close();
                    return;
                } else {
                    System.out.println("Invalid response. Please enter 'yes' or 'no'.");
                }
            }
        }
    }
}

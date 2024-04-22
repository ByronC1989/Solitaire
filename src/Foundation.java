import java.util.ArrayList;
import java.util.List;

public class Foundation {
    // Initialize the foundation with four piles, one for each suit.
    private final List<List<Card>> piles;

    public Foundation() {
        piles = new ArrayList<>(4); // One for each suit
        for (int i = 0; i < 4; i++) {
            piles.add(new ArrayList<>()); // Create an empty list for each suit
        }
    }

    // Check if a card can be placed on a specific suit pile.
    // A card can be placed if it's an Ace (when the pile is empty) or one rank
    // higher than the top card of the same suit.
    public boolean canPlace(Card card, Suit suit) {
        int index = suit.ordinal();
        List<Card> pile = piles.get(index);
        if (pile.isEmpty() && card.getRank() == Rank.ACE.getRank()) {
            return true; // Allow Ace on an empty pile
        } else if (!pile.isEmpty()) {
            Card topCard = pile.get(pile.size() - 1);
            // Check if the card is the next rank up and the same suit
            return card.getSuit() == topCard.getSuit() && card.getRank() == topCard.getRank() + 1;
        }
        return false;
    }

    // Place a card on the correct suit pile if the move is valid.
//    public void place(Card card, Suit suit) {
//        if (canPlace(card, suit)) {
//            piles.get(suit.ordinal()).add(card); // Add the card to the corresponding pile
//        } else {
//            throw new IllegalArgumentException("Cannot place card here"); // Throw an error if the move is not valid
//        }
//    }

    public void place(Card card, Suit suit) {
        if (canPlace(card, suit)) {
            piles.get(suit.ordinal()).add(card);
            System.out.println("Placed " + card + " on pile " + (suit.ordinal() + 1));
        } else {
            System.out.println("Invalid move: " + card + " cannot be placed on pile " + (suit.ordinal() + 1));
        }
    }

    // Count the total number of cards in all foundation piles.
    // This can be useful for tracking progress or determining if the game is won.
    public int count() {
        int total = 0;
        for (List pile : piles) {
            total += pile.size();
        }
        return total;
    }

    public void printFoundation() {
        for (int i = 0; i < piles.size(); i++) {
            List<Card> pile = piles.get(i);
            System.out.println("Pile " + (i + 1) + ":");
            if (pile.isEmpty()) {
                System.out.println("  (empty)");
            } else {
                for (Card card : pile) {
                    System.out.println("  " + card);
                }
            }
        }
    }

}

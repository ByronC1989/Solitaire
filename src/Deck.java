import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Deck {

    private final ArrayList<Card> cards;

    public Deck() {
        // create Card Array
        cards = new ArrayList<Card>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(rank, suit);
                card.flipCard(); // remove this after testing so that cards are facedown.
                cards.add(card);
            }
        }
    }

    public void emptyDeck() {
        cards.clear();
    }

    public void shuffleDeck() {
        // Use collections shuffle method come up with our own algorithm?
        Collections.shuffle(cards);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public String printDeck() {
        String str = "";
        for (Card c : cards) {
            str += c.toString() + "\n";
        }
        return str;
    }

    public Card drawCard() {
        // this method can return null if the deck is empty.
        if (deckSize() > 0) {
            return cards.remove(0);
        }
        return null;
    }

    public int deckSize() {
        return cards.size();
    }
}
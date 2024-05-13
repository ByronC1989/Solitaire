import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Deck implements CardPile {

    private final ArrayList<Card> cards;

    public Deck() {
        // create Card Array
        cards = new ArrayList<Card>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(rank, suit);
                //card.flipCard(); // remove this after testing so that cards are facedown.
                cards.add(card);
                Collections.shuffle(cards);
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

    @Override
    public Card topCard() {
        return cards.get(0);
    }

    @Override
    public void addCard(Card card) {
        cards.add(0,card);
    }

    public int deckSize() {
        return cards.size();
    }

    public void moveCardsToTalon(Talon talon, int numOfCards){
        for(int i = 0; i < numOfCards; i++){
            if(deckSize() > 0){
                Card card = drawCard();
                talon.addCard(card);
            }
        }
    }
}
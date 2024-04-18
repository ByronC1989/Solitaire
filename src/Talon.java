import java.util.ArrayList;

public class Talon implements CardPile{

    private final ArrayList<Card> cards;

    public Talon() {
        // create Card Array
        cards = new ArrayList<Card>();
    }

    @Override
    public int deckSize() {
        return cards.size();
    }

    @Override
    public Card drawCard() {
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

    public void moveCardsToDeck(Deck deck, int numOfCards){
        for(int i = 0; i < numOfCards; i++){
            if(deckSize() > 0){
                Card card = drawCard();
                deck.addCard(card);
            }
        }
    }

    public String printDeck() {
        String str = "";
        for (Card c : cards) {
            str += c.toString() + "\n";
        }
        return str;
    }
}

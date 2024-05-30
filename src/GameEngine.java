import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameEngine {
    private Deck deck;
    private Foundation foundation;
    private Tableau tableau;
    private Talon talon;


    GameEngine(){
        this.deck = new Deck();
        this.talon = new Talon();
        this.foundation = new Foundation();
        this.tableau = new Tableau();
    }

    public Deck getDeck() {
        return deck;
    }

    public Foundation getFoundation() {
        return foundation;
    }

    public Tableau getTableau() {
        return tableau;
    }

    public Talon getTalon() {
        return talon;
    }

    public boolean moveCard(Card card, String source) {

        // Card tempCard = card; // if card exists after logic and is from the talon return the card.

            if(foundation.canPlace(card, Suit.HEARTS)) {

                foundation.place(card, Suit.HEARTS);
                System.out.println(card + " moved to HEARTS foundation");
                return true;

            } else if(foundation.canPlace(card, Suit.DIAMONDS)) {

                foundation.place(card, Suit.DIAMONDS);
                System.out.println(card + " moved to DIAMONDS foundation");
                return true;

            } else if(foundation.canPlace(card, Suit.CLUBS)) {

                foundation.place(card, Suit.CLUBS);
                System.out.println(card + " moved to CLUBS foundation");
                return true;

            } else if(foundation.canPlace(card, Suit.SPADES)) {

                foundation.place(card, Suit.SPADES);
                System.out.println(card + " moved to SPADES foundation");
                return true;

            } else {

                if(moveCardTableau(card)){
                    return true;
                }

            }

            if(card != null && source.equals("talon")){
                // check if card exists still for testing
                System.out.println("Returned to talon " + card);
                talon.addCard(card); // return card to talon
            }

            return false;
    }

    public boolean moveCardTableau(Card tempCard) {

        for(int i = 0; i < 7; i++){

            if (tableau.addCard(i, tempCard)){
                return true;
            }

        }
        return false;
    }

    public void cleanUp() {
        System.out.println("Cleaned up board");
    }


}

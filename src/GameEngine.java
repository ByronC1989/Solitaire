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

    public void moveCard(Card card, String source) {

        System.out.println(source);
        System.out.println(card);
        Card tempCard = card; // if card exists after logic and is from the talon return the card.

        if(foundation.canPlace(tempCard, Suit.HEARTS)) {

           foundation.place(tempCard, Suit.HEARTS);
           tempCard = null;
           System.out.println(card + "moved to HEARTS foundation");

        } else if(foundation.canPlace(tempCard, Suit.DIAMONDS)) {

            foundation.place(tempCard, Suit.DIAMONDS);
            tempCard = null;
            System.out.println(card + "moved to DIAMONDS foundation");

        } else if(foundation.canPlace(tempCard, Suit.CLUBS)) {

            foundation.place(tempCard, Suit.CLUBS);
            tempCard = null;
            System.out.println(card + "moved to CLUBS foundation");

        } else if(foundation.canPlace(tempCard, Suit.SPADES)) {

            foundation.place(card, Suit.SPADES);
            tempCard = null;
            System.out.println(card + "moved to SPADES foundation");

        }

        if(tempCard != null && source.equals("talon")){
            talon.addCard(tempCard); // return card to talon
        }

        foundation.printFoundation();

    }

}

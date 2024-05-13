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

        if(foundation.canPlace(card, Suit.HEARTS)) {

           foundation.place(card, Suit.HEARTS);
           System.out.println(card + "moved to HEARTS foundation");

        } else if(foundation.canPlace(card, Suit.DIAMONDS)) {

            foundation.place(card, Suit.DIAMONDS);
            System.out.println(card + "moved to DIAMONDS foundation");

        } else if(foundation.canPlace(card, Suit.CLUBS)) {

            foundation.place(card, Suit.CLUBS);
            System.out.println(card + "moved to CLUBS foundation");

        } else if(foundation.canPlace(card, Suit.SPADES)) {

            foundation.place(card, Suit.SPADES);
            System.out.println(card + "moved to SPADES foundation");

        }

        foundation.printFoundation();

    }

}

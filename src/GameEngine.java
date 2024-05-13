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

        } else {

            for(int i = 0; i < 7; i++){

                if (tempCard == null){
                    break; // if a card is added to the tableau tempCard is made null
                }

                tempCard = tableau.addCard(i, tempCard);
            }
        }

        if(tempCard != null && source.equals("talon")){
            // check if card exists still for testing
            System.out.println("Returned to talon " + tempCard);
            talon.addCard(tempCard); // return card to talon
        }

    }

    public void cleanUp() {
        System.out.println("Cleaned up board");
    }


}

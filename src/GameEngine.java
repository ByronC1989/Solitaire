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
}

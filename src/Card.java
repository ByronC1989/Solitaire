import javax.swing.*;
import java.awt.*;

public class Card {

    private final Suit suit;
    private final Rank rank;
    // Image of Front and Back of Card add some images need to build GUI to display
    private final String frontOfCard;
    private final String backOfCard;
    private boolean isFaceDown;
    private boolean isRed;

    public Card(Rank rank, Suit suit) {
        this.suit = suit;
        this.rank = rank;
        isFaceDown = true;
        isRed = false;
        // determines if the card is red or black
        if (suit == Suit.HEARTS || suit == Suit.DIAMONDS) {
            this.isRed = true;
        }
        this.frontOfCard = "Solitaire/src/Images/" + rank.displayRank() + "_of_" + suit.displaySuit() + ".png";
        this.backOfCard = "Solitaire/src/Images/01_back.png";
    }

    public String getSuit() {
        return suit.displaySuit();
    }

    public int getRank() {
        return rank.getRank();
    }

    public boolean getIsRed() {
        return isRed;
    }

    public boolean getIsFaceDown() {
        return isFaceDown;
    }

    public void flipCard() {
        // flips isFaceDown value to true and false
        // flipping the card
        isFaceDown = !isFaceDown;
    }

    @Override
    public String toString() {
        String cardText = "";
        if (isFaceDown) {
            cardText = "Card is Face Down";
        } else {
            cardText += rank.displayRank() + " of "
                    + suit.displaySuit();
        }
        return cardText;
    }

    public ImageIcon displayCard() {
        // return filepath for cards, scaled, as an imageIcon
        int width = 72;
        int height = 90;
        ImageIcon card;
        if (isFaceDown) {
            //create ImageIcon with path to backOfCard
            card = new ImageIcon(this.backOfCard);
            Image img1 = card.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            card = new ImageIcon(img1);
        } else {
            //create ImageIcon with path to frontOfCard
            card = new ImageIcon(this.frontOfCard);
            Image img1 = card.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            card = new ImageIcon(img1);
        }
        return card;
    }



}
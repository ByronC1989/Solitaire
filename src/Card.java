public class Card {

    private Suit suit;
    private Rank rank;
    // Image of Front and Back of Card add some images need to build GUI to display
    // private Image frontOfCard;
    // private Image backOfCard;
    private boolean isFaceDown;

    public Card(Rank rank, Suit suit) {
        this.suit = suit;
        this.rank = rank;
        isFaceDown = true;
    }

    public String getSuit() {
        return suit.displaySuit();
    }

    public int getRank() {
        return rank.getRank();
    }

    public boolean isFaceDown() {
        return isFaceDown;
    }

    public void flipCard(){
        // flips isFaceDown value to true and false
        // flipping the card
        isFaceDown = !isFaceDown;
    }

    @Override
    public String toString(){
        String cardText = "";
        if(isFaceDown) {
            cardText = "Card is Face Down";
        } else {
            cardText += rank.displayRank() + " of "
                    + suit.displaySuit();
        }
        return cardText;
    }


}

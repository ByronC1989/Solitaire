public class Card {

    private final Suit suit;
    private final Rank rank;
    // Image of Front and Back of Card add some images need to build GUI to display
    // private Image frontOfCard;
    // private Image backOfCard;
    private boolean isFaceDown;
    private boolean isRed;

    public Card(Rank rank, Suit suit) {
        this.suit = suit;
        this.rank = rank;
        isFaceDown = true;

        // determines if the card is red or black
        if (suit == Suit.HEARTS || suit == Suit.DIAMONDS) {
            this.isRed = true;
        }
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

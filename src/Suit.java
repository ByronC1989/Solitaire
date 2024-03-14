public enum Suit {
    HEARTS("Hearts"),
    SPADES("Spades"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs");

    private final String suitString;

    Suit(String suitString){
        // Constructor for Suit enum
        this.suitString = suitString;
    }

    public String displaySuit(){
        // access the value of the rank
        return suitString;
    }

}

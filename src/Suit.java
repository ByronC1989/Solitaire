public enum Suit {
    HEARTS("hearts"),
    SPADES("spades"),
    DIAMONDS("diamonds"),
    CLUBS("clubs");

    private final String suitString;

    Suit(String suitString) {
        // Constructor for Suit enum
        this.suitString = suitString;
    }

    public String displaySuit() {
        // access the value of the rank
        return suitString;
    }

}
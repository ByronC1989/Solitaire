public enum Rank {
    // Values for each Card Rank.
    ACE(1, "Ace"), TWO(2, "Two"), THREE(3, "Three"),
    FOUR(4, "Four"), FIVE(5, "Five"), SIX(6, "Six"),
    SEVEN(7, "Seven"), EIGHT(8, "Eight"), NINE(9, "Nine"),
    TEN(10, "Ten"), JACK(11, "Jack"), QUEEN(12, "Queen"),
    KING(13, "King");

    private final int rankValue;
    private final String rankText;

    private Rank(int rankValue, String rankText) {
        // Constructor for Rank enum
        this.rankValue = rankValue;
        this.rankText = rankText;
    }

    public int getRank() {
        // access the value of the rank.
        return rankValue;
    }

    public String displayRank(){
        return rankText;
    }
}

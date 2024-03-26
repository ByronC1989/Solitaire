public enum Rank {
    // Values for each Card Rank.
    ACE(1, "ace"), TWO(2, "2"), THREE(3, "3"),
    FOUR(4, "4"), FIVE(5, "5"), SIX(6, "6"),
    SEVEN(7, "7"), EIGHT(8, "8"), NINE(9, "9"),
    TEN(10, "10"), JACK(11, "jack"), QUEEN(12, "queen"),
    KING(13, "king");

    private final int rankValue;
    private final String rankText;

    Rank(int rankValue, String rankText) {
        // Constructor for Rank enum
        this.rankValue = rankValue;
        this.rankText = rankText;
    }

    public int getRank() {
        // access the value of the rank.
        return rankValue;
    }

    public String displayRank() {
        return rankText;
    }
}
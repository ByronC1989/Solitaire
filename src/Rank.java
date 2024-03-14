public enum Rank {
    // Values for each Card Rank.
    ACE(1), TWO(2), THREE(3), FOUR(4),
    FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
    NINE(9), TEN(10), JACK(11), QUEEN(12),
    KING(13);

    private final int rankValue;

    private Rank(int rankValue) {
        // Constructor for Rank enum
        this.rankValue = rankValue;
    }

    public int getRank() {
        // access the value of the rank.
        return rankValue;
    }
}

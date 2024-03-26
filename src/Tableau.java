import java.util.ArrayList;
import java.util.List;

public class Tableau {

    private List<List<Card>> columns;

    public Tableau() {
        columns = new ArrayList<>();
        // initalize 7 piles
        for (int i = 0; i < 7; i++) {
            columns.add(new ArrayList<>());
        }
    }

    public void initialize(Deck deck) {
        for (int i = 0; i < columns.size(); i++) {
            for (int j = 0; j <= i; j++) {
                Card card = deck.drawCard();
                if (j == i) {
                    card.flipCard(); // Flip the last card in each column to face up (implement on swing to show the
                                     // card face up)
                }
                columns.get(i).add(card);
            }
        }
    }

    public List<Card> getColumn(int index) {
        // retrieve the column at the given index
        if (index >= 0 && index < columns.size()) {
            return columns.get(index);
        }
        return null;
    }

    // Get the top card of the column for checking if the card can be moved
    // onto it. For example, if the card is a King, it can only be moved onto
    // an empty column so 'peekTopCard' will be used to check if the column
    // is empty.

    public Card peekTopCard(int columnIndex) {
        List<Card> column = getColumn(columnIndex);
        if (column != null && !column.isEmpty()) {
            return column.get(column.size() - 1);
        }
        return null;
    }

    // Remove the card(s) from the column when it is moved to another column and
    // return
    // a sublist of cards from the column.
    public List<Card> removeCards(int columnIndex, int fromIndex) {
        List<Card> column = getColumn(columnIndex);
        if (column == null || fromIndex < 0 || fromIndex >= column.size()) {
            return null;
        }

        List<Card> removedCards = new ArrayList<>(column.subList(fromIndex, column.size()));
        column.subList(fromIndex, column.size()).clear();
        return removedCards;
    }

    // Adds list of cards to the end of a specified column. Functionaliy is for
    // moving cards from one column to another
    public void addCards(int columnIndex, List<Card> cards) {
        List<Card> column = getColumn(columnIndex);
        if (column != null && cards != null) {
            column.addAll(cards);
        }
    }

    // Check if the card can be moved to the column
    // USE FOR DEBUGGING/TEXT REPRESENTATION BEFORE SWING IMPLEMENTATION
    public void printTableau() {
        for (int i = 0; i < columns.size(); i++) {
            System.out.println("Column " + (i + 1) + ":");
            for (Card card : columns.get(i)) {
                System.out.println(card);
            }
        }
    }
}
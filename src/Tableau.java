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
                                     // card face up
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

    // Only accept cards of the opposite colour in the tableau.
    public void addCard(int columnIndex, Card card) {
        List<Card> column = getColumn(columnIndex);
        // if the tableau is empty and the card being added is a king add the card.
        if(column.isEmpty() && card.getRank() == 13){
            column.add(card);
        }
        // if card is less then the card before it.
        if(peekTopCard(columnIndex) != null && peekTopCard(columnIndex).getRank() - 1 == card.getRank()) {
            // if the card is red only add a black card.
            if (peekTopCard(columnIndex) != null) {
                if(peekTopCard(columnIndex).getIsRed()){
                    if(!card.getIsRed()){
                        column.add(card);
                    }
                    // if the card is black only add a red card.
                } else if(!peekTopCard(columnIndex).getIsRed()) {
                    if(card.getIsRed()){
                        column.add(card);
                    }
                }
            }
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

    // demo method some comments
    public void printFirstTableau() {
            System.out.println("Column " + (1) + ":");
            for (Card card : columns.getFirst()) {
                System.out.println(card);
            }
    }
}
import java.util.ArrayList;
import java.util.List;

public class Tableau {

    private List<List<Card>> columns;

    public Tableau() {
        columns = new ArrayList<>();
        // initialize 7 piles
        for (int i = 0; i < 7; i++) {
            columns.add(new ArrayList<>());
        }
    }

    public void initialize(Deck deck) {
        for (int i = 0; i < columns.size(); i++) {
            for (int j = 0; j <= i; j++) {
                Card card = deck.drawCard();
                if (j == i) {
                    card.flipCard(); // Flip the last card in each column to face up
                }
                columns.get(i).add(card);
            }
        }
    }

    public List<Card> getColumn(int index) {
        if (index >= 0 && index < columns.size()) {
            return columns.get(index);
        }
        return null;
    }

    public Card peekTopCard(int columnIndex) {
        List<Card> column = getColumn(columnIndex);
        if (column != null && !column.isEmpty()) {
            return column.get(column.size() - 1);
        }
        return null;
    }

    public Card removeTopCard(int columnIndex) {
        List<Card> column = getColumn(columnIndex);
        if (column != null && !column.isEmpty()) {
            Card card = column.remove(column.size() - 1);
            System.out.println("Removed top card: " + card + " from column: " + columnIndex);
            return card;
        }
        return null;
    }

    public List<Card> removeCards(int columnIndex, int fromIndex) {
        List<Card> column = getColumn(columnIndex);
        if (column != null && fromIndex >= 0 && fromIndex < column.size()) {
            List<Card> removedCards = new ArrayList<>(column.subList(fromIndex, column.size()));
            column.subList(fromIndex, column.size()).clear();
            System.out.println("Removed cards from index " + fromIndex + " from column: " + columnIndex);
            return removedCards;
        }
        return null;
    }

    public void addCards(int columnIndex, List<Card> cards) {
        List<Card> column = getColumn(columnIndex);
        if (column != null && cards != null) {
            column.addAll(cards);
            System.out.println("Added cards: " + cards + " to column: " + columnIndex);
        }
    }

    public boolean addCard(int columnIndex, Card card) {
        List<Card> column = getColumn(columnIndex);
        if (column.isEmpty() && card.getRank() == 13) { // King
            column.add(card);
            System.out.println("Added King card: " + card + " to empty column: " + columnIndex);
            return true;
        }
        if (peekTopCard(columnIndex) != null && peekTopCard(columnIndex).getRank() - 1 == card.getRank()) {
            if (peekTopCard(columnIndex).getIsRed() && !card.getIsRed()) {
                column.add(card);
                System.out.println("Added card: " + card + " to column: " + columnIndex);
                return true;
            } else if (!peekTopCard(columnIndex).getIsRed() && card.getIsRed()) {
                column.add(card);
                System.out.println("Added card: " + card + " to column: " + columnIndex);
                return true;
            }
        }
        return false;
    }

    public void printTableau() {
        for (int i = 0; i < columns.size(); i++) {
            System.out.println("Column " + (i + 1) + ":");
            for (Card card : columns.get(i)) {
                System.out.println(card);
            }
        }
    }

    public void printFirstTableau() {
        System.out.println("Column " + (1) + ":");
        for (Card card : columns.get(0)) {
            System.out.println(card);
        }
    }
}

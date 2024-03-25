import java.util.ArrayList;
import java.util.List;

public class Tableau {

    private List<List<Card>> columns;

    public Tableau() {
        columns = new ArrayList<>();
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
        return null; // Or throw an exception
    }

    public Card peekTopCard(int columnIndex) {
        List<Card> column = getColumn(columnIndex);
        if (column != null && !column.isEmpty()) {
            return column.get(column.size() - 1);
        }
        return null; // Or throw an exception
    }

    public List<Card> removeCards(int columnIndex, int fromIndex) {
        List<Card> column = getColumn(columnIndex);
        if (column == null || fromIndex < 0 || fromIndex >= column.size()) {
            return null; // Or throw an exception
        }

        List<Card> removedCards = new ArrayList<>(column.subList(fromIndex, column.size()));
        column.subList(fromIndex, column.size()).clear();
        return removedCards;
    }

    public void addCards(int columnIndex, List<Card> cards) {
        List<Card> column = getColumn(columnIndex);
        if (column != null && cards != null) {
            column.addAll(cards);
        }
    }

    public void printTableau() {
        for (int i = 0; i < columns.size(); i++) {
            System.out.println("Column " + (i + 1) + ":");
            for (Card card : columns.get(i)) {
                System.out.println(card);
            }
        }
    }
}

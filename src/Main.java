import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomePage();
            }
        });


        Card card = new Card(Rank.ACE, Suit.DIAMONDS);

        // flipping a card.
        System.out.println(card);
        card.flipCard();
        System.out.println(card);
        card.flipCard();
        System.out.println(card);




    }
}
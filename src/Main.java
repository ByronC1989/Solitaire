public class Main {
        public static void main(String[] args) {

                Card card = new Card(Rank.KING, Suit.SPADES);
//
//                System.out.println(card.displayCard());
                card.flipCard();
//                System.out.println(card.displayCard());
                System.out.println(card.getRank());

                // flipping a card.
                // System.out.println(card);
                // card.flipCard();
                // System.out.println(card);
                // card.flipCard();
                // System.out.println(card);

                // Create deck of cards
                Deck deck = new Deck();
                Tableau tableau = new Tableau();

                deck.shuffleDeck();

                tableau.initialize(deck);

                tableau.removeCards(0,0);

                tableau.addCard(0, card);

                tableau.printTableau();

                // System.out.println("Deck Before Shuffling: ");
                // System.out.println(deck.printDeck());
                // System.out.println("\n Deck of Cards Contains: " + deck.getCards().size() + "
                // Cards");

//                 System.out.println("Deck After Shuffling: ");
//                 System.out.println(deck.printDeck());
//

                // System.out.println(deck.drawCard() + " was drawn from the deck");
                // System.out.println("Deck After drawing a card: ");
                // System.out.println(deck.printDeck());

                // UNCOMMENT BELOW TO TEST DRAWING EVERY CARD FROM THE DECK!

                // System.out.println("Deck contains: " + deck.deckSize() + " cards");
                //
                // int numOfCards = 0;
                //
                // while(deck.deckSize() > 0){
                // System.out.println(deck.drawCard() + " was drawn from the deck");
                // numOfCards++;
                // }
                //
                // System.out.println(numOfCards + " cards removed from the deck!");

        }
}
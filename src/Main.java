public class Main {
        public static void main(String[] args) {

                // Create deck of cards
                Deck deck = new Deck();
                Talon talon = new Talon();
                Tableau tableau = new Tableau();

                deck.shuffleDeck();

                System.out.println("StockPile:");
                System.out.println(deck.printDeck());

                System.out.println("Talon:");
                System.out.println(talon.printDeck());

                System.out.println("After moving Card to Talon from Stockpile");
                deck.moveCardsToTalon(talon, 5);

                System.out.println("StockPile:");
                System.out.println(deck.printDeck());

                System.out.println("Talon:");
                System.out.println(talon.printDeck());

                System.out.println("After moving Card to Stockpile from Talon");
                talon.moveCardsToDeck(deck, 5);

                System.out.println("StockPile:");
                System.out.println(deck.printDeck());

                System.out.println("Talon:");
                System.out.println(talon.printDeck());


//                tableau.initialize(deck);
//
//                tableau.removeCards(0,0);
//
//                tableau.addCard(0, card);
//
//                tableau.printTableau();

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
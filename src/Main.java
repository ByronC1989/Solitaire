public class Main {
        public static void main(String[] args) {

                GameEngine solitaire = new GameEngine();
                Board board = new Board(solitaire);
                board.setup();

                solitaire.getDeck().shuffleDeck();
                solitaire.getTableau().initialize(
                        solitaire.getDeck());

                solitaire.getTableau().printTableau();

                board.drawDeck();

//                // Create deck of cards
//                Deck deck = new Deck();
//                Talon talon = new Talon();
//                Tableau tableau = new Tableau();
//                Foundation foundation = new Foundation();
//
//                deck.shuffleDeck();
//                board.drawDeck();
//
//
//                System.out.println("Card remaining in deck: " + deck.deckSize());
//                tableau.initialize(deck);
//                System.out.println("Cards remaining in deck after initializing the Tableau: "  + deck.deckSize());
//
//                System.out.println("\nStockPile:");
//                System.out.println(deck.printDeck());
//
//                System.out.println("Talon:");
//                System.out.println(talon.printDeck());
//
//                System.out.println("After moving Card to Talon from Stockpile");
//                deck.moveCardsToTalon(talon, 5);
//
//                System.out.println("StockPile:");
//                System.out.println(deck.printDeck());
//
//                System.out.println("Talon:");
//                System.out.println(talon.printDeck());
//
//                System.out.println("After moving Card to Stockpile from Talon");
//                talon.moveCardsToDeck(deck, 5);
//
//                System.out.println("StockPile:");
//                System.out.println(deck.printDeck());
//
//                System.out.println("Talon:");
//                System.out.println(talon.printDeck());
//
//                // * Foundation Demo *//
//
//                // add ace card to foundation
//                // Expected output: Should be added
//                Card aceOfH = new Card(Rank.ACE, Suit.HEARTS);
//                aceOfH.flipCard();
//                System.out.println("\nAttempting to add " + aceOfH + " to foundation");
//
//                if (foundation.canPlace(aceOfH, Suit.HEARTS)) {
//                        System.out.println("Added Ace of Hearts to the foundation.");
//                } else {
//                        System.out.println("Could not add Ace of Hearts to the foundation.");
//                }
//                foundation.place(aceOfH, Suit.HEARTS);
//                foundation.printFoundation();
//
//                // add king card to foundation
//                // Expected output: Shouldn't be able to add since not in order
//                Card kingCard = new Card(Rank.KING, Suit.HEARTS);
//                kingCard.flipCard();
//                System.out.println("\nAttempting to add " + kingCard + " to foundation");
//                if (foundation.canPlace(kingCard, Suit.HEARTS)) {
//                        System.out.println("Added King of Hearts to the foundation");
//                } else {
//                        System.out.println("Could not add King of Hearts to the foundation.");
//                }
//                foundation.place(kingCard, Suit.HEARTS);
//                foundation.printFoundation();
//
//                // add two card to foundation
//                // Expected output: Should be placed ontop of Ace of hearts
//                Card twoOfH = new Card(Rank.TWO, Suit.HEARTS);
//                twoOfH.flipCard();
//                System.out.println("\nAttempting to add " + twoOfH + " to foundation");
//
//                if (foundation.canPlace(twoOfH, Suit.HEARTS)) {
//                        System.out.println("Added Two of Hearts to the foundation.");
//                } else {
//                        System.out.println("Could not add Two of Hearts to the foundation.");
//                }
//                foundation.place(twoOfH, Suit.HEARTS);
//                foundation.printFoundation();
//
//                // add three of hearts to foundation
//                // Expected output: Should be placed ontop of two of hearts
//                Card threeOfH = new Card(Rank.THREE, Suit.HEARTS);
//                threeOfH.flipCard();
//                System.out.println("\nAttempting to add " + threeOfH + " to foundation");
//
//                if (foundation.canPlace(threeOfH, Suit.HEARTS)) {
//                        System.out.println("Added Three of Hearts to the foundation.");
//                } else {
//                        System.out.println("Could not add Three of Hearts to the foundation.");
//                }
//                foundation.place(threeOfH, Suit.HEARTS);
//                foundation.printFoundation();
//
//                // add Ace of Spades
//                // Expected output: Should be added to empty pile.
//                Card aceOfS = new Card(Rank.ACE, Suit.SPADES);
//                aceOfS.flipCard();
//                System.out.println("\nAttempting to add " + aceOfS + " to foundation");
//
//                if (foundation.canPlace(aceOfS, Suit.SPADES)) {
//                        System.out.println("Added Ace of Spades to the foundation.");
//                } else {
//                        System.out.println("Could not add Ace of Spades to the foundation.");
//                }
//                foundation.place(aceOfS, Suit.SPADES);
//                foundation.printFoundation();
//
//                // Add Five of Hearts
//                // Expected output: could not add to pile since it is not in order
//                Card fiveOfH = new Card(Rank.FIVE, Suit.HEARTS);
//                fiveOfH.flipCard();
//                System.out.println("\nAttempting to add " + fiveOfH + " to foundation");
//
//                if (foundation.canPlace(fiveOfH, Suit.HEARTS)) {
//                        System.out.println("Added Five of Hearts to the foundation.");
//                } else {
//                        System.out.println("Could not add Five of Hearts to the foundation.");
//                }
//                foundation.place(fiveOfH, Suit.HEARTS);
//                foundation.printFoundation();

                //----------------------------------------------- DEMO --------------------------------------------\
                /*
                 * Add demo for:
                 * Foundations can only be filled starting with an ace
                 * */



//        System.out.println("Deck contains: " + deck.deckSize() + " cards");
//
//        // deck before shuffling -- deck can be shuffled demo.
//        System.out.println("Deck before shuffling!\n");
//        deck.printDeck();
//
//        // deck after shuffling -- deck can be shuffled demo
//        System.out.println("Deck After shuffling!");
//        deck.shuffleDeck();
//        deck.printDeck();
//
//        Tableau tableau = new Tableau();
//        tableau.initialize(deck);
//
//        // show deck size is reduced after creating tableau
//        System.out.println("\nDeck contains: " + deck.deckSize() + " cards after drawing for tableau");
//
//        System.out.println("\nPrinting tableau's with the correct number of cards per column\n");
//        tableau.printTableau();
//
//        System.out.println("\n\nDrawing a card from the deck!");
//       // System.out.println(testCard + " was drawn from the deck!");
//        System.out.println("Deck contains: " + deck.deckSize() + " cards after drawing a card!");
//
//        // Foundation demo
//        Foundation foundation = new Foundation();
//
//        // Tableau demo
//        tableau.removeCards(0,0);
//        Card cardDemo = new Card(Rank.FOUR,Suit.SPADES);
//        cardDemo.flipCard();
//
//        // add wrong card to empty tableau
//        System.out.println("\nAttempting to add " + cardDemo + " to first empty tableau!");
//        tableau.addCard(0,cardDemo);
//        tableau.printFirstTableau();
//
//        // add correct card to empty tableau
//        cardDemo = new Card(Rank.KING,Suit.SPADES);
//        cardDemo.flipCard();
//        System.out.println("\nAttempting to add " + cardDemo + " to first empty tableau!");
//        tableau.addCard(0,cardDemo);
//        tableau.printFirstTableau();
//
//        // add wrong colour card to tableau
//        cardDemo = new Card(Rank.QUEEN,Suit.SPADES);
//        cardDemo.flipCard();
//        System.out.println("\nAttempting to add " + cardDemo + " to tableau");
//        tableau.addCard(0,cardDemo);
//        tableau.printFirstTableau();
//
//        // add correct colour card to tableau
//        cardDemo = new Card(Rank.QUEEN,Suit.HEARTS);
//        cardDemo.flipCard();
//        System.out.println("\nAttempting to add " + cardDemo + " to tableau");
//        tableau.addCard(0,cardDemo);
//        tableau.printFirstTableau();

                //----------------------------------------------- END DEMO --------------------------------------------

        }
}
# Project Requirements

For this project, you will be implementing a Solitaire game.

## 1. Rulebook Consultation

The first step is to consult the following rulebook: [Solitaire Rulebook](https://bicyclecards.com/how-to-play/solitaire/)

- The terms used in the game requirements are outlined in the link above.

## 2. Customer Requirements

The customer has outlined the following requirements for the project:

### a. User Interface

**NOTE:** _(High priority items are outlined in bold. Low priority items are regular font. Remember, high priority items must be completed for the initial prototype demonstration):_

1. **Cards must display both the back side and front side of the card, as appropriately based on their discard/discovery status.**
2. **Card suits must be appropriately displayed**
3. **Card values must be appropriately displayed**
4. The Tableau must be displayed and functional
5. Foundations are clearly outlined and functional
6. The stockpile must be displayed and functional
7. The talon pile must be clearly outlined and functional
8. Clicking/tapping a card will auto-stack it if an appropriate spot is available

### b. Game Logic

1. **Rank of cards must be functional as outlined by the rules**
2. **A full deck of cards that can be shuffled must be implemented.**
3. **As cards are sorted into their piles, they must be subtracted from the full deck of cards.**
4. **Tableau cards can only be stacked in alternating colors**
5. **Spare tableau spots can only be filled with kings**
6. **Foundations can only be filled starting with an ace**
7. Foundations can only be stacked by matching suits
8. Foundations can only be stacked in ascending order.
9. Tableau undiscovered cards must be stacked in the order outlined by the rules.
10. Stacks of cards can only be moved when appropriately stacked by alternating suits.
11. Stockpile must only contain cards that have not already been dealt
12. Talon pile must maintain order in which stockpile cards were discarded
13. Stockpile must maintain order set by talon pile when re-stocking.
14. User must be able to start a new game, thus receiving a freshly shuffled deck of cards

### c. Additional Requirements

1. A working scoring system
2. Current score must be displayed and accurate
3. Vegas rules should be available as an additional game mode.

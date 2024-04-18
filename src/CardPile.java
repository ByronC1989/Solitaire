public interface CardPile {

    public int deckSize();
    public Card drawCard();
    public Card topCard();
    public void addCard(Card card);

}

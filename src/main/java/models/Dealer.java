package models;

/**
 * Created by nathan on 3/8/16.
 * WILL BE FINISHED LATER
 */
public class Dealer extends User {
    private int totalValue;
    private int value;
    public Deck deck;

    Dealer() {
        deck = new Deck();
    }

    public void takeCard(Card c) {
        hand.add(c);
    }

    public void getCardValue() {

    }

    public void play() {
        //while (totalValue < 17) {

        //}
    }

    public int getTotalValue() { return totalValue; }
}

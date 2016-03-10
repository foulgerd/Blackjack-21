package models;

/**
 * Created by nathan on 3/8/16.
 * WILL BE FINISHED LATER
 */
public class Dealer extends User {
    private int score = 0;
    public Deck deck;

    Dealer() {
        deck = new Deck();
        deck.build();
    }

    public void takeCard(Card c) {
        hand.add(c);
    }

    public void play() {
        while (score < 17) {
            takeCard(deck.deal());
            score = getScore();
        }
    }

    public int getTotalScore() { return score; }

}

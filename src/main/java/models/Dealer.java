package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by nathan on 3/8/16.
 * WILL BE FINISHED LATER
 */
public class Dealer extends User {
    public Deck deck;

    Dealer() {
        deck = new Deck();
        deck.build();
    }

    public void takeCard(Card c) {
        hand.add(c);
    }

    public void play() {
        while (calculateScore(hand) < 17) {
            takeCard(deck.deal());
        }
    }
}

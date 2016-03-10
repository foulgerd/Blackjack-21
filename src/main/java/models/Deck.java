package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Devin on 3/7/2016.
 */
public class Deck {

    public java.util.List<Card> cards = new ArrayList<>();
    public char[] Values = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
    public char[] Suit = {'S', 'C', 'H', 'D'};

    public void build(){
        for (int i = 0; i < Suit.length; i++) {
            for (int j = 0; j < Values.length; j++) {
                cards.add(new Card(Values[j], Suit[i]));
            }
        }
    }

    public void shuffle(){
        long seed = System.nanoTime();
        Collections.shuffle(cards, new Random(seed));
    }

    public Card deal(){
        Card card = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        return card;
    }
}

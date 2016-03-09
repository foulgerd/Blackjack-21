package models;

import org.junit.Test;
import org.junit.runners.Suite;

import static org.junit.Assert.*;



/**
 * Created by Terrance on 3/7/2016.
 */
public class testCard {
    @Test
    public void testSuit(){
        Card c = new Card('T','D');
        assertEquals('D', c.suit);
    }
    @Test
    public void testValue(){
        Card c = new Card('2', 'D');
        assertEquals('2', c.value);
    }

    @Test
    public void testCard(){
        Card c = new Card('T','D');
        String s = "";
        s += c.value;
        s += c.suit;

        assertEquals("TD",s);
    }

}

package models;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Devin on 3/7/2016.
 */
public class testDeck {
    @Test
    public void testDeck(){
        Deck d = new Deck();

        //Assert
        assertNotNull(d);
    }

    @Test
    public void testBuildDeck(){
        //Arrange
        Deck d = new Deck();

        //Setup
        d.build();

        //Assert
        assertEquals(52, d.cards.size());
    }

    @Test
    public void testShuffle() {
        //Arrange
        Deck unshuffled = new Deck();
        Deck shuffled = new Deck();

        //Act
        unshuffled.build();
        shuffled.build();
        shuffled.shuffle();

        //Assert
        assertNotEquals(shuffled, unshuffled);
    }

    @Test
    public void testDeal() {
        //Arrange
        Deck d = new Deck();
        Card c = null;

        //Act
        d.build();
        c = d.deal();

        //Test
        assertNotNull(c);
    }
}

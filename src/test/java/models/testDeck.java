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
}

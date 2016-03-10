package models;

import org.junit.Test;

import static org.junit.Assert.*;



/**
 * Created by Terrance on 3/7/2016.
 */
public class testGame {

    @Test
    public void testGameInitialization(){
        // Arrange
        Game g = new Game();

        // Act

        assertNotNull(g);
        assertNotNull(g.player);
        assertNotNull(g.dealer);
        assertNotNull(g.dealer.deck);
        assertNotEquals(0,g.dealer.deck.cards.size());

    }



}

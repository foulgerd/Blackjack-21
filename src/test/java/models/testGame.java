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

        // Assert
        assertNotNull(g);
        assertNotNull(g.player);
        assertNotNull(g.dealer);
        assertNotNull(g.dealer.deck);
        assertNotEquals(0,g.dealer.deck.cards.size());

    }

    @Test
    public void testGameHit(){
        // Arrange
        Game g = new Game();

        // Act
        g.Hit('1'); // testing hit for main hand.
        g.Hit('2'); // Testing hit for second hand.

        // Assert
        assertEquals("KD", g.player.hand.get(0).concatenate());
        assertEquals("QD", g.player.splithand.get(0).concatenate());

    }



}

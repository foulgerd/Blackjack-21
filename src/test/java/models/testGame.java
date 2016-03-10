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

    @Test
    public void testGameSplit(){
        //Arrange
        Game g = new Game();
        Card c1 = new Card('7', 'D');
        Card c2 = new Card('7', 'H');

        //Act
        g.player.hand.add(c1);
        g.player.hand.add(c2);
        g.SplitPlayerHand();

        //Assert
        assertEquals(g.player.hand.get(0).concatenate(), "7D");
        assertEquals(g.player.splithand.get(0).concatenate(), "7H");
    }

    @Test
    public void testGameStay(){
        //Arrange
        Game g = new Game();

        //Act
        g.Hit('1');
        g.Hit('1');

        //Test that player hand is 2 before stay
        assertEquals(2, g.player.hand.size());

        g.Stay();

        //Assert
        //Test if player hand still equals two after stay
        assertEquals(2, g.player.hand.size());
        //Test if dealer's hand is greater than or equal to 2
        assertTrue(g.dealer.hand.size() >= 2);
    }

}

package models;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Devin on 3/8/2016.
 */
public class testUser {
    @Test
    public void testUserCreation() {
        //Arrange
        User u = new User();

        //Assert
        assertNotNull(u);
    }

    @Test
    public void testUserHand() {
        User u = new User();

        assertEquals(u.hand.size(), 0);
    }

    @Test
    public void testTakeCard() {
        //Arrange
        User u = new User();

        //Act
        u.takeCard();

        //Assert
        assertEquals(u.hand.size(), 0);
    }

    @Test
    public void testScoreEachCardValue(){
        //Arrange
        Player p = new Player();
        Deck d = new Deck();
        Card badCard = new Card('P','P');
        java.util.List<Card> cards = new ArrayList<>();

        //Act
        d.build(); // building deck

        // Getting cards A - K
        for(int i = 0; i < 13; i++)
        {
            cards.add(d.deal());
        }

        // Test KD = 10
        p.takeCard(cards.get(0)); // Adding card to hand
        assertEquals("KD", cards.get(0).concatenate());
        assertEquals(10, p.getScore()); // Check score of the card
        cards.remove(0); // Remove card from list
        p.hand.remove(0); // clearing hand

        // Test QD = 10
        p.takeCard(cards.get(0));
        assertEquals("QD", cards.get(0).concatenate());
        assertEquals(10, p.getScore());
        cards.remove(0);
        p.hand.remove(0);

        // Test JD = 10
        p.takeCard(cards.get(0));
        assertEquals("JD", cards.get(0).concatenate());
        assertEquals(10, p.getScore());
        cards.remove(0);
        p.hand.remove(0);

        // Test TD = 10
        p.takeCard(cards.get(0));
        assertEquals("TD", cards.get(0).concatenate());
        assertEquals(10, p.getScore());
        cards.remove(0);
        p.hand.remove(0);

        // Testing Cards 9 - 2
        for(int i = 9; i > 1; i--){

            // Adding card to player hand
            p.takeCard(cards.get(0));

            // Remove card from list;
            cards.remove(0);

            // Checking value;
            assertEquals(i, p.getScore());

            // Clearing hand
            p.hand.remove(0);

        }

        // Test AD = 11
        p.takeCard(cards.get(0));
        assertEquals("AD", cards.get(0).concatenate());
        assertEquals(11, p.getScore());
        cards.remove(0);
        p.hand.remove(0);

        // Check score for a bad card
        p.takeCard(badCard);
        assertEquals(0, p.getScore());

    }

    @Test
    public void testTwoAces(){
        // Arrange
        Player p = new Player();
        Card c = new Card('A','D');

        // Act
        p.takeCard(c);
        p.takeCard(c);

        // Assert
        assertEquals(12, p.getScore());

    }
}

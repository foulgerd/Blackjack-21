package models;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by Devin on 3/9/2016.
 */
public class testDealer {
    @Test
    public void testDealer() {
        //Arrange
        User dealer = new Dealer();

        //Assert
        assertNotNull(dealer);
    }

    @Test
    public void testDealerTakeCard() {
        //Arrange
        User dealer = new Dealer();
        Deck d = new Deck();

        //Act
        d.build();
        dealer.takeCard(d.deal());

        //Assert
        assertEquals(dealer.hand.size(), 1);
    }

    @Test
    public void testDealerScore() {
        //Arrange
        Dealer dealer = new Dealer();
        int score;

        //Act
        score = dealer.calculateScore();

        assertEquals(0, score);
    }

    @Test
    public void testDealerPlay() {
        //Arrange
        Dealer dealer = new Dealer();
        int score;

        //Act
        dealer.play();
        score = dealer.calculateScore();

        assertTrue(17 <= dealer.calculateScore());
        assertTrue(score >= 17);
    }
}

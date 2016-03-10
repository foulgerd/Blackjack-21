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
        Dealer dealer = new Dealer();

        //Assert
        assertNotNull(dealer);
    }

    @Test
    public void testDealerTakeCard() {
        //Arrange
        Dealer dealer = new Dealer();
        Deck d = new Deck();

        //Act
        d.build();
        dealer.takeCard(d.deal());

        //Assert
        assertEquals(dealer.hand.size(), 1);
    }

    @Test
    public void testDealerPlay() {
        //Arrange
        Dealer dealer = new Dealer();
        Deck d = new Deck();

        //Act
        d.build();
        dealer.play();

        assertTrue(17 <= dealer.getTotalValue());
    }
}

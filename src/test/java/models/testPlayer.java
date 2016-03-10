package models;

import org.junit.Test;
import org.junit.runners.Suite;
import static org.junit.Assert.*;

/**
 * Created by connorsedwick on 3/9/16.
 */
public class testPlayer {

    @Test
    public void testPlayer(){
        Player p = new Player();
        assertNotNull(p);
    }

    @Test
    public void testWin(){
        Player p = new Player();

        //Check original amount
        assertEquals(100, p.getMoney());

        //Add to money
        p.winBet();
        assertEquals(102, p.getMoney());
    }

    @Test
    public void testMoney(){
        Player p = new Player();
        assertEquals(100, p.getMoney());
    }

    @Test
    public void testBet(){
        Player p = new Player();
        Player p2 = new Player();

        //Default bet
        assertEquals(2, p.getBet());
        assertEquals(p2.getBet(), p.getBet());

        //User-defined bet above amount on hand
        p2.setBet(110);
        assertEquals(p2.getBet(), p.getBet());

        //User-defined bet at amount on hand
        p2.setBet(100);
        assertNotEquals(p2.getBet(), p.getBet());

        //User-defined bet below amount on hand
        p2.setBet(87);
        assertNotEquals(p2.getBet(), p.getBet());
    }

    @Test
    public void testWin_Lose(){
        Player p = new Player();
        Player p2 = new Player();

        //Check to see if winning increases money available
        p.setBet(20);
        p.winBet();
        assertNotEquals(p.getMoney(),p2.getMoney());

        //Check to see if losing decreases money available
        p.loseBet();
        assertEquals(p.getMoney(),p2.getMoney());
    }

    @Test
    public void testSplit(){
        Player p = new Player();
        Card c = new Card('T','D');
        Card c2 = new Card('T','H');

        //Set originl bet amount
        p.setBet(20);
        assertEquals(20, p.getBet());

        //Take card +1 to hand size
        p.takeCard(c);
        assertNotNull(p.hand);

        //Take card +1 to hand size, hand.size() = 2
        p.takeCard(c2);
        assertEquals(2,p.hand.size());

        //Check that each card in hand is unique
        assertNotEquals(p.hand.get(p.hand.size()-1), p.hand.get(p.hand.size()-2));

        //Check that values of both cards are the same
        assertEquals(p.hand.get(p.hand.size()-1).value, p.hand.get(p.hand.size()-2).value);

        //Check split flag to ensure it's not set
        assertEquals(0,p.getSplit());

        //Split the hand
        p.split();

        //Check that split flag was set
        assertEquals(1,p.getSplit());

        //Check that bet was doubled
        assertEquals(40,p.getBet());

        //hand should be of size 1
        assertEquals(1,p.hand.size());

        //Check that splithand exists
        assertNotNull(p.splithand);

        //splithand is of size 1
        assertEquals(1,p.splithand.size());

        //splithand now holds one card that was originally in hand
        assertEquals(p.hand.get(p.hand.size()-1).value, p.splithand.get(p.splithand.size()-1).value);



    }



}

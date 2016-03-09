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


}

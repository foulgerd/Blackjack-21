package models;

import org.junit.Test;
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
}

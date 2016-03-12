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

    @Test
    public void testGameNewRound(){
        //arrange
        Game game = new Game();

        //Act
        game.Hit('1');
        game.Hit('1');
        game.player.split();
        game.Hit('2');

        //Assert
        assertEquals(1,game.player.hand.size());
        assertEquals(2,game.player.splithand.size());

        //Act
        game.NewRound();

        //Assert
        assertEquals(2,game.player.hand.size());
        assertEquals(2,game.dealer.hand.size());
        assertEquals(0,game.player.splithand.size());
    }

    @Test
    public void testNewRound(){

        // Arrange
        Game game = new Game();

        // Act

        game.player.setBet(20);
        game.player.takeCard(game.dealer.deck.deal());
        game.dealer.takeCard(game.dealer.deck.deal());
        game.player.takeCard(game.dealer.deck.deal());
        game.dealer.takeCard(game.dealer.deck.deal());

        // Assert
        assertEquals(20, game.player.bet);
        assertEquals("KD",game.player.hand.get(0).concatenate());
        assertEquals("JD",game.player.hand.get(1).concatenate());
        assertEquals("QD",game.dealer.hand.get(0).concatenate());
        assertEquals("TD",game.dealer.hand.get(1).concatenate());

        // Act
        game.NewRound();

        assertEquals(20, game.player.bet);
        assertEquals(2,game.player.hand.size());
        assertEquals(2,game.dealer.hand.size());

    }

    @Test
    public void doubleDownTest(){
        // Arrange
        Game g = new Game();
        Card c1 = new Card('A','D');
        Card c2 = new Card('5','D');
        Card c3 = new Card('T','D');

        g.player.takeCard(c2);
        g.player.setBet(20);
        g.doubleDown();

        assertEquals(40, g.player.bet);
        assertEquals(1,g.stayFlag);

    }

    @Test
    public void testLogicPlayerWin(){

        // Arrange
        Game g = new Game();
        Card c1 = new Card('A','D');
        Card c2 = new Card('5','D');
        Card c3 = new Card('T','D');
        Card c4 = new Card('4','D');


        // Assert

        // setting player to score of 21
        g.player.takeCard(c1);
        g.player.takeCard(c3);

        // setting dealer to score of 5
        g.dealer.takeCard(c3);
        g.dealer.takeCard(c3);


        g.Logic();

        assertEquals(1,g.winner);

        g.player.hand.clear();
        g.player.takeCard(c3);
        g.player.takeCard(c3);

        g.Logic();
        assertEquals(1,g.winner);

        g.Stay();
        g.Logic();

        assertEquals(1,g.winner);


        //Player win split
        g.player.hand.clear();
        g.player.takeCard(c1);
        g.player.takeCard(c1);

        g.player.split();
        g.player.splithand.add(c2);
        g.player.takeCard(c3);

        g.Stay();
        g.Logic();
        assertEquals(1,g.winner);
        //
        g.player.hand.clear();
        g.player.splithand.clear();
        g.dealer.hand.clear();

        g.player.takeCard(c3);
        g.player.takeCard(c3);
        g.player.split();
        g.player.takeCard(c1);
        g.player.splithand.add(c1);
        g.dealer.hand.add(c2);
        g.Logic();
        assertEquals(1,g.winner);
        //
        g.player.hand.clear();
        g.player.splithand.clear();
        g.dealer.hand.clear();

        g.stayFlag = 0;
        g.player.takeCard(c3);
        g.player.takeCard(c3);
        g.player.split();
        g.player.takeCard(c3);
        g.player.splithand.add(c3);
        g.player.splithand.add(c3);
        g.dealer.hand.add(c3);
        g.dealer.hand.add(c2);
        g.Logic();
        assertEquals(1, g.winner);
        assertEquals(1, g.hand1);
        assertEquals(0, g.hand2);
        assertEquals(1, g.player.split);

        //
        g.stayFlag = 1;
        g.player.split = 0;
        g.player.hand.clear();
        g.player.takeCard(c1);
        g.player.takeCard(c3);
        g.player.money = 100;
        g.player.bet = 2;

        g.Logic();
        assertEquals(1,g.winner);
        assertEquals(102,g.player.money);

        g.player.takeCard(c3);
        g.player.takeCard(c4);

        g.Logic();
        assertEquals(2,g.winner);

        //
        g.stayFlag = 1;
        g.player.split = 0;
        g.player.hand.clear();
        g.player.takeCard(c4);
        g.player.takeCard(c3);
        g.player.takeCard(c2);
        g.Logic();
        assertEquals(1,g.winner);

        //
        g.stayFlag = 0;
        g.player.splithand.clear();
        g.player.hand.clear();
        g.player.takeCard(c3);
        g.player.takeCard(c3);
        g.player.split();
        g.player.splithand.add(c1);
        g.player.takeCard(c1);
        g.Logic();
        assertEquals(1,g.winner);



    }

    @Test
    public void testLogicDealerWin(){

        // Arrange
        Game g = new Game();
        Card c1 = new Card('A','D');
        Card c2 = new Card('5','D');
        Card c3 = new Card('T','D');

        // Assert

        // setting player to score of 21
        g.dealer.takeCard(c1);
        g.dealer.takeCard(c3);

        // setting dealer to score of 5
        g.player.takeCard(c2);

        g.stayFlag = 1;
        g.Logic();

        assertEquals(2,g.winner);


    }

    @Test
    public void testLogicDealerWinSplit(){

        // Arrange
        Game g = new Game();
        Card c1 = new Card('A','D');
        Card c2 = new Card('5','D');
        Card c3 = new Card('T','D');

        // Assert

        // setting player to score of 21
        g.dealer.takeCard(c3);
        g.dealer.takeCard(c3);

        // setting dealer to score of 5
        g.player.takeCard(c1);
        g.player.takeCard(c1);
        g.player.split();
        g.player.splithand.add(c3);

        g.stayFlag = 1;
        g.Logic();

        assertEquals(1,g.winner);

        g.player.hand.clear();
        g.player.takeCard(c2);
        g.player.takeCard(c2);
        g.player.split();

        g.Stay();
        g.Logic();

        assertEquals(2,g.winner);
    }


    @Test
    public void testLogicDealerBust(){

        // Arrange
        Game g = new Game();
        Card c1 = new Card('A','D');
        Card c2 = new Card('5','D');
        Card c3 = new Card('T','D');

        // Assert

        // setting player to score of
        g.dealer.takeCard(c2);
        g.dealer.takeCard(c3);
        g.dealer.takeCard(c3);

        // setting dealer to score of 5
        g.player.takeCard(c2);

        g.stayFlag = 1;
        g.Logic();

        assertEquals(1,g.winner);

    }

    @Test
    public void testLogicPlayerBust(){
        // Arrange
        Game g = new Game();
        Card c1 = new Card('5','D');
        Card c2 = new Card('T','D');
        Card c3 = new Card('T','D');

        // Assert

        // setting player to score of
        g.dealer.takeCard(c1);
        g.dealer.takeCard(c3);

        // setting dealer to score of 5
        g.player.takeCard(c2);
        g.player.takeCard(c1);
        g.player.takeCard(c3);

        g.Logic();

        assertEquals(2,g.winner);

    }
}

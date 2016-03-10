package models;

/**
 * Created by nathan on 3/10/16.
 */
public class Game {
    public Player player;
    public Dealer dealer;

    public Game(){
        player = new Player();
        dealer = new Dealer();
    }

}

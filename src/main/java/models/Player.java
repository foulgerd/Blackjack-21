package models;

/**
 * Created by nathan on 3/8/16.
 */
public class Player extends User {
    private int Bet;
    private int Money;
    private int Split;

    public Player(){
        Money = 200;
        Bet = 2;
        Split = 0;
    }

    public void setBet(int amount){
        if(amount <= getMoney()) {
            Bet = amount;
        }
    }

    public void loseBet(){
        Money = Money - Bet;
    }

    public void winBet(){
        Money = Money + Bet;
    }

    public int getBet(){
        return Bet;
    }

    public int getMoney(){
        return Money;
    }
}

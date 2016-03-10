package models;

import java.util.ArrayList;

/**
 * Created by nathan on 3/8/16.
 */
public class Player extends User {

    //public java.util.List<Card> hand = new ArrayList<>();
    public java.util.List<Card> splithand = new ArrayList<>();
    private int Bet;
    private int Money;
    private int Split;

    public Player(){
        Money = 100;
        Bet = 2;
        Split = 0;
    }

    public int getBet(){
        return Bet;
    }

    public int getMoney(){
        return Money;
    }

    public int getSplit() { return Split; }

    public void setBet(int amount){
        if(amount <= getMoney() && amount >=2) {
            Bet = amount;
        }
    }

    public void setSplit(int flag){
        Split = flag;
    }

    public void takeCard(Card c){
        hand.add(c);
    }

    public void takeCardSplit(Card c){
        splithand.add(c);
    }

    public void split(){
        setSplit(1);
        Card temp;

        temp = hand.get(hand.size()-1);
        splithand.add(temp);
        hand.remove(hand.size()-1);
        setBet(2 * getBet());
    }

    public void loseBet(){
        Money = Money - Bet;
    }

    public void winBet(){
        Money = Money + Bet;
    }


}

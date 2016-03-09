package models;

/**
 * Created by nathan on 3/7/16.
 */
public class Test {
    public String Hit;
    public String Stay;
    public String DDown;
    public String Split;
    public String State;
    public String Bet;

    public Test(){
        State = "Game state set";
    }

    public void getHit(){
        if(Hit == null)
        {
            Hit = "hit ";
        }
        else{
            Hit += "hit ";
        }
    }

    public void getStay(){
        Stay = "You Stayed";
    }

    public void getDoubleDown(){
        DDown = "You Doubled Down";
    }

    public void getSpit(){
        Split = "You Split";
    }

    public void getBet(){
        Bet = "You Placed a bet";
    }
}

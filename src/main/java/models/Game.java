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

    public void Hit(char hand){
        if(hand == '1'){
            player.takeCard(dealer.deck.deal());
        }
        else if(hand == '2'){
            player.takeCardSplit(dealer.deck.deal());
        }
    }

    public void SplitPlayerHand(){
        player.split();
    }

    public void Stay(){
        dealer.play();
    }

    public void Logic() {

        if(player.calculateScore() > 21){
            player.loseBet();
        }
        else if(dealer.calculateScore() > 21){
            player.winBet();
        }
        else if(player.calculateScore() > dealer.calculateScore()){
            player.winBet();
        }
        else if(player.calculateScore() < dealer.calculateScore()){
            player.loseBet();
        }
        NewRound();
    }

    public void NewRound() {
        player.split = 0;
        player.hand.clear();
        player.splithand.clear();
        dealer.hand.clear();
        for(int i = 0; i < 2; i++) {
            player.takeCard(dealer.deck.deal());
            dealer.takeCard(dealer.deck.deal());
        }
    }
}

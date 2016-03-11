package models;

/**
 * Created by nathan on 3/10/16.
 */
public class Game {
    public Player player;
    public Dealer dealer;
    public int winner;
    public int stayFlag;

    public Game(){
        player = new Player();
        dealer = new Dealer();
        stayFlag = 0;
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
        stayFlag = 1;
    }

    public void Logic() {
        int hand1 = 0;
        int hand2 = 0;

        //Checks splithand against dealer
        if(player.split == 1){
            if(player.calculateScore(player.splithand) > 21){
                winner = 2; // winner dealer
            }
            else if((dealer.calculateScore(dealer.hand) > 21) && stayFlag == 1){
                winner = 1; // winner player
                hand2 = 1;
            }
            else if((player.calculateScore(player.splithand) > dealer.calculateScore(player.hand)) && stayFlag == 1){
                winner = 1; // winner player
                hand2 = 1;
            }
            else if((player.calculateScore(player.splithand) < dealer.calculateScore(dealer.hand)) && (stayFlag == 1)){
                winner = 2; // winner dealer
            }
            else if(player.calculateScore(player.splithand) == 21){
                winner = 1; // winner player
                hand2 = 1;
            }
            else if(player.calculateScore(player.splithand) == dealer.calculateScore(dealer.hand) && (stayFlag == 1)){
                winner = 1; // winner player
                hand2 = 1;
            }
        }

        //checks hand against dealer
        if(player.calculateScore(player.hand) > 21){
            winner = 2; // winner dealer
        }
        else if((dealer.calculateScore(dealer.hand) > 21) && stayFlag == 1){
            winner = 1; // winner player
            hand1 = 1;
        }
        else if((player.calculateScore(player.hand) > dealer.calculateScore(dealer.hand)) && stayFlag == 1){
            winner = 1; // winner player
            hand1 = 1;
        }
        else if((player.calculateScore(player.hand) < dealer.calculateScore(dealer.hand)) && (stayFlag == 1)){
            winner = 2; // winner dealer
        }
        else if(player.calculateScore(player.hand) == 21){
            winner = 1; // winner player
            hand1 = 1;
        }
        else if(player.calculateScore(player.hand) == dealer.calculateScore(dealer.hand) && (stayFlag == 1)){
            winner = 1; // winner player
            hand1 = 1;
        }

        if((hand1 == 1 && hand2 == 1) && (stayFlag == 1)){
            player.winBet();
        }
        else if((hand1 == 1 && hand2 == 0) && (stayFlag == 1)){
            player.winBet();
        }
        else if((hand1 == 0 && hand2==1) && (stayFlag == 1)) {
            player.setBet(player.bet / 2);
            player.winBet();
        }
        else if((hand1 == 0 && hand2 == 0) && (stayFlag == 1)){
            player.loseBet();
        }
        // no winner
    }

    public void NewRound() {
        player.split = 0;
        stayFlag = 0;
        winner = 0;
        player.hand.clear();
        player.splithand.clear();
        dealer.hand.clear();
        for(int i = 0; i < 2; i++) {
            player.takeCard(dealer.deck.deal());
            dealer.takeCard(dealer.deck.deal());
        }
    }
}

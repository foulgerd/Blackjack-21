package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;



/**
 * Created by nathan on 3/10/16.
 */
public class Game {
    public Player player;
    public Dealer dealer;
    public int winner;
    public int stayFlag;
    public int split21 = 0;
    public int hand1 = 0;
    public int hand2 = 0;
    public int bust1 = 0;
    public int bust2 = 0;
    public int dealerBust = 0;

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

    public void doubleDown(){
        player.takeCard(dealer.deck.deal());
        player.doubleDown();
        Stay();
    }

    public void SplitPlayerHand(){
        player.split();
    }

    public void Stay(){
        dealer.play();
        stayFlag = 1;
    }

    public void Logic() {
        if (stayFlag == 1) {
            if (player.split == 1) {
                if ((player.calculateScore(player.hand) < dealer.calculateScore(dealer.hand)) && (player.calculateScore(player.splithand) < dealer.calculateScore(dealer.hand))) {
                    player.loseBet();
                    winner = 2;
                    return;
                } else if ((player.calculateScore(player.hand) > dealer.calculateScore(dealer.hand)) && (player.calculateScore(player.splithand) < dealer.calculateScore(dealer.hand)) || (player.calculateScore(player.hand) > 21 && player.calculateScore(player.splithand) == 21) || (player.calculateScore(player.hand) == 21 && player.calculateScore(player.splithand) > 21)){
                    player.setBet(player.bet / 2);
                    player.winBet();
                    winner = 1;
                    return;
                } else if ((player.calculateScore(player.hand) < dealer.calculateScore(dealer.hand)) && (player.calculateScore(player.splithand) > dealer.calculateScore(dealer.hand))) {
                    player.setBet(player.bet / 2);
                    player.winBet();
                    winner = 1;
                    return;
                } else {
                    player.winBet();
                    winner = 1;
                    return;
                }
            } else {
                if (player.calculateScore(player.hand) == 21) {
                    winner = 1;
                    player.winBet();
                    return;
                }
                if (player.calculateScore(player.hand) > 21) {
                    winner = 2; // winner dealer
                    player.loseBet();
                    return;
                }
                if (dealer.calculateScore(dealer.hand) > 21) {
                    winner = 1;
                    player.winBet();
                    return;
                }
                if (player.calculateScore(player.hand) == dealer.calculateScore(dealer.hand)) {
                    winner = 1;
                    player.winBet();
                    return;
                } else if (player.calculateScore(player.hand) < dealer.calculateScore(dealer.hand)) {
                    winner = 2;
                    player.loseBet();
                    return;
                } else {
                    winner = 1;
                    player.winBet();
                    return;
                }
            }
        } else {

            //Checks splithand against dealer
            if (player.split == 1 && split21 == 0) {
                if ((player.calculateScore(player.hand) == 21 && player.calculateScore(player.splithand) == 21)) {
                    winner = 1;
                    player.winBet();
                    return;
                }
                if (player.calculateScore(player.splithand) > 21) {
                    hand2 = 0;
                    bust2 = 1;
                }
            }

            if (player.calculateScore(player.hand) == 21) {
                hand1 = 1;
            }
            if (player.calculateScore(player.hand) > 21) {
                hand1 = 0;
                bust1 = 1;
            }

            if ((bust1 == 1 && bust2 == 1) || (bust1 == 1 && hand2 == 0)) {
                player.loseBet();
                winner = 2;
                return;
            } else if ((hand1 == 1 && hand2 == 1) || (dealerBust == 1)) {
                player.winBet();
                winner = 1;
                return;
            } else if ((hand1 == 1 && hand2 == 0) && player.split == 1) {
                player.setBet(player.bet / 2);
                player.winBet();
                winner = 1;
                return;
            } else if (hand1 == 0 && hand2 == 1) {
                player.setBet(player.bet / 2);
                player.winBet();
                winner = 1;
                return;
            } else if ((hand1 == 1 && hand2 == 0) && player.split == 0) {
                player.winBet();
                winner = 1;
                return;
            } else if (hand1 == 0 && player.split == 0 && bust1 == 1){
                player.loseBet();
                winner = 2;
                return;
            } else{
            }
        }
    }

    public void NewRound() {
        player.split = 0;
        stayFlag = 0;
        winner = 0;
        split21 = 0;
        hand1 = 0;
        hand2 = 0;
        bust1 = 0;
        bust2 = 0;
        dealerBust = 0;
        player.hand.clear();
        player.splithand.clear();
        dealer.hand.clear();
        if(dealer.deck.cards.size() < 10){
            dealer.deck.cards.clear();
            dealer.deck.build();
            dealer.deck.shuffle();
        }
        for(int i = 0; i < 2; i++) {
            player.takeCard(dealer.deck.deal());
            dealer.takeCard(dealer.deck.deal());
        }
    }
}

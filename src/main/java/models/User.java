package models;

import java.util.ArrayList;

/**
 * Created by nathan on 3/8/16.
 */
public class User {
    public java.util.ArrayList<Card> hand = new ArrayList<>();

    public void takeCard() { }

    public int getScore() {
        int score = 0;
        int aceFlag = 0;
        for(Card c : hand){
            switch(c.value){
                case 'A':
                    score += 11;
                    aceFlag = 1;
                    break;
                case '2':
                    score += 2;
                    break;
                case '3':
                    score += 3;
                    break;
                case '4':
                    score += 4;
                    break;
                case '5':
                    score += 5;
                    break;
                case '6':
                    score += 6;
                    break;
                case '7':
                    score += 7;
                    break;
                case '8':
                    score += 8;
                    break;
                case '9':
                    score += 9;
                    break;
                case 'T':
                    score += 10;
                    break;
                case 'J':
                    score += 10;
                    break;
                case 'Q':
                    score += 10;
                    break;
                case 'K':
                    score += 10;
                    break;
                default:
                    score += 0;
            }
        }

        // If the score is over the 21
        // and there is on ace remove 11 and add 1
        if(score > 21 && aceFlag == 1){
            score -= 11;
            score += 1;
        }

        return score;
    }
}

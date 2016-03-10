package models;

import java.util.ArrayList;

/**
 * Created by nathan on 3/8/16.
 */
public class User {
    public java.util.ArrayList<Card> hand = new ArrayList<>();

    public void takeCard(Card c) { };

    public int getScore() {
        int temp = 0;
        int aceFlag = 0;
        for(Card c : hand){
            switch(c.value){
                case 'A':
                    temp += 11;
                    aceFlag = 1;
                    break;
                case '2':
                    temp += 2;
                    break;
                case '3':
                    temp += 3;
                    break;
                case '4':
                    temp += 4;
                    break;
                case '5':
                    temp += 5;
                    break;
                case '6':
                    temp += 6;
                    break;
                case '7':
                    temp += 7;
                    break;
                case '8':
                    temp += 8;
                    break;
                case '9':
                    temp += 9;
                    break;
                case 'T':
                    temp += 10;
                    break;
                case 'J':
                    temp += 10;
                    break;
                case 'Q':
                    temp += 10;
                    break;
                case 'K':
                    temp += 10;
                    break;
                default:
                    temp += 0;
            }
        }

        // If the temp is over the 21
        // and there is one ace, remove 11 and add 1

        if(temp > 21 && aceFlag == 1){
            temp -= 11;
            temp += 1;
        }

        return temp;
    }

}

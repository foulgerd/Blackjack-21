/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;


import models.Game;
import ninja.Context;
import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;
import ninja.params.PathParam;


@Singleton
public class ApplicationController {

    public Result index() {
        return Results.html();
    }

    public Result blackjack() {
        return Results.html().template("views/Blackjack/Blackjack.flt.html");
    }

    public Result gameGet(){
        Game g = new Game();
        g.dealer.deck.shuffle();
        g.player.takeCard(g.dealer.deck.deal());
        g.dealer.takeCard(g.dealer.deck.deal());
        g.player.takeCard(g.dealer.deck.deal());
        g.dealer.takeCard(g.dealer.deck.deal());


        return Results.json().render(g);
    }

    public Result hitPOST(Context context, @PathParam("hand") char hand, Game g){
        if(context.getRequestPath().contains("hit")){
           g.Hit(hand);
        }
        return Results.json().render(g);
    }

    public Result betPOST(Context context,@PathParam("amount") int amount, Game g){
        if (context.getRequestPath().contains("bet")) {
            g.player.setBet(amount);
        }
        return Results.json().render(g);
    }

    public Result stayPOST(Context context, Game g){
        if(context.getRequestPath().contains("stay")){
            g.Stay();
        }
        return Results.json().render(g);
    }

    public Result splitPOST(Context context, Game g){
        if(context.getRequestPath().contains("split")){
            g.SplitPlayerHand();
        }
        return Results.json().render(g);
    }

    public Result doubledownPOST(Context context, Game g){
        if(context.getRequestPath().contains("doubledown")){
           g.player.doubleDown();
        }
        return Results.json().render(g);
    }


}

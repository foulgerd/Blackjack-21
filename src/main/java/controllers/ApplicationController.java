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

import models.Test;
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
        Test t = new Test();

        return Results.json().render(t);
    }

    public Result hitPOST(Context context, Test t){
        if(context.getRequestPath().contains("hit")){
            t.getHit();
        }
        return Results.json().render(t);
    }

    public Result stayPOST(Context context, Test t){
        if(context.getRequestPath().contains("stay")){
            t.getStay();
        }
        return Results.json().render(t);
    }

    public Result splitPOST(Context context, Test t){
        if(context.getRequestPath().contains("split")){
            t.getSpit();
        }
        return Results.json().render(t);
    }

    public Result betPOST(Context context, Test t){
        if (context.getRequestPath().contains("bet")) {
            t.getBet();
        }
        return Results.json().render(t);
    }

    public Result doubledownPOST(Context context, Test t){
        if(context.getRequestPath().contains("doubledown")){
            t.getDoubleDown();
        }
        return Results.json().render(t);
    }


}

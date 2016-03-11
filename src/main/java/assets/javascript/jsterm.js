/**
 * Created by nathan on 2/14/16.
 */

/**
 * Constructor for jsterm class
 * @type {{}}
 */
var jsterm = jsterm || {};

/**
 * Holds the current game state
 * @type {Gamestate Object}
 */
jsterm.gamestate = null;

/**
 * Hold a flag for if the user raised the bet.
 * @type {null}
 */
jsterm.betFlag = 0;

/**
 * Hold a flag for if the user stayed.
 * @type {number}
 */
jsterm.stayFlag = 0;

/**
 * Hold the counter of rows
 * @type {number}
 */
jsterm.count = 0;

/**
 * Hold the counter of displays
 * @type {number}
 */
jsterm.displayCount = 0;

/**
 * Flag to determine if terminal has been initialize.
 * @type {number}
 */
jsterm.init = 0;

/**
 * Main page function
 */
$(function () {
    var string;
    if (jsterm.init == 0) {
        $.getJSON("http://localhost:8080/game", function (data) {
            console.log(data);
            jsterm.gamestate = data;
            jsterm.display();
            jsterm.initialize();
        });
    }


    $(window).keyup(function (event) {

        // get the character from the keyboard
        var char = String.fromCharCode(event.keyCode);

        // removes the cursor from the current line
        jsterm.removeCursor();

        switch (event.keyCode) {
            case 8: // Delete key
                string = jsterm.delete();

                break;
            case 46: // Delete Key
                string = jsterm.delete();

                break;
            case 16: // Shift Key
                string = jsterm.delete();

                break;
            case 13: // Return Key
                jsterm.command();
                
                if(jsterm.gamestate['winner']){
                    jsterm.displayWinner(jsterm.gamestate['winner']);
                    jsterm.stayFlag = 0;
                    jsterm.betFlag = 0;
                    jsterm.newround();
                }
                string = jsterm.return();

                // Writing game state to log
                console.log(jsterm.gamestate);

                break;
            default: // Anything else
                string = jsterm.insert(char);

        }


        if (string != null)
        // Prints the screen.
            jsterm.print(string);


    });


});

/**
 * Initialize the terminal with first row
 * Add an terminal character
 */
jsterm.initialize = function () {
    $('#terminal').append(
        $('<div id="row-' + jsterm.count + '"></div>').html("> ")
    );

};

/**
 * Updates the terminal screen
 * @param string
 */
jsterm.print = function (string) {
    string += '<span class="cursor">|</span>';
    $('#row-' + jsterm.count).html(string);
};

/**
 * Takes a char from the user
 * Add the char to the current string
 * return new string with char appended to end
 * @param char
 * @returns {string}
 */
jsterm.insert = function (char) {

    var row = $('#row-' + jsterm.count).html();
    var stArray = row.split("");
    stArray.push(char);
    return stArray.join("");

};

/**
 * Remove the last char in the string
 * @param char
 * @returns {string}
 */
jsterm.delete = function () {
    var row = $('#row-' + jsterm.count).html();
    var stArray = row.split("");
    stArray.pop();
    return stArray.join("");

};
/**
 * Creates a new row
 * @returns {string}
 */
jsterm.return = function () {
    jsterm.count++;
    $('#terminal').append(
        $('<div id="row-' + jsterm.count + '"></div>')
    );
    return "> ";

};

/**
 * Removes the blinking cursor from the screen.
 */
jsterm.removeCursor = function () {
    $('.cursor').remove();
};


/**
 * Check if user input is a valid command
 * Check if there is the right about of arguments.
 */
jsterm.command = function () {
    var row = $('#row-' + jsterm.count).html();
    var commandArray = row.split(" ");

    if (!commandArray[1].localeCompare("HIT")) {
        if ((commandArray.length == 3) && jsterm.gamestate['player']['split']) {
            jsterm.hit(commandArray[2]);
        }
        else if ((commandArray.length == 3) && !jsterm.gamestate['player']['split']) {
            jsterm.error("Error: You have not split you hand.");
        }
        else {
            jsterm.hit('1');
        }

    }
    else if (!commandArray[1].localeCompare("STAY")) {
        jsterm.stayFlag = 1;
        jsterm.stay();
    }
    else if (!commandArray[1].localeCompare("DOUBLEDOWN")) {
        if(jsterm.gamestate['player']['money'] >= (jsterm.gamestate['player']['bet'] * 2) && !jsterm.gamestate['player']['ddown'] ) {
            jsterm.doubledown();
        }
        else{
            jsterm.error("Error: You do not have enough money to double down/ or you have already doubled down once.");
        }
    }
    else if (!commandArray[1].localeCompare("SPLIT")) {
        if (jsterm.gamestate['player']['hand'][0]['value'] == jsterm.gamestate['player']['hand'][1]['value']) {
            jsterm.split();
        }
        else {
            jsterm.error("Error: Your two cards values are not the same. Can't split");
        }

    }
    else if (!commandArray[1].localeCompare("BET")) {
        if ((commandArray.length == 3) && !jsterm.betFlag) {
            if (jsterm.gamestate['player']['money'] > parseInt(commandArray[2])) {
                jsterm.bet(parseInt(commandArray[2]));
                jsterm.betFlag = 1;
            }
            else {
                jsterm.error("Error: You dont have that much money to bet.");
            }
        }
        else {
            jsterm.error("Error: You did specify an amout or have changed your bet amount. ")
        }
    }
    else {
        //alert("Error: Not a command");
        jsterm.error("Error: No a valid command.")
    }

};

/**
 * Makes an ajax call to the "HIT" route
 * @param split
 * @param hand
 */
jsterm.hit = function (hand) {
    $.ajax({
        type: "POST",
        url: "/hit/" + hand,
        async: false,
        data: JSON.stringify(jsterm.gamestate),
        success: function (data, status) {
            jsterm.gamestate = data;
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    });
    jsterm.display();
};

/**
 * Makes an ajax call to the "STAY" route
 */
jsterm.stay = function () {
    $.ajax({
        type: "POST",
        url: "/stay",
        async: false,
        data: JSON.stringify(jsterm.gamestate),
        success: function (data, status) {

            jsterm.gamestate = data;

        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    });
    jsterm.display();
};

/**
 * Makes an ajax call to the "DOUBLEDOWN" route
 */
jsterm.doubledown = function () {
    $.ajax({
        type: "POST",
        url: "/doubledown",
        async: false,
        data: JSON.stringify(jsterm.gamestate),
        success: function (data, status) {
            jsterm.gamestate = data;

        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    });
    jsterm.display();
};

/**
 * Makes an ajax call to the "SPLIT" route
 */
jsterm.split = function () {
    $.ajax({
        type: "POST",
        url: "/split",
        async: false,
        data: JSON.stringify(jsterm.gamestate),
        success: function (data, status) {
            jsterm.gamestate = data;

        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    });
    jsterm.display();

};

/**
 * Make an ajax call to the "new round" route
 */
jsterm.newround = function () {
    $.ajax({
        type: "POST",
        url: "/newround",
        async: false,
        data: JSON.stringify(jsterm.gamestate),
        success: function (data, status) {
            jsterm.gamestate = data;

        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    });
    jsterm.display();
};

/**
 * Makes an ajax call to the "BET" route
 */
jsterm.bet = function (amount) {
    $.ajax({
        type: "POST",
        url: "/bet/" + amount,
        async: false,
        data: JSON.stringify(jsterm.gamestate),
        success: function (data, status) {
            jsterm.gamestate = data;

        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    });
    jsterm.display();

};

/**
 * Displays a given error message.
 * @param error
 */
jsterm.error = function (error) {
    $('#terminal').append(
        $('<div class="error"></div>').html(error)
    );
};

/**
 * Displays the current gamestate
 */
jsterm.display = function () {
    // build unique id
    var id = 'display-' + jsterm.displayCount;

    // Create div that information will be appended too.
    $('#terminal').append(
        $('<div id="' + id + '"></div>')
    );

    // Creating Dealer display
    jsterm.dealerDisplay(id);

    // Creating player display
    jsterm.playerDisplay(id);

    // Increase display count
    jsterm.displayCount++;


};

/**
 * Build the dealer display
 * @param id
 */
jsterm.dealerDisplay = function (id) {
    var display = "";
    var hideFlag = 1;
    display += 'Dealer: ';

    console.log(jsterm.stayFlag);
    // if the stayFlag is set then show all the cards
    if(jsterm.stayFlag){
        hideFlag = 0;
    }

    // Adding cards to the dealer display hiding the first card.
    for (var k in jsterm.gamestate['dealer']['hand']) {
        if (hideFlag ) {
            display += '[ ]';
            hideFlag = 0;
        }
        else {
            display += '[' + jsterm.gamestate['dealer']['hand'][k].value + jsterm.gamestate['dealer']['hand'][k].suit + ']';
        }
    }
    // Appending the dealer display.
    $('#' + id).append(
        $('<div></div>').html(display)
    );
};

jsterm.playerDisplay = function (id) {
    var display = "";
    display += 'Player: ';

    // Adding cards to the dealer display hiding the first card.
    for (var k in jsterm.gamestate['player']['hand']) {
        display += '[' + jsterm.gamestate['player']['hand'][k].value + jsterm.gamestate['player']['hand'][k].suit + ']';

    }

    display += '<br/>';

    // If the player has split show the second hand
    if (jsterm.gamestate['player']['split']) {

        display += 'Hand 2: ';
        for (var k in jsterm.gamestate['player']['splithand']) {
            display += '[' + jsterm.gamestate['player']['splithand'][k].value + jsterm.gamestate['player']['splithand'][k].suit + ']';

        }
        display += '<br/>';
    }

    // Formatting Money
    display += "Money: $" + jsterm.gamestate['player']['money'];

    display += '<br/>';

    // Formatting Bet
    display += "Current Bet: $" + jsterm.gamestate['player']['bet'];

    $('#' + id).append(
        $('<div></div>').html(display)
    );

};

jsterm.displayWinner = function(winner){
    // build unique id
    var id = 'display-' + jsterm.displayCount;

    // Create div that information will be appended too.
    $('#terminal').append(
        $('<div id="' + id + '"></div>')
    );

    if(winner == 1){
        $('#' + id).append(
            $('<div></div>').html("YOU WON!<br/><br/>")
        );
    }
    else if(winner == 2)
    {
        $('#' + id).append(
            $('<div></div>').html("YOU LOST!<br/><br/>")
        );
    }

    // Increase display count
    jsterm.displayCount++;
};



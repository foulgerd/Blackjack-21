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
jsterm.gamestate;

/**
 * Hold the counter of rows
 * @type {number}
 */

jsterm.count = 0;
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
        jsterm.initialize();
    }

    $(window).keyup(function (event) {

        // get the charator from the keyboard
        var char = String.fromCharCode(event.keyCode);

        // removes the cursor from the current line
        jsterm.removeCursor();

        switch(event.keyCode){
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

                string = jsterm.return();
                break;
            default: // Anything else
                string = jsterm.insert(char);
        }

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

jsterm.command = function () {
    var row = $('#row-' + jsterm.count).html();
    var commandArray = row.split(" ");
    if (commandArray.length > 2) {

        if (!commandArray[1].localeCompare("HIT")) {
            alert("Command: HIT");
        }
        else if (!commandArray[1].localeCompare("STAY")) {
            alert("Command: STAY");
        }
        else if (!commandArray[1].localeCompare("DOUBLEDOWN")) {
            alert("Command: Double Down");
        }
        else if(!commandArray[1].localeCompare("SPLIT")){
            alert("Command: SPLIT")
        }
        else {
            //alert("Error: Not a command");
            jsterm.error("Error: No a valid command.")
        }
    }
    else {
        //alert("Error: Command not entered.");
        jsterm.error("Error: Command not entered.");
    }

    console.log(commandArray);

};

/**
 * Makes an ajax call to the "HIT" route
 * @param split
 * @param hand
 */
jsterm.hit = function(split, hand){
    // make ajax call to "HIT"
};

/**
 * Makes an ajax call to the "STAY" route
 */
jsterm.stay = function(){
    // make ajax call to "STAY"
};

/**
 * Makes an ajax call to the "DOUBLEDOWN" route
 */
jsterm.doubledown = function(){

};

/**
 * Makes an ajax call to the "SPLIT" route
 */
jsterm.spit = function(){

};

/**
 * Displays a given error message.
 * @param error
 */
jsterm.error = function(error){
    $('#terminal').append(
        $('<div class="error"></div>').html(error)
    );
};




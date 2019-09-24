/**
 * Course: COMP 426
 * Assignment: a05
 * Author: <type your name here>
 *
 * This script uses jQuery to build an HTML page with content taken from the
 * data defined in data.js.
 */



/**
 * Given a hero object (see data.js), this function generates a "card" showing
 *     the hero's name, information, and colors.
 * @param hero  A hero object (see data.js)
 */
export const renderHeroCard = function(hero) {
    // TODO: Copy your code from a04 to render the hero card
    let div = $(`<div id=` + hero.id + ` style="background-color:` + hero.backgroundColor + `;` + `"></div>`)
    let profile_pic = $("<img src=" + hero.img + ">")
    let first = hero.firstSeen
    let name = $(`<h1 style="color:` + hero.color + `;` + `"></h1>`).text(hero.name)
    let realn = $(`<p></p>`).text(hero.first + " " + hero.last)
    let info = $(`<p></p>`).text(hero.description)
    let span = $(`<span></span>`).append(info)
    let button = $(`<button id=` + hero.id + ` class=edit style="background-color: green;"></button>`).text("Edit")
    div.append("<br>", profile_pic, "<br>", first, name, realn, span, button)
    return div
};



/**
 * Given a hero object, this function generates a <form> which allows the
 *     user to edit the fields of the hero. The form inputs should be
 *     pre-populated with the initial values of the hero.
 * @param hero  The hero object to edit (see data.js)
 */
export const renderHeroEditForm = function(hero) {
    // TODO: Copy your code from a04 to render the hero edit form
    let form = $(`<form id=` + hero.id + ` ></form>`)
    let i1 = $(`<input id = FirstName` + hero.id + ` type = "text" value =` + hero.first + `></input>`)
    let i2 = $(`<input id = LastName` + hero.id + ` type = "text" value =` + hero.last + `></input>`)
    let i3 = $(`<input id = HeroName` + hero.id + ` type = "text" value ="` + hero.name + `"></input>`)
    let i4 = $(`<textarea id = Description` + hero.id + ` rows = "10" cols = "150"></input>`).text(hero.description)
    let i5 = $(`<textarea id = Firstseendate` + hero.id + ` rows = "1" cols = "10"></input>`).text(hero.firstSeen)
    let save = $(`<button id=` + hero.id + ` class=save type=submit style="background-color: green;"></button>`).text("Save")
    let cancel = $(`<button id=` + hero.id + ` class=cancel style="background-color: green;"></button>`).text("Cancel")
    form.append("First Name:" + "<br>", i1, "<br>", "Last Name:" + "<br>", i2, "<br>", "Hero Name:" + "<br>", i3, "<br>", "Description:" + "<br>",
    i4, "<br>", "First Seen:" + "<br>", i5, "<br>", save, cancel)
    // div.append(form, )
    return form
};



/**
 * Handles the JavaScript event representing a user clicking on the "edit"
 *     button for a particular hero.
 * @param event  The JavaScript event that is being handled
 */
export const handleEditButtonPress = function(event) {
    // TODO: Render the hero edit form for the clicked hero and replace the
    //       hero's card in the DOM with their edit form instead
    console.log(event)
    console.log(event.data.heroes.find(h => h.id == event.target.id))
    let nextElement = document.getElementById(event.target.id).nextSibling
    let prevElement = document.getElementById(event.target.id).previousSibling
    document.getElementById(event.target.id).remove()
    if(nextElement == null){
        $(renderHeroEditForm(event.data.heroes.find(h => h.id == event.target.id))).insertAfter(prevElement)
    }
    else{
        $(renderHeroEditForm(event.data.heroes.find(h => h.id == event.target.id))).insertBefore(nextElement)
    }
    $("button.save").on("click", {heroes: event.data.heroes}, handleEditFormSubmit)
    $("button.cancel").on("click", {heroes: event.data.heroes}, handleCancelButtonPress)
};



/**
 * Handles the JavaScript event representing a user clicking on the "cancel"
 *     button for a particular hero.
 * @param event  The JavaScript event that is being handled
 */
export const handleCancelButtonPress = function(event) {
    // TODO: Render the hero card for the clicked hero and replace the
    //       hero's edit form in the DOM with their card instead
    let nextElement = document.getElementById(event.target.id).nextSibling
    let prevElement = document.getElementById(event.target.id).previousSibling
    document.getElementById(event.target.id).remove()
    if(nextElement == null){
        $(renderHeroCard(event.data.heroes.find(h => h.id == event.target.id))).insertAfter(prevElement)
    }
    else{
        $(renderHeroCard(event.data.heroes.find(h => h.id == event.target.id))).insertBefore(nextElement)
    }
    $("button.edit").on("click", {heroes: event.data.heroes}, handleEditButtonPress)
};



/**
 * Handles the JavaScript event representing a user clicking on the "cancel"
 *     button for a particular hero.
 * @param event  The JavaScript event that is being handled
 */
export const handleEditFormSubmit = function(event) {
    // TODO: Render the hero card using the updated field values from the
    //       submitted form and replace the hero's edit form in the DOM with
    //       their updated card instead
    console.log(event.data.heroes.find(h => h.id == event.target.id))
    event.data.heroes.find(h => h.id == event.target.id).last = document.getElementById("LastName" + event.target.id).value
    event.data.heroes.find(h => h.id == event.target.id).name = document.getElementById("HeroName" + event.target.id).value
    event.data.heroes.find(h => h.id == event.target.id).description = document.getElementById("Description" + event.target.id).value
    event.data.heroes.find(h => h.id == event.target.id).firstSeen = new Date(Date.parse(document.getElementById("Firstseendate" + event.target.id).value))
    event.data.heroes.find(h => h.id == event.target.id).first = document.getElementById("FirstName" + event.target.id).value
    let nextElement = document.getElementById(event.target.id).nextSibling
    let prevElement = document.getElementById(event.target.id).previousSibling
    document.getElementById(event.target.id).remove()
    if(nextElement == null){
        $(renderHeroCard(event.data.heroes.find(h => h.id == event.target.id))).insertAfter(prevElement)
    }
    else{
        $(renderHeroCard(event.data.heroes.find(h => h.id == event.target.id))).insertBefore(nextElement)
    }
    $("button.edit").on("click", {heroes: event.data.heroes}, handleEditButtonPress)
};



/**
 * Given an array of hero objects, this function converts the data into HTML,
 *     loads it into the DOM, and adds event handlers.
 * @param  heroes  An array of hero objects to load (see data.js)
 */
export const loadHeroesIntoDOM = function(heroes) {
    // Grab a jQuery reference to the root HTML element
    const $root = $('#root');

    // TODO: Generate the heroes using renderHeroCard()
    //       NOTE: Copy your code from a04 for this part
    let i
    let card = new Array(heroes.length)
    let first = new Array(heroes.length)
    for(i = 0; i < heroes.length; i++) {
        card[i] = renderHeroCard(heroes[i])
    }
    $root.append(card)
    // $root.append(renderHeroEditForm(heroes[1]))
    // TODO: Append the hero cards to the $root element
    //       NOTE: Copy your code from a04 for this part
    // TODO: Use jQuery to add handleEditButtonPress() as an event handler for
    //       clicking the edit button
    $("button.edit").on("click", {heroes: heroes}, handleEditButtonPress)
    // TODO: Use jQuery to add handleEditFormSubmit() as an event handler for
    //       submitting the form
    // TODO: Use jQuery to add handleCancelButtonPress() as an event handler for
    //       clicking the cancel button
};



/**
 * Use jQuery to execute the loadHeroesIntoDOM function after the page loads
 */
$(function() {
    loadHeroesIntoDOM(heroicData);
});

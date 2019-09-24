/**
 * Course: COMP 426
 * Assignment: a04
 * Author: Hongyi Zhang
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
    // TODO: Generate HTML elements to represent the hero
    // TODO: Return these elements as a string, HTMLElement, or jQuery object
    // Example: return `<div>${hero.name}</div>`;
    let div = $(`<div style="background-color:` + hero.backgroundColor + `;` + `"></div>`)
    let profile_pic = $("<img src=" + hero.img + ">")
    let first = hero.firstSeen
    let name = $(`<h1 style="color:` + hero.color + `;` + `"></h1>`).text(hero.name)
    let realn = $(`<p></p>`).text(hero.first + " " + hero.last)
    let info = $(`<p></p>`).text(hero.description)
    let span = $(`<span></span>`).append(info)
    let button = $(`<button style="background-color: green;"></button>`).text("Edit")
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
    // TODO: Generate HTML elements to represent the hero edit form
    // TODO: Return these elements as a string, HTMLElement, or jQuery object
    // Example: return `<form>${hero.name}</form>`;
    let form = $(`<form></form>`)
    let i1 = $(`<input name = "First Name" type = "text" value =` + hero.first + `></input>`)
    let i2 = $(`<input name = "Last Name" type = "text" value =` + hero.last + `></input>`)
    let i3 = $(`<input name = "Hero Name" type = "text" value =` + hero.name + `></input>`)
    let i4 = $(`<textarea name = "Description" rows = "50" cols = "150"></input>`).text(hero.description)
    let i5 = $(`<textarea name = "First seen date" rows = "1" cols = "10"></input>`).text(hero.firstSeen)
    form.append("First Name:" + "<br>", i1, "<br>", "Last Name:" + "<br>", i2, "<br>", "Hero Name:" + "<br>", i3, "<br>", "Description:" + "<br>",
    i4, "<br>", "First Seen:" + "<br>", i5, $(`<button type="submit" style="background-color: green;"></button>`).text("Save"),
    $(`<button style="background-color: green;"></button>`).text("Cancel"))
    return form
};



/**
 * Given an array of hero objects, this function converts the data into HTML and
 *     loads it into the DOM.
 * @param heroes  An array of hero objects to load (see data.js)
 */
export const loadHeroesIntoDOM = function(heroes) {
    // Grab a jQuery reference to the root HTML element
    const $root = $('#root');

    // TODO: Generate the heroes using renderHeroCard()
    let i
    let card = new Array(heroes.length)
    for(i = 0; i < heroes.length; i++) {
        $root.append(renderHeroCard(heroes[i]))
    }
    // TODO: Append the hero cards to the $root element
    // Pick a hero from the list at random
    // const randomHero = heroes[Math.floor(Math.random() * heroes.length)]

    // TODO: Generate the hero edit form using renderHeroEditForm()
    // let form = renderHeroEditForm(randomHero)
    // TODO: Append the hero edit form to the $root element
    // $root.append(form)
};



/**
 * Use jQuery to execute the loadHeroesIntoDOM function after the page loads
 */
$(function() {
    loadHeroesIntoDOM(heroicData);
});

import mpg_data from "./data/mpg_data";

/*
mpg_data is imported for you but that is for testing purposes only. All of the functions should use
a car_data param that is supplied as the first parameter.

As you write these functions notice how they could possibly be chained together to solve more complicated
queries.
 */

/**
 * @param {array} car_data - an instance of mpg_data that should be used for filtering.
 * @param minHorsepower {number}
 * @param minTorque {number}
 *
 * @return {array} An array of car objects with horsepower >= minHorsePower and torque >= minTorque
 * sorted by horsepower in descending order.
 *
 */
export function searchHighPower(car_data, minHorsepower, minTorque) {
    let mpg_data = JSON.parse(JSON.stringify(car_data))
    let i
    for(i = 0; i < mpg_data.length; i++) {
        if(mpg_data[i].horsepower < minHorsepower || mpg_data[i].torque < minTorque) {
            mpg_data.splice(i, 1)
            i--
        }
    }
    mpg_data.sort(function(a, b){return b.horsepower - a.horsepower})
    return mpg_data
}
// console.log(searchHighPower(mpg_data, 300, 250))

/**
 * @param {array} car_data
 * @param minCity
 * @param minHighway
 *
 *
 * @return {array} An array of car objects with highway_mpg >= minHighway and city_mpg >= minCity
 * sorted by highway_mpg in descending order
 *
 */
export function searchMpg(car_data, minCity, minHighway) {
    let mpg_data = JSON.parse(JSON.stringify(car_data))
    let i
    for(i = 0; i < mpg_data.length; i++) {
        if(mpg_data[i].city_mpg < minCity || mpg_data[i].highway_mpg < minHighway) {
            mpg_data.splice(i, 1)
            i--
        }
    }
    mpg_data.sort(function(a, b){return b.highway_mpg - a.highway_mpg})
    return mpg_data
}
// console.log(searchMpg(mpg_data, 20, 30))

/**
 * Find all cars where 'id' contains the search term below.
 * Sort the results so that if the term appears earlier in the string
 * it will appear earlier in the list. Make sure searching and sorting ignores case.
 * @param car_data
 * @param searchTerm A string to that is used for searching
 * @returns {[]} array of cars
 */
export function searchName(car_data, searchTerm) {
    let mpg_data = JSON.parse(JSON.stringify(car_data))
    let i
    for(i = 0; i < mpg_data.length; i++) {
        if(mpg_data[i].id.toLowerCase().search(searchTerm.toLowerCase()) == -1) {
            mpg_data.splice(i, 1)
            i--
        }
    }
    return mpg_data.sort(function(a, b){return a.id.toLowerCase().search(searchTerm.toLowerCase()) - b.id.toLowerCase().search(searchTerm.toLowerCase())})
}
// console.log(searchName(mpg_data, "Audi"))

/**
 * Find all cars made in the years asked for.
 *
 * @param car_data
 * @param {number[]} years - array of years to be included in the results e.g. [2010, 2012]
 * @returns {[]} an array of car objects
 */
export function searchByYear(car_data, years) {
    let mpg_data = new Array
    let i, j
    for(i = 0; i < years.length; i++) {
        for(j = 0; j < car_data.length; j++) {
            if(car_data[j].year == years[i]) {
                mpg_data.push(car_data[j])
            }
        }
    }
    return mpg_data.sort(function(a, b){return b.year - a.year})
}
// console.log(searchByYear(mpg_data, [2009, 2012]))
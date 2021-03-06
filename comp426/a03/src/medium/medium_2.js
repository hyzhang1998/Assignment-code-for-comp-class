import mpg_data from "./data/mpg_data";
import { getStatistics } from "./medium_1";

/*
This section can be done by using the array prototype functions.
https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array
see under the methods section
*/


/**
 * This object contains data that has to do with every car in the `mpg_data` object.
 *
 *
 * @param {allCarStats.avgMpg} Average miles per gallon on the highway and in the city. keys `city` and `highway`
 *
 * @param {allCarStats.allYearStats} The result of calling `getStatistics` from medium_1.js on
 * the years the cars were made.
 *
 * @param {allCarStats.ratioHybrids} ratio of cars that are hybrids
 */
export const allCarStats = {
    avgMpg: { city: mpg_data.reduce(function (sum, current) { return sum + current.city_mpg }, 0) / mpg_data.length, highway: mpg_data.reduce(function (sum, current) { return sum + current.highway_mpg }, 0) / mpg_data.length },
    allYearStats: getStatistics(getYearArray(mpg_data)),
    ratioHybrids: mpg_data.reduce(function (sum, current) { if (current.hybrid) { sum++ } return sum }, 0) / mpg_data.length,
}
// console.log(allCarStats)

function getYearArray(mpg) {
    let i
    let array = new Array
    for (i = 0; i < mpg.length; i++) {
        array.push(mpg[i].year)
    }
    return array
}


/**
 * HINT: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/reduce
 *
 * @param {moreStats.makerHybrids} Array of objects where keys are the `make` of the car and
 * a list of `hybrids` available (their `id` string). Don't show car makes with 0 hybrids. Sort by the number of hybrids
 * in descending order.
 *
 *[{
 *     "make": "Buick",
 *     "hybrids": [
 *       "2012 Buick Lacrosse Convenience Group",
 *       "2012 Buick Lacrosse Leather Group",
 *       "2012 Buick Lacrosse Premium I Group",
 *       "2012 Buick Lacrosse"
 *     ]
 *   },
 *{
 *     "make": "BMW",
 *     "hybrids": [
 *       "2011 BMW ActiveHybrid 750i Sedan",
 *       "2011 BMW ActiveHybrid 750Li Sedan"
 *     ]
 *}]
 *
 *
 *
 *
 * @param {moreStats.avgMpgByYearAndHybrid} Object where keys are years and each year
 * an object with keys for `hybrid` and `notHybrid`. The hybrid and notHybrid
 * should be an object with keys for `highway` and `city` average mpg.
 *
 * Only years in the data should be keys.
 *
 * {
 *     2020: {
 *         hybrid: {
 *             city: average city mpg,
 *             highway: average highway mpg
 *         },
 *         notHybrid: {
 *             city: average city mpg,
 *             highway: average highway mpg
 *         }
 *     },
 *     2021: {
 *         hybrid: {
 *             city: average city mpg,
 *             highway: average highway mpg
 *         },
 *         notHybrid: {
 *             city: average city mpg,
 *             highway: average highway mpg
 *         }
 *     },
 *
 * }
 */
export const moreStats = {
    makerHybrids: makerHybrids(mpg_data),
    avgMpgByYearAndHybrid: avgMpgByYear(mpg_data),
}
// console.log(moreStats.avgMpgByYearAndHybrid)

function avgMpgByYear(mpg){
    let i
    let mpg_data = JSON.parse(JSON.stringify(mpg))
    let year = new Array
    let yearData = new Array
    for(i = 0; i < mpg_data.length; i++) {
        if(year.indexOf(mpg_data[i].year) == -1) {
            year.push(mpg_data[i].year)
        }
    }
    for(i = 0; i < year.length; i++) {
        yearData.push(new Array)
    }
    for(i = 0; i < mpg_data.length; i++) {
        yearData[year.indexOf(mpg_data[i].year)].push(mpg_data[i])
    }
    let hybridNum = new Array
    for(i = 0; i < year.length; i++) {
        hybridNum[i] = 0
    }
    let j
    for(i = 0; i < year.length; i++) {
        for(j = 0; j < yearData[i].length; j++) {
            if(yearData[i][j].hybrid) {
                hybridNum[i]++
            }
        }
    }
    let avgMpg = {}
    for(i = 0; i < year.length; i++) {
        avgMpg[year[i]] = {
            hybrid: {
                city: yearData[i].reduce(function(sum, current){if(current.hybrid){return sum + current.city_mpg}else{return sum}}, 0)/hybridNum[i],
                highway: yearData[i].reduce(function(sum, current){if(current.hybrid){return sum + current.highway_mpg}else{return sum}}, 0)/hybridNum[i]
            },
            notHybrid: {
                city: yearData[i].reduce(function(sum, current){if(!current.hybrid){return sum + current.city_mpg}else{return sum}}, 0)/(yearData[i].length - hybridNum[i]),
                highway: yearData[i].reduce(function(sum, current){if(!current.hybrid){return sum + current.highway_mpg}else{return sum}}, 0)/(yearData[i].length - hybridNum[i])
            }
        }
    }
    return avgMpg
}

function makerHybrids(mpg) {
    let i
    let mpg_data = JSON.parse(JSON.stringify(mpg))
    let makerH = new Array
    for (i = 0; i < mpg_data.length; i++) {
        if (!mpg_data[i].hybrid) {
            mpg_data.splice(i, 1)
            i--
        }
    }
    for (i = 0; i < mpg_data.length; i++) {
        let j
        let contain = false
        for (j = 0; j < makerH.length; j++) {
            if(makerH[j].make == mpg_data[i].make){
                contain = true
            }
        }
        if (!contain) {
            makerH.push({
                make: mpg_data[i].make,
                hybrids: [mpg_data[i].id]
            })
        }
        else{
            for (j = 0; j < makerH.length; j++) {
                if (makerH[j].make == mpg_data[i].make) {
                    makerH[j].hybrids.push(mpg_data[i].id)
                }
            }
        }
    }
    return makerH
}
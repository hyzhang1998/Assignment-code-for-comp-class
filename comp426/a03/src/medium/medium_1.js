import {variance} from "./data/stats_helpers";


/**
 * Gets the sum of an array of numbers.
 * @param array
 * @returns {*}
 * see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array
 * prototype functions. Very useful
 */
export function getSum(array) {
    // let i
    // let sum = 0
    // for(i = 0; i < array.length; i++){
    //     sum += array[i]
    // }
    return array.reduce(function(sum, current){return sum + current}, 0)
}
// console.log(getSum([1,2,3,4]))
/**
 * Calculates the mean of an array of numbers.
 * @param {number[]} array
 * @returns {number|*}
 *
 * example:
 * let array = [3,2,5,6,2,7,4,2,7,5];
 * console.log(getMedian(array)); // 4.5
 */
export function getMedian(array) {
    array.sort(function(a, b){return b - a})
    let index = Math.ceil(array.length/2)
    let m = (array[index - 1] + array[index])/2
    // console.log("index is:", index)
    // console.log("array is:", array)
    if(array.length/2 - Math.floor(array.length/2) == 0){
        return m
    }
    else return array[index - 1]
}
// let a = [3,2,5,6,2,7,4,2,7,10,5]
// console.log("average:", getMedian(a))

/**
 * Calculates statistics (see below) on an array of numbers.
 * Look at the stats_helper.js file. It does variance which is used to calculate std deviation.
 * @param {number[]} array
 * @returns {{min: *, median: *, max: *, variance: *, mean: *, length: *, sum: *, standard_deviation: *}}
 *
 * example:
 * getStatistics([3,2,4,5,5,5,2,6,7])
 * {
  length: 9,
  sum: 39,
  mean: 4.333333333333333,
  median: 5,
  min: 2,
  max: 7,
  variance: 2.6666666666666665,
  standard_deviation: 1.632993161855452
 }
 */
export function getStatistics(array) {
    let dic = {
        length: array.length,
        sum: getSum(array),
        mean: getSum(array)/array.length,
        median: getMedian(array),
        min: array.reduce(function(min, current){return (min < current) ? min : current}),
        max: array.reduce(function(max, current){return (max > current) ? max : current}),
        variance: variance(array, getSum(array)/array.length),
        standard_deviation: Math.sqrt(variance(array, getSum(array)/array.length))
    }
    return dic
}
// console.log(getStatistics([3,2,4,5,5,5,2,6,7]))


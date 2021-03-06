/**
 *
 * @param {number} a
 * @param {number} b
 * @returns {string} 'a + b = (a + b)'
 *
 * example: sumToString(3, 4)
 * returns: '3 + 4 = 7'
 * see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Template_literals
 */
export function sumToString(a, b) {
    let sum = a + b
    let string = a + " + " + b + " = " + sum
    return string
}


/**
 *
 * @param {number} startNumber
 * @param {number} endNumber
 * @returns {number[]}
 *
 * example: getIncreasingArray(3, 7)
 * returns: [ 3, 4, 5, 6, 7 ]
 *
 */
export function getIncreasingArray(startNumber, endNumber) {
    let i;
    let length = endNumber - startNumber + 1
    let array = []
    for(i = 0; i < length; i++){
        array.push(startNumber + i)
    }
    return array
}

/**
 *
 * @param {number[]} numbers
 * @return {{min: number, max: number}}
 * see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Spread_syntax
 * and https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Math
 */
export function maxAndMin(numbers) {
    numbers.sort(function(a, b){return a - b})
    return {
        min: numbers[0],
        max: numbers[numbers.length - 1]
    }
}
// console.log(maxAndMin([ 10, 10, 11, 4, 5, 5, 6, 7, 8, 9 ]))

/**
 *
 * @param array - An array of any primitive type
 * @returns {object} Object where the keys are the values that were passed in
 * and the value was the number of times it occurred.
 *
 * example: countArray([3, 6, 3, 2, 2, 3, 'some', 'hello', 'some', [1, 2]])
 * returns: {'2': 2, '3': 3, '6': 1, some: 2, hello: 1, '1,2': 1}
 *
 */
export function countArray(array) {
    let i, j
    let count = new Array
    for(i = 0; i < array.length; i++){
        count.push(1)
    }
    for(i = 0; i < array.length; i++){
        for(j = i + 1; j < array.length; j++){
            if(JSON.stringify(array[j]) == JSON.stringify(array[i])){
                count[i]++
                count.splice(j, 1)
                array.splice(j, 1)
                j--
            }
        }
    }
    // let out = function(a){
    //     let string;
    //     for(i = 0; i < a.length; i++){
    //         this[a[i]] = count[i]
    //     }
    // }
    let dic = {}
    for(i = 0; i < array.length; i++){
        dic[array[i]] = count[i]
    }
    return dic
}
// console.log(countArray([{}, {}, [34, 43], [33, 33], {}, {}, [34, 43]]))

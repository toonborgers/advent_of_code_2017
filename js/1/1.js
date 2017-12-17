let fileUtil = require("../util/fileUtil");

let exercise1 = (input) =>
    input.split("")
        .map(num => parseInt(num))
        .filter((item, index, arr) =>
            item === arr[(index + 1) % arr.length]
        )
        .reduce((a, b) => a + b, 0);


console.log(exercise1(fileUtil.readFile("input1.txt")));
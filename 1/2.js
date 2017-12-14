function exercise1Part2(input) {
    return input.split("")
        .map(function (num) {
            return parseInt(num);
        })
        .filter(function (item, index, arr) {
            return item === arr[(index + arr.length/2) % arr.length];
        })
        .reduce(function (a, b) {
            return a + b;
        }, 0);
}
function exercise1(input) {
    return input.split("")
        .map(function (num) {
            return parseInt(num);
        })
        .filter(function (item, index, arr) {
            return item === arr[(index + 1) % arr.length];
        })
        .reduce(function (a, b) {
            return a + b;
        }, 0);
}
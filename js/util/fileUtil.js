let fs = require("fs");

let readFile = name => fs.readFileSync(name).toString('utf8');

exports.readFile = readFile;
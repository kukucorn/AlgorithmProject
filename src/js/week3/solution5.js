// const readline = require("readline");
// const rl = readline.createInterface({
//     input: process.stdin,
//     output: process.stdout,
// });

// rl.on("line", function (line) {
//     const N = Number(line);

//     console.log(solution(N));
//     rl.close();
// }).on("close", function () {
//     process.exit();
// });

let fs = require("fs");
let input = fs.readFileSync("/dev/stdin").toString();

console.log(solution(Number(input)));

function solution(N) {
    let primeArr = [];
    let current = 1;
    let count = 0;
    while (current <= N) {
        if (isPrime(current)) {
            primeArr.push(current);
            let sumOfArr = sum(primeArr);
            while (sumOfArr > N) {
                sumOfArr -= primeArr.shift();
            }

            if (sumOfArr === N) {
                count++;
            }
        }
        current++;
    }
    return count;
}

function isPrime(num) {
    if (num === 1) return false;
    if (num === 2) return true;
    if (num % 2 === 0) return false;

    for (let i = 3; i <= Math.sqrt(num); i += 2) {
        if (num % i === 0) return false;
    }

    return true;
}

function sum(arr) {
    return arr.reduce((total, current) => (total += current), 0);
}

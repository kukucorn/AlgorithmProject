const readline = require("readline");

const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

let inputLineCnt = 0;

let N = 0;
let K = 0;
let coords = [];

rl.on("line", function (line) {
    line = line.trim();
    if (inputLineCnt === 0) {
        N = Number(line);
    } else if (inputLineCnt === 1) {
        K = Number(line);
    } else {
        coords = line.split(" ").map(Number);
        console.log(solution(N, K, coords));
        rl.close();
    }

    inputLineCnt++;
}).on("close", function () {
    process.exit();
});

function solution(N, K, coords) {
    coords.sort((a, b) => a - b);

    const dists = coords.reduce(
        (dists, _, idx, array) => {
            if (idx < array.length - 1) dists.push(Math.abs(array[idx] - array[idx + 1]));
            return dists;
        },
        [0]
    );

    dists.sort((a, b) => b - a);

    const rests = dists.slice(K - 1);

    return rests.reduce((total, curr) => (total += curr), 0);
}

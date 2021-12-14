const readline = require("readline");

const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

const input = [];

rl.on("line", (line) => {
    input.push(line.trim());
}).on("close", () => {
    const [height, width] = input[0].split(" ").map(Number);
    const cheeseMap = input.slice(1).map((line) => line.split(" ").map(Number));

    const { hours, lastCheesePieceCnt } = solution(height, width, cheeseMap);

    console.log(hours);
    console.log(lastCheesePieceCnt);

    process.exit();
});

function solution(height, width, cheeseMap) {
    let hours = 0;
    let lastCheesePieceCnt = 0;
    while (true) {
        const exposedCheesePosArr = findExposedCheese(height, width, cheeseMap);

        if (exposedCheesePosArr.length === 0) break;

        exposedCheesePosArr.forEach(({ h, w }) => (cheeseMap[h][w] = 0));

        hours++;
        lastCheesePieceCnt = exposedCheesePosArr.length;
    }

    return { hours, lastCheesePieceCnt };
}

function findExposedCheese(height, width, cheeseMap) {
    const exposedCheeseArr = [];
    const isVisited = Array.from(Array(height), () => Array(width).fill(false));
    const dir = [
        [-1, 0],
        [0, 1],
        [1, 0],
        [0, -1],
    ];

    let queue = [{ h: 0, w: 0 }];
    isVisited[0][0] = true;

    while (true) {
        if (queue.length === 0) break;
        const nextQueue = [];
        queue.forEach(({ h, w }) => {
            const surroundPosArr = dir.map(([wDir, hDir]) => {
                return { w: w + wDir, h: h + hDir };
            });
            const posArrInBoundary = surroundPosArr.filter(({ h, w }) => 0 <= h && h < height && 0 <= w && w < width);
            const posArrNotVisited = posArrInBoundary.filter(({ h, w }) => !isVisited[h][w]);

            posArrNotVisited.forEach(({ h, w }) => {
                isVisited[h][w] = true;
                if (cheeseMap[h][w] === 0) nextQueue.push({ h, w });
                if (cheeseMap[h][w] === 1) exposedCheeseArr.push({ h, w });
            });
        });
        queue = nextQueue;
    }

    return exposedCheeseArr;
}

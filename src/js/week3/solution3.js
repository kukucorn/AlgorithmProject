const readline = require("readline");

const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

rl.on("line", function (line) {
    const [N, K] = line.trim().split(" ").map(Number);
    console.log(solution(N, K));
    rl.close();
}).on("close", function () {
    process.exit();
});

function solution(N, K) {
    return bfs(N, K);
}

function bfs(N, K) {
    let isVisited = Array(100001).fill(false);

    let time = 0;
    let posQueue = [];

    posQueue.push(N);
    isVisited[N] = true;

    while (true) {
        let queueSize = posQueue.length;

        if (queueSize.length === 0) break;

        // 텔레포트는 0 비용
        for (let i = 0; i < queueSize; i++) {
            const pos = posQueue[i];

            if (pos === 0) continue;

            let telepotedPos = pos * 2;
            while (telepotedPos <= K * 2) {
                if (telepotedPos > 100000) break;
                if (isVisited[telepotedPos]) {
                    telepotedPos *= 2;
                    continue;
                }
                posQueue.push(telepotedPos);
                isVisited[telepotedPos] = true;
                telepotedPos *= 2;
            }
        }

        queueSize = posQueue.length;
        for (let i = 0; i < queueSize; i++) {
            const pos = posQueue.shift();

            if (pos === K) return time;

            const leftPos = pos - 1;
            if (0 <= leftPos && leftPos <= 100000 && !isVisited[leftPos]) {
                posQueue.push(leftPos);
                isVisited[leftPos] = true;
            }
            const rightPos = pos + 1;
            if (0 <= rightPos && rightPos <= 100000 && !isVisited[rightPos]) {
                posQueue.push(rightPos);
                isVisited[rightPos] = true;
            }
        }

        time++;
    }
}

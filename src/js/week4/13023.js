const readline = require("readline");

const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

const input = [];

rl.on("line", (line) => {
    input.push(line.trim());
}).on("close", () => {
    const [N, M] = input[0].split(" ").map(Number);

    const graph = Array.from(Array(N), () => []);

    input
        .slice(1)
        .map((line) => line.split(" ").map(Number))
        .forEach(([v1, v2]) => {
            graph[v1].push(v2);
            graph[v2].push(v1);
        });

    console.log(solution(N, M, graph));

    process.exit();
});

function solution(N, M, graph) {
    const found = graph.some((_, idx) => {
        const isVisited = Array.from(Array(N), () => false);

        if (existABCDE(graph, isVisited, idx, 0)) return true;

        return false;
    });

    return found ? 1 : 0;
}

function existABCDE(graph, isVisited, idx, depth) {
    if (depth >= 4) return true;

    isVisited[idx] = true;

    for (let friendIdx of graph[idx]) {
        if (isVisited[friendIdx]) continue;

        if (existABCDE(graph, isVisited, friendIdx, depth + 1)) return true;
    }

    isVisited[idx] = false;

    return false;
}

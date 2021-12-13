const readline = require("readline");

const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

let inputLineCnt = 0;

let N = 0;
let n1 = 0;
let n2 = 0;
let nodes = [];

rl.on("line", function (line) {
    if (inputLineCnt === 0) {
        [N, n1, n2] = line.split(" ").map(Number);
        nodes = Array.from(Array(N + 1), () => []);
    } else {
        const [node1, node2, length] = line.split(" ").map(Number);

        nodes[node1].push({ node: node2, length });
        nodes[node2].push({ node: node1, length });
    }

    inputLineCnt++;
    if (inputLineCnt === N) {
        console.log(solution(nodes, n1, n2));
        rl.close();
    }
}).on("close", function () {
    process.exit();
});

function solution(nodes, node1, node2) {
    const tree = new Tree(nodes);

    // dfs로 1번 노드에서의 거리와 경로를 기록
    const { totalDistance, maxLength } = tree.dfs(-1, node1, node2, 0);

    return totalDistance - maxLength;
}

function Tree(nodes) {
    this.dfs = (prevNode, curNode, findNode, distance) => {
        if (curNode === findNode) return { totalDistance: distance, maxLength: 0 };

        return nodes[curNode]
            .filter(({ node }) => node !== prevNode)
            .reduce((prev, { node, length }) => {
                if (prev) return prev;

                let result = this.dfs(curNode, node, findNode, distance + length);

                if (result) result.maxLength = Math.max(result.maxLength, length);

                return result;
            }, null);
    };

    return this;
}

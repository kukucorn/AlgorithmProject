const readline = require("readline");

const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

const input = [];

rl.on("line", (line) => {
    input.push(line.trim());
}).on("close", () => {
    const n = Number(input[0]);
    const posArr = input.slice(1, n + 1).map((line) => line.split(" ").map(Number));
    const d = Number(input[n + 1]);

    const maxNum = solution(n, posArr, d);

    console.log(maxNum);

    process.exit();
});

function solution(n, posArr, d) {
    const posArrArranged = posArr.map(([house, office]) => {
        return { left: Math.min(house, office), right: Math.max(house, office) };
    });
    const posArrSortedByLeft = posArrArranged.sort((a, b) => (a.right === b.right ? a.left - b.left : a.right - b.right));

    const pQueue = new PriorityQueue((posA, posB) => (posB.left - posA.left > 0 ? true : false));

    const arr = posArrSortedByLeft.map((pos, idx, self) => {
        pQueue.push(pos);

        const boundary = { left: pos.right - d, right: pos.right };
        while (!pQueue.isEmpty()) {
            if (boundary.left <= pQueue.peek().left) break;
            pQueue.pop();
        }

        return pQueue.size();
    });

    return arr.reduce((prev, curr) => Math.max(prev, curr), 0);
}

const top = 0;
const parent = (i) => ((i + 1) >>> 1) - 1;
const left = (i) => (i << 1) + 1;
const right = (i) => (i + 1) << 1;

class PriorityQueue {
    constructor(comparator = (a, b) => a > b) {
        this._heap = [];
        this._comparator = comparator;
    }
    size() {
        return this._heap.length;
    }
    isEmpty() {
        return this.size() == 0;
    }
    peek() {
        return this._heap[top];
    }
    push(...values) {
        values.forEach((value) => {
            this._heap.push(value);
            this._siftUp();
        });
        return this.size();
    }
    pop() {
        const poppedValue = this.peek();
        const bottom = this.size() - 1;
        if (bottom > top) {
            this._swap(top, bottom);
        }
        this._heap.pop();
        this._siftDown();
        return poppedValue;
    }
    replace(value) {
        const replacedValue = this.peek();
        this._heap[top] = value;
        this._siftDown();
        return replacedValue;
    }
    _greater(i, j) {
        return this._comparator(this._heap[i], this._heap[j]);
    }
    _swap(i, j) {
        [this._heap[i], this._heap[j]] = [this._heap[j], this._heap[i]];
    }
    _siftUp() {
        let node = this.size() - 1;
        while (node > top && this._greater(node, parent(node))) {
            this._swap(node, parent(node));
            node = parent(node);
        }
    }
    _siftDown() {
        let node = top;
        while ((left(node) < this.size() && this._greater(left(node), node)) || (right(node) < this.size() && this._greater(right(node), node))) {
            let maxChild = right(node) < this.size() && this._greater(right(node), left(node)) ? right(node) : left(node);
            this._swap(node, maxChild);
            node = maxChild;
        }
    }
}

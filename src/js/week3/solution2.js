const path = require("path");
const readline = require("readline");
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
});

let count = 0;
let N = 0;
let L = 0;
let map = [];

rl.on("line", function (line) {
    const inputArr = line.split(" ").map(Number);

    if (count === 0) [N, L] = inputArr;
    else map.push(inputArr);

    if (count++ === N) {
        console.log(solution(N, L, map));
        rl.close();
    }
}).on("close", function () {
    process.exit();
});

function solution(N, L, mapArr) {
    // 길 추출(가로, 세로)
    const widthPaths = mapArr;
    const heightPaths = Array.from(Array(N), () => []).map((arr, index, array) => {
        mapArr.forEach((rowArr) => {
            arr.push(rowArr[index]);
        });
        return arr;
    });

    // 갈 수 있는 길인지?
    const allPaths = [...widthPaths, ...heightPaths];
    const passablePathCount = allPaths.filter((arr) => passablePath(arr, L)).length;

    return passablePathCount;
}

function passablePath(pathArr, slopeLength) {
    let pos = 0;
    while (pos < pathArr.length - 1) {
        const gap = pathArr[pos] - pathArr[pos + 1];

        if (gap === 0) {
            pos++;
            continue;
        }

        //  높이 차이가 1인지
        if (Math.abs(gap) !== 1) return false;

        if (gap === -1) {
            // 올라가는 경사로
            // 범위를 벗어남
            if (pos + 1 - slopeLength < 0) return false;
            // 평평하지 않음
            if (pathArr.slice(pos + 1 - slopeLength, pos + 1).some((elem) => elem !== pathArr[pos])) return false;
            // 이전에 내려가는 경사로가 있었던 경우
            const from = pos + 1 - slopeLength * 2 > 0 ? pos + 1 - slopeLength * 2 : 0;
            const to = pos + 1 - slopeLength > 0 ? pos + 1 - slopeLength : 0;
            if (
                to - from >= 0 &&
                pathArr.slice(from, to).some((_, index, arr) => {
                    if (index + 1 >= arr.length) return false;
                    const gap = arr[index] - arr[index + 1];
                    return gap === 1;
                })
            )
                return false;

            pos++;
        } else if (gap === 1) {
            // 내려가는 경사로
            // 범위를 벗어남
            if (pos + slopeLength >= pathArr.length) return false;
            // 평평하지 않음
            if (pathArr.slice(pos + 1, pos + 1 + slopeLength).some((elem) => elem !== pathArr[pos + 1])) return false;

            //  경사로 놓고 앞으로 가기
            pos += slopeLength;
        }

        // 경사로를 연속으로 놓을 수 있는 상황이 있음...
    }
    return true;
}

package shortestPathProblem.floydWarshall;

// https://programmers.co.kr/learn/courses/30/lessons/49191

public class Rank {
    public static void main(String[] args) {
        int n = 5;
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        System.out.println(new Rank().solution(n, results));
    }

    public int solution(int n, int[][] results) {
        int[][] graph = initGraph(n, results);
        int[][] dist = AllPairShortestPath.floydWarshall(graph);
        int cnt = countSettledPerson(dist);
        return cnt;
    }

    private int[][] initGraph(int n, int[][] results) {
        int[][] graph = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) graph[i][j] = 0;
                else graph[i][j] = 101;
            }
        }
        for(int[] result : results) {
            int winner = result[0] - 1;
            int loser = result[1] - 1;
            graph[winner][loser] = 1;
        }
        return graph;
    }


    private int countSettledPerson(int[][] dist) {
        int cnt = 0;
        for(int i = 0; i < dist.length; i++) {
            if(countWinnerFrom(dist, i) + countLoserFrom(dist, i) == dist.length - 1) cnt++;
        }
        return cnt;
    }

    private int countWinnerFrom(int[][] dist, int num) {
        int cnt = 0;
        for(int j = 0; j < dist.length; j++) {
            if(j != num && dist[num][j] != 101) cnt++;
        }
        return cnt;
    }

    private int countLoserFrom(int[][] dist, int num) {
        int cnt = 0;
        for(int i = 0; i < dist.length; i++) {
            if(i != num && dist[i][num] != 101) cnt++;
        }
        return cnt;
    }
}

class AllPairShortestPath {
    public static int[][] floydWarshall(int graph[][]) {
        int dist[][] = new int[graph.length][graph.length];
        int i, j, k;

        for (i = 0; i < graph.length; i++) {
            for (j = 0; j < graph.length; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for (k = 0; k < graph.length; k++) {
            for (i = 0; i < graph.length; i++) {
                for (j = 0; j < graph.length; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        return dist;
    }
}
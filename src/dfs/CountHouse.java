package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CountHouse {

    final static int[][] direction = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for(int i = 0; i < N; ++i) {
            String rowOfMap = br.readLine();
            for(int j = 0; j < N; ++j) {
                map[i][j] = rowOfMap.charAt(j) - '0';
            }
        }

        boolean[][] isVisited = new boolean[N][N];
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < N; ++j) {
                if(map[i][j] == 0) isVisited[i][j] = true;
            }
        }

        List<Integer> houseList = new ArrayList<>();
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < N; ++j) {
                if(!isVisited[i][j]) {
                    int sizeOfHouse = dfs(map, i, j, isVisited);
                    houseList.add(sizeOfHouse);
                }
            }
        }

        Collections.sort(houseList);

        System.out.println(houseList.size());
        for(int houseSize : houseList) {
            System.out.println(houseSize);
        }


        br.close();
    }

    private static int dfs(int[][] map, int i, int j, boolean[][] isVisited) {
        int sizeOfHouse = 0;

        isVisited[i][j] = true;
        sizeOfHouse++;

        for(int a = 0; a < 4; ++a) {
            int newI = i + direction[a][0];
            int newJ = j + direction[a][1];

            if(newI < 0 || newI >= map.length) continue;
            if(newJ < 0 || newJ >= map.length) continue;

            if(!isVisited[newI][newJ]) {
                sizeOfHouse += dfs(map, newI, newJ, isVisited);
            }
        }

        return sizeOfHouse;
    }


}
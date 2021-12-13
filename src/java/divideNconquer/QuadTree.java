package divideNconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1992
public class QuadTree {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] image = new int[N][N];
        for(int i = 0; i < N; ++i) {
            String rowOfImage = br.readLine();
            for(int j = 0; j < N; ++j) {
                image[i][j] = rowOfImage.charAt(j) - '0';
            }
        }

        String compactedImage = compactImage(image, new Point(0, 0), N);

        System.out.println(compactedImage);

        br.close();
    }

    private static String compactImage(int[][] image, Point point, int compactSize) {
        String result = "";
        int compactColor = image[point.getX()][point.getY()];
        if(canCompact(image, point, compactSize, compactColor)) {
            result += Integer.toString(compactColor);
        } else {
            int halfOfCompactSize = compactSize / 2;
            result += "(";
            result += compactImage(image, new Point(point.getX(), point.getY()), halfOfCompactSize);
            result += compactImage(image, new Point(point.getX(), point.getY() + halfOfCompactSize), halfOfCompactSize);
            result += compactImage(image, new Point(point.getX() + halfOfCompactSize , point.getY()), halfOfCompactSize);
            result += compactImage(image, new Point(point.getX() + halfOfCompactSize, point.getY() + halfOfCompactSize), halfOfCompactSize);
            result += ")";
        }

        return result;
    }

    private static boolean canCompact(int[][] image, Point point, int compactSize, int compactColor) {
        for(int i = point.getX(); i < point.getX() + compactSize; ++i) {
            for(int j = point.getY(); j < point.getY() + compactSize; ++j) {
                if(image[i][j] != compactColor) {
                    return false;
                }
            }
        }
        return true;
    }
}

class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
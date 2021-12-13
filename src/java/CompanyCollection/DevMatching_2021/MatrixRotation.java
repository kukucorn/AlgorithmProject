package CompanyCollection.DevMatching_2021;

import java.util.ArrayList;
import java.util.List;

public class MatrixRotation {
    public static void main(String[] args) {
//		1번 예시
//		int rows = 6;
//		int columns = 6;
//		int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};

//		2번 예시
//		int rows = 3;
//		int columns = 3;
//		int[][] queries = {{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}};

//		3번 예시
        int rows = 100;
        int columns = 97;
        int[][] queries = {{1,1,100,97}};
        int[] answers = new MatrixRotation().solution(rows, columns, queries);
        for(int answer : answers) {
            System.out.println(answer);
        }
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        List<Integer> answerList = new ArrayList<>();

        // 테이블 만들기
        int[][] table = initialTable(rows, columns);

        for(int[] query : queries) {
            // query to point
            Point leftTopPoint = new Point(query[0], query[1]);
            Point rightBottumPoint = new Point(query[2], query[3]);

            // 회전시킬 요소 찾기
            List<Integer> rotationElements = findRotationElements(table, leftTopPoint, rightBottumPoint);

            // 요소들 중 가장 작은 숫자
            answerList.add(findMinElement(rotationElements));

            // 회전시키기
            rotation(table, rotationElements, leftTopPoint, rightBottumPoint);
        }

        return answerList.stream().mapToInt(a -> a).toArray();
    }

    private void rotation(int[][] table, List<Integer> rotationElements, Point leftTopPoint, Point rightBottumPoint) {
        // 마지막 요소를 첫번째 요소로
        rotationElements.add(0, rotationElements.remove(rotationElements.size()-1));

        // 회전
        rotateLeftTopToRightTopElements(table, rotationElements, leftTopPoint, rightBottumPoint.getColumn());
        rotateRightTopToRightBottumElements(table, rotationElements, leftTopPoint.getRow(), rightBottumPoint);
        rotateRightBottumToLeftBottumElements(table, rotationElements, rightBottumPoint, leftTopPoint.getColumn());
        rotateLeftBottumToLeftTopElements(table, rotationElements, rightBottumPoint.getRow(), leftTopPoint);
    }

    private int findMinElement(List<Integer> rotationElements) {
        int min = Integer.MAX_VALUE;
        for(int element : rotationElements) {
            min = Math.min(min, element);
        }
        return min;
    }

    private List<Integer> findRotationElements(int[][] table, Point leftTopPoint, Point rightBottumPoint) {
        List<Integer> rotationElements = new ArrayList<>();
        rotationElements.addAll(findLeftTopToRightTopElements(table, leftTopPoint, rightBottumPoint.getColumn()));
        rotationElements.addAll(findRightTopToRightBottumElements(table, leftTopPoint.getRow(), rightBottumPoint));
        rotationElements.addAll(findRightBottumToLeftBottumElements(table, rightBottumPoint, leftTopPoint.getColumn()));
        rotationElements.addAll(findLeftBottumToLeftTopElements(table, rightBottumPoint.getRow(), leftTopPoint));
        return rotationElements;
    }

    private List<Integer> findLeftBottumToLeftTopElements(int[][] table, int rightBottumPointRow, Point leftTopPoint) {
        List<Integer> elements = new ArrayList<>();
        for(int row = rightBottumPointRow; row > leftTopPoint.getRow(); row--) {
            elements.add(table[row][leftTopPoint.getColumn()]);
        }
        return elements;
    }

    private List<Integer> findRightBottumToLeftBottumElements(int[][] table, Point rightBottumPoint, int leftTopPointColumn) {
        List<Integer> elements = new ArrayList<>();
        for(int column = rightBottumPoint.getColumn(); column > leftTopPointColumn; column--) {
            elements.add(table[rightBottumPoint.getRow()][column]);
        }
        return elements;
    }

    private List<Integer> findRightTopToRightBottumElements(int[][] table, int leftTopPointRow, Point rightBottumPoint) {
        List<Integer> elements = new ArrayList<>();
        for(int row = leftTopPointRow; row < rightBottumPoint.getRow(); row++) {
            elements.add(table[row][rightBottumPoint.getColumn()]);
        }
        return elements;
    }

    private List<Integer> findLeftTopToRightTopElements(int[][] table, Point leftTopPoint, int rightTopColumn) {
        List<Integer> elements = new ArrayList<>();
        for(int column = leftTopPoint.getColumn(); column < rightTopColumn; column++) {
            elements.add(table[leftTopPoint.getRow()][column]);
        }
        return elements;
    }

    private void rotateLeftBottumToLeftTopElements(int[][] table, List<Integer> rotationElements, int rightBottumPointRow, Point leftTopPoint) {
        for(int row = rightBottumPointRow; row > leftTopPoint.getRow(); row--) {
            table[row][leftTopPoint.getColumn()] = rotationElements.remove(0);
        }
    }

    private void rotateRightBottumToLeftBottumElements(int[][] table, List<Integer> rotationElements, Point rightBottumPoint, int leftTopPointColumn) {
        for(int column = rightBottumPoint.getColumn(); column > leftTopPointColumn; column--) {
            table[rightBottumPoint.getRow()][column] = rotationElements.remove(0);
        }
    }

    private void rotateRightTopToRightBottumElements(int[][] table, List<Integer> rotationElements, int leftTopPointRow, Point rightBottumPoint) {
        for(int row = leftTopPointRow; row < rightBottumPoint.getRow(); row++) {
            table[row][rightBottumPoint.getColumn()] = rotationElements.remove(0);
        }
    }

    private void rotateLeftTopToRightTopElements(int[][] table, List<Integer> rotationElements, Point leftTopPoint, int rightTopColumn) {
        for(int column = leftTopPoint.getColumn(); column < rightTopColumn; column++) {
            table[leftTopPoint.getRow()][column] = rotationElements.remove(0);
        }
    }

    private int[][] initialTable(int rows, int columns) {
        int[][] table = new int[rows][columns];
        int num = 1;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                table[i][j] = num++;
            }
        }
        return table;
    }
}

class Point {
    private int row;
    private int column;

    public Point(int x, int y) {
        this.row = x - 1;
        this.column = y - 1;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
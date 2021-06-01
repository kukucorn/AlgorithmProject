import java.util.*;

public class Solution {

	public static void main(String[] args) {

//		1번 예제 ans = 11
//		int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};

//		2번 예제 ans = -1
		int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}};

		System.out.println(new Solution().solution(maps));
	}

	public int solution(int[][] maps) {
		ShortestPathFinder shortestPathFinder = new ShortestPathFinder(maps);
		Point startPoint = new Point(0, 0);
		shortestPathFinder.findShortestPath(startPoint);
		return shortestPathFinder.isExistShortestPath() ? shortestPathFinder.getShortestPathLength() : -1;
	}
}

class ShortestPathFinder {
	private int[][] matrix;
	private boolean[][] isVisited;
	private int shortestPathLength;
	private final int ROW_LENGTH;
	private final int COL_LENGTH;
	private boolean existShortestPath;

	public ShortestPathFinder(int[][] matrix) {
		this.matrix = matrix;
		this.ROW_LENGTH = matrix.length;
		this.COL_LENGTH = matrix[0].length;
		initialIsVisited();
		this.shortestPathLength = 0;
		this.existShortestPath = false;
	}

	private void initialIsVisited() {
		this.isVisited = new boolean[ROW_LENGTH][COL_LENGTH];

		for(int r = 0; r < ROW_LENGTH; r++) {
			for(int c = 0; c < COL_LENGTH; c++) {
				if(matrix[r][c] == 0) isVisited[r][c] = true;
			}
		}
	}

	public void findShortestPath(Point start) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(start);

		while(!queue.isEmpty()) {
			this.shortestPathLength++;
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				Point point = queue.poll();
				if(isFinishPoint(point)) {
					this.existShortestPath = true;
					return;
				}
				List<Point> unvisitedPoints = findUnvisitedPoints(point);
				for(Point unvisitedPoint : unvisitedPoints) {
					queue.add(unvisitedPoint);
				}
			}
		}
	}

	private boolean isFinishPoint(Point point) {
		return point.getX() == ROW_LENGTH - 1 && point.getY() == COL_LENGTH - 1 ? true : false;
	}

	private List<Point> findUnvisitedPoints(Point point) {
		final int[][] arr = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		List<Point> unvisitedPoints = new ArrayList<>();
		for(int i = 0; i < 4; i++) {
			int newX = point.getX() + arr[i][0];
			int newY = point.getY() + arr[i][1];

			if(0 <= newX && newX < this.ROW_LENGTH) {
				if(0 <= newY && newY < this.COL_LENGTH) {
					if(!this.isVisited[newX][newY]) {
						this.isVisited[newX][newY] = true;
						unvisitedPoints.add(new Point(newX, newY));
					}
				}
			}
		}
		return unvisitedPoints;
	}

	public int getShortestPathLength() {
		return shortestPathLength;
	}

	public boolean isExistShortestPath() {
		return existShortestPath;
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
		return x;
	}

	public int getY() {
		return y;
	}
}
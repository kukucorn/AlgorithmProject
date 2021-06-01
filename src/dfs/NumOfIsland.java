package dfs;//package dfs;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class NumOfIsland {
//	
//	final static int SEA = 0;
//	final static int LAND = 1;
//	
//	// 0시 부터 시계방향으로(위, 오른쪽 위, 오른쪽, ...)
//	final static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
//	final static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder result = new StringBuilder();
//		
//		do {
//			String[] conditions = scan.readLine().split(" ");
//			int width = Integer.parseInt(conditions[0]);
//			int height = Integer.parseInt(conditions[1]);
//			if(width == 0 && height == 0) break;
//			
//			int[][] map = new int[height][width];
//			makeMap(scan, map, width, height);
//			
//			int numOfGroup = findIsland(map, width, height);
//			
//			result.append(numOfGroup + "\n");
//		} while(true);
//		
//		System.out.print(result);
//		
//		scan.close();
//	}
//	
//	private static void makeMap(BufferedReader scan, int[][] map, int width, int height) throws IOException {
//		for(int i = 0; i < height; i++) {
//			String[] line = scan.readLine().split(" ");
//			for(int j = 0; j < width; j++) {
//				map[i][j] = Integer.parseInt(line[j]);
//			}
//		}
//	}
//	
//	private static int findIsland(int[][] map, int width, int height) {
//		
//		int numOfIsland = 0;
//		for(int x = 0; x < height; x++) {
//			for(int y = 0; y < width; y++) {
//				if(map[x][y] == LAND) {
//					numOfIsland++;
//					findNearIsland(map, width, height, x, y);
//				}
//			}
//		}
//		return numOfIsland;
//	}
//	
//	private static void findNearIsland(int[][] map, int width, int height, int xIndex, int yIndex) {
//		map[xIndex][yIndex] = SEA;
//		
//		for(int i = 0; i < 8; i++) {
//			int x = xIndex + dx[i];
//			int y = yIndex + dy[i];
//			
//			if(0 <= x && x < height && 0 <= y && y < width) {
//				if(map[x][y] == LAND) findNearIsland(map, width, height, x, y);
//			}
//		}
//		
//		return;
//	}
//}
//

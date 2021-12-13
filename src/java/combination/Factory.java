package combination;

import java.util.*;

public class Factory {
	public static void main(String[] args) {
		// 1¹ø
//		int[][] needs = {{ 1, 0, 0 }, {1, 1, 0}, {1, 1, 0}, {1, 0, 1}, {1, 1, 0}, {0, 1, 1} };
//		int r = 2;
		
		//2¹ø
		int[][] needs = {{ 1, 1, 0}, {0, 1, 1}, {1, 0, 0}, {1, 0, 0} };
		int r = 1;
		System.out.println(solution(needs, r));
	}

	static public int solution(int[][] needs, int r) {
		
		boolean[] visited = new boolean[needs[0].length];
		List<Integer> combResult = new ArrayList<Integer>();
		
		combination(visited, combResult, 0, visited.length, r);
		
		int[] needsNums = new int[needs.length];
		for(int i = 0; i < needsNums.length; i++) {
			needsNums[i] = intArrToInt(needs[i]);
		}
		
		int max = 0;
		for(int i = 0; i < combResult.size(); i++) {
			int component = combResult.get(i);
			int cnt = 0;
			for(int j = 0; j < needsNums.length; j++) {
				if(needsNums[j] == (needsNums[j] & component)) {
					cnt++;
				}
			}
			if(cnt > max) max = cnt;
		}
		
		return max;
	}

	static void combination(boolean[] visited, List<Integer> result, int start, int n, int r) {
		if (r == 0) {
			result.add(binaryArrToInt(visited));
			return;
		}

		for (int i = start; i < n; i++) {
			visited[i] = true;
			combination(visited, result, i + 1, n, r - 1);
			visited[i] = false;
		}
	}

	static int binaryArrToInt(boolean[] binaryArr) {
		int result = 0;
		for (int i = 0; i < binaryArr.length; i++) {
			if (binaryArr[i]) {
				result += (int) Math.pow(2, i);
			}
		}
		return result;
	}
	
	static int intArrToInt(int[] intArr) {
		int result = 0;
		for (int i = 0; i < intArr.length; i++) {
			if (intArr[i] == 1) {
				result += (int) Math.pow(2, i);
			}
		}
		return result;
	}
}

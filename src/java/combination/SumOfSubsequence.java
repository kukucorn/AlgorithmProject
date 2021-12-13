package combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfSubsequence {
	
	private static int result = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] conditions = br.readLine().split(" ");
		int n = Integer.parseInt(conditions[0]);
		int sum = Integer.parseInt(conditions[1]);

		boolean[] isVisited = new boolean[n];
		int[] arr = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for(int r = 1; r <= n; r++) {
			combination(arr, isVisited, 0, n, r, sum);
		}

		System.out.print(result);

		br.close();
	}

	private static void combination(int[] arr, boolean[] isVisited, int start, int n, int r, int sum) {
		
		if (r == 0) {
			if (sumOfCombination(arr, isVisited, n) == sum) {
				result++;
			}
			return;
		}
		for (int i = start; i < n; i++) {
			isVisited[i] = true;
			combination(arr, isVisited, i + 1, n, r - 1, sum);
			isVisited[i] = false;
		}
	}

	private static int sumOfCombination(int[] arr, boolean[] isVisited, int n) {

		int sum = 0;
		for(int i = 0; i < n; i++) {
			if(isVisited[i]) sum += arr[i];
		}
		return sum;
	}
	
}
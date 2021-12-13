package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CuttingTree {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numOfTree = Integer.parseInt(st.nextToken());
		int lengthOfNeed = Integer.parseInt(st.nextToken());
		
		int[] treeArr = new int[numOfTree];
		int max = initArr(treeArr, new StringTokenizer(br.readLine()));
		
		long left = 0;
		long right = max;
		
		while(left <= right) {
			long mid = (left + right) / 2;
			long sum = 0;
			for(int i = 0; i < numOfTree; i++) {
				long cut = treeArr[i] - mid;
				if(cut > 0) {
					sum += cut;
				}
			} 
			
			if(sum == lengthOfNeed) {
				right = mid;
				break;
			} else if(sum > lengthOfNeed) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		System.out.print(right);
		
		br.close();
	}
	
	private static int initArr(int[] arr, StringTokenizer st) {
		int max = 0;
		for(int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(max < arr[i]) {
				max = arr[i];
			}
		}
		return max;
	}
}
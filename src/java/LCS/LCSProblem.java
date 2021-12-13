package LCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCSProblem {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str1 = br.readLine();
		String str2 = br.readLine();

		System.out.print(LCS(str1, str2));

		br.close();
	}
	
	private static int LCS(String str1, String str2) {
		int[][] arr = new int[str1.length()+1][str2.length()+1];
		for(int i = 0; i < arr[0].length; i++) {
			arr[0][i] = 0;
		}
		
		for(int i = 1; i < arr.length; i++) {
			char cmp = str1.charAt(i-1);
			for(int j = 1; j < arr[i].length; j++) {
				if(cmp == str2.charAt(j-1)) {
					arr[i][j] = arr[i-1][j-1] + 1;
				} else {
					arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]);
				}
			}
		}
		
		for(int[] a : arr) {
			for(int b : a) {
				System.out.print(b);
			}
			System.out.println();
		}
		
		return arr[arr.length-1][arr[0].length-1];
	}
}

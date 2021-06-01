package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CuttingLine {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numOfLine = Integer.parseInt(st.nextToken());
		int numOfLineNeed = Integer.parseInt(st.nextToken());
		
		int[] lineArr = new int[numOfLine];
		int max = initArr(lineArr, br);
		
		int left = 1; // 랜선을 0의 길이로 자를 수 없고, 나눗셈을 취하기 때문에 0은 제외한다.
		int right = max;
		
		while(left <= right) {
			int mid = (int)(((long)left + (long)right) / 2);
			long sum = 0;
			boolean isRest = false;
			for(int i = 0; i < numOfLine; i++) {
				int num = lineArr[i] / mid;
				if(num > 0) {
					sum += num;
					if(!isRest && lineArr[i] % mid > 0) {
						isRest = true;
					}
				}
			}
			
			if(sum < numOfLineNeed) {
				right = mid - 1;
			} else if(sum > numOfLineNeed | isRest) {
				left = mid + 1;
			} else {
				right = mid;
				break;
			}
		}
		
		System.out.print(right);
		
		br.close();
	}
	
	private static int initArr(int[] arr, BufferedReader br) throws NumberFormatException, IOException {
		int max = 0;
		for(int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(max < arr[i]) {
				max = arr[i];
			}
		}
		return max;
	}
}
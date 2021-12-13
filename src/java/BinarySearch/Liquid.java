package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Liquid {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int count = Integer.parseInt(br.readLine());
		
		int[] liquids = new int[count];
		int indexOfPosStart = initArr(liquids, new StringTokenizer(br.readLine()));
		
		Answer answer = new Answer(null, Integer.MAX_VALUE);
		for(int i = 0; i < count-1; i++) {
			Answer curAnswer;
			if(liquids[i] < 0) {
				curAnswer = findMinDiffFromInteger(liquids, i, i+1, count-1, indexOfPosStart);
			} else {
				curAnswer = findMinDiffFromPositive(liquids, i, i+1, count-1);
			}
			if(answer.min > curAnswer.min) answer = curAnswer;
		}
		
		System.out.print(answer.choice[0] + " " + answer.choice[1]);
		
		br.close();
	}
	
	private static int initArr(int[] arr, StringTokenizer st) {
		int indexOfPos = -1;
		for(int i = 0; i < arr.length; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
			if(indexOfPos == -1 && num > 0) indexOfPos = i;
		}
		return indexOfPos;
	}
	// 특성값이 오름차순으로 정렬되므로 경우를 나눴음.
	// 간격을 구하려는 수가 음수인 경우
	private static Answer findMinDiffFromInteger(int[] liquids, int curIndex, int left, int right, int indexOfPosStart) {
		
		int min = Integer.MAX_VALUE;
		int choice[] = {0,0};
		choice[0] = liquids[curIndex];
		
		int negativeLeft = left;
		int negativeRight = indexOfPosStart - 1;
		
		if(negativeLeft <= negativeRight) {
			min = Math.abs(liquids[negativeRight] + liquids[curIndex]);
			choice[1] = liquids[negativeRight];
		}
		
		int positiveLeft = indexOfPosStart;
		int positiveRight = right;
		
		while(positiveLeft <= positiveRight) {
			int mid = (positiveLeft + positiveRight) / 2;
			int diff = liquids[curIndex] + liquids[mid];
			
			if(diff > 0) {
				positiveRight = mid-1;
			} else if (diff < 0) {
				positiveLeft = mid+1;
			} else {
				min = 0;
				choice[1] = liquids[mid];
				break;
			}
			
			diff = Math.abs(diff);
			if(min > diff) {
				min = diff;
				choice[1] = liquids[mid];
			}
		}
		
		return new Answer(choice, min);
	}
	
	// 간격을 구하려는 수가 양수(or 0)인 경우
	private static Answer findMinDiffFromPositive(int[] liquids, int curIndex, int left, int right) {
		
		int[] choice = {0, 0};
		choice[0] = liquids[curIndex];
		int min = Integer.MAX_VALUE;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			int diff = liquids[curIndex] + liquids[mid];
			
			if(min > diff) {
				min = diff;
				choice[1] = liquids[mid];
			}
			
			if(diff > 0) {
				right = mid-1;
			} else if (diff < 0) {
				left = mid+1;
			} else {
				break;
			}
		}
		
		return new Answer(choice, min);
	}
}

class Answer {
	int[] choice;
	int min;
	
	public Answer(int[] choice, int min) {
		this.choice = choice;
		this.min = min;
	}
}
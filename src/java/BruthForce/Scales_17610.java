package BruthForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Scales_17610 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int numOfWeight = Integer.parseInt(br.readLine());
		int[] weights = new int[numOfWeight];
		
		initWeights(weights, new StringTokenizer(br.readLine()));
		Arrays.sort(weights);
		
		List[] weightLists = new List[numOfWeight];
		initWeightLists(weightLists);
		
		for(int i = 0; i < numOfWeight; i++) {
			boolean[] isVisited = new boolean[numOfWeight];
			combinationOfWeight(weights, weightLists[i], isVisited, 0, 0, numOfWeight, i+1);
		}

		// 마지막의 sum은 모든 weight의 합과 같다.
		int sumOfAllWeights = ((WeightGroup)weightLists[numOfWeight-1].get(0)).sum; 
		boolean[] isPossible = new boolean[sumOfAllWeights+1];
		int[] pick = new int[2];
		combinationOfGroup(weightLists, isPossible, pick, numOfWeight, 2, 0);
		
		int result = 0;
		for(int i = 1; i < isPossible.length; i++) { // 0g 은 없으므로 1부터 시작
			if(!isPossible[i]) result++;
		}
		System.out.print(result);
		
		br.close();
	}
	
	private static void initWeights(int[] weights, StringTokenizer st) {
		for(int i = 0; i < weights.length; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private static void initWeightLists(List[] weightLists) {
		for(int i = 0; i < weightLists.length; i++) {
			weightLists[i] = new ArrayList<WeightGroup>();
		}
	}
	
	private static void combinationOfWeight(int[] arr, List<WeightGroup> resultList, boolean[] isVisited, int start, int idx, int n, int r) {
		
		if (idx == r) {
			saveCombinationOfWeight(arr, resultList, isVisited, n, r);
			return;
		}
		for (int i = start; i < n; i++) {
			isVisited[i] = true;
			combinationOfWeight(arr, resultList, isVisited, i + 1, idx + 1, n, r);
			isVisited[i] = false;
		}
	}
	
	private static void combinationOfGroup(List<WeightGroup>[] weightLists, boolean[] isPossible, int[] pick, int n, int r, int depth) {
		
		if (depth == r) {
			if(pick[1] == 0) return; // 오른쪽에 추가 없으면 측정불가(추가 -가 없기 때문)
			if(pick[0] == 0) { // 오른쪽에만 추가 있는 경우
				List<WeightGroup> rightGroupList = weightLists[pick[1] - 1];
				for(int i = 0; i < rightGroupList.size(); i++) {
					int sum = rightGroupList.get(i).sum;
					if(!isPossible[sum]) isPossible[sum] = true;
				}
			} else { // 왼쪽 오른쪽 둘 다 추가 있는 경우
				int numOfPickedWeight = pick[0] + pick[1];
				if(0 < numOfPickedWeight && numOfPickedWeight <= n) {
					List<WeightGroup> leftGroupList = weightLists[pick[0] - 1];
					List<WeightGroup> rightGroupList = weightLists[pick[1] - 1];
					
					for(int i = 0; i < leftGroupList.size(); i++) {
						for(int j = 0; j < rightGroupList.size(); j++) {
							WeightGroup leftGroup = leftGroupList.get(i);
							WeightGroup rightGroup = rightGroupList.get(j);
							int sum = rightGroup.sum - leftGroup.sum;
							if(sum > 0 && !isPossible[sum]) {
								if(leftGroup.isDifferentWeightsOfGroup(rightGroup)) {
									isPossible[rightGroup.sum - leftGroup.sum] = true;
								}
							}
						}
					}
				}
			}
			return;
		}
		
		// (0,0) ~ (n, n) 까지의 순열(중복 포함)
		for (int i = 0; i <= n; i++) {
			pick[depth] = i;
			combinationOfGroup(weightLists, isPossible, pick, n, r, depth+1);
		}
	}
	
	private static void saveCombinationOfWeight(int[] arr, List<WeightGroup> resultList, boolean[] isVisited, int n, int r) {
		int[] weights = new int[r];
		int idx = 0;
		int sum = 0;
		for(int i = 0; i < n; i++) {
			if(isVisited[i]) {
				weights[idx++] = arr[i];
				sum += arr[i];
			}
		}
		resultList.add(new WeightGroup(weights, sum));
	}
}

class WeightGroup {
	int[] weights;
	int sum;
	
	public WeightGroup(int[] weights, int sum) {
		this.weights = weights;
		this.sum = sum;
	}
	
	public boolean isDifferentWeightsOfGroup(WeightGroup another) {
		int i = this.weights.length-1;
		int j = another.weights.length-1;
		while(i >= 0 && j >= 0) {
			if(this.weights[i] == another.weights[j]) return false;
			
			if(this.weights[i] > another.weights[j]) {
				i--;
			} else {
				j--;
			}
		}
		return true;
	}
//	
//	@Override
//	public String toString() {
//		String str = "합 : " + sum + ", 추 : " + weights[0];
//		
//		for(int i = 1; i < weights.length; i++) {
//			str += " ," + weights[i];
//		}
//		return str;
//	}
}
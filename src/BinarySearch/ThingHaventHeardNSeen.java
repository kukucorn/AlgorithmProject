package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ThingHaventHeardNSeen {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		String[] MenWhoDontHear = new String[N];
		String[] MenWhoDontSee = new String[M];
		for(int i = 0; i < N; i++) {
			MenWhoDontHear[i] = br.readLine();
		}
		for(int i = 0; i < M; i++) {
			MenWhoDontSee[i] = br.readLine();
		}
		
		Arrays.sort(MenWhoDontHear);
		Arrays.sort(MenWhoDontSee);
		
		int i = 0;
		int j = 0;
		
		StringBuilder sb = new StringBuilder();
		int count = 0;
		while(i < N && j < M) {
			int compare = MenWhoDontHear[i].compareTo(MenWhoDontSee[j]);
			if(compare == 0) {
				sb.append(MenWhoDontHear[i] + "\n");
				count++;
				i++;
				j++;
			} else if(compare < 0) {
				i++;
			} else {
				j++;
			}
		}
		
		while(i < N) {
			int compare = MenWhoDontHear[i].compareTo(MenWhoDontSee[M-1]);
			if(compare > 0) break;
			if(compare == 0) {
				sb.append(MenWhoDontHear[i] + "\n");
				count++;
			} 
			i++;
		} 
		while(j < M) {
			int compare = MenWhoDontHear[N-1].compareTo(MenWhoDontSee[j]);
			if(compare < 0) break;
			if(compare == 0) {
				sb.append(MenWhoDontSee[j] + "\n");
				count++;
			} 
			j++;
		}
		
		System.out.println(count);
		System.out.print(sb);
		
		br.close();
	}
}
package permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AllPermutation {

	private static boolean[] isVisited;
	private static int[] resultList;
	private static int[] list;
	private static int R;
	private static StringBuilder results = new StringBuilder();
	
   public static void main(String[] args) throws IOException {
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      R = Integer.parseInt(br.readLine());
      isVisited = new boolean[R];
      resultList = new int[R];
      list = new int[R];
      
      for(int i = 0; i < R; i++) {
    	  list[i] = i+1;
      }
      
      permutation(0);
      
      System.out.print(results);
      
      br.close();
   }
   
   private static void permutation(int idx) {
		if(idx == R) {
			for(int i = 0; i < R; i++) {
				results.append(resultList[i] + " ");
			}
			results.append("\n");
		}
		for(int i = 0; i < list.length; ++i) {
			if(isVisited[i]) continue;
				
			resultList[idx] = list[i];
			isVisited[i] = true;
			permutation(idx+1);
			isVisited[i] = false;
		}
	}
}
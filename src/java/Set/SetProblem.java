package Set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SetProblem {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder results = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Set set = new Set();
		set.init(n+1);
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int oper = Integer.parseInt(st.nextToken());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			
			switch(oper) {
			case 0: // 합집합
				set.union(num1, num2);
				break;
			case 1: // 같은 집합 여부
				if(set.findSet(num1) == set.findSet(num2)) {
					results.append("YES\n");
				} else {
					results.append("NO\n");
				}
				break;
			}
		}
		
		System.out.print(results);

		br.close();
	}
}

class Set {
	int[] parent;

	public void init(int N) {
		parent = new int[N];
		Arrays.fill(parent, -1);
	}

	public int findSet(int x) {
		if (parent[x] == -1)
			return x;
		return parent[x] = findSet(parent[x]);
	}

	boolean union(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);

		if (pa == pb)
			return false;

		parent[pa] = pb;
		return true;
	}
}
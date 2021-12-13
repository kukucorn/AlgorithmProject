package LCA;//package LCA;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.StringTokenizer;
//
//public class LCAProblem {
//
//	public static void main(String[] args) throws IOException {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		int edgeNum = Integer.parseInt(br.readLine());
//		
//		LCA tree = new LCA(edgeNum, 50000);
//		
//		for(int i = 0; i < edgeNum-1; i++) {
//			tree.addEdge(new StringTokenizer(br.readLine()));
//		}
//		
//		tree.dfs(1, 0);
//		tree.initSparseGraph();
//		
//		StringBuilder sb = new StringBuilder();
//		
//		int queryCount = Integer.parseInt(br.readLine());
//		for(int i = 0; i < queryCount; i++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			int v1 = Integer.parseInt(st.nextToken());
//			int v2 = Integer.parseInt(st.nextToken());
//			sb.append(tree.findLCA(v1, v2)).append("\n");
//		}
//		
//		System.out.print(sb.toString());
//		
//		br.close();
//	}
//}
//
//class LCA {
//	private List<Integer>[] tree;
//    private int[][] ancesters; // sparse graph
//    private int[] depths;
//    private boolean[] isVisited;
//    
//    private int N;
//    
//    private final int MAX_VERTEX_QUOTIENT; // log2(MAX_VERTEX_NUM)
//    
//    public LCA(int n, int maxVertexNum) {
//    	this.N = n;
//    	this.MAX_VERTEX_QUOTIENT = (int)Math.floor(Math.log(maxVertexNum) / Math.log(2));
//    	
//    	tree = new ArrayList[N+1];
//    	for(int i = 1; i < N+1; i++) {
//    		tree[i] = new ArrayList<>();
//    	}
//    	
//    	ancesters = new int[N+1][MAX_VERTEX_QUOTIENT+1];
//    	depths = new int[N+1];
//    	isVisited = new boolean[N+1];
//    }
//    
//    public void addEdge(StringTokenizer st) {
//    	int node1 = Integer.parseInt(st.nextToken());
//        int node2 = Integer.parseInt(st.nextToken());
//
//        tree[node1].add(node2);
//        tree[node2].add(node1);
//    }
//    
//    // init vertex의 깊이를 dfs로 측정
//    public void dfs(int current, int depth){
//    	isVisited[current] = true;
//        depths[current] = depth;
//
//        for(int next: tree[current]){
//            if(isVisited[next]) continue;
//
//            ancesters[next][0] = current;
//            dfs(next, depth + 1);
//        }
//    }
//    
//    public void initSparseGraph() {
//    	for(int p = 1; p < MAX_VERTEX_QUOTIENT+1; p++){
//            for(int cur = 1; cur < N+1; cur++){
//                ancesters[cur][p] = ancesters[ancesters[cur][p - 1]][p - 1];
//            }
//        }
//    }
//    
//    public int findLCA(int node1, int node2){
//    	
//        if(depths[node1] > depths[node2]){
//            int tmp = node1;
//            node1 = node2;
//            node2 = tmp;
//        }
//
//        // depths[node1] <= depths[node2] 인 상태이기에
//        // depth를 depths[node1]으로 동일하게 변경함
//        for(int i = MAX_VERTEX_QUOTIENT; i >= 0; i--){
//            int jump = 1 << i;
//            if(depths[node2] - depths[node1] >= jump) node2 = ancesters[node2][i];
//        }
//
//        if(node1 == node2) return node1;
//
//        for(int i = MAX_VERTEX_QUOTIENT; i >= 0; i--){
//            if(ancesters[node1][i] == ancesters[node2][i]) continue;
//
//            node1 = ancesters[node1][i];
//            node2 = ancesters[node2][i];
//        }
//
//        return ancesters[node1][0];
//    }
//}
package shortestPathProblem.floydWarshall;//package shortestPathProblem.floydWarshall;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class FindPath {
//
//	public static void main(String[] args) throws IOException {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//		int vertexNum = Integer.parseInt(br.readLine());
//		Graph graph = new Graph(vertexNum);
//		
//		for(int i = 1; i <= vertexNum; i++) {
//			graph.addEdges(i, new StringTokenizer(br.readLine()));
//		}
// 
//		graph.floidWarshall();
//		int[][] distances = graph.getDistances();
//		
//		StringBuilder sb = new StringBuilder();
//		
//		for(int i = 1; i <= vertexNum; i++) {
//			for(int j = 1; j <= vertexNum; j++) {
//				if(distances[i][j] == 10000) sb.append("0 ");
//				else sb.append("1 ");
//			}
//			sb.append("\n");
//		}
//		
//		System.out.print(sb.toString());
//
//		br.close();
//	}
//}
//
//class Graph {
//	
//	private int[][] edges;
//	private int[][] distances;
//	private int N; // vertex ¼ö
//
//	public Graph(int n) {
//		this.N = n;
//		edges = new int[N+1][N+1];
//	}
//	
//	private void initDistance() {
//		distances = new int[N+1][N+1];
//		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= N; j++) {
//				if(edges[i][j] == 0) distances[i][j] = 10000;
//				else distances[i][j] = edges[i][j];
//			}
//		}
//		for(int i = 1; i <= N; i++) {
//			distances[i][i] = 10000;
//		}
//	}
//
//	public void floidWarshall() {
//		
//		initDistance(); 
//		
//		for(int k = 1; k <= N; k++) {
//			for(int i = 1; i <= N; i++) {
//				for(int j = 1; j <= N; j++) {
//					if(distances[i][j] > distances[i][k] + distances[k][j]) {
//						distances[i][j] = distances[i][k] + distances[k][j];
//					}
//				}
//			}
//		}
//	}
//	
//	public void addEdges(int row, StringTokenizer st) {
//		for(int i = 1; i <= N; i++) {
//			edges[row][i] = Integer.parseInt(st.nextToken());
//		}
//	}
//	
//	public int[][] getDistances() {
//		return distances;
//	}
//}
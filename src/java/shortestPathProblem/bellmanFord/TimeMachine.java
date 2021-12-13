package shortestPathProblem.bellmanFord;//package shortestPathProblem.bellmanFord;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.StringTokenizer;
//
///* 이 문제 특징 : edge의 weight 범위 -10000 이상 10000 이하
// * vertex의 범위 : 500개 이하
// * edge의 범위 : 6000개 이하
// * 가장 짧은 거리의 distance가 -10000 * 6000 * 500 이어서 300억 정도가 나올 수 있음
// * 그래서 distance가 int로는 커버가 안되서 long을 사용함
//*/
//public class TimeMachine {
//
//	public static void main(String[] args) throws IOException {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		int vertexNum = Integer.parseInt(st.nextToken());
//		int edgeNum = Integer.parseInt(st.nextToken());
//
//		Graph graph = new Graph(vertexNum);
//		for (int e = 0; e < edgeNum; e++) {
//			addBusLine(graph, new StringTokenizer(br.readLine()));
//		}
//
//		if(graph.bellmanFord(1)) {
//			long[] distance = graph.getDistance();
//			
//			for(int i = 2; i < distance.length; i++) {
//				if(distance[i] == Long.MAX_VALUE) sb.append("-1");
//				else sb.append(distance[i]);
//				sb.append("\n");
//			}
//		} else {
//			sb.append("-1");
//		}
//		
//		System.out.print(sb);
//
//		br.close();
//	}
//
//	private static void addBusLine(Graph graph, StringTokenizer st) {
//		int start = Integer.parseInt(st.nextToken());
//		int end = Integer.parseInt(st.nextToken());
//		int weight = Integer.parseInt(st.nextToken());
//		graph.addEdge(start, end, weight);
//	}
//}
//
//class Graph {
//	private int n; // 노드들의 수
//	private List<Edge>[] edges; // 노드들간의 가중치 저장할 변수
//	private long[] distance; // 최단 거리
//
//	public Graph(int n) {
//		this.n = n;
//		edges = new List[n + 1];
//		for (int i = 1; i < n + 1; i++) {
//			edges[i] = new ArrayList<Edge>();
//		}
//	}
//
//	public void addEdge(int start, int end, int weight) {
//		edges[start].add(new Edge(start, end, weight));
//	}
//
//	private void initDistance() {
//		distance = new long[n + 1];
//		for (int i = 1; i < n + 1; i++) {
//			distance[i] = Long.MAX_VALUE;
//		}
//	}
//
//	// 최단 거리를 찾아 distance 에 저장 후 true 리턴
//	// 하지만, 음수 싸이클이 존재하면 false 리턴
//	boolean bellmanFord(int start) {
//
//		initDistance();
//		boolean isUpdated = false;
//		distance[start] = 0;
//
//		// N 번 검사. N-1번 동안 최단 거리 생성 후 N 번째에서 음수 싸이클 검사
//		for (int cnt = 0; cnt < n; cnt++) {
//			isUpdated = false;
//			for (int here = 1; here <= n; here++) { // 모든 간선 확인
//				for (Edge adj : edges[here]) {
//					if (distance[here] != Long.MAX_VALUE && distance[adj.end] > distance[here] + adj.weight) {
//						distance[adj.end] = distance[here] + adj.weight;
//						isUpdated = true;
//					}
//				}
//			}
//
//			if (!isUpdated) // 더이상 최단거리가 업데이트 안되었다면 break
//				break;
//		}
//
//		if (isUpdated) // N 번째에서도 update 됐다면 음수 싸이클이 존재
//			return false;
//
//		return true; // 음수 싸이클이 없는 그래프이고, 최단 거리를 찾음
//	}
//
//	public long[] getDistance() {
//		return distance;
//	}
//}
//
//class Edge {
//	int start;
//	int end;
//	int weight;
//
//	public Edge(int s, int e, int w) {
//		this.start = s;
//		this.end = e;
//		this.weight = w;
//	}
//}
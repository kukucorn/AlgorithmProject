package shortestPathProblem.bellmanFord;//package shortestPathProblem.bellmanFord;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.StringTokenizer;
//
//public class WormHole {
//
//	public static void main(String[] args) throws IOException {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		
//		int testCaseNum = Integer.parseInt(br.readLine());
//		
//		for(int testCase = 0; testCase < testCaseNum; testCase++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			int vertexNum = Integer.parseInt(st.nextToken());
//			int edgeNum = Integer.parseInt(st.nextToken());
//			int wormHoleNum = Integer.parseInt(st.nextToken());
//			
//			Graph graph = new Graph(vertexNum);
//			for(int e = 0; e < edgeNum; e++) {
//				addRoad(graph, new StringTokenizer(br.readLine()));
//			}
//			
//			for(int w = 0; w < wormHoleNum; w++) {
//				addWormHole(graph, new StringTokenizer(br.readLine()));
//			}
//			
//			boolean[] isVisited = new boolean[vertexNum+1];
//			int startIndex = 1;
//			boolean hasNegCycle = false;
//			
//			// 그래프가 끊겨 있을 수도 있기에 끊겨있는 그래프 끼리의 음수 사이클 존재를 파악
//			// 한 번이라도 음수 사키을이 존재하면 YES
//			while(startIndex <= vertexNum) {
//				if(!graph.bellmanFord(startIndex)) { // 음수 사이클 존재
//					hasNegCycle = true;
//					break;
//				}
//				
//				int[] distances = graph.getDistance();
//				for(int i = startIndex; i < vertexNum+1; i++) {
//					if(!isVisited[i] && distances[i] != Integer.MAX_VALUE) {
//						isVisited[i] = true;
//					}
//				}
//				
//				while(isVisited[startIndex]) {
//					startIndex++;
//					if(startIndex > vertexNum) break;
//				}
//			}
//			
//			if(hasNegCycle) {
//				sb.append("YES");
//			} else {
//				sb.append("NO");
//			}
//			sb.append("\n");
//		}
//
//		System.out.print(sb);
//		
//		br.close();
//	}
//	
//	private static void addRoad(Graph graph, StringTokenizer st) {
//		int start = Integer.parseInt(st.nextToken());
//		int end = Integer.parseInt(st.nextToken());
//		int weight = Integer.parseInt(st.nextToken());
//		graph.addEdge(start, end, weight);
//		graph.addEdge(end, start, weight);
//	}
//	
//	private static void addWormHole(Graph graph, StringTokenizer st) {
//		int start = Integer.parseInt(st.nextToken());
//		int end = Integer.parseInt(st.nextToken());
//		int weight = Integer.parseInt(st.nextToken());
//		graph.addEdge(start, end, -weight);
//	}
//}
//
//class Graph {
//	private int n; // 노드들의 수
//	private List<Edge>[] edges; // 노드들간의 가중치 저장할 변수
//	private int[] distance; // 최단 거리
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
//		distance = new int[n+1];
//		for (int i = 1; i < n+1; i++) {
//			distance[i] = Integer.MAX_VALUE;
//		}
//	}
//
//	// 최단 거리를 찾아 distance 에 저장 후 true 리턴
//    // 하지만, 음수 싸이클이 존재하면 false 리턴
//    boolean bellmanFord(int start) {
//    	
//    	initDistance();
//        boolean isUpdated = false;
//        distance[start] = 0;
// 
//        // N 번 검사. N-1번 동안 최단 거리 생성 후 N 번째에서 음수 싸이클 검사 
//        for(int cnt = 0; cnt < n; cnt++) {
//        	isUpdated = false;
//            for(int here = 1; here <= n; here++) { // 모든 간선 확인
//                for(Edge adj : edges[here]) {
//                    if(distance[here] != Integer.MAX_VALUE && distance[adj.end] > distance[here] + adj.weight) {
//                    	distance[adj.end] = distance[here] + adj.weight;
//                    	isUpdated = true;
//                    }
//                }
//            }
//        
//            if(!isUpdated) // 더이상 최단거리가 업데이트 안되었다면 break
//                break;
//        }
//        
//        if(isUpdated) // N 번째에서도 update 됐다면 음수 싸이클이 존재
//            return false;
//        
//        return true; // 음수 싸이클이 없는 그래프이고, 최단 거리를 찾음
//    }
//
//	public int[] getDistance() {
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
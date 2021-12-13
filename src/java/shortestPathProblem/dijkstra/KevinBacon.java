package shortestPathProblem.dijkstra;//package shortestPathProblem.dijkstra;
//
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.PriorityQueue;
//import java.util.StringTokenizer;
//
//public class KevinBacon {
//
//	public static void main(String[] args) throws IOException {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		int vCount = Integer.parseInt(st.nextToken());
//		int eCount = Integer.parseInt(st.nextToken());
//
//		// 그래프 초기화
//		Graph g = new Graph(vCount);
//		for (int i = 0; i < eCount; i++) {
//			g.addEdge(new StringTokenizer(br.readLine()));
//		}
//
//		int min = Integer.MAX_VALUE;
//		int minIndex = -1;
//		for(int startIndex = 1; startIndex < vCount+1; startIndex++) {
//			g.dijkstra(startIndex); // startIndex에서의 최단 거리 찾기
//			int sumOfDistances = sum(g.getDistance());
//			if(min > sumOfDistances) {
//				min = sumOfDistances;
//				minIndex = startIndex;
//			}
//		}
// 
//		System.out.print(minIndex);
//
//		br.close();
//	}
//	
//	private static int sum(int[] arr) {
//		int num = 0;
//		for(int i : arr) {
//			num += i;
//		}
//		return num;
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
//	private void initDistance() {
//		distance = new int[n+1];
//		for (int i = 1; i < n+1; i++) {
//			distance[i] = Integer.MAX_VALUE;
//		}
//	}
//
//	public void dijkstra(int start) {
//		
//		initDistance(); // distance arr 할당 및 값 초기화.
//		boolean[] isVisited = new boolean[n + 1]; // 해당 노드를 방문했는지 체크할 변수
//
//		PriorityQueue<Route> pQueue = new PriorityQueue<>();
//		
//		// 시작값 초기화.
//		distance[start] = 0;
//		pQueue.add(new Route(start, 0));
//
//		while(!pQueue.isEmpty() ) {
//			Route route = pQueue.poll();
//			
//			if(isVisited[route.dst]) continue;
//			isVisited[route.dst] = true; 
//			
//			for(Edge edge : edges[route.dst]) {
//				if(route.weight + 1 < distance[edge.end]) {
//					distance[edge.end] = route.weight + 1;
//					pQueue.add(new Route(edge.end, distance[edge.end]));
//				}
//			}
//		}
//	}
//	
//	public void addEdge(StringTokenizer st) {
//		int start = Integer.parseInt(st.nextToken());
//		int end = Integer.parseInt(st.nextToken());
//		//int weight = Integer.parseInt(st.nextToken()); weight가 1임
//		edges[start].add(new Edge(start, end));
//		edges[end].add(new Edge(end, start));
//	}
//	
//	public int[] getDistance() {
//		return distance;
//	}
//}
//
//class Edge {
//	int start;
//	int end;
//	
//	public Edge(int s, int e) {
//		this.start = s;
//		this.end = e;
//	}
//}
//
//class Route implements Comparable<Route> {
//
//	int dst;
//	int weight;
//
//	public Route(int d, int w) {
//		this.dst = d;
//		this.weight = w;
//	}
//
//	@Override
//	public int compareTo(Route o) {
//		// TODO Auto-generated method stub
//		return this.weight - o.weight;
//	}
//
////	@Override
////	public String toString() {
////		return "dst : " + dst + ", weight : " + weight;
////	}
//}
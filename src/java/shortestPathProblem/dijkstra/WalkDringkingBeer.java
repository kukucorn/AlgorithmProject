package shortestPathProblem.dijkstra;//package shortestPathProblem.dijkstra;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.PriorityQueue;
//import java.util.StringTokenizer;
//
//public class WalkDringkingBeer {
//
//	public static void main(String[] args) throws IOException {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		
//		int testCount = Integer.parseInt(br.readLine());
//		for(int test = 0; test < testCount; test++) {
//			
//			int martNum = Integer.parseInt(br.readLine());
//			
//			Pos[] posArr = new Pos[martNum+3];
//			
//			for(int i = 1; i < martNum+3; i++) {
//				initPos(posArr, i, new StringTokenizer(br.readLine()));
//			}
//			
//			List<Edge> edgeList = new ArrayList<>();
//			findEdge(posArr, edgeList);
//			
//			// 그래프 초기화
//			Graph g = new Graph(martNum+2);
//			for(Edge edge : edgeList) {
//				g.addEdge(edge);
//			}
//
//			// 최단거리 찾기
//			if(g.dijkstra(1, martNum+2)) sb.append("happy\n");
//			else sb.append("sad\n");
//		}
//		
//		System.out.print(sb.toString());
//
//		br.close();
//	}
//	
//	private static void initPos(Pos[] posArr, int index, StringTokenizer st) {
//		posArr[index] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
//	}
//
//	private static void findEdge(Pos[] posArr, List<Edge> edgeList) {
//		for(int i = 1; i < posArr.length-1; i++) {
//			for(int j = i+1; j < posArr.length; j++) {
//				int distance = Math.abs(posArr[i].x - posArr[j].x) + Math.abs(posArr[i].y - posArr[j].y);
//				if(distance <= 1000) {
//					edgeList.add(new Edge(i, j, 1));
//					edgeList.add(new Edge(j, i, 1));
//				}
//			}
//		}
//	}
//
//}
//
//class Pos {
//	int x;
//	int y;
//	
//	public Pos(int x, int y) {
//		this.x = x;
//		this.y = y;
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
//	public boolean dijkstra(int start, int finish) {
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
//				if(route.weight + edge.weight < distance[edge.end]) {
//					distance[edge.end] = route.weight + edge.weight;
//					pQueue.add(new Route(edge.end, distance[edge.end]));
//				}
//			}
//		}
//		
//		if(distance[finish] == Integer.MAX_VALUE) return false;
//		return true;
//	}
//	
//	public void addEdge(Edge edge) {
//		edges[edge.start].add(edge);
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
//	int weight;
//	
//	public Edge(int s, int e, int w) {
//		this.start = s;
//		this.end = e;
//		this.weight = w;
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
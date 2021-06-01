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
//public class FindCityInSpecificDistance {
//
//	public static void main(String[] args) throws IOException {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		int vCount = Integer.parseInt(st.nextToken()); // ������ ����
//		int eCount = Integer.parseInt(st.nextToken()); // ������ ����
//		int distanceToFind = Integer.parseInt(st.nextToken()); // �Ÿ� ����
//		int startVertex = Integer.parseInt(st.nextToken()); // ��� ������ ��ȣ
//
//		// �׷��� �ʱ�ȭ
//		Graph g = new Graph(vCount);
//		for (int i = 0; i < eCount; i++) {
//			g.addEdge(new StringTokenizer(br.readLine()));
//		}
//
//		// �ִܰŸ� ã��
//		g.dijkstra(startVertex);
//		int[] distances = g.getDistance();
//
//		System.out.print(distanceToSB(distances, distanceToFind).toString());
//
//		br.close();
//	}
//	
//	private static StringBuilder distanceToSB(int[] arr, int infoToFind) {
//		StringBuilder sb = new StringBuilder();
//		for (int i = 1; i < arr.length; i++) {
//			if(arr[i] == infoToFind) sb.append(i).append("\n");
//		}
//		
//		if(sb.length() == 0) sb.append(-1);
//		
//		return sb;
//	}
//}
//
//class Graph {
//	private int n; // ������ ��
//	private List<Edge>[] edges; // ���鰣�� ����ġ ������ ����
//	private int[] distance; // �ִ� �Ÿ�
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
//		initDistance(); // distance arr �Ҵ� �� �� �ʱ�ȭ.
//		boolean[] isVisited = new boolean[n + 1]; // �ش� ��带 �湮�ߴ��� üũ�� ����
//
//		PriorityQueue<Route> pQueue = new PriorityQueue<>();
//		
//		// ���۰� �ʱ�ȭ.
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
//		//int weight = 1;
//		edges[start].add(new Edge(start, end));
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
//	//int weight;
//	
//	public Edge(int s, int e) {
//		this.start = s;
//		this.end = e;
//		//this.weight = w;
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
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
//public class IsGreenManJelda {
//
//	public static void main(String[] args) throws IOException {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuffer sb = new StringBuffer();
//		
//		int num = 1;
//		while (true) {
//			int caveSize = Integer.parseInt(br.readLine()); // 칸의 개수
//			
//			if(caveSize == 0) break;
//
//			// 그래프 초기화
//			Graph g = new Graph(caveSize * caveSize);
//			initEdge(g, caveSize, br);
//			
//			// 최단거리 찾기
//			g.dijkstra(1);
//			int[] distances = g.getDistance();
//
//			sb.append("Problem ").append(num++).append(": ").append(distances[caveSize*caveSize]).append("\n");
//		}
//
//		System.out.print(sb);
//		
//		br.close();
//	}
//
//	private static void initEdge(Graph g, int caveSize, BufferedReader br) throws IOException {
//		// 칸의 상하좌우로 edge가 연결되어 있다고 생각한다.
//		
//		// 첫 줄은 위로 edge가 없다.
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		for (int j = 1; j < caveSize+1; j++) {
//			int weight = Integer.parseInt(st.nextToken());
//			g.addEdge(new Edge(j+caveSize, j, weight)); // 아래에서 오는 edge
//			if(j == 1) { // 첫번째는 왼쪽으로 edge가 없다.
//				g.setStartWeight(weight);
//				g.addEdge(new Edge(j+1, j, weight)); // 오른쪽에서 오는 edge
//			} else if (j == caveSize) { // 마지막은 오른쪽으로 edge가 없다.
//				g.addEdge(new Edge(j-1, j, weight)); // 왼쪽에서 오는 edge
//			} else {
//				g.addEdge(new Edge(j-1, j, weight)); // 왼쪽에서 오는 edge
//				g.addEdge(new Edge(j+1, j, weight)); // 오른쪽에서 오는 edge
//			}
//		}
//		
//		for (int i = 2; i < caveSize; i++) { // 첫, 마지막 줄을 제외한 줄은 4방향으로 edge가 온다.
//			st = new StringTokenizer(br.readLine());
//			for (int j = 1; j < caveSize+1; j++) {
//				int vertexNum = j + caveSize * (i - 1);
//				int weight = Integer.parseInt(st.nextToken());
//				g.addEdge(new Edge(vertexNum-caveSize, vertexNum, weight)); // 위쪽에서 오는 edge
//				g.addEdge(new Edge(vertexNum+caveSize, vertexNum, weight)); // 아래에서 오는 edge
//				if(1 < j && j < caveSize) { // 모든 방향에서 edge가 온다.
//					g.addEdge(new Edge(vertexNum-1, vertexNum, weight)); // 왼쪽에서 오는 edge
//					g.addEdge(new Edge(vertexNum+1, vertexNum, weight)); // 오른쪽에서 오는 edge
//				} else if(j == 1) { // 첫번째는 왼쪽에서 오는 edge가 없다.
//					g.addEdge(new Edge(vertexNum+1, vertexNum, weight)); // 오른쪽에서 오는 edge
//				} else { // 마지막은 오른쪽에서 오는 edge가 없다.
//					g.addEdge(new Edge(vertexNum-1, vertexNum, weight)); // 왼쪽에서 오는 edge
//				}
//			}
//		}
//		
//		// 마지막 줄은 아래에서 오는 edge가 없다.
//		st = new StringTokenizer(br.readLine());
//		for (int j = 1; j < caveSize+1; j++) {
//			int vertexNum = j + caveSize * (caveSize - 1);
//			int weight = Integer.parseInt(st.nextToken());
//			g.addEdge(new Edge(vertexNum-caveSize, vertexNum, weight)); // 위쪽에서 오는 edge
//			if(j == 1) { // 첫번째는 왼쪽으로 edge가 없다.
//				g.addEdge(new Edge(vertexNum+1, vertexNum, weight)); // 오른쪽에서 오는 edge
//			} else if (j == caveSize) { // 마지막은 오른쪽으로 edge가 없다.
//				g.addEdge(new Edge(vertexNum-1, vertexNum, weight)); // 왼쪽에서 오는 edge
//			} else {
//				g.addEdge(new Edge(vertexNum-1, vertexNum, weight)); // 왼쪽에서 오는 edge
//				g.addEdge(new Edge(vertexNum+1, vertexNum, weight)); // 오른쪽에서 오는 edge
//			}
//		}
//	}
//}
//
//class Graph {
//	private int n; // 노드들의 수
//	private List<Edge>[] edges; // 노드들간의 가중치 저장할 변수
//	private int[] distance; // 최단 거리
//	private int startWeight;
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
//		distance = new int[n + 1];
//		for (int i = 1; i < n + 1; i++) {
//			distance[i] = Integer.MAX_VALUE;
//		}
//	}
//	
//	public void setStartWeight(int weight) {
//		startWeight = weight;
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
//		distance[start] = startWeight;
//		pQueue.add(new Route(start, distance[start]));
//
//		while (!pQueue.isEmpty()) {
//			Route route = pQueue.poll();
//
//			if (isVisited[route.dst])
//				continue;
//			isVisited[route.dst] = true;
//
//			for (Edge edge : edges[route.dst]) {
//				if (route.weight + edge.weight < distance[edge.end]) {
//					distance[edge.end] = route.weight + edge.weight;
//					pQueue.add(new Route(edge.end, distance[edge.end]));
//				}
//			}
//		}
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
//	
//	@Override
//	public String toString() {
//		return "start: " + start + ", end : " + end + ", weight : " + weight;
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
//	@Override
//	public String toString() {
//		return "dst : " + dst + ", weight : " + weight;
//	}
//}
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
//			int caveSize = Integer.parseInt(br.readLine()); // ĭ�� ����
//			
//			if(caveSize == 0) break;
//
//			// �׷��� �ʱ�ȭ
//			Graph g = new Graph(caveSize * caveSize);
//			initEdge(g, caveSize, br);
//			
//			// �ִܰŸ� ã��
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
//		// ĭ�� �����¿�� edge�� ����Ǿ� �ִٰ� �����Ѵ�.
//		
//		// ù ���� ���� edge�� ����.
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		for (int j = 1; j < caveSize+1; j++) {
//			int weight = Integer.parseInt(st.nextToken());
//			g.addEdge(new Edge(j+caveSize, j, weight)); // �Ʒ����� ���� edge
//			if(j == 1) { // ù��°�� �������� edge�� ����.
//				g.setStartWeight(weight);
//				g.addEdge(new Edge(j+1, j, weight)); // �����ʿ��� ���� edge
//			} else if (j == caveSize) { // �������� ���������� edge�� ����.
//				g.addEdge(new Edge(j-1, j, weight)); // ���ʿ��� ���� edge
//			} else {
//				g.addEdge(new Edge(j-1, j, weight)); // ���ʿ��� ���� edge
//				g.addEdge(new Edge(j+1, j, weight)); // �����ʿ��� ���� edge
//			}
//		}
//		
//		for (int i = 2; i < caveSize; i++) { // ù, ������ ���� ������ ���� 4�������� edge�� �´�.
//			st = new StringTokenizer(br.readLine());
//			for (int j = 1; j < caveSize+1; j++) {
//				int vertexNum = j + caveSize * (i - 1);
//				int weight = Integer.parseInt(st.nextToken());
//				g.addEdge(new Edge(vertexNum-caveSize, vertexNum, weight)); // ���ʿ��� ���� edge
//				g.addEdge(new Edge(vertexNum+caveSize, vertexNum, weight)); // �Ʒ����� ���� edge
//				if(1 < j && j < caveSize) { // ��� ���⿡�� edge�� �´�.
//					g.addEdge(new Edge(vertexNum-1, vertexNum, weight)); // ���ʿ��� ���� edge
//					g.addEdge(new Edge(vertexNum+1, vertexNum, weight)); // �����ʿ��� ���� edge
//				} else if(j == 1) { // ù��°�� ���ʿ��� ���� edge�� ����.
//					g.addEdge(new Edge(vertexNum+1, vertexNum, weight)); // �����ʿ��� ���� edge
//				} else { // �������� �����ʿ��� ���� edge�� ����.
//					g.addEdge(new Edge(vertexNum-1, vertexNum, weight)); // ���ʿ��� ���� edge
//				}
//			}
//		}
//		
//		// ������ ���� �Ʒ����� ���� edge�� ����.
//		st = new StringTokenizer(br.readLine());
//		for (int j = 1; j < caveSize+1; j++) {
//			int vertexNum = j + caveSize * (caveSize - 1);
//			int weight = Integer.parseInt(st.nextToken());
//			g.addEdge(new Edge(vertexNum-caveSize, vertexNum, weight)); // ���ʿ��� ���� edge
//			if(j == 1) { // ù��°�� �������� edge�� ����.
//				g.addEdge(new Edge(vertexNum+1, vertexNum, weight)); // �����ʿ��� ���� edge
//			} else if (j == caveSize) { // �������� ���������� edge�� ����.
//				g.addEdge(new Edge(vertexNum-1, vertexNum, weight)); // ���ʿ��� ���� edge
//			} else {
//				g.addEdge(new Edge(vertexNum-1, vertexNum, weight)); // ���ʿ��� ���� edge
//				g.addEdge(new Edge(vertexNum+1, vertexNum, weight)); // �����ʿ��� ���� edge
//			}
//		}
//	}
//}
//
//class Graph {
//	private int n; // ������ ��
//	private List<Edge>[] edges; // ���鰣�� ����ġ ������ ����
//	private int[] distance; // �ִ� �Ÿ�
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
//		initDistance(); // distance arr �Ҵ� �� �� �ʱ�ȭ.
//		boolean[] isVisited = new boolean[n + 1]; // �ش� ��带 �湮�ߴ��� üũ�� ����
//
//		PriorityQueue<Route> pQueue = new PriorityQueue<>();
//
//		// ���۰� �ʱ�ȭ.
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
package shortestPathProblem.bellmanFord;//package shortestPathProblem.bellmanFord;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.StringTokenizer;
//
///* �� ���� Ư¡ : edge�� weight ���� -10000 �̻� 10000 ����
// * vertex�� ���� : 500�� ����
// * edge�� ���� : 6000�� ����
// * ���� ª�� �Ÿ��� distance�� -10000 * 6000 * 500 �̾ 300�� ������ ���� �� ����
// * �׷��� distance�� int�δ� Ŀ���� �ȵǼ� long�� �����
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
//	private int n; // ������ ��
//	private List<Edge>[] edges; // ���鰣�� ����ġ ������ ����
//	private long[] distance; // �ִ� �Ÿ�
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
//	// �ִ� �Ÿ��� ã�� distance �� ���� �� true ����
//	// ������, ���� ����Ŭ�� �����ϸ� false ����
//	boolean bellmanFord(int start) {
//
//		initDistance();
//		boolean isUpdated = false;
//		distance[start] = 0;
//
//		// N �� �˻�. N-1�� ���� �ִ� �Ÿ� ���� �� N ��°���� ���� ����Ŭ �˻�
//		for (int cnt = 0; cnt < n; cnt++) {
//			isUpdated = false;
//			for (int here = 1; here <= n; here++) { // ��� ���� Ȯ��
//				for (Edge adj : edges[here]) {
//					if (distance[here] != Long.MAX_VALUE && distance[adj.end] > distance[here] + adj.weight) {
//						distance[adj.end] = distance[here] + adj.weight;
//						isUpdated = true;
//					}
//				}
//			}
//
//			if (!isUpdated) // ���̻� �ִܰŸ��� ������Ʈ �ȵǾ��ٸ� break
//				break;
//		}
//
//		if (isUpdated) // N ��°������ update �ƴٸ� ���� ����Ŭ�� ����
//			return false;
//
//		return true; // ���� ����Ŭ�� ���� �׷����̰�, �ִ� �Ÿ��� ã��
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
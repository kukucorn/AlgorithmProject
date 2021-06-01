package prim;//package prim;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.PriorityQueue;
//import java.util.StringTokenizer;
//
//public class PlanetTunnel {
//
//	public static void main(String[] args) throws IOException {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//		int count = Integer.parseInt(br.readLine());
//		Coordinate[] coords = new Coordinate[count];
//		initCoords(coords, br);
//
//		PriorityQueue<Edge> pQueue = new PriorityQueue<Edge>();
//		initMinimumTunnels(pQueue, coords);
//
//		Set set = new Set();
//		set.init(count);
//
//		System.out.print(prim(pQueue, set, coords.length));
//
//		br.close();
//	}
//
//	private static void initCoords(Coordinate[] coords, BufferedReader br) throws IOException {
//		for (int i = 0; i < coords.length; i++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			int x = Integer.parseInt(st.nextToken());
//			int y = Integer.parseInt(st.nextToken());
//			int z = Integer.parseInt(st.nextToken());
//			coords[i] = new Coordinate(i, x, y, z);
//		}
//	}
//
//	private static void initMinimumTunnels(PriorityQueue<Edge> pQueue, Coordinate[] coords) {
//
//		int count = coords.length;
//
//		sortCoordByX(coords);
//		for (int i = 0; i < count - 1; i++) {
//			int weight = coords[i + 1].x - coords[i].x;
//			pQueue.add(new Edge(coords[i].id, coords[i + 1].id, weight));
//		}
//
//		sortCoordByY(coords);
//		for (int i = 0; i < count - 1; i++) {
//			int weight = coords[i + 1].y - coords[i].y;
//			pQueue.add(new Edge(coords[i].id, coords[i + 1].id, weight));
//		}
//
//		sortCoordByZ(coords);
//		for (int i = 0; i < count - 1; i++) {
//			int weight = coords[i + 1].z - coords[i].z;
//			pQueue.add(new Edge(coords[i].id, coords[i + 1].id, weight));
//		}
//	}
//
//	private static int prim(PriorityQueue<Edge> pQueue, Set set, int count) {
//
//		int result = 0;
//		for (int i = 0; i < count - 1; i++) { // n-1 개의 edge로 연결한 MST
//			while (!pQueue.isEmpty()) {
//				Edge edge = pQueue.poll();
//				if (set.union(edge.v1, edge.v2)) {
//					result += edge.weight;
//					break;
//				}
//			}
//		}
//		return result;
//	}
//
//	private static void sortCoordByX(Coordinate[] coords) {
//		// x를 기준으로 좌표 정렬
//		Arrays.sort(coords, new Comparator<Coordinate>() {
//			@Override
//			public int compare(Coordinate o1, Coordinate o2) {
//				// TODO Auto-generated method stub
//				return o1.x - o2.x;
//			}
//		});
//	}
//
//	private static void sortCoordByY(Coordinate[] coords) {
//		// y를 기준으로 좌표 정렬
//		Arrays.sort(coords, new Comparator<Coordinate>() {
//			@Override
//			public int compare(Coordinate o1, Coordinate o2) {
//				// TODO Auto-generated method stub
//				return o1.y - o2.y;
//			}
//		});
//	}
//
//	private static void sortCoordByZ(Coordinate[] coords) {
//		// z를 기준으로 좌표 정렬
//		Arrays.sort(coords, new Comparator<Coordinate>() {
//			@Override
//			public int compare(Coordinate o1, Coordinate o2) {
//				// TODO Auto-generated method stub
//				return o1.z - o2.z;
//			}
//		});
//	}
//}
//
//class Coordinate {
//	int x;
//	int y;
//	int z;
//	int id; // primary key
//
//	public Coordinate(int id, int x, int y, int z) {
//		this.id = id;
//		this.x = x;
//		this.y = y;
//		this.z = z;
//	}
//}
//
//class Set {
//	int[] parent;
//	int[] MST;
//
//	public void init(int N) {
//		parent = new int[N];
//		Arrays.fill(parent, -1);
//	}
//
//	public int findSet(int x) {
//		if (parent[x] == -1)
//			return x;
//		return parent[x] = findSet(parent[x]);
//	}
//
//	boolean union(int a, int b) {
//		int pa = findSet(a);
//		int pb = findSet(b);
//
//		if (pa == pb)
//			return false;
//
//		parent[pa] = pb;
//		return true;
//	}
//}
//
//class Edge implements Comparable<Edge> {
//	int v1;
//	int v2;
//	int weight;
//
//	public Edge(int v1, int v2, int weight) {
//		this.v1 = v1;
//		this.v2 = v2;
//		this.weight = weight;
//	}
//
//	@Override
//	public int compareTo(Edge e) {
//		// TODO Auto-generated method stub
//		return this.weight - e.weight;
//	}
//
//	@Override
//	public String toString() {
//		return v1 + "-" + v2 + " : " + weight;
//	}
//}

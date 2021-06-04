/*
package shortestPathProblem.dijkstra;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class Delivery {

    public static void main(String[] args) {

//		1번 예제 ans = 4
//		int N = 5;
//		int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
//		int K = 3;

// 		2번 예제 ans = 4
        int N = 6;
        int[][] road = {{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}};
        int K = 4;

        System.out.println(new Delivery().solution(N, road, K));
    }

    public int solution(int N, int[][] road, final int K) {

        Node[] nodes = initNodes(N, road);
        Graph graph = new Graph(nodes);
        graph.dijkstra(1);
        int[] minDistances = graph.getMinDistances();

        return (int)Arrays.stream(minDistances).filter(minDistance -> minDistance <= K).count();
    }

    private Node[] initNodes(int n, int[][] road) {
        Node[] nodes = new Node[n + 1];
        for(int i = 1; i < n + 1; i++) {
            nodes[i] = new Node(i);
        }

        for(int[] roadInfo : road) {
            int start = roadInfo[0];
            int end = roadInfo[1];
            int weight = roadInfo[2];
            nodes[start].addEdge(new Edge(nodes[start], nodes[end], weight));
            nodes[end].addEdge(new Edge(nodes[end], nodes[start], weight));
        }

        return nodes;
    }
}

class Graph {
    private int n;
    private boolean[] isVisited;
    private Node[] nodes;
    private int[] minDistances;

    public Graph(Node[] nodes) {
        this.n = nodes.length;
        this.isVisited = new boolean[n + 1];
        this.nodes = nodes;
        initDistance();
    }

    private void initDistance() {
        this.minDistances = new int[n+1];
        minDistances = Arrays.stream(minDistances).map(minDistance -> Integer.MAX_VALUE).toArray();
    }

    public void dijkstra(int start) {
        PriorityQueue<Route> pQueue = new PriorityQueue<>(comparing(Route::getDistance));

        // 시작값 초기화.
        minDistances[start] = 0;
        pQueue.add(new Route(start, 0));

        while(!pQueue.isEmpty() ) {
            Route route = pQueue.poll();

            if(isVisited[route.getDestination()]) continue;
            isVisited[route.getDestination()] = true;

            for(Edge edge : nodes[route.getDestination()].getEdges()) {
                if(route.getDistance() + edge.getWeight() < minDistances[edge.getEndNode().getNum()]) {
                    minDistances[edge.getEndNode().getNum()] = route.getDistance() + edge.getWeight();
                    pQueue.add(new Route(edge.getEndNode().getNum(), minDistances[edge.getEndNode().getNum()]));
                }
            }
        }
    }

    public int[] getMinDistances() {
        return minDistances;
    }
}

class Node {
    private int num;
    private List<Edge> edges;

    public Node(int num) {
        this.num = num;
        this.edges = new ArrayList<>();
    }

    public int getNum() {
        return num;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }
}

class Edge {
    private Node startNode;
    private Node endNode;
    private int weight;

    public Edge(Node startNode, Node endNode, int weight) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public int getWeight() {
        return weight;
    }
}

class Route {
    private int destination;
    private int distance;

    public Route(int destination, int distance) {
        this.destination = destination;
        this.distance = distance;
    }

    public int getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }
}
*/
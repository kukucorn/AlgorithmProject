package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NumOfConnectedElement {

	public static void main(String[] args) throws IOException {

		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		// StringBuilder results = new StringBuilder();

		String graphInfos[] = scan.readLine().split(" ");
		int vCount = Integer.parseInt(graphInfos[0]);
		int eCount = Integer.parseInt(graphInfos[1]);

		Node[] nodes = new Node[vCount + 1];
		for (int i = 1; i < vCount + 1; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < eCount; i++) {
			String[] parsedStr = scan.readLine().split(" ");
			int num1 = Integer.parseInt(parsedStr[0]);
			int num2 = Integer.parseInt(parsedStr[1]);

			nodes[num1].connect(nodes[num2]);
			nodes[num2].connect(nodes[num1]);
		}

		boolean[] isVisited = new boolean[vCount + 1];

		// root node부터 찾기 시작
		System.out.print(getNumOfConnectedComponents(nodes, isVisited));

		scan.close();
	}

	private static int getNumOfConnectedComponents(Node[] nodes, boolean[] isVisited) {

		int num = 0;
		for (int i = 1; i < nodes.length; i++) {
			if (!isVisited[nodes[i].num]) {
				num++;
				findConnectedComponents(isVisited, nodes[i]);
			}
		}
		return num;
	}

	private static void findConnectedComponents(boolean[] isVisited, Node startNode) {
		LinkedList<Node> queue = new LinkedList<>();
		isVisited[startNode.num] = true;
		queue.add(startNode);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node node = queue.pop();
				for (int j = 0; j < node.connects.size(); j++) {
					Node connected = node.connects.get(j);
					if (!isVisited[connected.num]) {
						isVisited[connected.num] = true;
						queue.add(connected);
					}
				}
			}
		}
	}
}

class Node {
	List<Node> connects;
	int num;

	public Node(int n) {
		this.connects = new ArrayList<>();
		this.num = n;
	}

	public void connect(Node other) {
		connects.add(other);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Node " + num + " : ");
		for (int i = 0; i < connects.size(); i++) {
			sb.append(connects.get(i).num + ", ");
		}

		return sb.toString();
	}
}
package kakao.year2020intern;

import java.io.IOException;
import java.util.*;

/*
 * 2020 īī�� ���Ͻ� 3�� ����
 * ���� ����
 * 
 * Ǯ�� :
 * �������� queue�� �����ϰ�,
 * �� �������� ���� �տ� �ִ� �������� treeSet���� �����ϸ�
 * ��� ������ ���Ե� ������ treeSet���� �����Ͽ���.
 * 
 * */


public class Solution3 {
	public int[] solution(String[] gems) {
        
        Map<String, Queue<Integer>> map = new HashMap<>();
        
        for(int i = 0; i < gems.length; i++) {
        	if(!map.containsKey(gems[i])) {
        		map.put(gems[i], new LinkedList<Integer>());
        	} 
        	map.get(gems[i]).add(i);
        }
        
        TreeSet<Node> treeSet = new TreeSet<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return o1.getIndex() - o2.getIndex();
			}
        	
        });
        for(String key : map.keySet()) {
        	treeSet.add(new Node(key, map.get(key).poll()));
        }
        
        int[] answer = new int[2];
        answer[0] = treeSet.first().getIndex();
        answer[1] = treeSet.last().getIndex();
        
        while(true) { 
        	if(map.get(treeSet.first().getGem()).isEmpty()) break;
        	
        	// ���� ���� ��ġ�� gem�� ���� ���� gem�� ���� ��ġ�� set�� add
        	Node lowestNode = treeSet.first();
        	treeSet.remove(lowestNode);
        	treeSet.add(new Node(lowestNode.getGem(), map.get(lowestNode.getGem()).poll()));
        	
        	// answer�� ���� ��Ȳ�� ���ؼ� ���� ���̸� ������ ���� answer��
        	int answerLength = answer[1] - answer[0];
        	int currentLength = treeSet.last().getIndex() - treeSet.first().getIndex();
        	
        	if(answerLength > currentLength) {
        		answer[0] = treeSet.first().getIndex();
        		answer[1] = treeSet.last().getIndex();
        	}
        }
        
        answer[0]++;
        answer[1]++;
        
        return answer;
    }

	public static void main(String[] args) throws IOException {

		String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};

		int[] answer = new Solution3().solution(gems);
		System.out.println(answer[0] + " " + answer[1]);
	}
}

class Node {
	private String gem;
	private int index;
	
	public Node(String gem, int index) {
		this.gem = gem;
		this.index = index;
	}

	public String getGem() {
		return gem;
	}

	public int getIndex() {
		return index;
	}
}
package Trie;//package Trie;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.Map;
//
///*
// * ��ȭ��ȣ ��� (���� 5052��)
// * 
// * ��ȭ��ȣ ����� �ϰ����� ���ϴ� ���α׷�
// * 
// * A(11234)�� ��ȭ�Ϸ��µ� B(112)�� ��ȭ�� ���� �ϰ��� ���ٰ� �Ǻ�
// * 
// * ��ȭ��ȣ�� �ִ� ���̴� 10�ڸ�
// * 
// * Trie �ڷᱸ�� ���
// * 
// * */
//
//public class PhoneBook {
//
//	public static void main(String[] args) throws IOException {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		
//		int testCount = Integer.parseInt(br.readLine());
//		for(int t = 0; t < testCount; t++) {
//			int n = Integer.parseInt(br.readLine());
//			String[] phoneNums = new String[n];
//			for(int i = 0; i < n; i++) {
//				phoneNums[i] = br.readLine();
//			}
//			
//			Trie phoneBook = new Trie();
//			boolean isConsistent = true;
//			for(String phoneNum : phoneNums) {
//				if(!phoneBook.insert(phoneNum)) {
//					isConsistent = false;
//					break;
//				}
//			}
//			
//			if(isConsistent) sb.append("YES\n");
//			else sb.append("NO\n");
//		}
//		
//		System.out.print(sb.toString());
//		
//		br.close();
//	}
//}
//
//class TrieNode {
//	private boolean isLastNum;
//	private Map<Character, TrieNode> childNodes;
//	
//	public TrieNode() {
//		childNodes = new HashMap<>();
//	}
//	
//	public boolean isLastNum() {
//		return isLastNum;
//	}
//	public void setIsLastNum(boolean isLastNum) {
//		this.isLastNum = isLastNum;
//	}
//	
//	public boolean hasChild() {
//		return !childNodes.isEmpty();
//	}
//	
//	public TrieNode getChild(char num) {
//		if(!childNodes.containsKey(num)) {
//			childNodes.put(num, new TrieNode());
//		}
//		return childNodes.get(num);
//	}
//}
//
//class Trie {
//	private TrieNode root;
//	
//	public Trie() {
//		root = new TrieNode();
//	}
//	
//	// ���� �� �ϰ����� ������ false ��ȯ
//	public boolean insert(String phoneNum) {
//		
//		TrieNode curNode = root;
//		int cipher = phoneNum.length(); // �ڸ��� ���ϱ�
//		
//		// ù��° ��ȣ���� �ڸ��� ��ŭ for�� ����
//		for(int i = 0; i < cipher; i++) { 
//			
//			char curNum = phoneNum.charAt(i);
//			
//			// ���� ������ child ��������
//			curNode = curNode.getChild(curNum);
//			
//			// if(childNode�� ������ ����) �ϰ����� ���� ��ȭ��ȣ��
//			if(curNode.isLastNum()) return false;
//		}
//		
//		curNode.setIsLastNum(true);
//		// ���� ��� �Ʒ��� child ��尡 �����ϸ� �ϰ����� ���� ��ȭ��ȣ��
//		if(curNode.hasChild()) return false;
//		
//		return true;
//	}
//}
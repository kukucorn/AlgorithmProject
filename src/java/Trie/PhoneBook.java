package Trie;//package Trie;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.Map;
//
///*
// * 전화번호 목록 (백준 5052번)
// * 
// * 전화번호 목록의 일관성을 구하는 프로그램
// * 
// * A(11234)에 전화하려는데 B(112)에 전화가 가면 일관성 없다고 판별
// * 
// * 전화번호의 최대 길이는 10자리
// * 
// * Trie 자료구조 사용
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
//	// 삽입 중 일관성이 없으면 false 반환
//	public boolean insert(String phoneNum) {
//		
//		TrieNode curNode = root;
//		int cipher = phoneNum.length(); // 자릿수 구하기
//		
//		// 첫번째 번호부터 자릿수 만큼 for문 실행
//		for(int i = 0; i < cipher; i++) { 
//			
//			char curNum = phoneNum.charAt(i);
//			
//			// 현재 숫자의 child 가져오기
//			curNode = curNode.getChild(curNum);
//			
//			// if(childNode가 마지막 숫자) 일관성이 없는 전화번호부
//			if(curNode.isLastNum()) return false;
//		}
//		
//		curNode.setIsLastNum(true);
//		// 현재 노드 아래에 child 노드가 존재하면 일관성이 없는 전화번호부
//		if(curNode.hasChild()) return false;
//		
//		return true;
//	}
//}
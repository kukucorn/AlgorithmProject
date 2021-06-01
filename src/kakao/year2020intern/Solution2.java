package kakao.year2020intern;

import java.util.*;

/*
 * 2020 īī�� ���Ͻ� 2�� ����
 * ���� �ִ�ȭ
 * 
 * Ǯ�� :
 * ���⼭ ���� �� �ִ� ����� ���� 6���� �ۿ� ����(������ �� �ʿ� ����)
 * expression���� ���ڿ� �����ڸ� ������� list�� �����ϸ鼭
 * �켱������ ���� ������ ���� ����س����鼭 
 * ����� ���� ���ڿ� �����ڴ� ���ְ��鼭
 * �������� �����ִ� ���ڰ� ���
 * 
 * */

class Solution {
    public long solution(String expression) {
        long answer = 0;
        
        String[] strNums = expression.split("[\\*\\+-]");
        LinkedList<Long> numList = new LinkedList<Long>();
        for(int i = 0; i < strNums.length; i++) {
        	numList.add(Long.parseLong(strNums[i]));
        }
        LinkedList<Character> operatorList = new LinkedList<Character>();
        for(int i = 0; i < expression.length(); i++) {
        	char c = expression.charAt(i);
        	if(c == '+' || c == '-' || c == '*') {
        		operatorList.add(c);
        	}
        }
        
        
        final char[] OPERATOR = {'+', '-', '*'};
        int[][] operatorComb = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};
        
        Long max = 0L;
        for(int[] comb : operatorComb) {
        	LinkedList<Long> tempNumList = (LinkedList<Long>) numList.clone();
        	LinkedList<Character> tempOperList = (LinkedList<Character>) operatorList.clone();
        	for(int operNum : comb) {
        		for(int i = 0; i < tempOperList.size();) {
        			if(tempOperList.get(i) == OPERATOR[operNum]) {
        				if(OPERATOR[operNum] == '+') {
        					tempNumList.set(i, tempNumList.get(i) + tempNumList.get(i + 1));
        				} else if(OPERATOR[operNum] == '-') {
        					tempNumList.set(i, tempNumList.get(i) - tempNumList.get(i + 1));
        				} else if(OPERATOR[operNum] == '*') {
        					tempNumList.set(i, tempNumList.get(i) * tempNumList.get(i + 1));
        				} 
        				tempNumList.remove(i + 1);
        				tempOperList.remove(i);
        			} else {
        				i++;
        			}
        		}
        	}
        	Long result = Math.abs(tempNumList.get(0)); 
        	if(max < result) {
        		max = result;
        	}
        }
        
        return max;
    }
}
package kakao.year2020intern;

import java.util.*;

/*
 * 2020 카카오 인턴십 2번 문제
 * 수식 최대화
 * 
 * 풀이 :
 * 여기서 나올 수 있는 경우의 수는 6가지 밖에 없다(조합을 쓸 필요 없음)
 * expression에서 숫자와 연산자를 순서대로 list에 유지하면서
 * 우선순위가 높은 연산자 부터 계산해나가면서 
 * 계산을 끝낸 숫자와 연산자는 없애가면서
 * 마지막에 남아있는 숫자가 결과
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
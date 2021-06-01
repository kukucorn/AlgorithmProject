package CompanyCollection.DevMatching_2021;

import java.util.*;

public class Lotto {
    public static void main(String[] args) {
//		1번 예제  ans = {3, 5}
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};

//		2번 예제  ans = {1, 6}
//		int[] lottos = {0, 0, 0, 0, 0, 0};
//		int[] win_nums = {38, 19, 20, 40, 15, 25};

//		3번 예제  ans = {1, 1}
//		int[] lottos = {45, 4, 35, 20, 3, 9};
//		int[] win_nums = {20, 9, 3, 45, 4, 35};

        for(int answer : new Lotto().solution(lottos, win_nums)) {
            System.out.println(answer);
        }
    }

    public int[] solution(int[] lottos, int[] win_nums) {

        final int[] RANK = {6, 6, 5, 4, 3, 2, 1};

        int minMatchingCnt = getMatchingCount(lottos, win_nums);
        int maxMatchingCnt = minMatchingCnt + countUnknownNum(lottos);

        int[] answer = {RANK[maxMatchingCnt], RANK[minMatchingCnt]};
        return answer;
    }

    private int countUnknownNum(int[] lottos) {
        return (int)Arrays.stream(lottos).filter(lotto -> lotto == 0).count();
    }

    private int getMatchingCount(int[] lottos, int[] win_nums) {
        return (int)Arrays.stream(lottos)
                .filter(lotto -> Arrays.stream(win_nums)
                        .anyMatch(win_num -> lotto == win_num))
                .count();
    }
}
package CompanyCollection.KAKAO_BLIND_2021;

import java.util.*;

public class MenuRenewal {

    public static void main(String[] args) {
//		1번 예제 ans = ["AC", "ACDE", "BCFG", "CDE"]
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};

// 		2번 예제 ans = ["ACD", "AD", "ADE", "CD", "XYZ"]
//		String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
//		int[] course = {2, 3, 5};

// 		3번 예제 ans = ["WX", "XY"]
//		String[] orders = {"XYZ", "XWY", "WXA"};
//		int[] course = {2, 3, 4};

        Arrays.stream(new MenuRenewal().solution(orders, course)).forEach(ans -> System.out.println(ans));
    }

    public String[] solution(String[] orders, int[] course) {

        Map<String, Integer> courseMap = new HashMap<>();
        for(String order : orders) {
            String sortedOrder = sortCharsInString(order);
            Combination combination = new Combination(sortedOrder);
            for(int courseCnt : course) {
                List<String> resultList = getCombinationResult(combination, sortedOrder.length(), courseCnt);
                putCombinationResultInMap(resultList, courseMap);
            }
        }

        List<String> answerList = new ArrayList<>();
        for(int courseCnt : course) {
            List<String> answerListForCnt = new ArrayList<>();
            int maxOrderCnt = Integer.MIN_VALUE;
            for(String key : courseMap.keySet()) {
                if(key.length() == courseCnt) {
                    if(courseMap.get(key) > 1 && courseMap.get(key) > maxOrderCnt) {
                        maxOrderCnt = courseMap.get(key);
                        answerListForCnt = new ArrayList<>();
                        answerListForCnt.add(key);
                    } else if(maxOrderCnt == courseMap.get(key)) {
                        answerListForCnt.add(key);
                    }
                }
            }
            answerList.addAll(answerListForCnt);
        }

        Collections.sort(answerList);

        return answerList.toArray(new String[answerList.size()]);
    }

    private String sortCharsInString(String order) {
        char[] chars = order.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    private List<String> getCombinationResult(Combination combination, int n, int r) {
        combination.initialResult();
        combination.combination(0, n, r);
        return combination.getResult();
    }

    private void putCombinationResultInMap(List<String> resultList, Map<String, Integer> courseMap) {
        for(String combinationResult : resultList) {
            if(courseMap.containsKey(combinationResult)) {
                courseMap.put(combinationResult, courseMap.get(combinationResult) + 1);
            } else {
                courseMap.put(combinationResult, 1);
            }
        }
    }
}

class Combination {
    private String elements;
    private boolean[] isVisited;
    private List<String> result;

    public Combination(String elements) {
        this.elements = elements;
        this.isVisited = new boolean[elements.length()];
        this.result = new ArrayList<>();
    }

    public void combination(int start, int n, int r) {
        if (r == 0) {
            result.add(makeCombination());
            return;
        }

        for (int i = start; i < n; i++) {
            isVisited[i] = true;
            combination(i + 1, n, r - 1);
            isVisited[i] = false;
        }
    }

    private String makeCombination() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < isVisited.length; i++) {
            if (isVisited[i]) {
                sb.append(elements.charAt(i));
            }
        }
        return sb.toString();
    }

    public void initialResult() {
        this.result = new ArrayList<>();
    }

    public List<String> getResult() {
        return result;
    }
}

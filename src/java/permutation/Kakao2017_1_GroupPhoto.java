package permutation;

import java.io.IOException;
import java.util.*;

public class Kakao2017_1_GroupPhoto {

    public int solution(int n, String[] data) {

        final Character[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};

        StandCondition[] conditions = dataToConditions(data);
        Permutation permutation = new Permutation(friends);
        permutation.permutation(0);
        List<String> permutationResults = permutation.getResults();

        int answer = factorial(8);
        for(String permutationResult : permutationResults) {
            for(StandCondition condition : conditions) {
                if(!isSatisfyingCondition(permutationResult, condition)) {
                    answer--;
                    break;
                }
            }
        }

        return answer;
    }

    private int factorial(int num) {
        if(num == 1) return 1;
        return num * factorial(num - 1);
    }

    private boolean isSatisfyingCondition(String standingLine, StandCondition condition) {
        int distance = measureDistance(standingLine, condition.getFirstFriend(), condition.getSecondFriend()) - 1;
        if(condition.getDistanceSign() == '>' && distance > condition.getDistance()) return true;
        else if(condition.getDistanceSign() == '=' && distance == condition.getDistance()) return true;
        else if(condition.getDistanceSign() == '<' && distance < condition.getDistance()) return true;
        return false;
    }

    private int measureDistance(String standingLine, char friend1, char friend2) {
        return Math.abs(standingLine.indexOf(friend1) - standingLine.indexOf(friend2));
    }

    private StandCondition[] dataToConditions(String[] data) {
        StandCondition[] conditions = new StandCondition[data.length];
        for(int i = 0; i < data.length; i++) {
            conditions[i] = dataToCondition(data[i]);
        }
        return conditions;
    }

    private StandCondition dataToCondition(String data) {
        return new StandCondition(data.charAt(0), data.charAt(2), data.charAt(4) - '0', data.charAt(3));
    }

    public static void main(String[] args) throws IOException {

        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};

        System.out.println(new Kakao2017_1_GroupPhoto().solution(n, data));

    }
}

class StandCondition {
    private char firstFriend;
    private char secondFriend;
    private int distance;
    private char distanceSign;

    public StandCondition(char firstFriend, char secondFriend, int distance, char distanceSign) {
        this.firstFriend = firstFriend;
        this.secondFriend = secondFriend;
        this.distance = distance;
        this.distanceSign = distanceSign;
    }

    public char getFirstFriend() {
        return firstFriend;
    }

    public char getSecondFriend() {
        return secondFriend;
    }

    public int getDistance() {
        return distance;
    }

    public char getDistanceSign() {
        return distanceSign;
    }
}

class Permutation {
    private Character[] elements;
    private boolean[] isVisited;
    private char[] arrangement;
    private int repetition;
    private List<String> results;

    public Permutation(Character[] elements) {
        this.elements = elements;
        this.isVisited = new boolean[elements.length];
        this.arrangement = new char[elements.length];
        this.repetition = elements.length;
        this.results = new ArrayList<>();
    }

    public void permutation(int idx) {
        if(idx == repetition) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < repetition; i++) {
                sb.append(arrangement[i]);
            }
            results.add(sb.toString());
        }
        for(int i = 0; i < elements.length; ++i) {
            if(isVisited[i]) continue;

            arrangement[idx] = elements[i];
            isVisited[i] = true;
            permutation(idx+1);
            isVisited[i] = false;
        }
    }

    public List<String> getResults() {
        return results;
    }
}
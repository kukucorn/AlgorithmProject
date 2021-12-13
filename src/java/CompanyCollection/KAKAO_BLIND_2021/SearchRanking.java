package CompanyCollection.KAKAO_BLIND_2021;

import java.util.*;

public class SearchRanking {

    public static void main(String[] args) {

//		1¹ø ¿¹Á¦ ans = {1,1,1,1,2,4}
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        Arrays.stream(new SearchRanking().solution(info, query)).forEach(System.out::println);
    }

    public int[] solution(String[] info, String[] query) {

        final Applicant[] applicants = initApplicants(info);
        final Query[] queries = initQueries(query);

        List<Integer> answerList = new ArrayList<>();
        ApplicantExtracter extracter = new ApplicantExtracter(applicants);
        for(Query q : queries) {
            List<Integer> list = extracter.extract(q);
            if(list == null) answerList.add(0);
            else {
                int findIndex = lowerBound(list, q.getScore());
                answerList.add(list.size() - findIndex);
            }
        }

        return answerList.stream().mapToInt(i -> i).toArray();
    }

    private Applicant[] initApplicants(String[] info) {
        return Arrays.stream(info).map(Applicant::new).toArray(Applicant[]::new);
    }

    private Query[] initQueries(String[] query) {
        return Arrays.stream(query).map(Query::new).toArray(Query[]::new);
    }

    public int lowerBound(List<Integer> list,  int value) {
        int low = 0;
        int high = list.size();
        while (low < high) {
            final int mid = low + (high - low)/2;
            if (value <= list.get(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}

class ApplicantExtracter {
    private Map<String, List<Integer>> map;

    public ApplicantExtracter(Applicant[] applicants) {
        this.map = new HashMap<>();
        initMap(applicants);
        sortValueOfMap();
    }

    private void initMap(Applicant[] applicants) {
        for(Applicant applicant : applicants) {
            String[] conditions = ConditionGenerator.generateConditionByApplicant(applicant);
            for(String condition : conditions) {
                if(map.containsKey(condition)) {
                    map.get(condition).add(applicant.getScore());
                } else {
                    map.put(condition, new ArrayList<Integer>());
                    map.get(condition).add(applicant.getScore());
                }
            }
        }
    }

    private void sortValueOfMap() {
        for(List<Integer> list : map.values()) {
            Collections.sort(list);
        }
    }

    public List<Integer> extract(Query query) {
        String condition = ConditionGenerator.generateConditionByQuery(query);
        if(!map.containsKey(condition)) return null;
        return map.get(condition);
    }
}

class Applicant {
    private String language;
    private String jobGroup;
    private String career;
    private String soulFood;
    private int score;

    public Applicant(String info) {
        String[] splitedInfo = info.split(" ");
        this.language = splitedInfo[0];
        this.jobGroup = splitedInfo[1];
        this.career = splitedInfo[2];
        this.soulFood = splitedInfo[3];
        this.score = Integer.parseInt(splitedInfo[4]);
    }

    public String getLanguage() { return language; }

    public String getJobGroup() {
        return jobGroup;
    }

    public String getCareer() { return career; }

    public String getSoulFood() {
        return soulFood;
    }

    public int getScore() {
        return score;
    }
}

class Query {
    private String language;
    private String jobGroup;
    private String career;
    private String soulFood;
    private int score;

    public Query(String query) {
        String[] piecesOfQuery = query.split(" ");
        this.language = piecesOfQuery[0];
        this.jobGroup = piecesOfQuery[2];
        this.career = piecesOfQuery[4];
        this.soulFood = piecesOfQuery[6];
        this.score = Integer.parseInt(piecesOfQuery[7]);
    }

    public String getLanguage() {
        return language;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public String getCareer() {
        return career;
    }

    public String getSoulFood() {
        return soulFood;
    }

    public int getScore() {
        return score;
    }
}

class ConditionGenerator {

    final static boolean[][] conditionCombinations = {
            {true, true, true, true},
            {false, true, true, true},
            {true, false, true, true},
            {true, true, false, true},
            {true, true, true, false},
            {false, false, true, true},
            {false, true, false, true},
            {false, true, true, false},
            {true, false, false, true},
            {true, false, true, false},
            {true, true, false, false},
            {false, false, false, true},
            {false, false, true, false},
            {false, true, false, false},
            {true, false, false, false},
            {false, false, false, false},
    };

    public static String[] generateConditionByApplicant(Applicant applicant) {
        Set<String> conditionSet = new HashSet<>();
        String[] applicantInfoCombination = new String[4];
        for(boolean[] conditionCombination : conditionCombinations) {
            for(int j = 0; j < 4; j++) {
                if(conditionCombination[j]) applicantInfoCombination[j] = "-";
                else {
                    switch(j) {
                        case 0:
                            applicantInfoCombination[j] = applicant.getLanguage();
                            break;
                        case 1:
                            applicantInfoCombination[j] = applicant.getJobGroup();
                            break;
                        case 2:
                            applicantInfoCombination[j] = applicant.getCareer();
                            break;
                        case 3:
                            applicantInfoCombination[j] = applicant.getSoulFood();
                            break;
                    }
                }
            }
            conditionSet.add(generateCondition(applicantInfoCombination[0], applicantInfoCombination[1], applicantInfoCombination[2], applicantInfoCombination[3]));
        }
        return conditionSet.toArray(String[]::new);
    }

    public static String generateConditionByQuery(Query query) {
        return generateCondition(query.getLanguage(), query.getJobGroup(), query.getCareer(), query.getSoulFood());
    }

    private static String generateCondition(String language, String jobGroup, String career, String soulFood) {
        return new StringBuilder().append(language).append(jobGroup).append(career).append(soulFood).toString();
    }
}
package CompanyCollection.KAKAO_BLIND_2021;

import java.util.HashMap;
import java.util.Map;

public class IDRecommendation {

    public static void main(String[] args) {
//		1번 예제 ans = "bat.y.abcdefghi"
//		String new_id = "...!@BaT#*..y.abcdefghijklm";

// 		2번 예제 ans = "z--"
//		String new_id = "z-+.^.";

//		3번 예제 ans = "aaa"
//		String new_id = "=.=";

//		4번 예제 ans = "123_.def"
//		String new_id = "123_.def";

//		5번 예제 ans = "abcdefghijklmn"
        String new_id = "abcdefghijklmn.p";

        System.out.println(new IDRecommendation().solution(new_id));
    }

    public String solution(String new_id) {
        StringBuilder id = new StringBuilder(new_id);

        convertUpperCaseToLowerCase(id);
        eliminateIllegalCharacter(id);
        convertChainDotToOneDot(id);
        eliminateDotAtStartAndFinish(id);
        initialAIfEmptyString(id);
        eliminateOverLength(id);
        if(id.length() == 15) eliminateDotAtIndex(id, 14);
        if(!isSatisfyMinimumLength(id)) repeatRearUntilSatisfyingMinimumLength(id);

        return id.toString();
    }



    private void convertUpperCaseToLowerCase(StringBuilder id) {
        for(int i = 0; i < id.length(); i++) {
            if(Character.isUpperCase(id.charAt(i))) {
                id.setCharAt(i, Character.toLowerCase(id.charAt(i)));
            }
        }
    }

    private void eliminateIllegalCharacter(StringBuilder id) {
        for(int i = 0; i < id.length();) {
            if(isLegalCharacter(id.charAt(i))) {
                i++;
            } else {
                id.deleteCharAt(i);
            }
        }
    }

    private boolean isLegalCharacter(char c) {
        if(Character.isLowerCase(c)) return true;
        if(Character.isDigit(c)) return true;
        if(c == '-' || c == '_' || c == '.') return true;
        return false;
    }

    private void convertChainDotToOneDot(StringBuilder id) {
        for(int i = 0; i < id.length(); i++) {
            if(id.charAt(i) == '.') {
                for(int j = i + 1; j < id.length();) {
                    if(id.charAt(j) == '.') {
                        id.deleteCharAt(j);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private void eliminateDotAtStartAndFinish(StringBuilder id) {
        eliminateDotAtIndex(id, 0);
        eliminateDotAtIndex(id, id.length() - 1);
    }

    private void eliminateDotAtIndex(StringBuilder id, int index) {
        if(index < 0) return;
        if(id.charAt(index) == '.') id.deleteCharAt(index);
    }

    private void initialAIfEmptyString(StringBuilder id) {
        if(id.length() == 0) id.append('a');
    }

    private void eliminateOverLength(StringBuilder id) {
        while(id.length() > 15) {
            id.deleteCharAt(15);
        }
    }

    private boolean isSatisfyMinimumLength(StringBuilder id) {
        return id.length() > 2;
    }

    private void repeatRearUntilSatisfyingMinimumLength(StringBuilder id) {
        while(!isSatisfyMinimumLength(id)) {
            id.append(id.charAt(id.length() - 1));
        }
    }
}
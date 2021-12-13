package CompanyCollection.DevMatching_2021;

import java.util.HashMap;
import java.util.Map;

public class SaleToothBrush {
    public static void main(String[] args) {
//		1번 예제  ans = {360, 958, 108, 0, 450, 18, 180, 1080}
//		String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
//		String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
//		String[] seller = {"young", "john", "tod", "emily", "mary"};
//		int[] amount = {12, 4, 2, 5, 10};

//		2번 예제  ans = {0, 110, 378, 180, 270, 450, 0, 0}
//		String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
//		String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
//		String[] seller = {"sam", "emily", "jaimie", "edward"};
//		int[] amount = {2, 3, 5, 4};

        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"sam"};
        int[] amount = {11};

        for(int answer : new SaleToothBrush().solution(enroll, referral, seller, amount)) {
            System.out.println(answer);
        }
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        Map<String, Member> memberMap = new HashMap<>();
        memberMap.put("-", new Member("center"));
        for(int i = 0; i < enroll.length; i++) {
            Member member = new Member(enroll[i]);
            member.introducedBy(memberMap.get(referral[i]));
            memberMap.put(member.getName(), member);
        }

        for(int i = 0; i < seller.length; i++) {
            memberMap.get(seller[i]).makeMoney(amount[i] * 100);
        }

        int[] answer = new int[enroll.length];
        for(int i = 0; i < enroll.length; i++) {
            answer[i] = memberMap.get(enroll[i]).getProfit();
        }

        return answer;
    }
}

class Member {
    private String name;
    private Member referral;
    private int profit;

    public Member(String name) {
        this.name = name;
        this.profit = 0;
    }

    public void introducedBy(Member referral) {
        this.referral = referral;
    }

    public void makeMoney(int money) {
        if(amICenter() || cannotDivideMoney(money)) {
            profit += money;
        } else {
            int referralProfit = (int)Math.floor(money * 0.1);
            int myProfit = money - referralProfit;
            profit += myProfit;
            referral.makeMoney(referralProfit);
        }
    }
    private boolean amICenter() {
        return this.referral == null ? true : false;
    }

    private boolean cannotDivideMoney(int money) {
        return money < 10 ? true : false;
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
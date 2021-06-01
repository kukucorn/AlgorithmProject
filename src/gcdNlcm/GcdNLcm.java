package gcdNlcm;

public class GcdNLcm {
	public static void main(String[] args) {
		
	}
    
}

class Solution {
    public int[] solution(int n, int m) {
        
        if(n < m) {
            int tmp = n;
            n = m;
            m = tmp;
        }
        
        int gcd = GCD(n, m);
        int lcm = gcd * (n / gcd) * (m / gcd);
        
        int[] answer = new int[2];
        answer[0] = gcd;
        answer[1] = lcm;
        
        return answer;
    }

    public int GCD(int a, int b) {
        while(b > 0) {
            int tmp = a;
            a = b;
            b = tmp % b;
        }
        return a;
    }
}
package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GcdNLcm {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int gcd = gcd(a, b);
        int lcm = gcd * (a / gcd) * (b / gcd);

        System.out.println(gcd);
        System.out.println(lcm);
    }

    // 유클리드 호제법 사용
    public static int gcd(int p, int q)
    {
        if (q == 0) return p;
        return gcd(q, p%q);
    }
}

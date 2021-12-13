package math;

// 이항계수 : 조합의 경우의 수
// Dynamic Programming 을 활용함
// 이항계수의 법칙
// 1) nCk = 1 (n == k 혹은 k == 0)
//    (n개 중 n개를 선택하는 경우의 수) 와 (n개 중 0개를 선택하는 경우의 수) 는 항상 1개
// 2) nCk = (n-1)C(k-1) + (n-1)C(k)
//    위 법칙을 점화식으로 해석하면 쉬움
//    n개 중 k개를 선택하는 경우의 수는 1개를 선택하고 n-1개 중 k-1개를 선택하는 경우의 수 + 1개를 선택하지 않고 n-1개 중 k개를 선택하는 경우의 수
class BinomialCoefficient {
    private int[][] DP;

    public BinomialCoefficient(int n, int k) {
        DP = new int[n + 1][k + 1];
    }

    public int bino_coef(int n, int k) {

        if(DP[n][k] > 0) return DP[n][k];
        if(n == k || k == 0) return DP[n][k] = 1;

        return DP[n][k] = (bino_coef(n - 1, k) + bino_coef(n - 1, k - 1));
    }
}
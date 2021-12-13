package math;

// ���װ�� : ������ ����� ��
// Dynamic Programming �� Ȱ����
// ���װ���� ��Ģ
// 1) nCk = 1 (n == k Ȥ�� k == 0)
//    (n�� �� n���� �����ϴ� ����� ��) �� (n�� �� 0���� �����ϴ� ����� ��) �� �׻� 1��
// 2) nCk = (n-1)C(k-1) + (n-1)C(k)
//    �� ��Ģ�� ��ȭ������ �ؼ��ϸ� ����
//    n�� �� k���� �����ϴ� ����� ���� 1���� �����ϰ� n-1�� �� k-1���� �����ϴ� ����� �� + 1���� �������� �ʰ� n-1�� �� k���� �����ϴ� ����� ��
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
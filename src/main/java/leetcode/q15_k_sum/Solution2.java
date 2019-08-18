package leetcode.q15_k_sum;

/**
 * LintCode solution
 * Refer: https://www.jiuzhang.com/solutions/k-sum/
 */

public class Solution2 {
    /**
     * @param A:      An integer array
     * @param k:      A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */

    // 此解法限定了target必须要正整数！！
    public int kSum(int[] A, int k, int target) {
        int len = A.length;
        /* mem[i][j][t] 表示前i个整数中，选j个，和为t的解法个数！为了对应，下面建的数组[i][j]行列个多加1, [t] 也要加1， 因为可能选从0~>target 一同 target+1的和数*/
        int[][][] mem = new int[len + 1][k + 1][target+1];

        //init: set mem[0][1][t] = mem[1][1][t] for convenient
        for (int p = 0; p < len; p++) mem[0][1][A[p]] = 1;

        for (int i = 1; i <= len; i++) {//前1～len个整数
            for (int j = 1; j <= k && j <= i; j++) { //选1～k 或 1～i (if i < k) 个，
                for (int t = 0; t <= target; t++) { //使和为t , t ~ [0, target]
                    if (A[i-1] > t) {
                        mem[i][j][t] = mem[i - 1][j][t]; //don't use A[i-1], 注意这里是(i-1)th index，因为i在这里的意思表示的前i个（长度）
                    } else
                        mem[i][j][t] = mem[i - 1][j - 1][t - A[i-1]] + mem[i - 1][j][t]; //use A[i]  + dont use A[i-1]
                }
            }
        }

        return mem[len][k][target];
    }

    //参考答案1: target不能为负数!
//        public int  kSum(int A[], int k, int target) {
//        int n = A.length;
//        int[][][] f = new int[n + 1][k + 1][target + 1];
//        for (int i = 0; i < n + 1; i++) {
//            f[i][0][0] = 1;
//        }
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= k && j <= i; j++) {
//                for (int t = 1; t <= target; t++) {
//                    f[i][j][t] = 0;
//                    if (t >= A[i - 1]) {
//                        f[i][j][t] = f[i - 1][j - 1][t - A[i - 1]];
//                    }
//                    f[i][j][t] += f[i - 1][j][t];
//                } // for t
//            } // for j
//        } // for i
//        return f[n][k][target];
//    }

//    int[] res;
//    int tot;
//    int[] A;
//    int K;
//    int[][][] f;
//
//    void printAnswer(int i, int j, int k) {
//        if (j == 0) {
//            for (int h = 0; h < K; ++h) {
//                System.out.print(res[h]);
//                if (h == K - 1) {
//                    System.out.println("=" + tot);
//                }
//                else {
//                    System.out.print("+");
//                }
//            }
//
//            return;
//        }
//
//        if (f[i - 1][j][k] > 0) {
//            printAnswer(i - 1, j, k);
//        }
//
//        if (j > 0 && k >= A[i - 1] && f[i - 1][j - 1][k - A[i - 1]] > 0) {
//            res[j - 1] = A[i - 1];
//            printAnswer(i - 1, j - 1, k - A[i - 1]);
//        }
//    }
//
//    public int kSum(int[] AA, int KK, int T) {
//        K = KK;
//        A = AA;
//        int n = A.length;
//        res = new int[K];
//        tot = T;
//        f = new int[n + 1][K + 1][T + 1];
//        int i, j, k;
//        for (j = 0; j <= K; ++j) {
//            for (k = 0; k <= T; ++k) {
//                f[0][j][k] = 0;
//            }
//        }
//
//        f[0][0][0] = 1;
//        for (i = 1; i <= n; ++i) {
//            for (j = 0; j <= K; ++j) {
//                for (k = 0; k <= T; ++k) {
//                    // not using A[i - 1]
//                    f[i][j][k] = f[i - 1][j][k];
//
//                    // using A[i - 1]
//                    if (j > 0 && k >= A[i - 1]) {
//                        f[i][j][k] += f[i - 1][j - 1][k - A[i - 1]];
//                    }
//                }
//            }
//        }
//
//        printAnswer(n, K, T);
//
//        return f[n][K][T];
//    }
}

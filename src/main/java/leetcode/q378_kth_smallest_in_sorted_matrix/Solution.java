package leetcode.q378_kth_smallest_in_sorted_matrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    public int kthSmallest(int[][] matrix, int k) {
        final int n = matrix.length;

        if (k < 1 || k > n * n || n != matrix[0].length)
            throw new IllegalArgumentException("Input error");

        //Mathematic solution
        final double a = -1;
        final double b = 2 * (n - 1);
        final double c = 2 * n - 1 - k;

        final double delta = Math.sqrt(b * b - 4 * a * c);
        final double x1 = (-b + delta) / (2 * a);

        // the sum of elements in matrix from 0 to index i <= k
        final int i = (int) Math.floor(x1); //todo: Potential data loss if n is large ( double -> int !!)

        int remain = k - (i + 1) * (2 * n - 1 - i);
        if (remain == 0 || i == n - 1)
            return Math.max(matrix[i][n - 1], matrix[n - 1][i]);
        else if (remain == 1)
            return matrix[i + 1][i + 1];
        else {
            int pr = i + 2, pc = i + 2; //Pointer to the next index on row/column
            remain--;

            int res = -1;
            while (remain != 0) {
                if (pr == n || (pc < n && matrix[i + 1][pc] <= matrix[pr][i + 1])) { //TODO: Note this short-surcuite judge
                    res = matrix[i + 1][pc];
                    pc++;
                    remain--;
                    continue;
                }

                if ( pc == n || (pr < n && matrix[pr][i + 1] <= matrix[i + 1][pc])) {
                    res = matrix[pr][i + 1];
                    pr++;
                    remain--;
                    continue;
                }
            }

            return res;
        }

    }
}

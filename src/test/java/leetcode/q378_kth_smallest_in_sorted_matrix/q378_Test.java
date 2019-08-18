package leetcode.q378_kth_smallest_in_sorted_matrix;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class q378_Test {
    Solution s;
    int[][] input;

    @Before
    public void setUp() throws Exception {
        s = new Solution();
        input = new int[][]{
                {1, 5, 9, 11},
                {10, 11, 13, 15},
                {12, 14, 15, 17},
                {14, 15, 16, 20}

        };
    }

    @Test
    public void kthSmallest() {
        final int n = input.length;
        assertEquals(s.kthSmallest(input, 1), input[0][0]);
        assertEquals(s.kthSmallest(input, n * n), input[n - 1][n - 1]);

        assertEquals(s.kthSmallest(input, 6), 12);
        assertEquals(s.kthSmallest(input, 12), 15);
        assertEquals(s.kthSmallest(input, 14), 16);
    }
}
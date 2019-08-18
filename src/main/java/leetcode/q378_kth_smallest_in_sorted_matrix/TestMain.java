package leetcode.q378_kth_smallest_in_sorted_matrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

        int[][] input = new int[][]{
                {1, 5, 9, 11},
                {10, 11, 13, 15},
                {12, 14, 15, 17},
                {14, 15, 16, 20}

        };
        int res = s.kthSmallest(input, 14);
        logger.debug("");
    }
}

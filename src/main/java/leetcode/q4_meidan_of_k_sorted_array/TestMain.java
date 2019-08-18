package leetcode.q4_meidan_of_k_sorted_array;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] input = new int[][]{
                {1, 3, 5, 7},
                {9},
                {2, 4, 8,12},
                {6, 10}
        };
        double res = s.findMedian(input);
        logger.debug("");
    }
}

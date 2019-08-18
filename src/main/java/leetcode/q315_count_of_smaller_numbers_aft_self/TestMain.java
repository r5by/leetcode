package leetcode.q315_count_of_smaller_numbers_aft_self;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
//        int[] input = new int[] {5, 2, 6, 1};
//        int[] input = new int[]{};
//        int[] input = new int[]{1, 7, 5, 4, 2, 3, 0};
        int[] input = new int[]{-1, -1};
        List<Integer> res = s.countSmaller(input);

        logger.debug("");
    }
}

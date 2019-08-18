package leetcode.q31_next_permutation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {1,2,7,4,3,1};
        s.nextPermutation(nums);
        logger.debug("");
    }
}

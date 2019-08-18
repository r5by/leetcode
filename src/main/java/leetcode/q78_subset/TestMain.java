package leetcode.q78_subset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] nums = {1, 2, 3};
        int[] nums_dup = {1, 2, 1};

        //1. without duplicates
//        List<List<Integer>> res = s.subset(nums, true);

        //2. dups
        List<List<Integer>> res = s.subset(nums_dup, false);

        logger.debug("");
    }
}

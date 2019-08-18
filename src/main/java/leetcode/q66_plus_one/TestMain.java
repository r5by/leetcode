package leetcode.q66_plus_one;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

//        int[] input = {1, 2, 3};
        int[] input = {8,9,9,9};
        int[] puls1 = s.plusOne(input);
        logger.debug("");
    }
}

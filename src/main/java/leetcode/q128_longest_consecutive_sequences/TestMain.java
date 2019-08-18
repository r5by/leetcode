package leetcode.q128_longest_consecutive_sequences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

//        int[] input = {100, 4, 200, 1, 3, 2};
        int[] input = {};
        int longest = s.longestConsecutive(input);
        logger.debug("");
    }
}

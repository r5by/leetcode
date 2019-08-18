package leetcode.q3_longest_substring_without_repeat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

        String input = "abba";
//        String input = "abcabcbb";
        int res = s.lengthOfLongestSubstring(input);
        logger.debug("");
    }
}

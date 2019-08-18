package leetcode.q74_min_window_substring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
//        String in = "ADOBECODEBANC";
//        String t = "ABC";
        String in = "ab";
        String t = "a";
//        String in = "aa";
//        String t = "aa";

        String res = s.minWindow(in, t);

        logger.debug("");
    }
}

package leetcode.q344_reverse_string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
        char[] input = {'h', 'e', 'l', 'l', 'o'};
        s.reverseString(input);
        logger.debug("");
    }
}

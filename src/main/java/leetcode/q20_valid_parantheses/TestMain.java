package leetcode.q20_valid_parantheses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

        boolean res = s.isValid("()");
        boolean res1 = s.isValid("()[]{}");
        boolean res2 = s.isValid("([)]");
        boolean res4 = s.isValid("]");
        logger.debug("");
    }
}

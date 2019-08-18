package leetcode.q227_basic_calculator_ii;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
//        String input = "3  +2* 20";
        String input = "1-2 * 2";
        int res = s.calculate(input);
        logger.debug("");
    }
}

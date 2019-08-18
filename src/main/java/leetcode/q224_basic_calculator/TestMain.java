package leetcode.q224_basic_calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
//        String input = "1+(2-3)+((4-5)+(6-7))";
        String input = "1-2+1";
        int res = s.calculate(input);
        logger.debug("");
    }
}

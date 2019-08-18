package leetcode.q65_valid_number;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

//        String input = "0";
        String input = "+.8";
        boolean b = s.isNumber(input);
        logger.debug("");
    }
}

package leetcode.q50_my_pow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

        double r1 = s.myPow(2, -2);
        double r2 = s.myPow(2, 3);
        logger.debug("");
    }
}

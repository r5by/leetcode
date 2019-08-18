package leetcode.rolling_average;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

        double a = s.addAndGet(3);
        s.addAndGet(2);
        s.addAndGet(4);
        double b = s.addAndGet(5);
        double c = s.addAndGet(1);
        double d = s.addAndGet(6);
        s.addAndGet(1);
        double e = s.addAndGet(7);
        double k = s.addAndGet(1);

        logger.debug("");
    }
}

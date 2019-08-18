package leetcode.q307_range_sum_query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        int[] input = {2, 1, 4, 5, 7, 3};
        NumArray test = new NumArray(input);

        int out = test.sumRange(2,5);
        test.update(1, 2);
        logger.debug("");
    }
}

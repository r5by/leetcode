package leetcode.q39_combination_sum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.List;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] input = {2, 9, 6, 7};

        List<List<Integer>> res =  s.combinationSum(input, 7);

        logger.debug(res.toString());
    }
}

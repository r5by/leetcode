package leetcode.q56_merge_intervals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
//        int[][] input =  {{1,3},{8,10},{2,6},{15,18}};
//        int[][] input =  {{1,4},{0,4}};
        int[][] input =  {{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}};
        int[][] out = s.merge(input);
        logger.debug("");
    }
}

package leetcode.q218_skyline_problem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
//        List<int[]> out = s.getSkyline(buildings);
        List<List<Integer>> res = s.getSkyline(buildings);
        logger.debug("");
    }
}

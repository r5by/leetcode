package leetcode.q51_52_N_queens;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

        List<List<String>> res = s.solveNQueens(6);
        int num = s.totalNQueens(6);
        logger.debug("");
    }
}

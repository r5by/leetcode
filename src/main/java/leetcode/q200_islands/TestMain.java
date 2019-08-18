package leetcode.q200_islands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
//        char[][] grid = {{'1','1', '0', '0','0'},
//                {'1','1','0','0','0'},
//                {'0','0','1','0','0'},
//                {'0','0','0','1','1'}};
        int islands = s.numIslands(grid);
        logger.debug("");
    }
}

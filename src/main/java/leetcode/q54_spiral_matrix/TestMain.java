package leetcode.q54_spiral_matrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

        int[][] m = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        List<Integer> res= s.spiralOrder(m);
        //for row: (n, m-1, 0, m-1)
//        List<Integer> pingpong = s.pingpong(4, 4, 0, 4);
//        List<Integer> pingpong = s.pingpong(6, 2, 0, 2);
//        List<Integer> pingpong = s.pingpong(3, 4, 0, 4);
        logger.debug("");
    }
}

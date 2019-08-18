package leetcode.q75_sort_colors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
//        int[] t1 = new int[]{2, 0, 2, 1, 1, 0};
//        int[] t2 = new int[]{1};
//        int[] t3 = new int[]{2, 1};

//        int[] t = new int[]{1,2,0};
        int[] t = new int[]{2,0,2,1,1,0};
        s.sortColors(t);
        logger.debug("");
    }
}

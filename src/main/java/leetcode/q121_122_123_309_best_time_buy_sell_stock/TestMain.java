package leetcode.q121_122_123_309_best_time_buy_sell_stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
//        Solution s = new Solution();
//        Solution2 s = new Solution2();
//        Solution4 s = new Solution4();
        Solution3 s = new Solution3();

//        int[] input = new int[]{7,1,5,3,6,4};
//        int[] input = new int[]{7,6,4,3,1};

        int[] input = new int[]{1, 2, 3, 4, 5};
//        int[] input = new int[]{1,2,3,0,2};

//        int[] input = new int[]{3, 2, 1};
//        int[] input = new int[]{1, 4, 2};
//        int[] input = new int[]{12, 14, 17, 10, 14, 13, 12, 15};
//        int max = s.maxProfit(input);
        int max = s.maxProfit(input, 2);
        logger.debug("");
    }
}

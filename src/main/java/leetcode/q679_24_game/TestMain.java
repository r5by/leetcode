package leetcode.q679_24_game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
        Solution_Feng sf = new Solution_Feng();

//        int[] cards = new int[]{4, 1, 8, 7};
//        int[] cards = new int[]{1, 2, 1, 2};
//        boolean b = s.judgePoint24(cards);

        int[] test = new int[]{ 2, 3, 8, 4};
//        int[] test = new int[]{1,2, 3, 4, 9, 2};
        List<String> res0 = s.extractValue(test, 44);

//        sf.extractValue(test, 44);
        logger.debug("");
    }
}

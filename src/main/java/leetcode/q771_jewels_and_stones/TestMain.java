package leetcode.q771_jewels_and_stones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
        String S = "aAAbbbb";
        String J = "aA";
        int jewelCount = s.numJewelsInStones(J, S);
        logger.debug("");
    }
}

package leetcode.q316_remove_duplicated_letters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
        String test1 = "bcabc";
        String test2 = "cbacdcbc";
        String res1 = s.removeDuplicateLetters(test1);
        String res2 = s.removeDuplicateLetters(test2);
        logger.debug("");
    }
}

package leetcode.q273_integer_to_en_words;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
        String out = s.numberToWords(1234567891);
        logger.debug("");
    }
}

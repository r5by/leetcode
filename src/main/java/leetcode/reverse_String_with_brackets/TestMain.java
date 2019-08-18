package leetcode.reverse_String_with_brackets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

//        String str = "a{ab{cd}}";
        String str = "a{ab{cd}x{pq}mn}";
        String res = s.reverseWithBrackets(str);
        logger.debug("");
    }
}

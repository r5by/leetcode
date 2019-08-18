package leetcode.q394_decode_string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
        String input = "3[a]2[bc]";
//        String input = "3[a2[c]]";
//        String input = "2[abc]3[cd]ef";
        String res = s.decodeString(input);
        logger.debug("");
    }
}

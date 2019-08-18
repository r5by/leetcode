package leetcode.q22_generate_parentheses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

        List<String> res = s.generateParenthesis(5);
        logger.debug("");
    }
}

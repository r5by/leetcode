package leetcode.q301_remove_invalid_parentheses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

//        String input = "(a)())()";
//        String input = ")(";
//        String input = "(x))(xx()(()x";
//        String input = "(x)((x)(x)))";

        String input = "";
//        String input = "))";
//        String input = "())((((((((((b))(";
//        String input = "(";
//        String input = "(((k()((";
        List<String> res = s.removeInvalidParentheses(input);
        logger.debug("");
    }
}

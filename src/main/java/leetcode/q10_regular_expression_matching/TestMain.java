package leetcode.q10_regular_expression_matching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ruby_
 * @create 2018-12-07-8:56 PM
 */

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(leetcode.q10_regular_expression_matching.TestMain.class);

    public static void main(String[] args) {
        Solution solution = new Solution();

        String a = "abdefg";
        String b = "a.*....g";
        System.out.println(solution.isMatch(a, b));
//        System.out.println(a.substring(1, 3));
    }
}

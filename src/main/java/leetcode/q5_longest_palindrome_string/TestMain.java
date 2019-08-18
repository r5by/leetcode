package leetcode.q5_longest_palindrome_string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Util;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
//        String input = "abba";
//        String input = "abb";
//        String input = "babad";

//        String input = "ac";
//        String input = "abababa";
//        String input = "abb";
//        String input = "cbbd";

        /* Big size test case */
        String p1 = Util.generatePalindrome(500000); //500w
        String p2 = Util.generatePalindrome(300000);
        String p3 = Util.generatePalindrome(200000);
        String input = p1 + p3 + p2;

        String str1 = s.longestPalindrome(input);
        String str2 = s.getLongestPalindrome(input);
        logger.debug("");
    }
}

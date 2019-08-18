package leetcode.q30_substring_with_concatenation_of_words;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author ruby_
 * @create 2018-12-07-8:56 PM
 */

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {

        Solution solution = new Solution();

        String s = "barfoothefoobarman";
        String[] words = {"foo","bar"};
        List<Integer> result = solution.findSubstring(s, words);
        System.out.println("done" + result);
    }
}

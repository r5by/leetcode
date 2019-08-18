package leetcode.q336_palindrome_pairs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
//        String[] words = new String[]{"", "abc", "aa"};
//        String[] words = new String[]{"abcd", "dcba", "lls", "s", "sssll"};

        String[] words = new String[]{"a", ""};
        List<List<Integer>> res = s.palindromePairs(words);
        logger.debug("");
    }
}

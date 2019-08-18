package leetcode.q243_244_245_shortest_word_dist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

        String[] words = new String[]{"practice", "makes", "perfect", "coding", "makes"};
        WordDistance wd = new WordDistance(words);
        int dist = wd.shortest( "coding", "practice");
        logger.debug("");
    }
}

package leetcode.q17_letter_combination_of_a_phone_number;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

        String d = "23";
        List<String> res =
                s.numToStringCombanitions(d);
        logger.debug("");
    }
}

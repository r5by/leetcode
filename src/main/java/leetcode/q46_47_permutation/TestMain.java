package leetcode.q46_47_permutation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

        //Q.46 test
        int[] input = {0,1,1};
//        s.permutate(input);
//        s.permutate(input, 2);

        //Q.47 test
//        int[] input2 = {1, 2, 1};
//        s.permutateWithDups(input2);

//        List<List<Integer>> output = s.getRes();
        List<List<Integer>> output = s.permuteUnique(input);
        logger.debug("");
    }
}

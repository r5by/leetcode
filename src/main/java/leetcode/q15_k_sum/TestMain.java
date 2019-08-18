package leetcode.q15_k_sum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
        Solution2 s2 = new Solution2();
        Solution3 s3 = new Solution3();

        int[] input = {-1, 0, 1, 1, 2, -1, -4};
//        int[] input = {-1, 0, 1, 1};
//        int[] input = {0, 1, 2, 1, 2};
//        int[] input = {0, 1, 1};
//        Set<Set<Integer>> res = s.kSum(s.toList(input), 3, 0);
//        List<List<Integer>> res = s.threeSum(input);

//        int[] input1 = {1,3,4,6,8,10,12,15,18,21,22,23,25,28,31,32,34,37,40};
//        Set<Set<Integer>> res1 = s.kSum(s.toList(input1), 6, 135);

//        s2.kSum()

//        int tres = s3.kSum(input, 1, 1);
        Set<Set<Integer>> res = s3.ksum(input, 1, 1);
        logger.debug("");
    }
}

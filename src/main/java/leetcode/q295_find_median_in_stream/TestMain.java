package leetcode.q295_find_median_in_stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
//        int[] input = new int[]{78,14,50,20};

        MedianFinder finder = new MedianFinder();
        double res;
        finder.addNum(78);
        res = finder.findMedian();
        finder.addNum(14);
        res = finder.findMedian();
        finder.addNum(50);
        res = finder.findMedian();
        finder.addNum(20);
        res = finder.findMedian();

//        for (int i : input) {
//            finder.addNum(i);
//        }

//        double res = finder.findMedian();

        logger.debug("");
    }
}

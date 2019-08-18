package leetcode.q53_maximum_subarray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Util;

import java.util.List;

/**
 * @author ruby_
 * @create 2018-12-01-8:45 AM
 */

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        int[] input = {1, -2,2 , -1, 5};

        List al = Util.toArrayList(input);

//        int max =  Utils.rightWeightOfIndex(al, 0);
        int max =  Util.leftWeightOfIndex(al, 2);

        logger.info("Max:"+ max);

    }
}

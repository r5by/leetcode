package leetcode.q138_copy_list_with_random_pointer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] vals = new int[]{1,2,3,4,5};
        int[] rand = new int[]{2,0,4,2,1};
        Solution.Node node = s.generateNodeList(vals, rand);
        Solution.Node copy = s.copyRandomList(node);
        logger.debug("");
    }
}

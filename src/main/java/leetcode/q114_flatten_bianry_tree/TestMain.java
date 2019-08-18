package leetcode.q114_flatten_bianry_tree;

import leetcode.common.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static utils.Util.stringToTreeNode;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

        String input = "[1,2,5,3,4,null,6]";
        TreeNode root = stringToTreeNode(input);
        s.flatten(root);
        logger.debug("");
    }
}

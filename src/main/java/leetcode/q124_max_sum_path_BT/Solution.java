package leetcode.q124_max_sum_path_BT;

import leetcode.common.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    private int maxVal = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        //dfs explore the tree to get the max sum  of a path
        dfs(root);
        return maxVal;
    }

    //return the max-sum path that pass the node directly, modify the global max sum path in each iteration
    private int dfs(TreeNode node) {
        int lContr = node.left == null ? 0 : Math.max(0, dfs(node.left)); //Contribution of each child, only when it's greater than 0 it counts
        int rContr = node.right == null ? 0 : Math.max(0, dfs(node.right));

        int curMax = node.val + lContr + rContr; //re-calc the global max value
        maxVal = Math.max(maxVal, curMax);

        return node.val + Math.max(lContr, rContr); //TODO: Note --> this is important,  we need to recursively return the direct upper path (only one branch can be selected)
    }
}

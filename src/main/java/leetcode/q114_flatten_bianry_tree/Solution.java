package leetcode.q114_flatten_bianry_tree;

import leetcode.common.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        // write your code here
        if(root == null) return;
        helper(root);
    }

    //flatten the tree rooted at node, then return the last right child in the flattened tree leaf
    private TreeNode helper(TreeNode node) {
        if(node.left == null && node.right == null) return node; //corner case: leaf node do nothing

        TreeNode l = node.left;
        TreeNode r = node.right;

        if(l != null) {
            node.right = l;
            node.left = null;

            TreeNode n = helper(l);

            if(r != null) {
                n.right = r;
                return helper(r);
            } else
                return n;
        } else {//if left is null, indicate rigth is not null
            return helper(r);
        }

    }
}

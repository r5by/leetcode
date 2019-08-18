package leetcode.q173_binary_search_tree_iterator;

import leetcode.common.TreeNode;

import java.util.Stack;

class BSTIterator {

    //TODO: 解题思路：使用一个stack 来将当前节点的左子孙入栈，每次弹出某个节点判断其是否有右孩子，是的话重复将其右孩子连带其左子孙入栈
    private Stack<TreeNode> nxtMinStack;
    public BSTIterator(TreeNode root) {
        nxtMinStack = new Stack<>();
        populateMinStack(root);
    }

    private void populateMinStack(TreeNode node) {
        nxtMinStack.push(node);

        TreeNode p = node;
        while (p.left != null) {
            nxtMinStack.push(p.left);
            p = p.left;
        }
    }

    //TODO: NOTE: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
    //TODO: You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
    /** @return the next smallest number */
    public int next() {
        int res;
        TreeNode node = nxtMinStack.pop();
        res = node.val;
        if(node.right != null)
            populateMinStack(node.right);

        return res;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !nxtMinStack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

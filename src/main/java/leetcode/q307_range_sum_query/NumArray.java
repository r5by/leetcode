package leetcode.q307_range_sum_query;


/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
class NumArray {
    TreeNode root;
    public NumArray(int[] nums) {
        if(nums == null || nums.length < 1)
            throw new IllegalArgumentException("input erro");

        root = generateTree(0, nums.length - 1, nums);
    }

    private TreeNode generateTree(int start, int end, final int[] nums) {
        TreeNode res = new TreeNode(start, end);

        if (start == end) {
            res.value = nums[start];
        } else {
            int mid = (start + end) / 2;
            TreeNode left = generateTree(start, mid, nums);
            TreeNode right = generateTree(mid + 1, end, nums);

            res.left = left;
            res.right = right;
            res.value = left.value + right.value;
        }

        return res;
    }

    public void update(int i, int val) {
        update(i, val, root);
    }

    private void update(final int i, final int val, TreeNode node) {
        if(node.start == node.end) {
            node.value = val;
            return;
        }

        int mid = (node.start + node.end) / 2;
        if (i <= mid) {
            update(i, val, node.left);
        } else {
            update(i, val, node.right);
        }
        node.value = node.left.value + node.right.value;
    }

    public int sumRange(int i, int j) {
        return sumRange(i, j, root);
    }

    private int sumRange(final int i, final int j, final TreeNode node) {
        if(node.start == i && node.end == j) return node.value;

        int mid = (node.start + node.end) / 2;
        if (mid >= j) {
            return sumRange(i, j, node.left);
        } else if (mid < i) {
            return sumRange(i, j, node.right);
        } else {
            return sumRange(i, mid, node.left) + sumRange(mid + 1, j, node.right);
        }
    }

    private class TreeNode {
        int start;
        int end;
        TreeNode left;
        TreeNode right;
        int value;

        TreeNode(int s, int e) {
            start = s;
            end = e;
        }
    }
}
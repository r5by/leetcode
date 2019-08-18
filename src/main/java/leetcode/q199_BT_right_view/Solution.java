package leetcode.q199_BT_right_view;

import leetcode.common.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    private List<Integer> res = new ArrayList<Integer>();

    public List<Integer> rightSideView(TreeNode root) {
        if(root != null) {
            Queue<TreeNode> q = new LinkedList<TreeNode>();
            q.offer(root);

            //method-1: recursive
            // bfs(q);

            //method-2: iterative
            while(!q.isEmpty()) {
                final int s = q.size();
                for(int i = 0; i < s; i++) {
                    TreeNode cur = q.poll();

                    if(i == 0) res.add(cur.val);

                    if(cur.right != null) q.offer(cur.right);
                    if(cur.left != null) q.offer(cur.left);
                }
            }
        }

        return res;
    }

//     private void bfs(Queue<TreeNode> q) {
//         res.add(q.peek().val);

//         Queue<TreeNode> tmp = new LinkedList<TreeNode>();
//         while(!q.isEmpty()) {
//             TreeNode view = q.poll();
//             if(view.right != null) tmp.offer(view.right);
//             if(view.left != null) tmp.offer(view.left);
//         }

//         if(!tmp.isEmpty()) bfs(tmp);
//     }
}

package leetcode.q297_serialize_deserialize_binary_tree;

import leetcode.common.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * TODO: NOTE:
 * 1) 熟悉StringBuilder的操作 append(), deleteCharAt(.length() - 1), toString()
 * 2) 熟悉树dfs 和bfs的遍历
 */
class Codec {
    private static Logger LOG = LoggerFactory.getLogger(Codec.class);

    //Efficient way of serialization and deser.
    // Encodes a tree to a single string.
    //              1
    //          2       3
    //                 4  5
    //DFS (前序遍历）: return "1,2,#,#,3,4,#,#,5,#,#" //可反序列化
    //DFS (中序遍历): return "#,2,#,1,#,4,#,3,#,5,#" NOTE: pre- 和 post- order都无法反序列化，原因是结果不唯一：举例：
    //      2               3
    //    1  3             2
    //                    1
    // pre-order 都是: "#, 1, #, 2, #, 3"
    //BFS (层次遍历): return "1,2,3,#,#,4,5,#,#,#,#" // 可反序列化
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();

        /* BFS 序列化 */
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            if(node != null) {
//                builder.append(node.val);
//                queue.offer(node.left);
//                queue.offer(node.right);
//            } else
//                builder.append("#");
//
//            builder.append(" ");
//        }
//        builder.deleteCharAt(builder.length() - 1);
//        return builder.toString();

        //Ser: DFS:
        //ser. the current node
        /* DFS 序列化 */
        if (root != null) {
            builder.append(root.val + " ");
            //recursively serialize the left & right child
            builder.append(serialize(root.left));
            builder.append(serialize(root.right));
        } else
            builder.append("# ");

        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.equals("# ")) return null;

        // DFS 反序列化
        String[] str = data.trim().split(" ");

        TreeNode root = new TreeNode(Integer.valueOf(str[0]));

        //DFS Deserialization
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root); //init stack

        TreeNode poped = null;
        for (int i = 1; i < str.length; i++) {
//            if(str[i].equals("#")) {
//                if(!stack.isEmpty()) poped = stack.pop();
//            } else {
//                TreeNode node = new TreeNode(Integer.valueOf(str[i]));
//                if(poped != null) {
//                    poped.right = node;
//                    poped = null;
//                } else if(!stack.isEmpty()) {
//                    TreeNode pre = stack.peek();
//                    pre.left = node;
//                }
//
//                stack.push(node);
//            }
            if (!str[i].equals("#")) {//if the inspected string is a number
                TreeNode cur = new TreeNode(Integer.valueOf(str[i]));

                //TODO: NOTE this is important to check whether if this char is following a #, if so, shoudn't put it the lift child!!
                if (poped != null) {
                    poped.right = cur;
                    poped = null; //don't forget to reset it, but can't give it the null value...
                } else if (!stack.isEmpty()) {
                    TreeNode pre = stack.peek();
                    pre.left = cur;
                }

                stack.push(cur);
            } else {//if it's a #, continue pop the stack until get a number
                if (!stack.isEmpty()) //NOTE: the end of serialized string can have multip "####", so need to check
                    poped = stack.pop();
            }
        }

        //BFS Deserialization
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        for (int i = 1; i < str.length; i+=2) {//每次移动两位
//            TreeNode cur = queue.poll();
//            if (!str[i].equals("#")) {//左孩子
//                cur.left = new TreeNode(Integer.parseInt(str[i]));
//                queue.offer(cur.left);
//            }
//            int j = i + 1; //当前节点右边1个为queue中取出元素的右孩子！
//            if (!str[j].equals("#")) {//右孩子
//                cur.right = new TreeNode(Integer.parseInt(str[j]));
//                queue.offer(cur.right);
//            }
//        }
        return root;
    }


    //TODO: Large waste of space if serialized in the following way (that is, line by line, fill null value with marks)
    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        HashMap<Integer, TreeNode> mem = new HashMap<>();
//        mem.put(0, root);
//        int h = serialize(root, 0, mem);
//        String res = "";
//        for (int i = 0; i < Math.pow(2, h) - 1; i++) {
//            TreeNode node = mem.getOrDefault(i, null);
//            String cat = node == null ? "#" : String.valueOf(node.val);
//            res = res + cat + ",";
//        }
//        return res;
//    }
//
//    //Return the max height of the node
//    private int serialize(TreeNode node, int i, HashMap<Integer, TreeNode> mem) {
//        if(node == null)
//            return (int) Math.floor(Math.log(i)/Math.log(2));
//
//        mem.put(i, node);
//        return Math.max(serialize(node.left, 2 * i + 1, mem), serialize(node.right, 2 * i + 2, mem));
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        if(data == null || data.length() < 1)
//            return null;
//
//        String[] vals = data.split(",");
//        if(vals.length < 1) return null;
//        HashMap<Integer, TreeNode> nodes = new HashMap<>();
//        TreeNode root = new TreeNode(Integer.valueOf(vals[0]));
//        nodes.put(0, root);
//        for (int i = 1; i < vals.length; i++) {
//            int p = (i - 1) / 2;
//
//            TreeNode node = !vals[i].equals("#") ? new TreeNode(Integer.valueOf(vals[i])) : null ;
//            if(node != null) {
//                nodes.put(i, node);
//                if(i % 2 == 1) nodes.get(p).left = node;
//                else nodes.get(p).right = node;
//            }
//        }
//
//        return root;
//    }

}

package leetcode.q173_binary_search_tree_iterator;

import apple.laf.JRSUIUtils;
import leetcode.common.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {

        String test = "7,3,15,null,null,9,20";
        String[] vals = test.split(",");
        TreeNode root = formBST(new HashMap<>(), vals);

        BSTIterator iterator = new BSTIterator(root);
        iterator.next();    // return 3
        iterator.next();    // return 7
        iterator.hasNext(); // return true
        iterator.next();    // return 9
        iterator.hasNext(); // return true
        iterator.next();    // return 15
        iterator.hasNext(); // return true
        iterator.next();    // return 20
        iterator.hasNext(); // return false

        logger.debug("");
    }

    public static TreeNode formBST(HashMap<Integer, TreeNode> tree, String[] values) {
        if(values == null || values.length == 0)
            throw new IllegalArgumentException("input error");

        TreeNode root = new TreeNode(Integer.valueOf(values[0]));
        tree.put(0, root);

        for (int i = 1; i < values.length; i++) {
            String str = values[i];
            if(str.equals("null")) continue;

            TreeNode node = new TreeNode(Integer.valueOf(str));
            tree.put(i, node);
            TreeNode parrent = tree.get((i - 1) / 2);
            if(i % 2 == 0)
                parrent.right = node;
            else
                parrent.left = node;
        }

        return root;
    }
}

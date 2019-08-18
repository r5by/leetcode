package leetcode.q297_serialize_deserialize_binary_tree;

import leetcode.common.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import static utils.Util.stringToTreeNode;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        while ((line = in.readLine()) != null) {
//            TreeNode root = stringToTreeNode(line);
//
//            String ret = new Codec().Codec(root);
//
//            String out = (ret);
//
//            System.out.print(out);
//            System.out.println("");
//        }
//    }

    public static void main(String[] args) {
        Codec s = new Codec();
//        String input = "10,5,20,#,#,3,7,#,#,#,#,9,18";
//        String input = "[1, null, 2, null, 3, null, 4, null, 5, null, 6, null, 7, null, 8, null]";
//        String input = "[1,2,3,null,null,4,5]";
//        String input = "[1,2,3,4,null,5,6,null,7,8,9]";
        String input = "[]";
        TreeNode root = stringToTreeNode(input);

        String res = s.serialize(root);
        TreeNode root2 = s.deserialize(res);
        logger.debug("");
    }
}

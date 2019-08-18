package leetcode.q21_q206_q141_merge_two_sorted_list_reverse_list_cycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

//        Solution.ListNode l1 = s.createListNode(new int[]{1, 2, 4});
//        Solution.ListNode l2 = s.createListNode(new int[]{1, 3, 4});
//        Solution.ListNode res = s.mergeTwoLists(l1, l2);

        Solution.ListNode l3 = s.createListNode(new int[]{1, 2, 3, 4, 5});
//        Solution.ListNode res2 = s.reverseListIter(l3);
//        Solution.ListNode res1 = s.reverseList(l3);
        Solution.ListNode circleNode = s.createListNodeWithCircle(new int[]{1, 2, 3, 4, 5}, 2);
        boolean t = s.hasCycle(circleNode);
        boolean f = s.hasCycle(l3);
        logger.debug("");
    }
}

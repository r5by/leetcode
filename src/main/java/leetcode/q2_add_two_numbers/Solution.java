package leetcode.q2_add_two_numbers;

import leetcode.common.ListNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解题思路：
 * 1）不断考察两个list的头部，注意carry的情况
 * 2) 当有list走完了以后，要处理将另一条链挂在结果链上； 这时候要特别注意挂载的链为 9->9->9... 这种情况
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode(-1);

        ListNode cur = dummy;
        boolean carry = false;
        while (l1 != null && l2 != null) {
            int sum = carry ? l1.val + l2.val + 1 : l1.val + l2.val;
            int res = sum % 10;
            cur.next = new ListNode(res);

            //set next
            carry = sum < 10 ? false : true;
            l1 = l1.next;
            l2 = l2.next;
            cur = cur.next;
        }

        if (l1 == null && l2 == null && carry) {
            cur.next = new ListNode(1);
        }

        if (l1 == null && l2 != null) {
            cur.next = l2;
            if (carry) carryOn(l2);
        }

        if (l2 == null && l1 != null) {
            cur.next = l1;
            if (carry) carryOn(l1);
        }

        return dummy.next;
    }

    /**
     * Repeatly check each node on its value + 1
     * @param node
     */
    private void carryOn(ListNode node) {
        if (node.val != 9) {//如果不是9， 直接加1
            node.val++;
            return;
        }

        //是9的话，首先该位设为0；
        node.val = 0;
        if(node.next == null) { //如果下面没有节点了，加个为1的新节点就返回
            node.next = new ListNode(1);
            return;
        }

        carryOn(node.next); //不然重复carry on操作
    }
}

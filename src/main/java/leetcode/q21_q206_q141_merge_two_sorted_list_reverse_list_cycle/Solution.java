package leetcode.q21_q206_q141_merge_two_sorted_list_reverse_list_cycle;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    /*Requirement: Given a linked list, determine if it has a cycle in it.
Follow up:
Can you solve it without using extra space?*/
    public boolean hasCycle(ListNode head) {
        if(head == null) throw new IllegalArgumentException("e");
        if(head.next == null) return false;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }

        return false;
    }


    /* Requirement: A linked list can be reversed either iteratively or recursively. Could you implement both?*/
    /*1) recursive */
    public ListNode reverseList(ListNode head) {
        //end condition
        return reverseList(head, null);
    }

    private ListNode reverseList(ListNode cur, ListNode pre) {
        ListNode next = cur.next;
        cur.next = pre;
        if(next == null)
            return cur;
        else
            return reverseList(next, cur);
    }

    /*2) Iterative */
    public ListNode reverseListIter(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            //move pointers
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode p = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        if(l1 == null)
            p.next = l2;
        if(l2 == null)
            p.next = l1;

        return dummyHead.next;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    //helper
    public ListNode createListNode(int[] arr) {
        ListNode head = new ListNode(-1);
        ListNode p = head;
        for (int i : arr) {
            ListNode node = new ListNode(i);
            p.next = node;
            p = p.next;
        }

        return head.next;
    }

    public ListNode createListNodeWithCircle(int[] arr, int i) {
        List<ListNode> nodes = new ArrayList<>();
        for (int k = 0; k < arr.length; k++) {
            ListNode p = new ListNode(arr[i]);
            nodes.add(p);

            if(k > 0) nodes.get(k - 1).next = p;
        }

        nodes.get(nodes.size() - 1).next = nodes.get(i);

        return nodes.get(0);
    }

}
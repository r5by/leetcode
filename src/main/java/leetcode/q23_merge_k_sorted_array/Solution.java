//package leetcode.q23_merge_k_sorted_array;
//
//import edu.utarlington.learning.example_java_priorityqueue.ListNode;
//import edu.utarlington.learning.example_java_priorityqueue.NodeComparator;
//
//import java.util.PriorityQueue;
//
///**
// * @author ruby_
// * @create 2018-12-07-8:57 PM
// */
//
//class Solution {
//
//    public ListNode mergeKLists(ListNode[] lists) {
//        if(lists.length == 0)
//            return null;
//
//        //A pq (min-heap) to save (k+1) elements that need to be compared
//        PriorityQueue<ListNode> q = new PriorityQueue(lists.length + 1, new NodeComparator());
//
//        for(int i= 0; i < lists.length ; i++) {
//            ListNode head = lists[i];
//            if(head != null)
//                q.add(lists[i]);
//        }
//
//        if(q.size()==0)
//            return null;
//
//        ListNode result = q.poll();
//        if(result.next != null)
//            q.add(result.next);
//        ListNode current = result;
//
//        while(!q.isEmpty()) {
//            ListNode pop = q.poll();
//            if(pop.next != null)
//                q.add(pop.next);
//
//            current.next = pop;
//            current = current.next;
//        }
//
//        return result;
//    }
//
//}

package leetcode.q4_meidan_of_k_sorted_array;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.PriorityQueue;

class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    public double findMedian(int[][] sortedArrs) {
        if(sortedArrs == null || sortedArrs.length == 0 || sortedArrs[0].length == 0)
            throw new IllegalArgumentException("input error");

        int total = 0;

        //TODO: Note two methods to create max heap from priorityqueue
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        PriorityQueue<Candidates> minHeap = new PriorityQueue<Candidates>(sortedArrs.length, (x, y) -> x.val() - y.val());
        //init minHeap
        for (int[] arr : sortedArrs) {
            //check the corner case
            if(arr.length == 0) continue;

            total += arr.length; //calc the total elements

            Candidates cand = new Candidates(0, arr);
            minHeap.offer(cand);
        }

        final int k = (total + 1) / 2; // find the k-th smallest element, if total is odd, return this element, else coninously find 1 more and return the avg of both
        int cnt = 0; //count till k-th smallest element
        int lRes = 0; //the value of the k-th smallest element
        while (cnt < k && !minHeap.isEmpty()) {
           Candidates cand = minHeap.poll();

           if(cnt == k-1) lRes = cand.val();
           cnt++;

           if(cand.hasNext()) {//if cand has more elements left, re-offer it; o.w. remove it
               cand.next();
               minHeap.offer(cand);
           }
        }

        //Return the values based on the odd/even of total, note here the minHeap has already the (k+1)-th smallest element on top.
        if(total % 2 == 1) return lRes;
        else {
            int rRes = minHeap.peek().val();
            return (lRes + rRes) / 2.0;
        }
    }

    //A pointer points the value to its originate list
    private class Candidates {
        int _p;
        int[] _arr;

        Candidates(int p, int[] arr) {
            _p = p;
            _arr = arr;
        }

        int val() {
            return _arr[_p];
        }

        boolean hasNext() {
            return _p < _arr.length - 1;
        }

        void next() {
            if(hasNext()) ++_p;
        }
    }

}

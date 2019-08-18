package leetcode.rolling_average;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Question: add a number and return the top 25% of average
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());//save the max elements
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>(); //save the last max elements, the tricky part
    private int round = 0;
    private int sum = 0;

    public double addAndGet(int num) {
        round += 1;
        int numbersToPop = round / 4;

        maxHeap.offer(num);
        sum += num;

        if (numbersToPop == 0) return sum / round;

        if (minHeap.isEmpty()) {
            int poped = maxHeap.poll();
            minHeap.add(poped);
            sum -= poped;
            return sum / (round - numbersToPop);
        } else {
            if (round % 4 == 0) {//more poped needed
                int swapOut = maxHeap.poll();
                minHeap.offer(swapOut);
                sum -= swapOut;
                return (double) sum / (round - numbersToPop);
            } else {
                if (num > minHeap.peek()) {
                    int swapIn = minHeap.poll();
                    int swapOut = maxHeap.poll();
                    minHeap.offer(swapOut);
                    sum = sum - swapOut + swapIn;
                    return (double) sum / (round - numbersToPop);
                } else {
                    return (double) sum / (round - numbersToPop);
                }
            }
        }
    }
}

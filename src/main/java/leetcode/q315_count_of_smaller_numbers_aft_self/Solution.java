package leetcode.q315_count_of_smaller_numbers_aft_self;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    public List<Integer> countSmaller(int[] nums) {

        if(nums == null || nums.length == 0)
            return new ArrayList<>();

        Integer[] res = new Integer[nums.length];
        //Method 2 => Use another list to add element gradually, in order!
        List<Integer> added = new ArrayList<>();
        added.add(nums[nums.length - 1]); // add the first element
        res[nums.length - 1] = 0;

        for (int i = nums.length - 2; i > -1; i--) {
            int num = nums[i];
            int insertPos = binaryInsert(num, added);
            res[i] = insertPos;
        }

//      //TODO: Method-1 => The following solution using maxHeap has bad time complexity!! Aborted!
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

//
//        //add the first ele
////        res.add(nums.length - 1, 0);
//        maxHeap.offer(nums[nums.length - 1]);
//
//        //reverse iterate nums to populate result
//        for (int i = nums.length - 2; i > -1; i--) {
//            int num = nums[i];
//            if(num > maxHeap.peek())
//                res.set(i, maxHeap.size());
//            else {
//                PriorityQueue<Integer> copy = new PriorityQueue<>(maxHeap);
//                while(!copy.isEmpty() && num <= copy.peek()) copy.poll();
//                res.set(i, copy.size());
//            }
//
//            maxHeap.offer(num);
//        }
//
////        Collections.reverse(res);
        return Arrays.asList(res);
    }

    /**
     * User binary search to insert the num into the right position of the ordered list; return that insertion index
     * TODO: Note that if the inserted num is equal to the some element in the list, must insert it in front of the list!
     * @param num
     * @param list
     * @return
     */
    private int binaryInsert(int num, List<Integer> list) {
        int len = list.size();
        int start = 0, end = len - 1;

        if(len == 1) {
            if(num <= list.get(0)) {
                list.add(0, num);
                return 0;
            } else {
                list.add(1, num);
                return 1;
            }
        }

        while(start < end) {
            int mid = (start + end) / 2;
            if(num <= list.get(mid)) end = mid;
            else start = mid + 1;
        }

        if(num <= list.get(end)) {
            list.add(end, num);
            return end;
        } else {
            list.add(end + 1, num);
            return end + 1;
        }

        //NOTE: The following style is less efficient, but easier to understand.
//        int pos = -1, len = list.size();
//        int start = 0, end = len - 1;
//        while (start < end + 1) {
//
//            int mid = (start + end) / 2;
//            if(mid == 0 && num < list.get(0)) {
//                pos = 0; //corner case 1: add to the very front
//                break;
//            }
//
//            if((len == 1 && num > list.get(0)) || (mid == len - 2 && num >= list.get(len - 1))) {
//                pos = len; //corner case 2: add to the very end
//                break;
//            }
//
//            if(list.get(mid) <= num && list.get(mid + 1) > num) {
//                pos = mid + 1; //normal case return: find the insertion position in middle
//                break;
//            }
//
//            if(list.get(mid) <= num) start = mid;
//            else end = mid;
//        }
//
//        list.add(pos, num); //TODO: Note here insert the element use add(index, val) API!!
//        return pos;
    }
}

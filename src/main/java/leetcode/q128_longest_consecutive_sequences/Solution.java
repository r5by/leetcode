package leetcode.q128_longest_consecutive_sequences;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums == null)
            throw new IllegalArgumentException("e");

        Set<Integer> set = new HashSet<Integer>();
        for(int i : nums) set.add(i); //O(n) save to hash set

        int res = 0;
        for(int num: nums) {
            if(set.remove(num)) {
                int pre = num -1; int next = num+1;
                while(set.remove(pre)) pre--;
                while(set.remove(next)) next++;
                res = Math.max(res, next - pre - 1);
            }
        }

        return res;
    }
}

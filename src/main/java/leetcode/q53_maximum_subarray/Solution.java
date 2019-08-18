package leetcode.q53_maximum_subarray;

import java.util.HashMap;

/**
 * @author ruby_
 * @create 2018-12-07-8:57 PM
 */

public class Solution {

    // Best solution : time : O(n) space : O(1);
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(nums[i], sum + nums[i]);
            res = Math.max(res, sum);
        }
        return res;
    }

    //Faster version O(n) space : O(n);
//    public int maxSubArray(int[] nums) {
//        int[] dp = new int[nums.length];
//        dp[0] = nums[0];
//        int res = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            dp[i] = nums[i] + (dp[i - 1] < 0 ? 0 : dp[i - 1]);
//            res = Math.max(res, dp[i]);
//        }
//        return res;
//    }

    //Slow version of mine: 6ms
//    HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
//
//    public int maxSubArray(int[] nums) {
//        if(nums == null || nums.length == 0)
//            return 0;
//
//        int res = nums[0];
//        cache.put(0, res);
//
//        for(int i = 1; i < nums.length; i++) {
//            int tmp = maxInclude(nums, i);
//            res = Math.max(res, tmp);
//        }
//        return res;
//    }
//
//    private int maxInclude(final int[] nums, final int i) {
//        int pre, res;
//        if(!cache.isEmpty() && cache.containsKey(i-1)) {
//            pre = cache.get(i-1);
//        } else {
//            pre = maxInclude(nums, i - 1);
//        }
//
//        res = pre > 0 ? pre + nums[i] : nums[i];
//
//        if(!cache.containsKey(i))
//            cache.put(i, res);
//
//        return res;
//    }

}

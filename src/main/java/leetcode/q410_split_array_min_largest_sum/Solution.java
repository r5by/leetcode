package leetcode.q410_split_array_min_largest_sum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 解題思路：
 * 1）使用dp[i][m] 紀錄從0到i（包括i）的i＋1個元素，分成m個相連的組，所包含的最小的每組最大值情況。這樣原問題就化成了這樣一個自問題：
 * 當考察新加入的元素i時，觀察i之前全部數的已經分成了m－1組的情況，此時i為最後一組的最後一個元素，假如i比之前總共i個元素（因為i表示第i＋1個位置）的最小max情況大，說明無論如何i這一組一定是新狀態
 * 的最小max值了，因為所有其他的可能都要比i還大；相反的話，就考察i加上之前的元素組成新的組，再加上除去這兩個元素剩下的元素所成自問題的m－1組最優解的解，看是否比當前還要小；以此類推直到不能再優化
 * 2）則轉移方程為：
 * for i ~[1, n-1], j ~ [1,m]:
 * dp[i][j] = for k ~[0,i-1] min{
 * do: break if sum(i-k,i) = A[i] + A[i-1] +...+ A[i-k] is negative (integer overflow)
 * <p>
 * max {dp[i-1-k][j-1], sum(i-k,i)}
 * }
 * do: break if dp[i-1-k][j-1] <= sum(i-k,i) //此時沒有必要考察把前面的元素放進最後一個組了
 * <p>
 * 3)關於類型溢出： 例如
 * int a = 1 + 2147483647; //MIN_VALUE: -2147483648
 * int b = 2147483647 + 2147483646; //-3 = MIN_VALUE + 2147483646
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    boolean inited = false;
    /*My DP solution */
    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length < 1) return 0;

        int n = nums.length;
        if (m > n) return splitArray(nums, n);

        int[][] dp = new int[n][m + 1];//dp[i][j] is the minimal max m-split sum for subarray end at i (include i, i ~ [0, n-1])

        int[] cache = new int[n];

        //init dp table: first column and all rest as MAX_VALUE
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][0] = sum(nums, 0, i, cache);
            dp[i][1] = sum(nums, 0, i, cache);
        }
        //init dp table: fill all that has more groups than numbers with the max number value
        for (int j = 1; j <= m; j++) {
            for (int i = 0; i < j; i++)
                dp[i][j] = max(nums, i);
        }

        inited = true;

        //fill dp table by columns and rows
        for (int j = 2; j <= m; j++) {
            for (int i = 1; i < n; i++) {
                //state trans for j >= 1; i >= 1 (at least two element and more than 1 split)
                for (int k = 0; k <= i - 1; k++) {
                    int curSum = sum(nums, i - k, i, cache);
                    if (curSum < 0)
                        break; //todo: NOTE this is important, if a > 0 and b > 0 then sum(a, b) return <0 means integer overflow, should break

                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1 - k][j - 1], curSum));
                    if (curSum >= dp[i - 1 - k][j - 1]) break;
                }
            }//end rows
        }//end columns

        return dp[n - 1][m];
    }

    /**
     * Returns the sum of integer from start to end; with or without cached (cache sum from 0 ~ i)
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int sum(final int[] nums, final int start, final int end, int[] cache) {
        if (inited) return cache[end] - cache[start - 1];

        int s = 0;
        for (int i = start; i <= end; i++) s += nums[i];

        cache[end] = s;

        return s;
    }

    /**
     * Returns the max in array from 0 to end
     *
     * @param nums
     * @param end
     * @return
     */
    private int max(int[] nums, int end) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= end; i++) max = Math.max(max, nums[i]);
        return max;
    }

    /* Binary Search solution reference: (CAN'T deal with integer overflow) */
//    public int splitArray(int[] nums, int m) {
//        int max = 0; long sum = 0;
//        for (int num : nums) {
//            max = Math.max(num, max);
//            sum += num;
//        }
//        if (m == 1) return (int)sum;
//        //binary search
//        long l = max; long r = sum;
//        while (l <= r) {
//            long mid = (l + r)/ 2;
//            if (valid(mid, nums, m)) {
//                r = mid - 1;
//            } else {
//                l = mid + 1;
//            }
//        }
//        return (int)l;
//    }
//    public boolean valid(long target, int[] nums, int m) {
//        int count = 1;
//        long total = 0;
//        for(int num : nums) {
//            total += num;
//            if (total > target) {
//                total = num;
//                count++;
//                if (count > m) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    /*O(n) 近似解法。。 類似於晃動一堆水桶，直到水桶裡面的水差不多高；缺點是不能保證生成最優解 */
//    public int splitArray(int[] nums, int m) {
//        if (nums == null || nums.length < 1) return 0;
//
//        int n = nums.length;
//        if (m > n) return splitArray(nums, n);
//        if (m == 1) return sum(nums);
//
//        int[] p = new int[m]; //p[i] points to the ith subarray's end position (not included), i ~ [0, n-1];
//        int[] sum = new int[m]; // sum[i] saves ith subarray's sum
//        int max = Integer.MAX_VALUE; //The current minimal max sum value
//
//        //split array to (close) equal size sub-arrays
//        splitEqually(nums, m, p);
//
//        //initialize the split
//        for (int i = 0, temp = 0, bucket = 0; i < n; i++) {
//            if (nums[i] == Integer.MAX_VALUE) {
//                return Integer.MAX_VALUE; //no possible smaller max sum can be assigned
//            }
//
//            if (bucket >= m)
//                return Integer.MIN_VALUE; //can't possibly devide the input array to m subarrays without avoiding integer overflow
//
//            if (overflow(temp, nums[i])) {
//                sum[bucket] = temp;
//                p[bucket++] = i;
//                temp = nums[i];
//            } else {
//                temp += nums[i];
//                if (i == p[bucket] - 1) {
//                    sum[bucket++] = temp;
//                    temp = 0; //reset
//                }
//            }
//        }
//
//        // adjust the volumn from left to right, that is from 1 to (m-1) subsection, until no more adjustment is needed between each section-i and section-(i+1)
//        int curMax = Integer.MIN_VALUE;
//        while (true) {
//            for (int i = 1; i < m; i++) {
//                int left = sum[i - 1];
//                int right = sum[i];
//
//                if (left == right) continue;
//                if (left < right && !overflow(left, nums[p[i - 1]]) && left + nums[p[i - 1]] < right) {
//                    sum[i - 1] = left + nums[p[i - 1]];
//                    sum[i] = right - nums[p[i - 1]];
//                    p[i - 1] += 1;
//                } else if (left > right && !overflow(right, nums[p[i - 1] - 1]) && right + nums[p[i - 1] - 1] < left) {
//                    sum[i - 1] = left - nums[p[i - 1] - 1];
//                    sum[i] = right + nums[p[i - 1] - 1];
//                    p[i - 1] -= 1;
//                }
//
//                //update the current max in this iteration
//                curMax = Math.max(Math.max(curMax, sum[i - 1]), sum[i]);
//            }
//
//            if (curMax == max) break; //end condition
//            else max = curMax;
//        }
//
//        return max;
//    }
//
//    private boolean overflow(int a, int b) {
//        return (a > 0 && b > 0 && a + b < 0);
//    }

    private int sum(int[] nums) {
        int res = 0;
        for (int s : nums) {
            res += s;
        }
        return res;
    }

//    private void splitEqually(final int[] nums, final int m, int[] p) {
//        int base = nums.length / m;
//        int res = nums.length % m;
//        int k = 0, cur = 0;
//        for (int i = 0; i < m; i++) {
//            cur += base;
//            if (k++ < res) {
//                cur++;
//            }
//            p[i] = cur;
//        }
//    }

}

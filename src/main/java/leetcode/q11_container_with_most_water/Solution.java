package leetcode.q11_container_with_most_water;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 解题思路：
 *
 *  首先考虑对于任意两个区间 [l1, r1] [l2, r2] 取舍的关键就是对比两个矩形面积的大小：
 *      Min(height[l1], height[r1]) * (r1 - l1) vs. Min(height[r2], height[l2]) * (r2 - l2)
 *
 *   那么假如我们现在对比以 0 位置为起点的所有区间 [0, r], 对于 r ~ [1, len - 1] 我们发现所有处在最后一个height[r] 比 height[0] 高的中间的一切矩形面积，都可以被跳过。
 *   同理，如果 从后往前看，也是一样。这暗示我们可以使用从两端逼近的方法来逐步得到最优解。
 *
 *   对于一段区间如下：
 *          \
 *          \   \   \
 *      \   \   \   \
 *      \   \   \   \
 *      0   1   2   3
 *      l           r
 *   此时我们看到height[0] < height[3], 那么到底从 0==>1 (l++) 还是 3==>2 (r--): 考虑如果走r--, 那么因为0是此时的最小值，新的面积必然比原来的小; 同理可以分析另一种情况
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) return 0;

        int len = height.length;

        int l = 0, r = len - 1;
        int max = 0;

        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            if(height[l] > height[r]) r--;
            else l++;
        }

        return max;
    }
}

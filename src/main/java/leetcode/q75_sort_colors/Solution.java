package leetcode.q75_sort_colors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Util;

/**
 * 解题思路：
 * 考虑顺序遍历过程中，如下情形下包含四个数据段的被操作输入数组（由三个指针分隔）:
 * [0,0 ... 0, 1, 1 ... 1, x, x, .... x, 2, 2, ...2]
 *             i           p             j
 *  i 指针：指向已划分好的 1..1 数据段头部；那么从 [0, i) 区间为 0..0 数据段；i的初始值应该为0；
 *  p 指针：指向当前的（未探索的）位置， 理论上从 [0, len-1]范围，但是实际上当碰到j指针，就可以结束了；p的初始值为0；
 *  j 指针：指向尾部已划分好的 2..2 数据段头部； 那么从 [j, len) 区间即为 2..2 数据段，从 [p, j-1] 为未探索的 x..x 数据段； j的初始值应为len；
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    public void sortColors(int[] nums) {
        if(nums == null || nums.length < 2)
            return;

        int len = nums.length;
        int i = 0, j = len, p = 0;

        while (p < j ) {//注意边界条件，j表示已经sort好了的2的启示位置，所以p一旦碰到j就完成了
            switch (nums[p]) {
                case 0:
                    Util.swap(nums, i++, p++);
                    break;
                case 1:
                    ++p;
                    break;
                case 2:
//                    Util.swap(nums, --j, p++);//todo: 这里一定要注意，为什么不把当前p指针前移一位？因为换到p位置的元素是x（未探索值）！！一定要跟0的情况区分开，0的情况下换过来的已经是1了，所以可以直接前移p指针！！
                    Util.swap(nums, --j, p);
                    break;
                    default:
                        throw new IllegalArgumentException("Check input array format!");
            }
        }
    }

    //参考：
    //        int i = 0, j = 0, k = len - 1;
//        while (j <= k) {
//            if (nums[j] == 0) {
//                Util.swap(nums, i++, j++);
//            } else if (nums[j] == 1) {
//                j++;
//            } else {
//                Util.swap(nums, j, k--);
//            }
//        }
}

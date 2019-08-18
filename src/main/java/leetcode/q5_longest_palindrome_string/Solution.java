package leetcode.q5_longest_palindrome_string;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 解题思路：
 * 使用中心扩散没有重用信息，很浪费，所以这样考虑：
 * 如图：
 * +++{xxx[-o-]yyyMyyy[-k-]xxx}+++
 * 假如我们当前指针在M, {M}内表示以M为中心的最长回文范围，用中心扩散法得出，}所在的位置即为M所对应的最远半径位置。则考虑 M+1 ~ } 范围内的下一步指针 k
 * <p>
 * 1) 使用公式 o = 2 * M - k 找到 k关于M的镜像点o，因为之前已经算出了 o 的最远半径距离为 ], 那么：
 * 1.1） 假如 k ＋ dist < } ; 其中 dist ＝ ］ － o ，这说明 k 无论如何也不可能超过 当前 {M} 的范围，故可以直接跳过；
 * 1.2） 否则的话，可以从 } 以及 2 * k - } 的位置进行扩散，得到以 k 为中心的最大回文范围，并更新当前最远的半径位置 （如果碰到了尾巴，就可以结束循环）
 * 2）需要注意的是： 前期要遍历一次，将字符串包入"#"， 这样就可以通过中心扩散统一处理 "bb" 和 "bab" 的方法了，同时最长的区间去掉"＃"之后一定也是原字符串最大的回文字串
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);
    /*中心扩散法: 适合短字符串 （<1000 chars) */
//    private String res = "";
//    public String longestPalindrome(String s) {
//        Stopwatch watch = new Stopwatch();
//        watch.start();
//
//        if (s == null || s.length() == 0) return s;
//        for (int i = 0; i < s.length(); i++) {
//            helper(s, i, i);
//            helper(s, i, i + 1);
//        }
//        watch.stop();
//        LOG.debug("中心扩散时间开销：" + watch.elapsed(TimeUnit.MILLISECONDS));
//        return res;
//    }
//
//    public void helper(String s, int left, int right) {
//        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
//            left--;
//            right++;
//        }
//        String cur = s.substring(left + 1, right);
//        if (cur.length() > res.length()) {
//            res = cur;
//        }
//    }


    /* Manacher 解法, 下面实现对于小数据集慢; 但对于大数据集效率高 O(n) */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;

        Stopwatch watch = new Stopwatch();
        watch.start();

        /* （1） 前处理： 将原字符串包裹进＃中， 例如： "abc" -> "#a#b#c#"这样 */
        StringBuilder str = new StringBuilder();
        str.append("#");
        for (int i = 0; i < s.length(); i++) {
//            br.append("#" + s.charAt(i)); //todo: 注意千万不要这样写，效率会比下面单独增加低很多！！
            str.append(s.charAt(i));
            str.append("#");
        }

        watch.stop();
        LOG.debug("前处理时间：" + watch.elapsed(TimeUnit.MILLISECONDS));
//        String str = br.toString(); //todo：注意stringbuilder可以直接当成string来使用，只有当作为返回值时候才调用toString()
        watch.reset();
        watch.start();

        /* (2) 实现manacher算法 */
//        Map<Integer, Integer> maxRMap = new HashMap<>(); //todo: 注意直接用int[]数组代替这个map，节约资源！！它的对称中心位置 => 最远半径位置

        //用来记录结果
        int maxRadius = 0;
        int l =  -1, r = -1;

        //实际过程
        int[] rad = new int[str.length()];
        int right = -1, pos = -1;

        for (int i = 0; i < str.length() && right < str.length() ; i++) { //right < str.length() 可以提前结束
            if (i >= right) {//当前没有前可用信息
                right = nexRight(i, str);
            } else {
                int mirrorI = 2 * pos - i;

                if(i + rad[mirrorI] < pos + rad[pos]) {//不可能比当前更远的情况（对称区间被夹在了已知最大区间内）
                    rad[i] = rad[mirrorI];
                    continue;
                }

                //对有可能超过当前最大区间的情况，可以重复利用 right -> i 之间的部分，因此只需要扩大搜索 right 之后的部分
                right = right + span(2 * i - right- 1, right + 1, str)  - 1;
            }

            //update max pos and most right position
            int currentRadius =  right - i + 1;
            rad[i] = currentRadius;
            pos = i;

            if(currentRadius > maxRadius) {
                maxRadius = currentRadius;
                l = i - currentRadius + 1;
                r = i + currentRadius;
            }
        }

        watch.stop();
        LOG.debug("中心扩散时间开销：" + watch.elapsed(TimeUnit.MILLISECONDS));

        return str.substring(l, r).replaceAll("#", "");
    }

    /**
     * Check the polindrome of current postion and return the right-most reached position
     *
     * @param pos
     * @param str
     * @return
     */
    private int nexRight(final int pos, final StringBuilder str) {
        int r = span(pos - 1, pos + 1, str);
        return pos + r - 1;
    }

    /**
     * span from [left, right] out
     *
     * @param left
     * @param right
     * @param str
     * @return
     */
    private int span(int left, int right, final StringBuilder str) {
        int r = 1;
        while (left >= 0 && right < str.length() && str.charAt(left--) == str.charAt(right++)) {
            r++;
        }

        return r;
    }

    /* reference: https://juejin.im/entry/58c7936944d90400699c2db4 */
    public String getLongestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;

        Stopwatch watch = new Stopwatch();
        watch.start();

        // 1.构造新的字符串
        // 为了避免奇数回文和偶数回文的不同处理问题，在原字符串中插入'#'，将所有回文变成奇数回文
        StringBuilder newStr = new StringBuilder();
        newStr.append('#');
        for (int i = 0; i < s.length(); i++) {
            newStr.append(s.charAt(i));
            newStr.append('#');
        }

        watch.stop();
        LOG.debug("前处理时间：" + watch.elapsed(TimeUnit.MILLISECONDS));
        watch.reset();
        watch.start();

        // rad[i]表示以i为中心的回文的最大半径，i至少为1，即该字符本身
        int[] rad = new int[newStr.length()];
        // right表示已知的回文中，最右的边界的坐标
        int right = -1;
        // pos表示已知的回文中，拥有最右边界的回文的中点坐标
        int pos = -1;
        // 2.计算所有的rad
        // 这个算法是O(n)的，因为right只会随着里层while的迭代而增长，不会减少。
        for (int i = 0; i < newStr.length(); i++) {
            // 2.1.确定一个最小的半径
            int r = 1;
            if (i <= right) {
                r = Math.min(pos + rad[pos] - i, rad[2 * pos - i]);
            }
            // 2.2.尝试更大的半径
            while (i - r >= 0 && i + r < newStr.length() && newStr.charAt(i - r) == newStr.charAt(i + r)) {
                r++;
            }
            // 2.3.更新边界和回文中心坐标
            if (i + r - 1 > right) {
                right = i + r - 1;
                pos = i;
            }
            rad[i] = r;
        }

        // 3.扫描一遍rad数组，找出最大的半径
        int l = -1, r = -1;
        int maxLength = 0;
        for (int i = 0; i < rad.length; i++) {
            if (rad[i] > maxLength) {
                maxLength = rad[i];

                l = i - rad[i] + 1;
                r = i + rad[i];
            }
        }

        watch.stop();
        LOG.debug("中心扩散时间开销：" + watch.elapsed(TimeUnit.MILLISECONDS));

        return newStr.substring(l, r).replaceAll("#", "");
    }
}

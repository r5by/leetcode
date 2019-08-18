package leetcode.q3_longest_substring_without_repeat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 解题思路：
 *  跳转指针 或 dp; 跳转指针更快
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    /* 跳指针法： 每当发现重复的数字，就跳到上次该数字出现的位置下一个位置作为计算当前子字串的长度方法 (因为此时该重复字符将包含之前的最长情况）
     *   比如: abcdeca...
     *      1)第二个c出现时将start指向第一个c的下一个位置，即3，那么当前包含第二个c的子串将包含最大可能的de;
     *      2) 特别需要注意的是，每次判断时只判断start后面的是否出现重复，这是因为start之前的情况不可用(否则跳转会产生错误的结果，比如上例中第二个c后面出现的a，虽然重复但出现在此时start位置之前，故不考虑
     *      3) 结尾以后别忘了计算最后一个start到末尾之间的最后一种情况（没有在for loop中考虑）
     * */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() < 1) return 0;
        Map<Character, Integer> cache = new HashMap<>();

        int len = s.length();
        int max = Integer.MIN_VALUE;
        int start = 0; //为了直接使用 i - start 刚开始也成立

       for(int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if (cache.isEmpty() || !cache.containsKey(c) || cache.get(c) < start) {//todo: 注意这里面的最后一个判断，表示start前的重复情况都不考虑!!
                cache.put(c, i);
                continue;
            }

            max = Math.max(max, i - start);
            start = cache.get(c) + 1;
            cache.put(c, i);
        }

        max = Math.max(max, len - start);

        return max;
    }

    /* DP 解法， 慢。。。*/
//    public int lengthOfLongestSubstring(String s) {
//        if(s == null || s.length()  < 1 ) return 0;
//
//        int len = s.length();
//        int[] dp = new int[len]; //dp[i]: up to char i, longest substring with unique char
//        dp[0] = 1;
//
//        for (int i = 1; i < len; i++) {
//            dp[i] = Math.max(dp[i - 1], maxSubEndAt(i, s));
//        }
//
//        return dp[len - 1];
//    }
//
//    private int maxSubEndAt(final int i, final String s) {
//        Set<Character> mem = new HashSet<>();
//        int j = i;
//        while( j >= 0 && (mem.isEmpty() || !mem.contains(s.charAt(j)))) mem.add(s.charAt(j--));
//
//        return  mem.size();
//    }
}

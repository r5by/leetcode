package leetcode.q243_244_245_shortest_word_dist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解题思路：
 *      单次询问的话，只需要o(n)时间和o(1)space就可以，每次更新上次某个单次出现位置的时候重新计算两个词的距离
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);


    public int shortestDistance(String[] words, String word1, String word2) {
        if(words == null || words.length < 1) return 0;
        if(word1.equals(word2)) return 0; //what if words doesn't contain word1 or word2?... precheck that then...

        int shortestDist = Integer.MAX_VALUE;
        int l1 = -1;
        int l2 = -1;
        for (int i = 0; i < words.length; i++) {
            String cw = words[i];

            if (cw.equals(word1)) {
                l1 = i;
                if(l2 != -1) shortestDist = Math.min(Math.abs(l1 - l2), shortestDist);

            } else if (cw.equals(word2)){
                l2 = i;
                if(l1 != -1) shortestDist = Math.min(Math.abs(l1 - l2), shortestDist);
            }
        }


        return shortestDist == Integer.MAX_VALUE ? -1 : shortestDist;
    }
}

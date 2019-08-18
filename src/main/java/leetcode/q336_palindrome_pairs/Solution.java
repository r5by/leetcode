package leetcode.q336_palindrome_pairs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Util;

import java.util.*;

/**
 *  解题思路：
 *  1）要有一个判断palindrome的方法
 *
 *  2） 对于每一个word，要想到将它拆成两部分看，其中一部分的reverse在words中，就可以组成回文，比如说假如当前看的词为"xxYY"
 *      对于任意一种拆分, 比如 "xx + YY", 那么：
 *      if "xx" 是回文； 则只要 "YY".reverse() 在 words 中，就可以用 YY.reverse() + xx + YY 组成回文
 *      同理，if "YY" 是回文； 则只要 xx.reverse() 在 words 中， 就可以将 xx + YY + xx.reverse() 组成回文
 *
 *  3） 需要注意特殊情况 空字符串 的处理！
 *
 *  涵盖的知识点：
 *  1） new StringBuilder(xxx).reserver().toString() => 获得 xx 字符串的反转字符串;
 *  2)  res.add ( Arrays.asList(1, 2, 3) ) => 快速创建 [1,2,3] int[]类型数组，并将其加入到 ArrayList<List<Integer>> res 结果中
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if(words == null || words.length < 1) return res;

        //init dictionary: word -> its position at words[]
        Map<String, Integer> dict = new HashMap<>();
        for (int i = 0; i < words.length; i++) dict.put(words[i], i);

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            for (int j = 0; j < word.length(); j++) {
                String lPart = word.substring(0, j); // ""  -> "w"   -> "wo" -> "wor"
                String rPart = word.substring(j); // "word" -> "ord" -> "rd" -> "d"

                if(Util.isPolindrome(rPart)) {
                    String lPartReverse = new StringBuilder(lPart).reverse().toString(); //todo: 注意这里面使用StringBuilder()对字符串进行反转的方法
                    if(dict.containsKey(lPartReverse) && dict.get(lPartReverse) != i) {
                        res.add(Arrays.asList(i, dict.get(lPartReverse))); //todo: 注意这里使用Arrays.asList()快速创建一个数组并加入到结果的方法

                        if(lPart.equals("")) res.add(Arrays.asList(dict.get(lPartReverse), i)); //todo：这里需要注意的是，如果此时左半边为""并且也存在于数组的话，需要将其加入进结果，因为该for循环没有加入""的情况，
                        // 也就是说对于{"", "a"} 这种输入，当考虑"a"的时候，既要添加[1,0]进入结果，也不要忘了对称的[0,1]
                    }
                }

                if (Util.isPolindrome(lPart)) {
                    String rPartReverse = new StringBuilder(rPart).reverse().toString();
                    if (dict.containsKey(rPartReverse) && dict.get(rPartReverse) != i) {
                        res.add(Arrays.asList(dict.get(rPartReverse), i));
                    }
                }
            }
        }

        return res;
    }
}

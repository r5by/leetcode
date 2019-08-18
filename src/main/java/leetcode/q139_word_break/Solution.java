package leetcode.q139_word_break;

import utils.Util;

import java.util.*;

class Solution {

    /* Save the length of "s" that can be segmented */
    private HashMap<String, Boolean> memo = new HashMap<>();

    public boolean wordBreak(String s, List<String> wordDict) {

        //preprocess: put wordDict to hashset
        Set<String> dict = new HashSet<>(wordDict);
//        return check(s, dict);
        return checkDFS(s, dict);
    }

    /**
     * Approach (1): "aaabbb" can be segmented iff dict.contains("aaa") && check("bbb") is true; save to the cache if a word can or can not be segmented!!
     * runTime: 5ms
     * @param s
     * @param dict
     * @return
     */
    private boolean check(final String s, final Set<String> dict) {
        if(s.length()==0) return true;

        if (!memo.isEmpty() && memo.containsKey(s))
            return memo.get(s);

        boolean res = false;
        for (int i = 1; i <= s.length(); i++) {
            if(dict.contains(s.substring(0, i))) {
                res = check(s.substring(i), dict);
                if(res==true) {
                    memo.put(s, true);
                    break;
                }
            }
        }

        if(res==false) memo.put(s, false);
        return res;
    }

    /**
     * Approach (2) : DFS
     * runTime: 3ms
     * @param s
     * @param dict
     * @return
     */
    private boolean checkDFS(String s, final Set<String> dict) {
        if(s.length() == 0) return true;
        if(!memo.isEmpty() && memo.containsKey(s))
            return memo.get(s);

        boolean res = false;
        for (String pattern : dict) {
            if(Util.sStartsWithPattern(s, pattern)) {
                res = checkDFS(s.substring(pattern.length()), dict);
                if(res==true) break;
            }
        }

        if(res==true)
            memo.put(s, true);
        else
            memo.put(s, false);

        return res;
    }
}

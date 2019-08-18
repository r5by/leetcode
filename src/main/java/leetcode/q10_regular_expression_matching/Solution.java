package leetcode.q10_regular_expression_matching;

import java.util.HashMap;
class Solution {
    //The DP solution, refer to:  https://www.youtube.com/watch?v=KN22ZEpRTFY
    public boolean isMatch(String s, String p) {
        if(s == null || p == null)
            throw new IllegalArgumentException("error");

        //boolean array dp[i][j] to save whether s[:i] (means len of s is i) match p[:j] (len of p is j)
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        dp[0][0] = true; //empty string matches empty pattern
        for (int i = 1; i < p.length() ; i+=2) { // since empty pattern doesn't match any string, dp[i][0] are already false; dp[0][j] needs to be taken care of
            if(p.charAt(i) == '*' && dp[0][i - 1]) dp[0][i] = true; //only if the p[i] == * and dp[0][i-1] is true that dp[0][i] should be set true
        }

        //Dynamic programming state-transition functions
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) { //Note the index i, j are related to update dp[i+1][j+1]!!
                if(p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') // If the current characters match, inspect the following
                    dp[i + 1][j + 1] = dp[i][j];
                else {// Else, the only true condition states are as following:
                    if (p.charAt(j) == '*') {
                        if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.')
                            dp[i + 1][j + 1] = dp[i + 1][j - 1]; //if the previous char not match, use p[j-1,j] as empty string
                        else { //o.w. use p[j-1,j] as (1) single char replace (2) multiple replacement (3) empty
                            dp[i + 1][j + 1] = (dp[i + 1][j] //single replacement case: x* -> x, thus check if p[:j-1] match s[:i]
                                    || dp[i][j + 1] //multiple replacement case: x* -> xxx..., thus check if p[:j] match s[:i-1]
                                    || dp[i + 1][j - 1]); //empty case: x* -> "", thus check if p[:j-2] match s[:i]
                        }
                    }
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    /* My own convoluted solution... sigh.. */

//    private HashMap<State, Boolean> cache = new HashMap<State, Boolean>();
//    public boolean isMatch(String s, String p) {
//        //pre-process p to get rid of possible continuous "*"
//        String pp = preProcess(p);
//
//        State state = new State(s, p);
//        boolean result = false;
//
//        if(!cache.isEmpty() && cache.containsKey(state))
//            result = cache.get(state);
//
//        //Empty string matches only empty string
//        if(p.length() == 0) result = (s.length() == 0);
//
//        boolean firstMatch = s.length() != 0 && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0));
//
//        if(p.length() >= 2 && p.charAt(1) == '*') {
//            result = isMatch(s, p.substring(2))
//                    || (firstMatch && isMatch(s.substring(1), p));
//        }
//        else
//            result = firstMatch && isMatch(s.substring(1), p.substring(1));
//
//        if(!cache.containsKey(state))
//            cache.put(state, result);
//        return result;
//    }


    /**
     * Remove the duplicated "*" from pattern
     * @param pattern
     * @return
     */
    private String preProcess(String pattern) {
        String result = "";

        int i = 0;
        while (i < pattern.length()) {
            int j = i;
            while(j < pattern.length() && pattern.charAt(j) == '*') //TODO: Note that the conditions have an order!!!
                j++;

            if(j != i) {
                result += '*';
                i = j;
            }

            if(i != pattern.length()) {
                result += pattern.charAt(i);
                i++;
            }
            else {
                break;
            }
        }

        return result;
    }

    /**
     * Customized key for d.p. cache
     */
    private class State {
        String text;
        String pattern;

        State(String s, String p) {
            this.text = s;
            this.pattern = p;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(o == null || this.getClass() != o.getClass()) return false;

            State state = (State) o;

            if(text != state.text) return false;
            if(pattern != state.pattern) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = (pattern != null ? pattern.hashCode() : 0);
            result += (text != null ? text.hashCode() : 0);
            return result;
        }
    }
}

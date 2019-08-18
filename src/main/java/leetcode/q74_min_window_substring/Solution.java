package leetcode.q74_min_window_substring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    public String minWindow(String s, String t) {
        if (t.length() > s.length())
            return "";

        HashMap<Character, Integer> charDict = new HashMap<>();
        for (char c : t.toCharArray()) {
            if (charDict.containsKey(c)) {
                Integer tmp = charDict.get(c);
                charDict.put(c, ++tmp);
            } else
                charDict.put(c, 1);
        }

        Queue<Integer> nextPos = new LinkedList<>(); //Next occur of a target char
        int minL = Integer.MAX_VALUE;
        int lRes = 0, rRes = 0; //left and right position of the result
        int totalCnt = 0; //cnt how many goals have been accomplished
        int fast, slow = -1; //two running pointers to create sliding window
        for (fast = 0; fast < s.length(); fast++) {

            char c = s.charAt(fast);
            if (!charDict.containsKey(c)) continue;

            nextPos.offer(fast); //save the record
            int cntLeft = charDict.get(c);
            charDict.put(c, --cntLeft);

            if (cntLeft == 0) {//current char just reached the goal
                totalCnt++;
            }

            while (totalCnt == charDict.keySet().size()) {//move the slow pointer to next available position until certain goal is not matched
                slow = nextPos.poll();
                int curL = fast - slow + 1;
                if (curL < minL) {
                    minL = curL;
                    lRes = slow;
                    rRes = fast;
                }

                int tmp = charDict.get(s.charAt(slow));
                charDict.put(s.charAt(slow), ++tmp);
                if (tmp > 0) totalCnt--;
            }
        }

        return slow == -1 ? "" : s.substring(lRes, rRes + 1);
    }


}

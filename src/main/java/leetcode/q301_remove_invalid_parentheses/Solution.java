package leetcode.q301_remove_invalid_parentheses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 解题思路：
 * 1）dfs 肯定是要的
 * 2）此题的trick在于：
 *      a）遍历第一遍的时候将不对称的"）"直接替换掉，方法就是选择之前不相连的可选右括号位置；
 *      b）最有趣的地方在于dfs一遍后，不是在原有的基础上进行删除多余做括号操作，而是直接反转字符串，然后用同样的dfs方法去处理该反转后的字符串，不同的在于将 char[]{'(', ')'} 改变为 char[]{')', '('}
 *      这样的做法极大的简化了dfs的实现复杂度，而且重用了整个方法代码，很巧妙
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    /* 改： */
    private Set<String> res = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        if (s == null || s.length() < 1) return new ArrayList<String>(){{
                add("");
            }};

        dfs(new ArrayList<>(), new StringBuilder(s), 0, 0, new char[]{'(', ')'});

        return new ArrayList<>(res);
    }

    private void dfs(List<Integer> cands, StringBuilder br, int pos, int cnt, char[] pair) {
        if (pos == br.length()) {
            if (pair[0] == '(') {
                dfs(new ArrayList<>(), new StringBuilder(br).reverse(), 0, 0, new char[]{')', '('}); //巧妙的操作，重用dfs过程
            } else {
                res.add(new StringBuilder(br).reverse().toString().replaceAll("#", ""));
            }
            return;
        }

        char c = br.charAt(pos);
        if (c == pair[0]) {
            cnt++;
        } else if (c == pair[1]) {
            int newCand = findCand(pos, pair[1], br);
            if (cands.isEmpty() || !cands.contains(newCand)) cands.add(newCand);

            if (--cnt < 0) {
                for (int i = 0; i < cands.size(); i++) {
                    List<Integer> copy = new ArrayList<>(cands); //todo: 注意这里要新建副本，不要在原来的数据上修改
                    int rPos = copy.get(i);
                    br.setCharAt(rPos, '#');
                    copy.remove(i);

                    if (rPos + 1 < br.length() && br.charAt(rPos + 1) == pair[1]) { //注意这里判断不要越界
                        copy.add(rPos + 1); //注意将可选的位置更新，如果与之相连之后的位置依然是pair[1]
                    }
                    dfs(copy, br, pos + 1, 0, pair);

                    //backtracking
                    br.setCharAt(rPos, pair[1]);
                }

                return; //别忘了return 这是一个独立的分支
            }
        }

        dfs(cands, br, pos + 1, cnt, pair);
    }

    private int findCand(int pos, char c, StringBuilder br) {
        while (pos >= 0 && pos < br.length() && br.charAt(pos) == c) pos--;
        return pos + 1;
    }

    /* 下面这个方法将 左右括号都记录下来最后处理，太慢
     * 考虑优化：实际上右括号一旦出现不匹配， 可以直接去前面找可能的位置替换了
     * */
//    private Set<String> res = new HashSet<>();
//    private List<Integer> lpPositions = new ArrayList<>();
//    private List<Integer> rpPositions = new ArrayList<>();
//
//    public List<String> removeInvalidParentheses(String s) {
//
//        if (s == null || s.length() == 0) return new ArrayList<>(Arrays.asList(""));
//
//        Deque<Integer> redundantLP = new ArrayDeque<>();
//        Deque<Integer> redundantRP = new ArrayDeque<>();
//
//        //第一次遍历记录多出来的（ 和 ）的位置
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (c == '(') {
//                lpPositions.add(i);
//                redundantLP.push(i);
//            } else if (c == ')') {
//                rpPositions.add(i);
//                if (redundantLP.isEmpty()) {
//                    redundantRP.push(i);
//                } else
//                    redundantLP.pop();
//            }
//        }
//
//        //对两个多余的括号stack不断出栈操作并修改可选位置：对于（为其之后的位置，）则可以替换任意之前的位置
//        replaceRedundantParentheses(redundantLP, redundantRP, s);
//
//        return new ArrayList<>(res);
//    }
//
//    private void replaceRedundantParentheses(Deque<Integer> lp, Deque<Integer> rp, String s) {
//        if (lp.isEmpty() && rp.isEmpty()) {
//            res.add(s.replaceAll("#", ""));
//            return;
//        }
//
//        if (!lp.isEmpty()) {
//            for (int nxtPos : lpPositions) {
//                if(s.charAt(nxtPos) == '#' ||
//                        (nxtPos < lp.peek() && !adjcentLP(nxtPos, lp.peek()))) continue;
//
//                int lpPos = lp.pop();
//                StringBuilder str = new StringBuilder(s);
//                str.setCharAt(nxtPos, '#');
//                replaceRedundantParentheses(lp, rp, str.toString());
//                lp.push(lpPos);
//            }
//
//        } else {
//            for (int prePos : rpPositions) {
//                if(s.charAt(prePos) == '#' ||
//                        (rp.peek() < prePos && !adjcentRP(prePos, rp.peek()))) break; //todo: check if this is right
//
//                int rpPos = rp.pop();
//                StringBuilder str = new StringBuilder(s);
//                str.setCharAt(prePos, '#');
//                replaceRedundantParentheses(lp, rp, str.toString());
//                rp.push(rpPos);
//            }
//        }
//
//    }
//
//    private boolean adjcentRP(int p, int i) {
//        boolean res = true;
//
//        while (p > i) {
//            if(!rpPositions.contains(p--)) {
//                res = false;
//                break;
//            }
//        }
//        return res;
//    }
//
//    private boolean adjcentLP(int p, int i) {
//        boolean res = true;
//        while(p < i) {
//            if (!lpPositions.contains(p++)) {
//                res = false;
//                break;
//            }
//        }
//
//        return res;
//    }



    /* Reference */
    //    public List<String> removeInvalidParentheses(String s) {
//        List<String> res = new ArrayList<>();
//        helper(res, s, 0, 0, new char[]{'(', ')'});
//        return res;
//    }
//    public void helper(List<String> res, String s, int last_i, int last_j, char[] pair) {
//        for (int count = 0, i = last_i; i < s.length(); i++) {
//            if (s.charAt(i) == pair[0]) count++;
//            if (s.charAt(i) == pair[1]) count--;
//            if (count >= 0) continue;
//            for (int j = last_j; j <= i; j++) {
//                if (s.charAt(j) == pair[1] && (j == last_j || s.charAt(j - 1) != pair[1])) {
//                    helper(res, s.substring(0, j) + s.substring(j + 1), i, j, pair);
//                }
//            }
//            return;
//        }
//        String reversed = new StringBuilder(s).reverse().toString();
//        if (pair[0] == '(') {
//            helper(res, reversed, 0, 0, new char[]{')', '('});
//        } else {
//            res.add(reversed);
//        }
//    }
}

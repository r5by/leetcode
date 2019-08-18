package leetcode.q22_generate_parentheses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    public List<String> generateParenthesis(int n) {
        if (n < 0)
            throw new IllegalArgumentException("error");

        List<String> res = new ArrayList<String>();
        helper(res, "", n, 0, 0, 0);
        return res;
    }

    private void helper(List<String> res, String tmp, final int pNum, final int pos, final int left, final int right) {
            if(pos == pNum * 2 && left == right) {
                res.add(tmp);
                return;
            }

            //画树状图，然后剪枝！
            if(left <= pNum) {
                helper(res,  tmp + "(", pNum, pos + 1, left + 1, right);

                if(left > right)
                    helper(res, tmp + ")", pNum, pos + 1, left, right + 1);
            }
    }


//    public List<String> generateParenthesis(int n) {
//        List<String> res = new ArrayList<String>();
//        helper(n, n, "", res);
//        return res;
//    }
//    void helper(int left, int right, String out, List<String> res) {
//        if (left < 0 || right < 0 || left > right) return;
//        if (left == 0 && right == 0) {
//            res.add(out);
//            return;
//        }
//        helper(left - 1, right, out + "(", res);
//        helper(left, right - 1, out + ")", res);
//    }

//    public List<String> generateParenthesis(int n) {
//        Set<String> res = new HashSet<String>();
//        if (n == 0) {
//            res.add("");
//        } else {
//            List<String> pre = generateParenthesis(n - 1);
//            for (String str : pre) {
//                for (int i = 0; i < str.length(); ++i) {
//                    if (str.charAt(i) == '(') {
//                        str = str.substring(0, i + 1) + "()" + str.substring(i + 1, str.length());
//                        res.add(str);
//                        str = str.substring(0, i + 1) +  str.substring(i + 3, str.length());
//                    }
//                }
//                res.add("()" + str);
//            }
//        }
//        return new ArrayList(res);
//    }

}

package leetcode.q8_atoi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解题思路：
 * 1) 使用正则表达式匹配
 * 2）使用自动机（参考第65题）
 * <p>
 * 知识点： 注意一下对于超出最大最小整数的处理！
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    public int myAtoi(String str) {
        String s = str.trim();

        Pattern pattern = Pattern.compile("(^[+-]?\\d+)([^\\d]*)");
        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            String numStr = matcher.group(1);
            try {
                int num = Integer.parseInt(numStr);
                return num;
            }
            catch (Exception e) {
                if(numStr.charAt(0) == '-') return Integer.MIN_VALUE;
                else return Integer.MAX_VALUE;
            }
        }
        else
            return 0;
    }

    /* 自动机解法：*/
//    public int myAtoi(String str) {
//        //以下自动机 match 这个表达式： "([+-]?\\d+)([^\\d]*)"
//        // [+-]? : [+-] 表示  从 ＋ 或 － 选一个，？表示此项可有可无
//        // \\d+  : 表示至少有一位的digit组成的整数
//        // [^\\d]* : [^\\d] 表示除了数字之外的任何一个输入; 加 ＊ 表示 这一项可以有从0到无穷多个
//
//        String s = str.trim();
//
//        // -1 state all return 0 according to leetcode
//        int[][] stateMatchine = {
//                {1, 2, -1}, //初始状态
//                {-1, 2, -1}, //"+" 或 "-"
//                {-1, 2, 3}, // "123" 这种纯数字
//                {3, 3, 3}, //"-444 abc" 这种
//        };
//
//        int state = 0, i = 0;
//        for (; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (c == '+' || c == '-') {
//                state = stateMatchine[state][0];
//            } else if (Character.isDigit(c)) {
//                state = stateMatchine[state][1];
//            } else {
//                state = stateMatchine[state][2];
//            }
//
//            if (state == 3 || state == -1) break; //todo: 注意这块发现不接受的state －1直接break
//        }
//
//        if (state == 2 || state == 3) {
//            return myParseInt(s.substring(0, i));
//        } else return 0;
//    }
//
//    private int myParseInt(String s) {
//        int res = 0;
//        try {
//            res = Integer.parseInt(s);
//        } catch (Exception e) {
//            if(s.charAt(0) == '-') res = Integer.MIN_VALUE;
//            else res = Integer.MAX_VALUE;
//        }
//
//        return res;
//    }


}

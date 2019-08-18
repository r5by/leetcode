package leetcode.q394_decode_string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 解题思路：
 *  跟 224. basic calculator 题目很像，使用stack来完成回溯，问题是如何来pop？
 *  －－－使用两个stack 分别记录 数字 和 字符串
 *  发现无论是给数字还是字符串加level，都无法完成同步操作两个栈，因为这个情况下： 2[x]kkk2[y] kkk的处理很难办
 *  －－－发现上述棘手情况可以通过将数字跟依赖它的字符串结对儿考虑，这种情况下只需要维护一个如下的栈：
 *  xxx3[ab2[c]kk2[ab]]
 *  <1, xxx> <3, ab> <2,c> "kk" <2, ab>
 *  1) 每次碰到数字，直接处理跟它连在一起的字符串作为基底；例如 3 ==> "3[ab"
 *  2) 每次碰到"]", 将栈顶元素出栈，复制基底n次，然后将结果推回新栈顶的base，如果栈空，则新建 <1, xxx> 并入栈；例如：第一个"]" => <1, xxx> <3, ab + 2 * c>
 *  3) 每次碰到字符串，如果栈非空，直接将其入栈加入base；否则的话建立新的<1,xxx>
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    private int mP = 0; //point to current position

    private Deque<ops> opStack = new ArrayDeque<>();
    public String decodeString(String s) {
        if(s == null || s.length() < 1)
            return "";

        for (int i = 0; i < s.length(); ) {
            char c = s.charAt(i);
            if (c == ']') {
                operate();

                i++;
            } else if (Character.isDigit(c)) {
                ops curOp = getOp(i, s);
                opStack.push(curOp);

                i = mP;
            } else {//letters
                String text = getNextText(i, s);
                if(opStack.isEmpty()) opStack.push(new ops(1, text));
                else opStack.peek()._base += text;

                i = mP;
            }
        }

        while(!opStack.isEmpty() && opStack.peek()._cnt != 1) {
            operate();
        }

        return opStack.pop()._base;
    }

    //operate on stack down one level, if already bottom add <1, xxxx> as a new bottom layer
    private void operate() {
        ops curOp = opStack.pop();
        StringBuilder br = new StringBuilder();
        while(curOp._cnt-- > 0) br.append(curOp._base);

        if(opStack.isEmpty()) opStack.push(new ops(1, br.toString()));
        else opStack.peek()._base += br.toString();
    }

    // Invoked when scanner hit a number
    private ops getOp(int pos, String s) {
        int cnt = getNextNumber(pos, s);
        pos = mP + 1; //jump over '[' and get the string after it
        String text = getNextText(pos, s);
        return new ops(cnt, text);
    }

    // Invoked until scanner hit '[', move the position to ']' and return the
    private int getNextNumber(int pos, String s) {
        StringBuilder builder = new StringBuilder();
        while(pos < s.length() && s.charAt(pos) != '[') {
            builder.append(s.charAt(pos++));
        }

        mP = pos; //set the pointer to'['
        return Integer.valueOf(builder.toString());
    }

    //Invoked when scanner hit a letter, move to find the plain text
    private String getNextText(int pos, String s) {
        StringBuilder builder = new StringBuilder();
        while (pos < s.length()) {
            char c = s.charAt(pos++);
            if(c == ']' || Character.isDigit(c)) break;
            builder.append(c);
        }

        if(pos != s.length())
            mP = pos - 1; //set pointer to any char after the selected text, pointer to ']' or [0..9]
        else
            mP = pos; //if already to the end;
        return builder.toString();
    }

    private class ops {
        int _cnt;
        String _base;

        ops(int cnt, String base) {
            _cnt = cnt;
            _base = base;
        }
    }
}

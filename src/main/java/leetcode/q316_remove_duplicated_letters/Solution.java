package leetcode.q316_remove_duplicated_letters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 解题思路：
 * 1）第一次循环拿到全部字符的频率表
 * 2）第二次循环，根据栈顶字符于当前字符的大小，如果大于当前字符并且剩余频率大于0，说明后面还会出现该字符，所以将其出栈；循环此过程直到再无满足条件的字符在栈顶，此时将当前字符入栈；
 * 3）最后从栈底到栈顶输出字符即为所寻找的最小字串；注意使用ArrayDeque的pullLast()来从栈底向栈顶取元素！
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);
    public String removeDuplicateLetters(String s) {
        if(s == null || s.length() == 0)
            return "";

        Map<Character, Integer> dict = new HashMap<>();
        //first iteration construct dict: o(n)
        for (char c : s.toCharArray()) {
            if(dict.isEmpty() || !dict.containsKey(c)) dict.put(c, 1);
            else dict.put(c, dict.get(c) + 1);
        }

        //second iteration populate result: o(n)
        StringBuilder builder = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if(!stack.isEmpty()) {
                if(stack.contains(c)) { //if stack has c, count and continue
                    dict.put(c, dict.get(c) - 1);
                    continue;
                }

                //o.w. continuesly check top of stack, if greater than c and has more left, pop it out then push c
                while (!stack.isEmpty() && stack.peek() > c && dict.get(stack.peek()) > 0) {
                    stack.pop();
                }
            }

            stack.push(c);
            dict.put(c, dict.get(c) - 1);
        }

        while(!stack.isEmpty()) builder.append(stack.pollLast()); //todo: 注意 对于deque来说，pollfirst()等于pop()， polllast()等于从栈底往外拿元素
        return builder.toString();
    }

}

package leetcode.reverse_String_with_brackets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    public String reverseWithBrackets(String s) {
        if(s == null || s.length() < 1) return "";

        Deque<Character> stack = new ArrayDeque<>();
        Deque<Integer> openBrecketPos = new ArrayDeque<>();//Use another stack to save position of open brecket is much easier..

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{') {
                openBrecketPos.push(i);
                stack.push(c);
            } else if (c == '}') {
                int lPos = openBrecketPos.pop();
                int j = i-1;
                StringBuilder br = new StringBuilder();
                br.append('{');
                while (j-- != lPos) {
                    Character poped = stack.pop();
                    if(poped == '{') br.append('}');
                    else if(poped == '}') br.append('{');
                    else br.append(poped);
                }
                br.append('}');
                stack.pop();//remove the '{'
                for (char b : br.toString().toCharArray()) {
                    stack.push(b);
                }
            } else {
                stack.push(c);
            }
        }

        StringBuilder res = new StringBuilder();
        while(!stack.isEmpty()) res.append(stack.pop());
        return res.reverse().toString();
    }
}

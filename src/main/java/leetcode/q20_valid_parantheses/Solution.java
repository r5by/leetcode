package leetcode.q20_valid_parantheses;

import java.util.Stack;

class Solution {

    public boolean isValid(String s) {
        if(s == null)
            throw new IllegalArgumentException("erro");

        if(s.length() == 0)
            return false;

        Stack<Character> input = new Stack<Character>();
        boolean res = true;
        for (char c : s.toCharArray()) {
            if(c == '(' || c == '{' || c == '[')
                input.add(c);
            else {
                if(input.isEmpty()) {
                    res = false;
                    break;
                }

                char x = input.pop();
                if(
                        (c == ')' && x != '(') ||
                                (c == ']' && x != '[' ||
                                        (c == '}' && x != '{'))
                )
                    res = false;
            }
        }

        return res;
    }
}

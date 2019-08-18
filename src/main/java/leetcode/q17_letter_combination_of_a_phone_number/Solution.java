package leetcode.q17_letter_combination_of_a_phone_number;

import java.util.ArrayList;
import java.util.List;

class Solution {

    private final String[] table = {"abc", "def", "ghi", "jkl",
            "mno", "pqrs", "tuv", "wxyz"};

    public List<String> numToStringCombanitions(String digits) {
        List<String> res = new ArrayList<String>();

        if (digits == null)
            throw new IllegalArgumentException("erro");
        else if (digits.length() == 0)
            return res;

        String tmp = "";
        helper(res, tmp, digits, 0);

        return res;
    }

    private void helper(List<String> res, String tmp, final String digits, final int pos) {
        if (pos == digits.length()) {
            res.add(tmp);
            return;
        }

        String choices = table[toIndex(digits.charAt(pos))];

        for (int i = 0; i < choices.length(); i++) {
            tmp += choices.charAt(i);
            helper(res, tmp, digits, pos + 1);
            tmp = removeStringLastChar(tmp);
        }
    }


    private int toIndex(char dnum) {
        int num = dnum - '0'; //Convert char to int
        if(num < 2 || num > 9)
            throw new IllegalArgumentException("erro");

        return num - 2;
    }

    private String removeStringLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        return s.substring(0, s.length() - 1);
    }
}

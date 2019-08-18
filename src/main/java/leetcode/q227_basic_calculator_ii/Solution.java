package leetcode.q227_basic_calculator_ii;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

//    private Deque<Character> opStack = new ArrayDeque<>(); //save '+' '-' TODO: Note here there is no need to save '-' operator since we can't re-order + and -; instand, we save "-" with the following number x as "-x"
    private Deque<Integer> nums = new ArrayDeque<>(); //save numbers for '+' '-'
    private int p = 0; //save the next pos after nextInt()
    public int calculate(String s) {
        if(s == null) return 0;
        s = s.replaceAll("\\s", "");
        if(s.equals("")) return 0;

        int firstNum = nextInt(0, s);
        nums.push(firstNum);

        for(int i = p + 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) continue;
            else {
                switch(c) {
                    case '+':
                        int pNum = nextInt(i + 1, s); //add positive number
                        nums.push(pNum);
                        break;
                    case '-':
                        int nNum = -nextInt(i + 1, s); //add negative number
                        nums.push(nNum);
                        break;
                    case '*':
                    case '/':
                        multiOrDev(c, i, s);
                        break;
                }
            }

            i = p;
        }

        int res = 0;
        while(!nums.isEmpty()) {
            res += nums.pop();
        }

        return res;
    }

    private void multiOrDev(char c, int pos, String s) {
        int a = nums.pop();
        int b = nextInt(pos + 1, s);
        if(c == '*') nums.push(a * b);
        else nums.push(a / b);
    }

    private int nextInt(int pos, String s) {
        while(s.charAt(pos) == ' ') pos++;
        int num = s.charAt(pos) - '0';
        while(pos < s.length() - 1 &&  Character.isDigit(s.charAt(pos+1)) ) {
            num = num * 10 + s.charAt(pos+1) - '0';
            pos++;
        }
        p = pos;
        return num;
    }
}

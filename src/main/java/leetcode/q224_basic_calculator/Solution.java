package leetcode.q224_basic_calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.UnexpectedException;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 解题思路：两个堆栈，一个存数字，一个存运算符及其level，顺序扫描字符串：
 * 1） 如果是数字直接入数字栈
 * 2）如果是"（" 将运算符level＋1， "）" 则将其－1
 * 3） 如果是运算符，判断当前level是否比运算符栈顶的运算符大，如果是将当前运算符连带level入栈；否则弹出运算符和两个数字运算，将结果押回数字栈，并将当前运算符入栈
 * 4) 整个字符串扫描结束后将全部剩余全部运算符出栈，并最终返回数字栈的最后一个元素
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    public int calculate(String s) {
        if(s == null || s.equals("")) return 0;

        //todo: note java use deque (double ended queue) interface to replace the stack class (extended from Vector) for efficiency concern
//        Stack<Integer> numbers = new Stack<>();
        Deque<Integer> numbers = new ArrayDeque<>();
        Deque<Operator> operators = new ArrayDeque<>();

        int opLevl = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '+' || c == '-') {
                Operator op = new Operator(opLevl, c);
                while(!operators.isEmpty() && operators.peek()._lvl >= op._lvl) {//TODO: Note here to check the stack before peek, because the first element to be added
                    Operator curOp = operators.pop();
                    try {
                        curOp.calc(numbers);
                    } catch (UnexpectedException e) {
                        e.printStackTrace();
                    }
                }

                operators.push(op);
            } else if (c == '(') {
                opLevl++;
            } else if (c == ')') {
                opLevl--;
            } else {//NUMBERS
//                numbers.push(Integer.parseInt(String.valueOf(c)));
//              todo: note here how to parse the integer value of a char !! need to continuely add-up to the final integer
                int num = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                numbers.push(num);
            }
        }

        //Pop out all numbers and operators in the end
        while(!operators.isEmpty()) {
            Operator op = operators.pop();
            try {
                op.calc(numbers);
            } catch (UnexpectedException e) {
                e.printStackTrace();
            }
        }

        return numbers.pop();
    }

    class Operator {
        int _lvl; //level of the operator, higher the sooner to be poped out
        char _op;

        Operator(int lvl, char op) {
            _lvl = lvl;
            _op = op;
        }

        //Calculate the value of two elements on top of the stack based on _op then push the result back
        void calc(Deque<Integer> nums) throws UnexpectedException {
            int num1 = nums.pop();
            int num2 = nums.pop();
            switch (_op) {
                case '-':
                    nums.push(num2 - num1);
                    break;
                case '+':
                    nums.push(num2 + num1);
                    break;
                default:
                    throw new UnexpectedException("Unrecognized operator");
            }
        }
    }

}

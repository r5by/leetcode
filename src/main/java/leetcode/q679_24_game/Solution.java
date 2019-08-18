package leetcode.q679_24_game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 解题思路：
 * 1）使用 divide & conquer 的思想， 4个卡运算后结果为24，可以划归为最后是两个卡运算加上之前所有运算的结果为24的可能，或者最后一个卡的数字于之前所有卡的运算结果然后求||
 * 2) 基础情况为两种，一个是最后一张卡是否为target，另一个是最后两张卡运算后是否可能为target
 * 3）需要注意的当过程中出现0这种中间结果时的讨论
 * <p>
 * <p>
 * 扩展练习题：Arithmetic Expression to Construct a Value
 * Given an array nums of positive and negative integers and an int target. You have +, -, *, / and () operators.
 * Find out if it's possible to build an expression that evaluates to the target value. Each operator can only be used once.
 * Return any solution or an empty string if it's not possible.
 * <p>
 * Example 1:
 * Input: nums = [1, 2, 3, 8, 4], target = 44
 * Output: "(3+8)*4"
 * <p>
 * Example 2:
 * Input: nums = [2, 3, 4, 1, 9, 2], target = 21
 * Output: "3+2*9"
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    /* 扩展题 */

    /**
     * Devide & Conquer solution:
     *  Recursively think the problem from last operator used to produce the final result and return them all!
     */
    public List<String> extractValue(int[] nums, int target) {
        if (nums == null || nums.length < 1) return new ArrayList<String>() {{ add(""); }}; //corner case

        //init
        List<Integer> numList = new ArrayList<>();
        for (int i : nums) numList.add(i);

        Set<Character> ops = new HashSet<Character>(){{
            add('+');
            add('-');
            add('*');
            add('/');
        }};

        //D&C solve
        Set<Expr> exprs = extractValueHelper(numList, ops, target, null);
        if (exprs.isEmpty()) return new ArrayList<String>() {{ add(""); }};
        else {
            List<String> res = new ArrayList<>();
            for (Expr e : exprs) res.add(e.expr);
            return res;
        }
    }

    /**
     * DFS by ops
     * @param nums  : numbers available at current state
     * @param ops   : operators available at current state
     * @param target    :target (needs to be double)
     * @param lastOp    : Challenging part => the last operator that has to be performed at the previous state, if it's higher order than current state, needs parenthesis.
     * @return
     */
    private Set<Expr> extractValueHelper(List<Integer> nums, Set<Character> ops, double target, Character lastOp) {
        /* return defination */
        Set<Expr> res = new HashSet<>();

        //End conditions
        if (nums.isEmpty()) return res;
        else {//first try without using further operators, can we return the target value?
            for (int num : nums) {
                if (num == target) {
                    res.add(new Expr(String.valueOf(num), false));
                    break;
                }
            }
        }
        if (ops.isEmpty()) return res; //if no more operators available, return

        // DFS by operator
        for (char op : ops) {
            Set<Character> opsCopy = new HashSet<>(ops);
            opsCopy.remove(op);

            for (int j = 0; j < nums.size(); j++) {

                List<Integer> numsCopy = new ArrayList<>(nums);
                int num = nums.get(j);
                numsCopy.remove(j);

                Set<Expr> exprL = null, exprR = null;
                boolean requireParenthesis = false;

                switch (op) {
                    case '+':
                        exprL = extractValueHelper(numsCopy, opsCopy, target - num, op);
                        if (isHighLvlOp(lastOp)) requireParenthesis = true;
                        break;
                    case '-':
                        exprL = extractValueHelper(numsCopy, opsCopy, target + num, op);
                        exprR = extractValueHelper(numsCopy, opsCopy, num - target, op);
                        if (isHighLvlOp(lastOp)) requireParenthesis = true;
                        break;
                    case '*':
                        //requires input num not 0
                        if (target != 0) {
                            exprL = extractValueHelper(numsCopy, opsCopy, target / num, op);
                        }
                        break;
                    case '/':
                        if (target != 0) {
                            exprL = extractValueHelper(numsCopy, opsCopy, target * num, op);
                            exprR = extractValueHelper(numsCopy, opsCopy, num / target, op);
                        }
                        break;
                } //end switch

                //populate result
                String str = null;
                if (exprL != null && !exprL.isEmpty()) {
                    for (Expr e : exprL) {
                        if (requireParenthesis) {
                            if (e.isPUsed) continue; //If current last operator requires parenthesis yet it has already been used, abandon this result

                            str = "(" + e.expr + op + num + ")";
                            res.add(new Expr(str, true));
                        } else {
                            str = "" + e.expr + op + num;
                            res.add(new Expr(str, e.isPUsed));
                        }
                    }
                }

                if (exprR != null && !exprR.isEmpty()) {
                    for (Expr e : exprR) {
                        if (requireParenthesis) {
                            if (e.isPUsed) continue;

                            str = "(" + num + op + e.expr + ")";
                            res.add(new Expr(str, true));
                        } else {
                            str = "" + num + op + e.expr;
                            res.add(new Expr(str, e.isPUsed));
                        }
                    }
                }
            }//end for-nums
        }//end for-ops
        return res;
    }

    /* '*' and '/' are higher level operators than '+' and '-' */
    private boolean isHighLvlOp(Character op) {
        return op != null && (op == '*' || op == '/');
    }

    /* Wrap-up the two return values since java not support multiple returns.. */
    private class Expr {
        String expr = null;
        boolean isPUsed = false;

        Expr(String pExpr, boolean pIsPUsed) {
            expr = pExpr;
            isPUsed = pIsPUsed;
        }

        @Override
        public int hashCode() {
            return expr.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (obj.getClass() != this.getClass()) return false;
            Expr that = (Expr) obj;
            return (this.expr.equals(that.expr) && this.isPUsed == that.isPUsed);
        }
    }

    /* game 24 原题 */
    public boolean judgePoint24(int[] nums) {
        if (nums == null || nums.length < 2) return false;

        List<Double> cards = new ArrayList<>();
        for (int num : nums) cards.add((double) num);

        return helper(cards, 24);
    }

    private boolean helper(List<Double> cards, double target) {
        int len = cards.size();
        boolean res = false;

        if (len == 1) {
            res = cards.get(0) == target;
        } else if (len == 2) {
            double card1 = cards.get(0);
            double card2 = cards.get(1);

            res = (card1 + card2 == target) || (card1 * card2 == target) ||
                    (card1 - card2 == target || card2 - card1 == target) ||
                    (card2 / card1 == target) || (card1 / card2 == target);
        } else {
            //case1: (x, y) ? (z, k)  || case2: k ? (x, y, z)
            res = helper2(cards, target) || helper1(cards, target);
        }

        return res;
    }

    //Select two element to be operated to get results
    private boolean helper2(List<Double> cards, double target) {
        int len = cards.size();
        boolean res = false;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                double card1 = cards.get(i);
                double card2 = cards.get(j);

//                if(i == 0 && j == 2)
//                    LOG.debug("");

                List<Double> nums = numerate(card1, card2);
                List<Double> copy = new ArrayList<>();
                for (int k = 0; k < len; k++) {
                    if (k == i || k == j) continue;
                    copy.add(cards.get(k));
                }

                for (double t : nums) {
//                    if(t == 4)
//                        LOG.debug("");
                    if (t == 0) { //todo：注意此处中间结果出现0
                        res |= helper(copy, target) || helper(copy, -target);
                    } else {
                        res |= helper(copy, target + t) ||
                                helper(copy, target * t) ||
                                helper(copy, target - t) ||
                                helper(copy, t - target) ||
                                helper(copy, target / t) ||
                                (target != 0 && helper(copy, t / target));
                    }

                }

                if (res) break;
            }
        }
        return res;
    }

    //Select one element to be operated to get results
    private boolean helper1(List<Double> cards, double target) {
        int len = cards.size();
        boolean res = false;
        for (int i = 0; i < len; i++) {
            double card = cards.get(i);
            List<Double> copy = new ArrayList<>(cards);
            copy.remove(i);

            res |= helper(copy, target + card) || helper(copy, target * card) ||
                    helper(copy, target / card) || helper(copy, card / target) ||
                    helper(copy, target - card) || helper(copy, card - target);

            if (res) break;
        }

        return res;
    }

    private List<Double> numerate(double x, double y) {
        Set<Double> res = new HashSet<>();
        res.add(x + y);
        res.add(x * y);
        res.add(x / y);
        res.add(y / x);
        res.add(x - y);
        res.add(y - x);
        return new ArrayList<>(res);
    }

}

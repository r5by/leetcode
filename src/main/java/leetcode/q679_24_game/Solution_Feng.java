package leetcode.q679_24_game;


import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution_Feng {

    public Solution_Feng() {}

    public Set<String> extractValue(int[] nums, double target) {
        List<Integer> n = new ArrayList<>();
        for(int num : nums) n.add(num);

        Set<String> ops = new HashSet<String>(){{
            add("+");
            add("-");
            add("*");
            add("/");
        }};

        return GetAllSolutions(target, n, ops, 1);
    }

    public Set<String> GetAllSolutions(Double target, List<Integer> nums, Set<String> ops, int rbp) {
        return Sub(target, nums, ops, rbp);
    }

    /**
     * @param target  the remaining value of target
     * @param nums  the remaining available List of numbers (allow duplicated)
     * @param ops  the remaining available Set of arithmetic operators
     * @param rbp  the remaining available number of Round Bracket Pair
     * @return
     */
    private Set<String> Sub(Double target, List<Integer> nums, Set<String> ops, int rbp) {
        Set<String> setOfExprs = new HashSet<String>();

        // terminate condition
        if (nums.isEmpty() || rbp < 0)
            return setOfExprs;

        // single num
        for (Integer num : nums) {
            if (target == num.doubleValue()) {
                setOfExprs.add(num.toString());
                return setOfExprs;
            }
        }
        if(ops.isEmpty()) return setOfExprs;

        // Divide and Conquer
        // Traverse all possible sub-problems
        for (Integer num : nums) {
            List<Integer> remainingNums = new ArrayList<Integer>(nums);
            remainingNums.remove(num);

            for (String op : ops) {
                Set<String> remainingOps = new HashSet<String>(ops);
                remainingOps.remove(op);

                switch (op) {
                    case "+":
                        Set<String> setOfSubExprs = Sub(target - num, remainingNums, remainingOps, rbp);
                        for (String subExpr : setOfSubExprs) {
                            String expr = num.toString() + "+" + subExpr;
                            setOfExprs.add(expr);
                        }
                        break;
                    case "-":
                        setOfSubExprs = Sub(num - target, remainingNums, remainingOps, rbp);
                        for (String subExpr : setOfSubExprs) {
                            String expr = num.toString() + "-" + subExpr;
                            setOfExprs.add(expr);
                        }

                        setOfSubExprs = Sub(target + num, remainingNums, remainingOps, rbp);
                        for (String subExpr : setOfSubExprs) {
                            String expr = subExpr + "-" + num.toString();
                            setOfExprs.add(expr);
                        }
                        break;
                    case "*":
                        if (0 != num) {
                            Set<String> sameLevelOps = new HashSet<String>(remainingOps);
                            sameLevelOps.remove("+");
                            sameLevelOps.remove("-");
                            setOfSubExprs = Sub(target / num, remainingNums, sameLevelOps, rbp);
                            for (String subExpr : setOfSubExprs) {
                                String expr = num.toString() + "*" + subExpr;
                                setOfExprs.add(expr);
                            }

                            setOfSubExprs = Sub(target / num, remainingNums, remainingOps, rbp - 1);
                            for (String subExpr : setOfSubExprs) {
                                String expr = num.toString() + "*" + "(" + subExpr + ")";
                                setOfExprs.add(expr);
                            }
                        }
                        break;
                    case "/":
                        if (0 != num) {
                            if (0 != target) {
                                Set<String> sameLevelOps = new HashSet<String>(remainingOps);
                                sameLevelOps.remove("+");
                                sameLevelOps.remove("-");
                                setOfSubExprs = Sub(num / target, remainingNums, sameLevelOps, rbp);
                                for (String subExpr : setOfSubExprs) {
                                    String expr = num.toString() + "/" + subExpr;
                                    setOfExprs.add(expr);
                                }

                                setOfSubExprs = Sub(num / target, remainingNums, remainingOps, rbp - 1);
                                for (String subExpr : setOfSubExprs) {
                                    String expr = num.toString() + "/" + "(" + subExpr + ")";
                                    setOfExprs.add(expr);
                                }
                            }

                            Set<String> sameLevelOps = new HashSet<String>(remainingOps);
                            sameLevelOps.remove("+");
                            sameLevelOps.remove("-");
                            setOfSubExprs = Sub(num * target, remainingNums, sameLevelOps, rbp);
                            for (String subExpr : setOfSubExprs) {
                                String expr = subExpr + "/" + num.toString();
                                setOfExprs.add(expr);
                            }
                            setOfSubExprs = Sub(num * target, remainingNums, remainingOps, rbp - 1);
                            for (String subExpr : setOfSubExprs) {
                                String expr = "(" + subExpr + ")" + "/" +  num.toString();
                                setOfExprs.add(expr);
                            }
                        }
                        break;
                    default:
                }
            }
        }

        return setOfExprs;
    }

}
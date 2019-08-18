package leetcode.q65_valid_number;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解题思路：
 *  使用 Determinative FSM (Finite-state machine) 或 正则表达式来过滤输入字符串：
 *
 *  1）使用fsm的方法：
 *  第一步：划分出所有输入的可能性，每一种可能作为transition table的一列
 *  第二步：从其实状态开始画图s0并填表，每一种输入对应某个新状态或者已有的状态，判断的标准是新生成的状态之后接受输入参数的行为是否属于某个已经画出来的状态。比如此例中的s5，就包含以下三种情况："-4.", ".22", "22."
 *  第三步：所有不符合标准的转移状态标识为－1，所有到自身的状态标为当前状态。完成表以后，记录在二维数组中。
 *  第四步：初始状态变量state为0，循环读取字符串并根据转移表赋值新状态；出现－1立即break掉loop
 *  第五步：将所有可能的中止状态选出，并返回true，其他的状态皆为false
 *
 *  2）正则表达式：
 *  "[+-]?((\\d+\\.?\\d*)|(\\d*\\.?\\d+))(e[+-]?\\d+)?"
 *
 *  3）类似题： q8 atoi
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    public boolean isNumber(String s) {

        int[][] transitionTable = new int[][]{
                {1, 2, 3, -1, -1}, //Q0: START
                {-1, 4, 3, -1, -1}, //Q1: SIGN
                {-1, 2, 5, 6, -1}, //Q2: NUM    => finish state
                {-1, 5, -1, -1, -1}, //Q3: DOT
                {-1, 4, 5, 6, -1}, //Q4: SIGN + NUM     => finish state
                {-1, 5, -1, 6, -1}, //Q5: {NUM, DOT}    => finish state
                {7, 8, -1, -1, -1}, //Q6: NUM + E
                {-1, 8, -1, -1, -1}, //Q7: NUM + E + SIGN
                {-1, 8, -1, -1, -1} //Q8: NUM + E + NUM     => finish state
        };

        String str = s.trim();
        int state = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '-' || c == '+') {
                state = transitionTable[state][0];
            } else if (Character.isDigit(c)) {
                state = transitionTable[state][1];
            } else if (c == '.') {
                state = transitionTable[state][2];
            } else if (c == 'e') {
                state = transitionTable[state][3];
            } else
                state = transitionTable[state][4];

            if (state == -1) break; //todo: 注意别忘记一旦出现－1，直接break
        }

        if (state == 2 || state == 4 || state == 5 || state == 8) return true;
        else return false;
    }
}

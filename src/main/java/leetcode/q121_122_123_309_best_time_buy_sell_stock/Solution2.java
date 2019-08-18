package leetcode.q121_122_123_309_best_time_buy_sell_stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Best Time to Buy and Sell Stock II: 可以多次交易，但是不允许同一时间下持有超过一个交易数
 * 解题思路：
 *      dp的想法：dp[n]表示第i天所能获得的最大收益，那么：
 *      dp[n] = Max { dp[n - i] + A[n-1] - A[n-i] } 对于所有的 i ~ [1, n]
 *      这样定义是为了使得 dp可以被划归：即第n天所能得到的最大获利，是包含以下可能的最大值：
 *      1） 所有在第n天不出售的最大值，即从 dp[1] 一直到 dp[n]： 这对应dp转移方程的 i = n 的情况;
 *      2) 所有在第n天卖出的可能性，与之相对的之前买入的那一天可以取值从1到n-1，这种情况当然总计所得要包含从第1天到买入前一天的最大获利，相加作为这种情况的每一个结果; 对应转移方程中 i = 1 ~ (n-1) 的情况
 */
class Solution2 {
    private static Logger LOG = LoggerFactory.getLogger(Solution2.class);

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int len = prices.length;
        int[] dp = new int[len + 1]; //dp[i] 表示 第i天 所能累积的最大收获, i ~ [1, n], 初始值： dp[0]==dp[1]==0 即第0天和第一天的最大收获皆为0
        dp[0] = dp[1] = 0;

        for (int n = 2; n <= len; n++) {
            for (int i = 1; i <= n; i++) {
                dp[n] = Math.max(dp[n], dp[n - i] + prices[n - 1] - prices[n - i]);
            }
        }

        return dp[len];
    }
}

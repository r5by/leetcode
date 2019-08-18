package leetcode.q121_122_123_309_best_time_buy_sell_stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Best Time to Buy and Sell Stock with Cooldown
 * 解题思路：
 * 因为第i天的状态与第i-2天是否卖出有关，所以定义两个状态：hold[i] 表示第i天持有股票时最大的收益； unhold[i]表示当天卖出股票的情况下的收益；那么状态转移方程为：
 * hold[i] = max {
 *      hold[i-1],  //保持之前的持有股票状态（保持持股状态）
 *      unhold[i-2] - prices[i-1] //两天前买了，昨天休息，今天才买入（重新进入持股状态）；
 * }
 * unhold[i] = max {
 *     hold[i-1] + prices[i-1], //直到昨天一直持有股票, 今天卖掉；
 *     unhold[i-1] //或者是直到昨天都没有持有股票，今天没得买，继续保持；
 * }
 * 初始值：
 * hold[1] = -price[0];
 * unhold[1] = 0;
 */
class Solution4 {
    private static Logger LOG = LoggerFactory.getLogger(Solution4.class);

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int len = prices.length;

        int[] hold = new int[len + 1];
        int[] unhold = new int[len + 1];

        //init
        hold[1] = -prices[0];
        unhold[1] = 0;

        for (int i = 2; i <= len; i++) {
            hold[i] = Math.max(hold[i - 1], unhold[i - 2] - prices[i - 1]);
            unhold[i] = Math.max(hold[i - 1] + prices[i - 1], unhold[i - 1]);
        }

        return unhold[len];
    }
}

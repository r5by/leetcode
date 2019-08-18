package leetcode.q121_122_123_309_best_time_buy_sell_stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Best Time to Buy and Sell Stock I
 * 解题思路：
 *  1）第一遍从后往前遍历，拿出当前天之后的最大出售价格
 *  2）第二次从前往后遍历，更新差价最大的利润
 */
class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);
    /**
     * Example:
     * Input: [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     *              Not 7-1 = 6, as selling price needs to be larger than buying price.
     *
     *  Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2)
            return 0;

        int[] maxPrices = new int[prices.length];
        int max = -1;
        for(int j = prices.length - 1; j >= 0; j--) {
            int price = prices[j];
            max = Math.max(max, price);
            maxPrices[j] = max;
        }

        int maxProfit = 0;
        for(int i = 0; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, maxPrices[i] - prices[i]);
        }

        return maxProfit;
    }
}

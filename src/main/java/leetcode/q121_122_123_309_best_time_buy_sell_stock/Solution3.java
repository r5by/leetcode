package leetcode.q121_122_123_309_best_time_buy_sell_stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Best Time to Buy and Sell Stock III => 扩展到允许k次交易
 * 解题思路：
 *      dp[n][k]    ：day n 天完成至多k次交易可以获得的最大收益
 *      = max{
 *          dp[n-1][k],         ：day n 不做交易的情况
 *          price[n] - price[i] + dp[i][k-1]  (i ~ [0, n-1])    ：day i 买进， day n 卖出 的情况
 *      }
 *
 *  知识点：
 *  1）关于二维dp的初始化： 这里面的状态转移方程只与前一列和前一栏有关，因此就是初始化 dp[0][cols] 和 dp[rows][0];
 *  2) 将k加一栏 k=0,是为了跟问题的描述简单对应：即交易的次数(为0也可以简单地赋值0）
 */
class Solution3 {
    private static Logger LOG = LoggerFactory.getLogger(Solution3.class);

    /* 原题（两次交易）答案*/
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) return 0;

//        return maxProfit(prices, 2);
        return maxProfitTwoTrades(prices);
    }

    /* todo: 针对只允许最多两次交易的优化！ */
    private int maxProfitTwoTrades(int[] prices) {
        return 0;
    }


    /* todo: 以下是允许k次交易的dp方法解题 */
    /**
     * 交易k次或少于k次最大收益
     * @param prices
     * @param K
     * @return
     */
    public int maxProfit(int[] prices, int K) {
        int len = prices.length;

        int[][] dpMaxProfit = new int[len][K + 1]; //dp[n][k] 表示截止到第n天， 刚好交易k次所得最大收益

        //populate results
        for (int k = 1; k <= K; k++) {
            for (int n = 1; n <= len - 1; n++) {
                int maxSoFar = 0;

                //1）计算假如之前从 day 0 ～ n－1 某一天 j 完成了 k －1 次交易， 计算从j之后某一天买进然后在 day n 卖出，将获得的最大收益
                //注意： 这里面隐含一个逻辑， 即如果上次的最优解是在day j卖出，那么该等式依然可以成立，因为这等价于从买进日一直到 day n 才卖出 （即减少了一次交易, 不会超过k次交易限制）
                for (int j = 0; j <= n -1; j++) {
                    maxSoFar = Math.max(maxSoFar,  prices[n] - prices[j]+ dpMaxProfit[j][k - 1]);
                }

                //2） 将上式结果于 day n 不做交易的情况对比
                dpMaxProfit[n][k] = Math.max(maxSoFar, dpMaxProfit[n - 1][k]);
//                LOG.debug("dp [" + n + "," + k +"] = " + dpMaxProfit[n][k] + "\n" );
            }
        }

        //return
        return dpMaxProfit[len-1][K];
    }

    //TODO: O(n^2) solution, too slow, think about DP
//    public int maxProfit(int[] prices) {
//        if(prices == null || prices.length < 2) return 0;
//        int len = prices.length;
//
//        int maxProfit = maxProfitInRange(prices, 0, len - 1); //max-profit to trade once!
//
//        if(len < 4)
//            return maxProfit;
//        else {
//            int maxProfit2 = 0;
//            for (int p = 2; p <= len - 2; p++) {
//                maxProfit2 = Math.max(maxProfit2, maxProfitInRange(prices, 0, p - 1) + maxProfitInRange(prices, p, len - 1));
//            }
//
//            return Math.max(maxProfit, maxProfit2);
//        }
//    }
//
//    /**
//     * MaxProfit for 1 time purchase in range [start, end] inclusive
//     * @param prices
//     * @param start
//     * @param end
//     * @return
//     */
//    private int maxProfitInRange(final int[] prices, final int start, final int end) {
//        Map<Integer, Integer> maxPAfter = new HashMap<>();
//
//        int curMax = -1;
//        for (int i = end; i >= start; i--) {
//            curMax = Math.max(curMax, prices[i]);
//            maxPAfter.put(i, curMax);
//        }
//
//        int maxProfit = 0;
//        for (int j = start; j <= end; j++) {
//            maxProfit = Math.max(maxProfit, maxPAfter.get(j) - prices[j]);
//        }
//
//        return maxProfit;
//    }
}

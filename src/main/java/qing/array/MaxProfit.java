package qing.array;

/**
 * description: MaxProfit
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 示例 1:
 *
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * 注意:
 *
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 * @author yq
 * @date 2020/8/7.
 */
public class MaxProfit {

    /**
     * 解答错误
     * @param prices
     * @param fee
     * @return
     */
    @Deprecated
    public static int solution(int[] prices, int fee) {

        /*最低价格的买入，利润大于0卖出，重复上一步*/
        int profit = 0;
        int lowPrice = prices[0];
        int diff;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < lowPrice) {
                lowPrice = prices[i];
                continue;
            }
            if((diff = prices[i] - lowPrice - fee) > 0) {
                profit += diff;
                if(i == prices.length - 1) {
                    break;
                }
                lowPrice = prices[i + 1];
            }
        }
        return profit;
    }

    /**
     * 动态规划
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solution/dong-tai-gui-hua-by-liweiwei1419-6/
     * @param prices
     * @param fee
     * @return
     */
    public static int solution2(int[] prices, int fee) {
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 状态方程
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }




    public static void main(String[] args) {
        // [1,3,7,5,10,3]
        // 3
        int[] prices = new int[]{1,3,7,5,10,3};
        int free = 3;
        System.out.println(solution2(prices, free));

    }
}
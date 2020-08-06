package qing.array;

import java.util.ArrayList;

/**
 * description: FindMinFibonacciNumbers
 * 给你数字 k ，请你返回和为 k 的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。
 * 斐波那契数字定义为：
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 ， 其中 n > 2 。
 * 数据保证对于给定的 k ，一定能找到可行解。
 *
 * 示例 1：
 * 输入：k = 7
 * 输出：2
 * 解释：斐波那契数字为：1，1，2，3，5，8，13，……
 * 对于 k = 7 ，我们可以得到 2 + 5 = 7 。
 * 示例 2：
 * 输入：k = 10
 * 输出：2
 * 解释：对于 k = 10 ，我们可以得到 2 + 8 = 10 。
 * 示例 3：
 * 输入：k = 19
 * 输出：3
 * 解释：对于 k = 19 ，我们可以得到 1 + 5 + 13 = 19 。
 * 提示：
 * 1 <= k <= 10^9
 * @author yq
 * @date 2020/8/6.
 */
public class FindMinFibonacciNumbers {

    /**
     * 想太多，逻辑不够清晰
     * @param k
     * @return
     */
    public static int solution(int k) {

        if(k == 0) {
            return 0;
        }

        if(k == 1) {
            return 1;
        }
        /*
        根据k获取对应的斐波那契数字组int[n] fibonacci（fibonacci[n-1] <= k），
        求diff = k - fibonacci[n-1], 计数器加1， 根据diff二分查找最近的数，重复求差值， 如果diff==0,  返回计数器
         */

        // 获取斐波那契数组
        ArrayList<Integer> fibArr = new ArrayList<>();
        fibArr.add(1);
        fibArr.add(1);
        int index = 1;
        while (fibArr.get(index) < k) {
            int temp = fibArr.get(index) + fibArr.get(index - 1);
            if(temp == k) {
                return 1;
            }
            if(temp > k) {
                break;
            }
            fibArr.add(temp);
            index++;
        }

        // 二分查找
        int diff = k - fibArr.get(index);
        int count = 1;

        while (true) {
            int i = 0;
            while (i <= index) {
                int m = (i + index) / 2;
                if(fibArr.get(m) == diff) {
                    return ++count;
                }else if(fibArr.get(m) < diff && fibArr.get(m + 1) > diff) {
                    diff -= fibArr.get(m);
                    count++;
                    index = m;
                    break;
                } else if (fibArr.get(m) > diff && fibArr.get(m - 1) <= diff){
                    diff -= fibArr.get(m - 1);
                    count++;
                    index = m - 1;
                    break;
                } else if (fibArr.get(m) < diff){
                    i = m;
                } else {
                    index = m;
                }
            }
            if(diff == 0) {
                return count;
            }
        }

    }

    /**
     * 网上解法
     * @param k
     * @return
     */
    public static int solution2(int k) {
        int count = 0;
        while(k > 0){
            int n = getNearestFibo(k);
            k -= n;
            count++;
        }
        return count;
    }
    static int getNearestFibo(int k){
        if(k == 1) {
            return 1;
        }
        if(k == 2) {
            return 2;
        }
        int a = 1 , b = 2;
        while(b <= k){
            int t = b;
            b = a+b;
            a = t;
        }
        return a;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {

            System.out.println(solution(i));
        }
    }

}

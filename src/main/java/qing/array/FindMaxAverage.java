package qing.array;

/**
 * description: 子数组最大平均数 I
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 * 示例 1:
 * 输入: [1,12,-5,-6,50,3], k = 4
 * 输出: 12.75
 * 解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * 注意:
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 * @author qingshui
 * @Email qingshuidx@qq.com
 * @date 2020/8/16.
 */

public class FindMaxAverage {

    /**
     * 滑动窗口
     * @param nums
     * @param k
     * @return
     */
    public static double solution(int[] nums, int k) {
        // 窗口值
        int maxNum = 0;
        for (int i = 0; i < k; i++) {
            maxNum += nums[i];
        }

        int temp = maxNum;
        for(int i = k; i < nums.length; i++) {
            temp = temp - nums[i - k] + nums[i];
            if(temp > maxNum) {
                maxNum = temp;
            }
        }
        return (double)maxNum / k;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,12,-5,-6,50,3};
        int k = 4;
        System.out.println(solution(nums, k));
    }
}

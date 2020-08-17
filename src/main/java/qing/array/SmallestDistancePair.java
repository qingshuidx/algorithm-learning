package qing.array;

import java.util.Arrays;

/**
 * description: SmallestDistancePair
 * 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 * 示例 1:
 * 输入：
 * nums = [1,3,1]
 * k = 1
 * 输出：0
 * 解释：
 * 所有数对如下：
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * 因此第 1 个最小距离的数对是 (1,1)，它们之间的距离为 0。
 * 提示:
 * 2 <= len(nums) <= 10000.
 * 0 <= nums[i] < 1000000.
 * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 * @author yq
 * @date 2020/8/17.
 */
public class SmallestDistancePair {

    /**
     * 桶排序
     * @param nums
     * @param k
     * @return
     */
    public static int solution(int[] nums, int k) {
        // 桶
        int[] temp = new int[1000000];
        for(int i = 0; i < nums.length; i++) {
            for(int j = i+1; j < nums.length; j++) {
                int m = Math.abs(nums[i] - nums[j]);
                temp[m]++;
            }
        }
        for(int i = 0; i < temp.length; i++) {
            if((k = k - temp[i]) <= 0) {
                return i;
            }
        }
        return 0;
    }


    /**
     * 二分查找 + 双指针
     * @param nums
     * @param k
     * @return
     */
    public static int solution2(int[] nums, int k) {

        Arrays.sort(nums);

        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0];
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            int count = 0, left = 0;
            for (int right = 0; right < nums.length; ++right) {
                while (nums[right] - nums[left] > mi) {
                    left++;
                }
                count += right - left;
            }
            //count = number of pairs with distance <= mi
            if (count >= k) {
                hi = mi;
            }
            else {
                lo = mi + 1;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,1};
//        nums[0]++;
        int k = 1;
        System.out.println(solution2(nums, k));
    }
}

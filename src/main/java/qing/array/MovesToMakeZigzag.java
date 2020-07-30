package qing.array;

/**
 * description: MovesToMakeZigzag
 * 给你一个整数数组 nums，每次 操作 会从中选择一个元素并 将该元素的值减少 1。
 * 如果符合下列情况之一，则数组 A 就是 锯齿数组：
 * 每个偶数索引对应的元素都大于相邻的元素，即 A[0] > A[1] < A[2] > A[3] < A[4] > ...
 * 或者，每个奇数索引对应的元素都大于相邻的元素，即 A[0] < A[1] > A[2] < A[3] > A[4] < ...
 * 返回将数组 nums 转换为锯齿数组所需的最小操作次数。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：2
 * 解释：我们可以把 2 递减到 0，或把 3 递减到 1。
 * 示例 2：
 * 输入：nums = [9,6,1,6,2]
 * 输出：4
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 *
 * @author yq
 * @date 2020/7/30.
 */
public class MovesToMakeZigzag {

    static int solution(int[] nums) {
        if(nums.length < 2) {
            return 0;
        }
        return Math.min(numCount(nums, true), numCount(nums, false));
    }

    static int numCount(int[] nums, boolean isOdd) {
        int i = isOdd ? 0 : 1;
        int count = 0;
        int minNum;
        while (i < nums.length) {
            if(i == 0 && isOdd) {
                minNum = nums[i+1];
            } else if(i == nums.length -1) {
                minNum = nums[i-1];
            } else {
                minNum = Math.min(nums[i-1], nums[i+1]);
            }
            if (nums[i] >= minNum) {
                count += nums[i]- minNum + 1;
            }
            i += 2;
        }
        return count;
    }


    public static void main(String[] args) {
        // nums = [1,2,3]
        int[] nums = {1,2,3};
        // nums = [9,6,1,6,2]
        int[] nums2 = {9,6,1,6,2};

        int[] nums3 = {10,4,4,10,10,6,2,3};
//        System.out.println(solution(nums));
//        System.out.println(solution(nums2));
        System.out.println(solution(nums3));
    }
}

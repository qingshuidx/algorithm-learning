package qing.array;

/**
 * description: 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 *
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 *
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 *
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 * @author qingshui
 * @Email qingshuidx@qq.com
 * @date 2020/8/16.
 */

public class SearchInsert {

    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public static int solution(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        // 二分查找
        int i = 0,  j = nums.length - 1;

        if(nums[i] > target) {
            return 0;
        }

        if(nums[j] < target) {
            return j + 1;
        }


        while (i < j) {
            int m = (i + j) / 2;
            if(nums[m] > target) {
                j = m;
            } else if(nums[m] == target){
                return m;
            } else {
                i = m + 1;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,6};
        int target = 7;
        System.out.println(solution(nums, target));
    }

}

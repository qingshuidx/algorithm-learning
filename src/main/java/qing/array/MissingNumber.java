package qing.array;

/**
 * description: MissingNumber
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * 示例 1:
 * 输入: [0,1,3]
 * 输出: 2
 * 示例 2:
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 * 限制：
 * 1 <= 数组长度 <= 10000
 * @author yq
 * @date 2020/8/5.
 */
public class MissingNumber {

    /**
     * 迭代查找
     * @param nums
     * @return
     */
    public static int solution(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        for(int i = 0; i < nums.length; i++) {
            if(i != nums[i]) {
                return i;
            }
        }
        return nums.length;
    }

    /**
     * 迭代查询优化
     * @param nums
     * @return
     */
    public static int solution2(int[] nums) {
       if(nums.length == 0) {
           return 0;
       }
        int j;
        for(int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
            if((j = (nums.length - i)) != nums[nums.length - i - 1]) {
                return j;
            }

        }
        return nums.length;
    }

    /**
     * 二分查找
     * @param nums
     * @return
     */
    public static int solution3(int[] nums) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] == m) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }
        return i;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,3,4,5,6,7,9};
        System.out.println(solution3(nums));
    }
}

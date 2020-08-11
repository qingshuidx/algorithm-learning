package qing.array;

/**
 * description: FirstMissingPositive
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。

 * 示例 1:
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * 提示：
 *
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 * @author yq
 * @date 2020/8/11.
 */
public class FirstMissingPositive {

    /**
     * 没有考虑数字重复出现，解题失败
     * @param nums
     * @return
     */
    public static int solution(int[] nums) {
        if(nums.length < 1 || (nums.length == 1 && nums[0] != 1)) {
            return 1;
        }
        if(nums.length == 1) {
            return 2;
        }

        int maxNum = 0;
        int minNum = Integer.MAX_VALUE;
        int count = 0;
        for(int n : nums) {
            if(n < 1) {
                continue;
            }
            if(n > maxNum) {
                maxNum = n;
            }
            if(n < minNum) {
                minNum = n;
            }
            count++;
        }
        // 没有正整数，缺失最小的就是1
        if(maxNum == 0) {
            return 1;
        }
        if(maxNum == 1) {
            return 2;
        }
        // 数组正整数中最小的数大于1，缺失的最小数就是1
        if(minNum > 1) {
            return 1;
        }
        // 数组正整数中最小数为1，并且正整数连续分布，缺失最小的数为最大值加1
        if((maxNum - minNum + 1) == count) {
            return maxNum + 1;
        }
        // 数组正整数中最小数为1，正整数非连续分布，求缺失最小的数
        for(int i = 2; i <= count; i++) {
            //判断i是否存在nums中，不存在则为缺失最小正整数
            if(!check(nums, i)) {
                return i;
            }
        }

        return 1;
    }

    static boolean check(int[] nums, int n) {
        for(int m : nums) {
            if(m == n) {
                return true;
            }
        }
        return false;
    }


    /**
     * 最小正整数一定在[1, n+1]区间中
     * 将数组中正整数放到对应位置，如果找到第一个下标+1与对应数值不符，
     * 则下标+1为最小正整数
     * @return
     */
    public static int solution2(int[] nums) {
        if(nums.length < 1 || (nums.length == 1 && nums[0] != 1)) {
            return 1;
        }
        if(nums.length == 1) {
            return 2;
        }
        for(int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i]-1] != nums[i]) {
                int temp = nums[nums[i]-1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }

        }
        for(int i = 0; i < nums.length; i++) {
            if((i + 1) != nums[i]) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    /**
     * 最小正整数一定在[1, n+1]区间中
     * 把存在的数打上标记，判断哪些没有标记的
     * @param nums
     * @return
     */
    public static int solution3(int[] nums) {
        if(nums.length < 1 || (nums.length == 1 && nums[0] != 1)) {
            return 1;
        }
        if(nums.length == 1) {
            return 2;
        }
        // 把越界的数标记为nums.length + 1
        for(int i = 0; i < nums.length ; i++) {
            if(nums[i] <= 0) {
                nums[i] = nums.length + 1;
            }
        }
        // 把在区间[1, n+1]中的数打上标记
        for (int n : nums) {
            int temp = Math.abs(n);
            if(temp > nums.length) {
                continue;
            }
            nums[temp - 1] = -Math.abs(nums[temp - 1]);
        }
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,0};
        System.out.println(solution3(nums));
    }
}

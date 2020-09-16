package qing.array;

import jdk.nashorn.internal.ir.IfNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * description: ThreeSum
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 * 请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 * @author yq
 * @date 2020/9/16.
 */
public class ThreeSum {

    /**
     * 排序+双指针
     * 标签：数组遍历
     * 首先对数组进行排序，排序后固定一个数 nums[i]，再使用左右指针指向 nums[i]后面的两端，数字分别为 nums[L]
     * 和 nums[R]，计算三个数的和 sum 判断是否满足为 0，满足则添加进结果集
     * 如果 nums[i]大于 0，则三数之和必然无法等于 0，结束循环
     * 如果 nums[i] == nums[i-1]，则说明该数字重复，会导致结果重复，所以应该跳过
     * 当 sum == 0 时，nums[L] == nums[L+1] 则会导致结果重复，应该跳过，L++
     * 当 sum == 0 时，nums[R] == nums[R-1] 则会导致结果重复，应该跳过，R--
     * 时间复杂度：O(n^2)
     * n 为数组长度
     * @param nums
     * @return
     */
    public static List<List<Integer>> solution(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if(len < 3) {
            return result;
        }
        // 排序
        Arrays.sort(nums);
        // 数组值重复
        if(nums[0] == nums[len-1]) {
            if(nums[0] != 0) {
                return result;
            } else {
                result.add(Arrays.asList(nums[0], nums[1], nums[2]));
            }
        }
        int a = nums[len-1];
        for (int i = 0; i < len-2; i++) {
            if(nums[i] > 0) {
                break;
            }
            if(nums[i] == a) {
                continue;
            }
            a = nums[i];
            int bi = i+1;
            int ci = len-1;
            while (bi < ci) {
                int sum = a + nums[bi] + nums[ci];
                if(sum == 0) {
                    result.add(Arrays.asList(a, nums[bi], nums[ci]));
                    while (nums[bi] == nums[bi+1]) {
                        bi++;
                    }
                    while (nums[ci] == nums[ci-1]) {
                        ci--;
                    }
                    bi++;
                    ci--;
                } else if(sum > 0) {
                    ci--;
                } else {
                    bi++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(solution(nums));
    }
}

package qing.array;

import java.util.Arrays;

/**
 * description: MissingTwo
 * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
 * 以任意顺序返回这两个数字均可。
 * 示例 1:
 * 输入: [1]
 * 输出: [2,3]
 * 示例 2:
 * 输入: [2,3]
 * 输出: [1,4]
 * 提示：
 * nums.length <= 30000
 *
 * @author yq
 * @date 2020/8/18.
 */
public class MissingTwo {

    /**
     * 解题通过，代码逻辑不清晰
     * @param nums
     * @return
     */
    public static int[] solution(int[] nums) {
        int[] expand = new int[2];
        int len = nums.length;
        for(int i = 0; i < len; i++) {
            while (nums[i] != (i + 1) && nums[i] != 0) {
                if(nums[i] > len) {
                    expand[nums[i] - len - 1] = nums[i];
                    nums[i] = 0;
                    break;
                }
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        int[] result = new int[2];
        if (expand[0] == 0 && expand[1] == 0) {
            result[0] = len + 1;
            result[1] = len + 2;
            return result;
        }

        if (expand[0] != 0 && expand[1] == 0) {
            result[1] = len + 2;
        } else {
            result[1] = len + 1;
        }
        int count = 0;
        for(int i = 0; i < len; i++) {
            if(nums[i] == 0) {
                result[count] = i + 1;
                count++;
            }
        }
        return result;
    }

    /**
     * 异或求解
     * @param nums
     * @return
     */
    public static int[] solution2(int[] nums) {
        int[] out = new int[2];
        int res = 0;
        int len = nums.length;
        for(int i = 0; i < len; i++){
            res ^= nums[i];
        }
        for(int i = 1; i<= len + 2; i++){
            res ^= i;
        }
        int m = res & (-res);
        int resy = 0;
        for (int i = 0; i < len; i++) {
            if ((m & nums[i]) == 1) {
                resy ^= nums[i];
            }
        }
        for (int i = 1; i <= len + 2; i++) {
            if ((m & i) == 1) {
                resy ^= i;
            }
        }
        out[0] = resy;
        out[1] = resy ^ res;
        return out;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        System.out.println(Arrays.toString(solution2(nums)));
//        System.out.println(1^3^1^2^3^4);
//        System.out.println(2^4);
//        System.out.println(6&1);
//        System.out.println(6^2);
    }
}

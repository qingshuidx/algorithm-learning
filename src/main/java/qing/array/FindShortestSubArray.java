package qing.array;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * description: FindShortestSubArray
 * 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * 示例 1:
 * 输入: [1, 2, 2, 3, 1]
 * 输出: 2
 * 解释:
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 * 示例 2:
 * 输入: [1,2,2,3,1,4,2]
 * 输出: 6
 * 注意:
 * nums.length 在1到50,000区间范围内。
 * nums[i] 是一个在0到49,999范围内的整数。
 * @author yq
 * @date 2020/8/19.
 */
public class FindShortestSubArray {

    /**
     * 解题失败，没有考虑有相同度的不同数
     * @param nums
     * @return
     */
    public static int solution(int[] nums) {

        int len = nums.length;
        if(len == 1) {
            return 1;
        }
        int[] barrel = new int[50000];
        for(int i = 0; i < len; i++) {
            barrel[nums[i]]++;
        }

        int target = 0;
        int maxLen = 0;
        for(int i = 0; i < barrel.length; i++) {
            if(barrel[i] > maxLen) {
                maxLen = barrel[i];
                target = i;
            }
        }

        int left = 0;
        for(int i = 0; i < len; i++) {
            if(nums[i] == target) {
                left = i;
                break;
            }
        }
        int right = 0;
        int count = 0;
        for(int i = left; i < len; i++) {
            if(nums[i] == target) {
                right = i;
                count++;
            }
            if(count == maxLen) {
                break;
            }
        }
        return right - left + 1;
    }

    public static int solution2(int[] nums) {
        int len = nums.length;
        if(len == 1) {
            return 1;
        }
        Map<Integer, Integer> left = new HashMap<>(),
                right = new HashMap<>(),
                count = new HashMap<>();
        for(int i = 0; i < len; i++) {
            left.putIfAbsent(nums[i], i);
            right.put(nums[i], i);
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }
        Integer maxCount = Collections.max(count.values());
        if(maxCount.equals(1)) {
            return 1;
        }
        int minLen = len;
        for(Integer x : count.keySet()) {
            if(maxCount.equals(count.get(x))) {
                int temp = right.get(x) - left.get(x) + 1;
                if(minLen > temp) {
                    minLen = temp;
                }
            }
        }
        return minLen;
    }


    public static int solution3(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        //找出数组中的最大值和最小值
        for(int i = 0 ; i < nums.length; i++){
            if(min > nums[i]){
                min = nums[i];
            }
            if(max < nums[i]){
                max = nums[i];
            }
        }
        int[] count = new int[max - min + 1];
        int maxCount = Integer.MIN_VALUE;
        for(int i = 0 ; i < nums.length; i++){
            maxCount = Math.max(maxCount, ++count[nums[i] - min]);
        }
        if(maxCount == 1){
            return 1;
        }
        int ans = nums.length;
        for(int i = 0 ; i < count.length ;i ++){
            if(count[i] == maxCount){
                int l = 0, r = nums.length - 1,target = i + min;
                while(l < r && nums[l] != target){
                    l++;
                }
                while(l < r && nums[r] != target){
                    r--;
                }
                ans = Math.min(ans,r - l + 1);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5};
        System.out.println(solution2(nums));
    }
}

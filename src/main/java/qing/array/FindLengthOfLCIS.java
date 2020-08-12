package qing.array;

/**
 * description: FindLengthOfLCIS
 * 给定一个未经排序的整数数组，找到最长且连续的的递增序列，并返回该序列的长度。
 * 示例 1:
 * 输入: [1,3,5,4,7]
 * 输出: 3
 * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
 * 示例 2:
 * 输入: [2,2,2,2,2]
 * 输出: 1
 * 解释: 最长连续递增序列是 [2], 长度为1。
 * 注意：数组长度不会超过10000。
 * @author yq
 * @date 2020/8/12.
 */
public class FindLengthOfLCIS {

    /**
     *
     * @param nums
     * @return
     */
    public static int solution(int[] nums){
        if(nums.length < 2) {
            return nums.length;
        }
        // 前一个最长子序的长度
        int len = 0;
        // 当前子序长度
        int tempLen = 1;
        for(int i = 1; i < nums.length; i++) {
            // 子序的边界
            if(nums[i] <= nums[i-1]) {
                // 获取最长子序
                len = len > tempLen ? len : tempLen;
                // 当前子序长度置为1
                tempLen = 1;
                continue;
            }
            tempLen++;
        }
        return len > tempLen ? len : tempLen;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,2,2,2};
        System.out.println(solution(nums));
    }
}

package qing.array;

/**
 * description: NumOfSubArrays
 *
 * 给你一个整数数组 arr 和两个整数 k 和 threshold 。
 * 请你返回长度为 k 且平均值大于等于 threshold 的子数组数目。
 *
 * 示例 1：
 * 输入：arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
 * 输出：3
 * 解释：子数组 [2,5,5],[5,5,5] 和 [5,5,8] 的平均值分别为 4，5 和 6 。其他长度为 3 的子数组的平均值都小于 4 （threshold 的值)。
 *
 * 示例 2：
 * 输入：arr = [1,1,1,1,1], k = 1, threshold = 0
 * 输出：5
 *
 * 示例 3：
 * 输入：arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
 * 输出：6
 * 解释：前 6 个长度为 3 的子数组平均值都大于 5 。注意平均值不是整数。
 *
 * 示例 4：
 * 输入：arr = [7,7,7,7,7,7,7], k = 7, threshold = 7
 * 输出：1
 *
 * 示例 5：
 * 输入：arr = [4,4,4,4], k = 4, threshold = 1
 * 输出：1
 *  
 * 提示：
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^4
 * 1 <= k <= arr.length
 * 0 <= threshold <= 10^4
 *
 * @author yq
 * @date 2020/7/28.
 */
public class NumOfSubArrays {

    /**
     * 暴力破解
     * @param arr a
     * @param k k
     * @param threshold t
     * @return i
     */
    public int solution(int[] arr, int k, int threshold) {

        // 前指针
        int si = 0;
        // 后指针
        int ei = k - 1;

        int count = 0;
        while (ei <= arr.length - 1) {
            int sum = 0;
            int avg;
            for (int i = si; i <= ei; i++) {
                sum += arr[i];
            }
            avg = sum / k;
            if (avg >= threshold) {
                count++;
            }
            si++;
            ei++;
        }

        return count;
    }

    /**
     * 滑动窗口
     * @param arr a
     * @param k k
     * @param threshold t
     * @return i
     */
    public int solution2(int[] arr, int k, int threshold) {
        int count = 0;
        // 将比较平均值转变成比较总和
        int value = k * threshold;
        int temp = 0;
        //求出窗口值
        for(int i = 0; i < k; i++) {
            temp += arr[i];
        }

        if(temp >= value) {
            count++;
        }
        for(int i = k; i < arr.length; i++) {
            temp = temp - arr[i - k] + arr[i];
            if(temp >= value) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
        int[] arr1 = {2,2,2,2,5,5,5,8};
        //arr = [1,1,1,1,1], k = 1, threshold = 0
        int[] arr2 = {1,1,1,1,1};
        // arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
        int[] arr3 = {11,13,17,23,29,31,7,5,2,3};
        // arr = [7,7,7,7,7,7,7], k = 7, threshold = 7
        int[] arr4 = {7,7,7,7,7,7,7};
        // arr = [4,4,4,4], k = 4, threshold = 1
        int[] arr5 = {4,4,4,4};
        NumOfSubArrays num = new NumOfSubArrays();
        System.out.println(num.solution2(arr1, 3, 4));
        System.out.println(num.solution2(arr2, 1, 0));
        System.out.println(num.solution2(arr3, 3, 5));
        System.out.println(num.solution2(arr4, 7, 7));
        System.out.println(num.solution2(arr5, 4, 1));

        System.out.println("-----------------------------------------------");

        System.out.println(num.solution(arr1, 3, 4));
        System.out.println(num.solution(arr2, 1, 0));
        System.out.println(num.solution(arr3, 3, 5));
        System.out.println(num.solution(arr4, 7, 7));
        System.out.println(num.solution(arr5, 4, 1));
    }
}

package qing.array;

/**
 * description: FindMedianSortedArrays
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 * @author yq
 * @date 2020/8/26.
 */
public class FindMedianSortedArrays {

    /**
     * 双指针归并排序
     * @param nums1
     * @param nums2
     * @return
     */
    public static double solution(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        if(len1 == 0) {
            if(len2 % 2 == 0) {
                return (nums2[len2 / 2] + nums2[len2 / 2 - 1])/2.0;
            }
            return nums2[len2 / 2];
        }

        if(len2 == 0) {
            if(len1 % 2 == 0) {
                return (nums1[len1 / 2] + nums1[len1 / 2 - 1])/2.0;
            }
            return nums1[len1 / 2];
        }

        // [1,2] [3,4]
        if(len1 == len2 && nums1[len1 - 1] <= nums2[0]) {
            return (nums1[len1 - 1] + nums2[0])/2.0;
        }
        // [3,4] [1,2]
        if(len1 == len2 && nums2[len2 - 1] <= nums1[0]) {
            return (nums2[len2 - 1] + nums1[0])/2.0;
        }

        int len = len1 + len2;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < len1 && (bStart >= len2 || nums1[aStart] < nums2[bStart])) {
                right = nums1[aStart++];
            } else {
                right = nums2[bStart++];
            }
        }
        if ((len & 1) == 0) {
            return (left + right) / 2.0;
        }
        else {
            return right;
        }
    }

    /**
     * 分割数组，二分查找
     * @param nums1
     * @param nums2
     * @return
     */
    public static double solution2(int[] nums1, int[] nums2) {
        // 保证nums1长度小于nums2
        if(nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        // 分割线所有元素个数：(len1 + len2 + 1) / 2 , 防止溢出 len1 + (len2 - len1) / 2
        int leftNumsLen = len1 + (len2 - len1 + 1) / 2;

        // 在最短数组nums1中找到合适分割线,区间为[0, len1]
        // 分割线满足 nums1[i-1] <= nums2[j] && nums1[i] >= nums2[j-1]
        int left = 0;
        int right = len1;

        while (left < right) {
            // 防止溢出
            int i = left + (right - left + 1) / 2;
            int j = leftNumsLen - i;
            if (nums1[i - 1] > nums2[j]) {
                // 区间[left, i - 1]
                right = i - 1;
            } else {
                // 区间[i, right]
                left = i;
            }
        }

        int i = left;
        int j = leftNumsLen - i;
        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int nums1LeftMin = i == len1 ? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = j == len2 ? Integer.MAX_VALUE : nums2[j];
        if((len2 + len1) % 2 != 0) {
            return Math.max(nums1LeftMax, nums2LeftMax);
        }
        return (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1LeftMin, nums2RightMin)) / 2.0;
    }

    /**
     * 普通二分查找
     * @param nums1
     * @param nums2
     * @return
     */
    public static double solution3(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public static int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */
        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{1,3, 7, 9, 10};
        int[] nums2 = new int[]{2, 5, 8, 11, 12};
        System.out.println(solution3(nums1, nums2));

//        System.out.println((nums1.length + nums2.length) / 2);
//        System.out.println(nums1.length + (nums2.length - nums1.length)/2);
//        System.out.println(nums2.length + (nums1.length - nums2.length)/2);
//        System.out.println((nums1.length - nums2.length)/2);
    }
}

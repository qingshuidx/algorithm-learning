package qing.sort;

import java.util.Arrays;

/**
 * description: NonComparativeSort
 * 非基于元素比较的排序-时间复杂度为到线性复杂度
 * 1. 基数排序
 * 2. 计数排序
 * 3. 桶排序
 * @author yq
 * @date 2020/9/1.
 */
public class NonComparativeSort {

    /**
     * 基数排序（Radix sort）
     * 是一种非比较型整数排序算法，
     * 其原理是将整数按位数切割成不同的数字，
     * 然后按每个位数分别比较。
     * 由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，所以基数排序也不是只能使用于整数。
     * 平均时间复杂度	最好情况	最坏情况	空间复杂度
     * O(d*(n+r))	O(d*(n+r))	O(d*(n+r))	O(n+r)
     * @param nums
     */
    public static void radixSort(int[] nums) {
        int len = nums.length;
        if(len < 2) {
            return;
        }
        // 取最大数，求最大位数
        int max = 0;
        for(int i : nums) {
            max = Math.max(max, i);
        }
        int maxDigit = String.valueOf(max).length();

        // 赋值桶中元素-1
        int[][] barrel = new int[10][len];
        for(int i = 0; i < barrel.length; i++) {
            for(int j = 0; j < barrel[i].length; j++) {
                barrel[i][j] = -1;
            }
        }
        int[] indexs = new int[10];
        int digit = 10;
        for(int i = 0; i < maxDigit; i++) {
            // 入桶
            for(int n : nums) {
                int index = n % digit / (digit / 10);
                barrel[index][indexs[index]] = n;
                indexs[index]++;
            }

            // 出桶
            int k = 0;
            for(int m = 0; m < barrel.length; m++) {
                // 索引复原
                indexs[m] = 0;
                for (int n = 0; n < barrel[m].length; n++) {
                    if(barrel[m][n] < 0) {
                        continue;
                    }
                    nums[k++] = barrel[m][n];
                    barrel[m][n] = -1;
                }
            }
            digit *= 10;
        }
    }

    /**
     * 计数排序（Counting sort）
     * 计数排序的核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。
     * 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。
     * 计数排序(Counting sort)是一种稳定的排序算法。计数排序使用一个额外的数组C，
     * 其中第i个元素是待排序数组A中值等于i的元素的个数。
     * 然后根据数组C来将A中的元素排到正确的位置。它只能对整数进行排序。
     * 最佳情况：T(n) = O(n+k)  最差情况：T(n) = O(n+k)  平均情况：T(n) = O(n+k)
     */
    public static void countingSort(int[] nums) {
        int len = nums.length;
        if(len < 2) {
            return;
        }

        int max = 0;
        for(int n : nums) {
            max = Math.max(max, n);
        }

        // 计数数组空间,初始化值-1
        int[] countArr = new int[max + 1];
        for(int i = 0; i <= max; i++) {
            countArr[i] = -1;
        }
        for(int i = 0; i < len; i++) {
            countArr[nums[i]]++;
        }
        int k = 0;
        for(int i = 0; i <= max; i++) {
            while (countArr[i] >= 0) {
                nums[k++] = i;
                countArr[i]--;
            }
        }
    }

    // todo 桶排序

    public static void main(String[] args) {
        int[] nums = new int[]{11,13,17,23,29,31,7,5,2,3,0, 30, 0, 1, 999, 3,32, 22211, 333,75};
        radixSort(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{11,13,17,23,29,31,7,5,2,3,0, 30, 0, 1, 999, 3,32, 22211, 333,75};
        countingSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}

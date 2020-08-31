package qing.sort;

import java.util.Arrays;

/**
 * description: QuickSort
 * 快速排序
 * 1.挖坑法
 * 2.左右指针法
 * @author yq
 * @date 2020/8/31.
 */
public class QuickSort {

    public static void sort(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        quickSort2(nums, left, right);
    }

    /**
     * 挖坑法
     * 开始填坑时要填已经被挖的坑
     * @param nums
     * @param left
     * @param right
     */
    private static void quickSort(int[] nums, int left, int right) {
        if(nums.length < 2) {
            return;
        }

        if(left > right) {
            return;
        }

        int l = left;
        int r = right;
        // 挖的第一个坑为当前序列的第一位，所以也要先填这个坑
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            nums[right] = nums[left];
        }

        nums[left] = pivot;
        quickSort(nums, l, left - 1);
        quickSort(nums, left + 1, r);
    }

    /**
     * 左右指针法
     * 序列交换完成后，需要更换基数，避免出现首位永远是第一个基数
     * @param nums
     * @param left
     * @param right
     */
    private static void quickSort2(int[] nums, int left, int right) {
        if(nums.length < 2) {
            return;
        }

        if(left > right) {
            return;
        }

        int l = left;
        int r = right;
        // 选取基数
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] > pivot) {
                right--;
            }
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            if (left < right) {
                swap(nums, left, right);
            }
        }
        swap(nums, l, left);
        quickSort2(nums, l, left - 1);
        quickSort2(nums, left + 1, r);
    }

    /**
     * 交换
     * @param nums
     * @param i
     * @param j
     */
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{4, 3, 7, 5, 10, 9, 1, 6, 8, 2, 4, 4};
//        int[] nums = new int[]{2, 3, 4, 4, 4, 1};
        int[] nums = new int[]{10, 9, 1, 6, 8, 2, 4, 4};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}

package qing.array;

/**
 * description: HeightChecker
 * 学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。
 * 请你返回能让所有学生以 非递减 高度排列的最小必要移动人数。
 * 注意，当一组学生被选中时，他们之间可以以任何可能的方式重新排序，而未被选中的学生应该保持不动。
 * 示例：
 * 输入：heights = []
 * 输出：3
 * 解释：
 * 当前数组：[1,1,4,2,1,3]
 * 目标数组：[1,1,1,2,3,4]
 * 在下标 2 处（从 0 开始计数）出现 4 vs 1 ，所以我们必须移动这名学生。
 * 在下标 4 处（从 0 开始计数）出现 1 vs 3 ，所以我们必须移动这名学生。
 * 在下标 5 处（从 0 开始计数）出现 3 vs 4 ，所以我们必须移动这名学生。
 * 示例 2：
 * 输入：heights = [5,1,2,3,4]
 * 输出：5
 * 示例 3：
 * 输入：heights = [1,2,3,4,5]
 * 输出：0
 * 提示：
 * 1 <= heights.length <= 100
 * 1 <= heights[i] <= 100
 * @author yq
 * @date 2020/8/3.
 */
public class HeightChecker {

    public static int solution(int[] heights) {
        int len = heights.length;
        int count = 0;
        if(len < 2) {
            return count;
        }
        // 暴力破解
        // 把当前数组排序获取目前数组，迭代比较并统计不同出
        int[] sortArr = heights.clone();
        //排序
        bubbleSort(sortArr);
        for(int i = 0; i < len; i++) {
            if(sortArr[i] == heights[i]) {
                continue;
            }
            count++;
        }
        return count;
    }

    /**
     * 冒泡排序
     */
    static void bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 1; j < arr.length - i; j++) {
                if(arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static int solution2(int[] heights) {
        // 值的范围是1 <= heights[i] <= 100，因此需要1,2,3,...,99,100，共101个桶
        int[] arr = new int[101];
        // 遍历数组heights，计算每个桶中有多少个元素，也就是数组heights中有多少个1，多少个2，。。。，多少个100
        // 将这101个桶中的元素，一个一个桶地取出来，元素就是有序的
        for (int height : heights) {
            arr[height]++;
        }

        int count = 0;
        for (int i = 1, j = 0; i < arr.length; i++) {
            // arr[i]，i就是桶中存放的元素的值，arr[i]是元素的个数
            // arr[i]-- 就是每次取出一个，一直取到没有元素，成为空桶
            while (arr[i]-- > 0) {
                // 从桶中取出元素时，元素的排列顺序就是非递减的，然后与heights中的元素比较，如果不同，计算器就加1
                if (heights[j++] != i) {
                    count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        int[] arr = {1,1,4,2,1,3};
        solution(arr);
        System.out.println(solution2(arr));
    }
}

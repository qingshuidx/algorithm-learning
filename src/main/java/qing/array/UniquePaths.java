package qing.array;

import java.util.Arrays;

/**
 * description: UniquePaths
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 * 示例 1:
 *
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10 ^ 9
 * @author yq
 * @date 2020/8/4.
 */
public class UniquePaths {

    /**
     * 动态规划
     * @param m
     * @param n
     * @return
     */
    public static int solution(int m, int n) {
        if(m == 0 || n == 0) {
            return 0;
        }
        if(m == 1 || n == 1) {
            return 1;
        }
        // 用来记录起始点到任意点的路径
        int[] temp = new int[m];
        Arrays.fill(temp, 1);
        for (int i = 2; i <= n; i++) {
            int swap = 0;
            for (int j = 0; j < m; j++) {
                swap += temp[j];
                temp[j] = swap;
            }
        }
        return temp[m - 1];
    }


    public static void main(String[] args) {
        System.out.println(solution(3,2));
    }
}

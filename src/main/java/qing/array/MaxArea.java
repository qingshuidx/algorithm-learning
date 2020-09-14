package qing.array;

/**
 * description: MaxArea
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * @author yq
 * @date 2020/9/14.
 */
public class MaxArea {

    /**
     * 暴力求解
     * @param height
     * @return
     */
    public static int solution(int[] height) {
        int len = height.length;
        if (len == 2) {
            return height[0] < height[1] ? height[0] : height[1];
        }

        int maxArea = 0;

        for (int i = 0; i < len; i++) {
            for(int j = i + 1; j < len; j++) {
                if (height[i] > height[j]) {
                    maxArea = Math.max(height[j]*(j-i), maxArea);
                } else {
                    maxArea = Math.max(height[i]*(j-i), maxArea);
                }
            }
        }

        return maxArea;
    }

    /**
     * 双指针
     * @param height
     * @return
     */
    public static int solution2(int[] height) {
        int len = height.length;
        if (len == 2) {
            return height[0] < height[1] ? height[0] : height[1];
        }
        int left = 0;
        int right = len-1;
        int maxArea = 0;
        while (left < right) {
            if (height[left] > height[right]) {
                maxArea = Math.max(height[right]*(right-left), maxArea);
                right--;
            } else {
                maxArea = Math.max(height[left]*(right-left), maxArea);
                left++;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(solution2(height));
    }
}

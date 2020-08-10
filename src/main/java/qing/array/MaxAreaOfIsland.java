package qing.array;

/**
 * description: MaxAreaOfIsland
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 *  
 * 示例 1:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 *
 * 示例 2:
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 *
 * @author yq
 * @date 2020/8/10.
 */
public class MaxAreaOfIsland {

    public static int solution(int[][] grid) {

        if(grid == null || grid.length == 0) {
            return 0;
        }

        int sum = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                sum = Math.max(sum, dfs(grid, i,j,0));
            }
        }

        return sum;
    }

    static int dfs(int[][] grid, int i, int j, int sum) {

        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length ||  grid[i][j] == 0) {
            return sum;
        }

        if(grid[i][j] == 1) {
            sum++;
            // 标记已扫描
            grid[i][j] = 2;
        }

        sum = dfs(grid,i-1, j, sum);
        sum = dfs(grid,i, j-1, sum);
        sum = dfs(grid,i+1, j, sum);
        sum = dfs(grid,i, j+1, sum);
        return sum;
    }

    /**
     * 精简版
     * @param grid
     * @param i
     * @param j
     * @return
     */
    static int dfs(int[][] grid, int i, int j) {

        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length ||  grid[i][j] == 0) {
            return 0;
        }
        int sum = 1;
        // 标记已扫描
        grid[i][j] = 2;
        sum += dfs(grid,i-1, j);
        sum += dfs(grid,i, j-1);
        sum += dfs(grid,i+1, j);
        sum += dfs(grid,i, j+1);
        return sum;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                new int[]{0,0,1,0,0,0,0,1,0,0,0,0,0},
                new int[]{0,0,0,0,0,0,0,1,1,1,0,0,0},
                new int[]{0,1,1,0,1,0,0,0,0,0,0,0,0},
                new int[]{0,1,0,0,1,1,0,0,1,0,1,0,0},
                new int[]{0,1,0,0,1,1,0,0,1,1,1,0,0},
                new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0},
                new int[]{0,0,0,0,0,0,0,1,1,1,0,0,0},
                new int[]{0,0,0,0,0,0,0,1,1,0,0,0,0},
        };
        System.out.println(dfs(grid, 3, 8));
        System.out.println(solution(grid));
    }
}

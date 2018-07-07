package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BombEnemy
 * Creator : duqiang
 * Date : Jan, 2018
 * Description : 361. Bomb Enemy
 */
public class BombEnemy {
    /**
     * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero),
     * return the maximum enemies you can kill using one bomb.
     The bomb kills all the enemies in the same row and column from the planted point 
     until it hits the wall
     since the wall is too strong to be destroyed.
     Note that you can only put the bomb at an empty cell.

     Example:
     For the given grid

     0 E 0 0
     E 0 W E
     0 E 0 0

     return 3. (Placing a bomb at (1,1) kills 3 enemies)

     for(i)
        for(j) {
            j
     }

     1,遍历
     2,扫描当前行 ：i 不变 j rowCount
     3,扫描当前列 ：j 不变 i （colCount[]）

     time : O(m * n)
     space : O(n)

     * @param grid
     * @return
     */
    // 原因是由我们的遍历顺序决定的，我们是逐行遍历的，在每行的开头就统计了该行的敌人总数，
    // 所以再该行遍历没必要用数组，但是每次移动时就会换到不同的列，
    // 我们总不能没换个列就重新统计一遍吧，所以就在第一行时一起统计了存到数组中供后来使用。
    // 有了水平方向和竖直方向敌人的个数，那么如果当前位置是0，表示可以放炸弹，
    // 我们更新结果res即可
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int rowCount = 0;
        int[] colCount = new int[n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || grid[i][j - 1] == 'W') {// this is optimization, which means we need to calculate
                    rowCount = 0;
                    for (int k = j; k < n && grid[i][k] != 'W'; k++) {
                        rowCount += grid[i][k] == 'E' ? 1 : 0;
                    }
                }
                if (i == 0 || grid[i - 1][j] == 'W') {// same as previous
                    colCount[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'W'; k++) {
                        colCount[j] += grid[k][j] == 'E' ? 1 : 0;
                    }
                }
                if (grid[i][j] == '0') {
                    res = Math.max(res, colCount[j] + rowCount);
                }
            }
        }
        return res;
    }
}

package hatecode._0001_0999;
import java.util.*;
public class _764LargestPlusSign {
/*
764. Largest Plus Sign
这道题给了我们一个数字N，表示一个NxN的二位数字，初始化均为1，
又给了一个mines数组，里面是一些坐标，表示数组中这些位置都为0，
然后让我们找最大的加型符号。
所谓的加型符号是有数字1组成的一个十字型的加号，
题目中也给出了长度分别为1，2，3的加型符号的样子。
Example 1:

Input: N = 5, mines = [[4, 2]]
Output: 2
Explanation:
11111
11111
11111
11111
11011
In the above grid, the largest plus sign can only be order 2.  One of them is marked in bold.
Example 2:

Input: N = 2, mines = []
Output: 1
Explanation:
There is no plus sign of order 2, but there is of order 1.
Example 3:

Input: N = 1, mines = [[0, 0]]
Output: 0
*/
//the technical of the solution is to scan the row, 
    //left->right, then right ->left, we always use min() to get the smaller one, 
    //so technically we can get count how many 1's and 
    
    //this is too tricky, that several scan and it is jut coincidence the result
public int orderOfLargestPlusSign(int N, int[][] mines) {
    int[][] grid = new int[N][N];

    for (int i = 0; i < N; i++) {
        Arrays.fill(grid[i], N);
    }

    for (int[] m : mines) {
        grid[m[0]][m[1]] = 0;
    }
        
    for (int i = 0; i < N; i++) {
    /*
    for (int j = 0, k = N - 1, l = 0, r = 0, u = 0, d = 0; j < N; j++, k--) {
            grid[i][j] = Math.min(grid[i][j], l = (grid[i][j] == 0 ? 0 : l + 1));  // left direction
            grid[i][k] = Math.min(grid[i][k], r = (grid[i][k] == 0 ? 0 : r + 1));  // right direction
            grid[j][i] = Math.min(grid[j][i], u = (grid[j][i] == 0 ? 0 : u + 1));  // up direction
            grid[k][i] = Math.min(grid[k][i], d = (grid[k][i] == 0 ? 0 : d + 1));  // down direction
        }
    */          //g[i][j] was initialized N, so we should always get 1, 2, if they are contious
                for (int j=0, l=0; j < N; j++) {
                    // j is a column index, iterate from left to right
                    // every time check how far left it can reach.
                    // if grid[i][j] is 0, l needs to start over from 0 again, otherwise increment
                    grid[i][j] = Math.min(grid[i][j], l = (grid[i][j] == 0 ? 0 : l + 1));
                }

                for (int k = N-1, r=0; k >= 0; k--) {
                    // k is a column index, iterate from right to left
                    // every time check how far right it can reach.
                    // if grid[i][k] is 0, r needs to start over from 0 again, otherwise increment
                    grid[i][k] = Math.min(grid[i][k], r = (grid[i][k] == 0 ? 0 : r + 1));
                }

                for (int j = 0, u=0; j < N; j++) {
                    // j is a row index, iterate from top to bottom
                    // every time check how far up it can reach.
                    // if grid[j][i] is 0, u needs to start over from 0 again, otherwise increment
                    grid[j][i] = Math.min(grid[j][i], u = (grid[j][i] == 0 ? 0 : u + 1));
                }

                for (int k = N-1, d=0; k >= 0; k--) {
                    // k is a row index, iterate from bottom to top
                    // every time check how far down it can reach.
                    // if grid[k][i] is 0, d needs to start over from 0 again, otherwise increment
                    grid[k][i] = Math.min(grid[k][i], d = (grid[k][i] == 0 ? 0 : d + 1));
                }
                
                // after four loops each time taking Math.min over the grid value itself
                // all grid values will eventually take the min of the 4 direcitons.
            }
/*
grid= 
[1, 1, 1, 1, 1]
[1, 2, 2, 2, 1]
[1, 2, 2, 2, 1]
[1, 2, 1, 2, 1]
[1, 1, 0, 1, 1]
 */
    int res = 0;
        
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            res = Math.max(res, grid[i][j]);
        }
    }
        
    return res;
}
}
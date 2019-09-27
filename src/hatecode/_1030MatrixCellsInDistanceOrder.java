package hatecode;

import java.util.*;
public class _1030MatrixCellsInDistanceOrder {
/*
1030. Matrix Cells in Distance Order
We are given a matrix with R rows and C columns has cells with integer coordinates (r, c), where 0 <= r < R and 0 <= c < C.

Additionally, we are given a cell in that matrix with coordinates (r0, c0).

Return the coordinates of all cells in the matrix, sorted by their distance from (r0, c0) from smallest distance to largest distance.  Here, the distance between two cells (r1, c1) and (r2, c2) is the Manhattan distance, |r1 - r2| + |c1 - c2|.  (You may return the answer in any order that satisfies this condition.)

 

Example 1:

Input: R = 1, C = 2, r0 = 0, c0 = 0
Output: [[0,0],[0,1]]
*/
    //thinking process: O(n)/O(n)
    
    //given R and C for a 2D array, and (r0, c0) as core coordination, 
    //return a 2D array,sort by manhant distance
    
    //if we sort BF, we can have a O(nlgn) how can we get a O(n)
    //
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        boolean[][] visited = new boolean[R][C];
        int[][] result = new int[R * C][2];
        int i = 0;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[] { r0, c0 });
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];

            if (r < 0 || r >= R || c < 0 || c >= C) {
                continue;
            }
            if (visited[r][c]) {
                continue;
            }

            result[i] = cell;
            i++;
            visited[r][c] = true;

            queue.offer(new int[] { r, c - 1 });
            queue.offer(new int[] { r, c + 1 });
            queue.offer(new int[] { r - 1, c });
            queue.offer(new int[] { r + 1, c });
        }
        return result;
    }
}
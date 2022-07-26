package hatecode._0001_0999;
import java.util.*;
public class _827MakingALargeIsland {
/*
 * 827. Making A Large Island
In a 2D grid of 0s and 1s, we change at most one 0 to a 1.

After, what is the size of the largest island? (An island is a 4-directionally 
connected group of 1s).

Example 1:

Input: [[1, 0], [0, 1]]
Output: 3
*/
    
    //KeyNote: this should be 1 because suppose [[0, 0]], all-zero matrix, you can 
    //turn 1 to block
    int maxArea = 1;
    //O(MN)/O(N)
    public int largestIsland(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        UnionFind uf = new UnionFind(rows * cols);
        int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }

                // update the area of curr gird
                uf.area[i * cols + j] = 1;
                // we only need to union the left and up gird of current position here
                // the other two girds' area are all 0, cuz we haven't visited them.
                for (int k = 0; k < 2; k++) {
                    int preRow = i + dirs[k][0];
                    int preCol = j + dirs[k][1];
                    if (preRow < 0 || preCol < 0 || grid[preRow][preCol] == 0) {
                        continue;
                    }
                    uf.union(i * cols + j, preRow * cols + preCol);
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // skip the 1s gird
                if (grid[i][j] == 1) {
                    continue;
                }

                int curr = i * cols + j;
                uf.area[curr] = 1;
                // Use set to record neighbors' father to avoid adding area repeatedly
                Set<Integer> neighs = new HashSet<>();
                for (int[] dir : dirs) {
                    int nextRow = i + dir[0];
                    int nextCol = j + dir[1];
                    if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols || grid[nextRow][nextCol] == 0) {
                        continue;
                    }
                    int neighFather = uf.compressFind(nextRow * cols + nextCol);
                    if (!neighs.contains(neighFather)) {
                        neighs.add(neighFather);
                        uf.area[curr] += uf.area[neighFather];
                    }
                }
                maxArea = Math.max(maxArea, uf.area[curr]);
            }
        }

        return maxArea;
    }

    class UnionFind {
        int[] parent;
        int[] area;

        UnionFind(int n) {
            parent = new int[n];
            area = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int compressFind(int index) {
            if (index != parent[index]) {
                parent[index] = compressFind(parent[index]);
            }
            return parent[index];
        }

        // Union areaB to areaA
        // You can also use area value to make balance, but we ignore it for concise code
        public void union(int a, int b) {
            int aFather = compressFind(a);
            int bFather = compressFind(b);
            if (aFather != bFather) {
                parent[bFather] = aFather;
                area[aFather] += area[bFather];
                //KeyNote: suppose [[1,1],[1,1]], after union, we do not need to change the cell value,
                //so it will be the value
                maxArea = Math.max(maxArea, area[aFather]);
            }
        }
    }


}
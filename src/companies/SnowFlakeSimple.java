package companies;

import java.util.Arrays;

public class SnowFlakeSimple {

    /*
     * 
     * 1M*N# grid, each cell could be: a light source 'L', a rock 'R', or empty. Every light source emits light to four directions until blocked by a rock. Assume the luminence from one light source is 1. Return the max luminence on a cell.
L--
-L-
-R-
---
The luminence on each cell is:
121
211
100
100
     */

     public static int[][] calc(char[][] A) {
        if(A == null || A.length < 1 || A[0].length < 1) return new int[][]{};

        int m = A.length, n = A[0].length;
        int[][] dist = new int[m][n];

        for(int i = 0; i<m; i++) {
            for(int j = 0; j< n; j++) {
                if(A[i][j] =='L') {
                    dist[i][j] = 1;
                    boolean[][] visited = new boolean[m][n];
                    helper(A, i, j, dist, visited);
                }
            }
        }

        return dist;
     }


     private static void helper(char[][] A, int i, int j, int[][] dist, boolean[][] visited) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        int m = A.length, n = A[0].length;
        for(int[] d: dirs) {
            int ni = i +d[0];
            int nj = j + d[1];

            while(ni >=0 && ni < m && nj >=0 && nj < n && (A[ni][nj] =='-' || A[ni][nj] =='L') && !visited[ni][nj]) {
                visited[ni][nj] = true;
                dist[ni][nj] += 1;
                ni += d[0];
                nj += d[1];
            }
        }
     }


     public static void main(String[] args) {
        char[][] A = {{'L', '-', '-'}, {'-', 'L', '-'}, {'-', 'R', '-'}, {'-', '-','-'}};
        System.out.println(Arrays.deepToString(calc(A)));
     }
    
}

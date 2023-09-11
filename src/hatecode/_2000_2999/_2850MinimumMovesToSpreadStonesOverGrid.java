package hatecode._2000_2999;

import java.util.*;

public class _2850MinimumMovesToSpreadStonesOverGrid {
    
    /*
    2850. Minimum Moves to Spread Stones Over Grid

      You are given a 0-indexed 2D integer matrix grid of size 3 * 3, representing the number of stones in each cell. The grid contains exactly 9 stones, and there can be multiple stones in a single cell.

In one move, you can move a single stone from its current cell to any other cell if the two cells share a side.

Return the minimum number of moves required to place one stone in each cell.

 

Example 1:


Input: grid = [[1,1,0],[1,1,1],[1,2,1]]
Output: 3
    */

    /*
      interview friendly
     * thinking process: O()
     * 
     * the problem is to say: given 3x3 2D matrix,  each element is one integer, you goal is to make 9 1 in this matrix, 
     * for example 
     * 1 1 0
     * 1 1 1
     * 1 2 1
     * 
     * 
     * --->   
     * 
     * 1 1 1
     * 1 1 1
     * 1 1 1
     * 
     * you will need to move 1 from A[2][1] to A[0][2], you will need at least 3 moves, so return 3
     * 
     * the problem is essentially to say: multiple sources to multiple targets
     * 
     * here you will a list of cells which are bigger than 1 while there are some list of 0 
     * you can substrate 1 from above list and move to any places with 0. 
     * 
     * each distance is the move you will spent from one source to one target, and return the min sum of distances
     * 
     * we cannot use solution of "LC 317, shortest path from all buildings"
     */
    public static int minimumMoves(int[][] A) {
        if(A == null || A.length < 1 || A[0].length < 1) return 0;
        
        int m = A.length, n = A[0].length;
        
        Set<String> visited = new HashSet<>();
        Queue<int[][]> q = new LinkedList<>();
        q.offer(A);
        visited.add(getKey(A));
        int move = 0;
        int[][] dirs = {{-1, 0}, {0,-1}, {1, 0}, {0,1}};
        while(!q.isEmpty()) {
            int size = q.size();
            
            while(size-- > 0) {
                int[][] cur = q.poll();
                if(getKey(cur).equals("111111111")) return move;
                for(int i = 0; i<m; i++) {
                    for(int j = 0; j<n; j++) {
                        if(cur[i][j] > 1) {
                            for(int[] d: dirs) {
                                int ni = i + d[0];
                                int nj = j + d[1];
                                if(ni >=0 && ni < m && nj >=0 && nj < n) {
                                    int[][] next = copy(cur);
                                    next[i][j]--;
                                    next[ni][nj]++;
                                    String nk = getKey(next);
                                    if(!visited.contains(nk)) {
                                       q.offer(next);
                                       visited.add(nk);
                                    }
                                }
                            }
                        }
                    }
                } 
            }
            move++;
        }
        
        return -1;
    }
    
    private static String getKey(int[][] A) {
        StringBuilder  sb = new StringBuilder();
        for(int[] list: A) {
            for(int a : list) {
                sb.append(a);
            }
        }
        
        return sb.toString();
    }
    
    
    private static int[][] copy(int[][] A) {
        int[][] res = new int[A.length][A[0].length];
        for(int i = 0; i<A.length; i++) {
            for(int j = 0; j<A[0].length; j++) {
                res[i][j] = A[i][j];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[][] A = {{1,1,0}, {1,1,1}, {1,2,1}};
        System.out.println(minimumMoves(A));
    }
}
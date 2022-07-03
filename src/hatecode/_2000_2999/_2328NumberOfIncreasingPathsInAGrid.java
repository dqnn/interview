package hatecode._2000_2999;

import java.util.*;
public class _2328NumberOfIncreasingPathsInAGrid {
    
    
/*
2328. Number of Increasing Paths in a Grid
You are given an m x n integer matrix grid, where you can move from a cell to any adjacent cell in all 4 directions.

Return the number of strictly increasing paths in the grid such that you can start from any cell and end at any cell. Since the answer may be very large, return it modulo 109 + 7.

Two paths are considered different if they do not have exactly the same sequence of visited cells.

 

Example 1:


Input: grid = [[1,1],[3,4]]
Output: 8
Explanation: The strictly increasing paths are:
- Paths with length 1: [1], [1], [3], [4].
- Paths with length 2: [1 -> 3], [1 -> 4], [3 -> 4].
- Paths with length 3: [1 -> 3 -> 4].
The total number of paths is 4 + 3 + 1 = 8.
*/
    
    //thinking process: O(mn)/O(mn) becuase we have memo, each i j 
    //should visit once
    
    //memo[i][j] means starting from i, j how many strictly increasing 
    //sequence path exists
    long[][] memo;
    long MOD = 1_000_000_007;
    public int countPaths(int[][] A) {
        int r = A.length, c= A[0].length;
        long res = 0;
        
        memo = new long[r][c];
        for(int i = 0; i< r;i++) {
            for(int j= 0; j<c;j++) {
                res = (res + helper(A, i, j)) %MOD;
            }
        }
        
        return (int)res;
    }
    
    
    private long helper(int[][] A,int i, int j) {
        if (memo[i][j] > 0) return memo[i][j];
        
        
        
        int[][] dirs={{1,0}, {0, 1}, {-1,0}, {0,-1}};
        long sum = 1;
        for(int[] d: dirs) {
            int x = i + d[0];
            int y = j + d[1];
            if (x >= 0 && x < A.length && y >=0 && y < A[0].length && A[i][j] < A[x][y]) {
                sum = (sum + helper(A, x, y)) % MOD;
            }
        }
        return memo[i][j] = sum;
    }
    
    
    
    
    
    class Node{
        int x;
        int y;
        String path;
        public Node(int x, int y, String path) {
            this.x= x;
            this.y = y;
            this.path = path;
        }
    }

    
    public int countPaths_TLE(int[][] A) {
        
        int r = A.length, c= A[0].length;
        int res = 0;
        
        Queue<Node> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        for(int i = 0; i< r;i++) {
            for(int j= 0; j<c;j++) {
                Node temp  =  new Node(i, j, i + "," + j);
                q.offer(temp);
                visited.add(temp.path);
            }
        }
        
        int[][] dirs={{1,0}, {0, 1}, {-1,0}, {0,-1}};
        while(!q.isEmpty()) {
            int size = q.size();
            res += size;
            //System.out.println(res);
            while(size-- > 0) {
                Node cur = q.poll();
                int x = cur.x, y = cur.y;
                String path = cur.path;
                for(int[] d: dirs) {
                    int nx = x + d[0];
                    int ny = y + d[1];
                    String key = path + "-" + nx +"," +ny ;
                    //System.out.println(key);
                    if (nx >=0 && nx < r && ny >=0 && ny < c && A[x][y] < A[nx][ny] && !visited.contains(key)) {
                        q.offer(new Node(nx, ny, key));
                        visited.add(key);
                        //System.out.println(key);
                    } 
                }
            }
        }
                                        
       return res;
    }
}
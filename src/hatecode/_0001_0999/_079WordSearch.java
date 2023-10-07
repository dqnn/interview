package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WordSearch
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 79. Word Search
 */
public class _079WordSearch {
    /**
     * For example,
     Given board =

     [
     ['A','B','C','E'],
     ['S','F','C','S'],
     ['A','D','E','E']
     ]
     word = "ABCCED", -> returns true,
     word = "SEE", -> returns true,
     word = "ABCB", -> returns false.


     time : 不知道
     space : 不知道

     * @param board
     * @param word
     * @return
     */
//thinking process:
    
    // so the problem is to veirfy whether word is existed in this 2D array, the char only can adjacent 
    //which means the 4 around it
    // since we don't know which char would be the starting point, so we start from each of 
    //them, and have a backtracking point 
    //from this i, j, we need to figure out the exit condition, and when we visit
    //each cell, we need to mark it already visited. 
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean exist(char[][] board, int i, int j, String word, int start) {
        if (start >= word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        if (board[i][j] == word.charAt(start++)) {
            char c = board[i][j];
            board[i][j] = '#';
            boolean res = exist(board, i + 1, j, word, start) ||
                    exist(board, i - 1, j, word, start) ||
                    exist(board, i, j + 1, word, start) ||
                    exist(board, i, j - 1, word, start);
            board[i][j] = c;
            return res;
        }
        return false;
    }


    /*
     * thinking process: O(m^2n^2)/O(mn)
     * 
     * one key is that why we have to flip visited[i][j] is that because this node can be used later when searching, 
     * 
     * for example:
     * 
     * [["A","B","C","E"],
     *  ["S","F","E","S"],
     *  ["A","D","E","E"]]
      
     
       "ABCESEEEFS"  when S (1,3) meet E(1,2), it will mark E as visited, but we actually need it when search for SEEE
     */

    public boolean exist_Better(char[][] A, String s) {
        if(A == null || A.length < 1|| A[0].length < 1 ) return false;
        
        int m = A.length, n = A[0].length;
        
        for(int i = 0; i< m; i++) {
            for(int j = 0; j<n; j++) {
                    if(helper(A, i, j, s, 0, new boolean[m][n])) return true;
                }
        }
        
        return false;
    }
    
    
    private boolean helper(char[][] A, int i, int j, String s, int pos, boolean[][] visited) {
        if(pos == s.length()) return true;
        
        int m = A.length, n = A[0].length;
        if(i < 0 || i >=m || j <0 || j >=n || s.charAt(pos) != A[i][j] || visited[i][j]) return false;
        
        
        visited[i][j] = true;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        
        for(int[] d: dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            
            if(helper(A, ni, nj, s, pos+1, visited)) return true;
        }
        visited[i][j] = false;
        
        return false;
    }
}

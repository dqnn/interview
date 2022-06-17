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
}

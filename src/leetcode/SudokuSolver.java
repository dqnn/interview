package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SudokuSolver
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 37. Sudoku Solver
 */
public class SudokuSolver {

    // time : 不知道 space : 不知道

    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        solve(board);
    }

    public boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        //if we found the cell is valid for 
                        // this number or not used, we just use it
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            //
                            if (solve(board)) return true;
                            else board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            //already used c in some other cell, so must be false
            if (board[i][col] != '.' && board[i][col] == c) return false;
            //column
            if (board[row][i] != '.' && board[row][i] == c) return false;
            //bigger cell
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i / 3] != '.'
                    && board[3 * (row / 3) + i / 3][3 * (col / 3) + i / 3] == c) {
                return false;
            }
        }
        return true;
    }
}

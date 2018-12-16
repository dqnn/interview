package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SudokuSolver
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 37. Sudoku Solver
 */
public class SudokuSolver {
/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 
9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.

Note:

The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single 
unique solution.
The given board size is always 9x9.
 */
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
                            //try to fill next '.' with other number
                            //if not successful, then we change back
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
            //because row, col == '.'
            if (board[i][col] != '.' && board[i][col] == c) return false;
            //column
            if (board[row][i] != '.' && board[row][i] == c) return false;
            //bigger cell, so how to calc each element idx in cube，
            //thinking in this way: 9 x 9, 
            // the row will be 0, 1, 2, column will be 0, 1, 2 
            // so we want to evaluate row, col this row and col related to big cube
            //
            int boardx = (row / 3) * 3 + i/3;
            int boardy = (col / 3) * 3 + i % 3;
            if (board[boardx][boardy] != '.' && board[boardx][boardy] == c) {
                return false;
            }
        }
        return true;
    }
}

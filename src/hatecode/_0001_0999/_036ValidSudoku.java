package hatecode._0001_0999;

import java.util.HashSet;

/**
 Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according 
 to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

The Sudoku board could be partially filled, 
where empty cells are filled with the character '.'.

Example 1:

Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true
 */
public class _036ValidSudoku {


    // time : O(n^2) space : O(n)
    // thinking process:
    // the problem is to say i want to validate 3 rules on a 2D matrix, row, column and 3x3 cell 
    // only contains 1-9 digits, you only need validate the cell which contains digits
    
    
    //so for rule validating problems and this is 2D matrix, so we should consider backtracking immediately, 
    // in solution 1, we use 3 Set when visiting each column, 
    // we leverage set.add() to return false or true to determine whether set contains
    //this number or not. so you can see 3 if clause. 
    
    //also note how to calc the coordination of cubes, column index and row index 
    public boolean isValidSudoku1(char[][] A) {
        //row scan mode to 
        for (int i = 0; i < A.length; i++) {
            HashSet<Character> rows = new HashSet<>();
            HashSet<Character> cols = new HashSet<>();
            HashSet<Character> cube = new HashSet<>();
            for (int j = 0; j < A[0].length; j++) {
                //if row has duplicated number
                if (A[i][j] != '.' && !rows.add(A[i][j])) return false;
                // here we have a trick, stabilize i and loop on column as j. 
                if (A[j][i] != '.' && !cols.add(A[j][i])) return false;
                //cube index calc，and this is base, the big cell index in
                //9x9 matrix, this is the key of the problem
                int iBase = 3 * (i / 3);
                int jBase = 3 * (i % 3);

                if (A[iBase + j / 3][jBase + j % 3] != '.' && !cube.add(A[iBase + j / 3][jBase + j % 3]))
                    return false;
            }
        }
        return true;
    }
    //
    public boolean isValidSudoku2(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') continue;
                if (!valid(board, i, j)) return false;
            }
        }
        return true;
    }

    public boolean valid(char[][] board, int i, int j) {
        for (int row = 0; row < board.length; row++) {
            if (row == i) continue;
            if (board[row][j] == board[i][j]) return false;
        }
        for (int col = 0; col < board[0].length; col++) {
            if (col == i) continue;
            if (board[i][col] == board[i][j]) return false;
        }
        for (int row = (i / 3) * 3; row < (i / 3 + 1) * 3; row++) {
            for (int col = 
                    (j / 3) * 3; col < (j / 3 + 1) * 3; col++) {
                if (row == i && col == j) continue;
                if (board[row][col] == board[i][j]) return false;
            }
        }
        return true;
    }
}

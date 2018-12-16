package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : GameofLife
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 289. Game of Life
 */
public class GameofLife {

    /**
     * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
     * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) 
     * using the following four rules (taken from the above Wikipedia article):

     Any live cell with fewer than two(-infinite, 2) live neighbors dies, as if caused by under-population.
     Any live cell with two or three[2,3] live neighbors lives on to the next generation.
     Any live cell with more than three(3, infinite) live neighbors dies, as if by over-population..
     Any dead cell with exactly three live neighbors(= 3) becomes a live cell, as if by reproduction.
     Write a function to compute the next state (after one update) of the board given its current state.

     time : O(m * n)
     space : O(1)
     
     Example:

Input: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
Follow up:

Could you solve it in-place? Remember that the board needs to be updated at the same time: 
You cannot update some cells first and then use their updated values to update other cells.

In this question, we represent the board using a 2D array. In principle, the board is infinite, 
which would cause problems when the active area encroaches the border of the array. 
How would you address these problems?

     * @param board
     */
/*
 * [2nd bit, 1st bit] = [next state, current state]

- 00  dead (next) <- dead (current)
- 01  dead (next) <- live (current)  
- 10  live (next) <- dead (current)  
- 11  live (next) <- live (current) 

this question how to expand?
 */
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = countNeighbor(board, i, j);
                // so current state is  live 1, so next state will be 1 0r 0 
                if (board[i][j] == 1) {
                    if (count == 2 || count == 3) {
                        board[i][j] += 2; //from 01--> 11, next state still 1
                    }
                } else if (count == 3) {
                    board[i][j] += 2; // next state is 1, so it will be 10
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] >> 1;// move one bit so we can get next state
            }
        }
    }

    private int countNeighbor(int[][] board, int i, int j) {
        int count = 0;
        // this is how to visit the 8 neighbours. 
        // starting from i - 1 if bigger than 0, row += means we are row scan
        for (int row = Math.max(0, i - 1); row <= Math.min(i + 1, board.length - 1); row++) {
            // so we iterate from j - 1, but stop min(j + 1, and len) because we may already at last line
            for (int col = Math.max(0, j - 1); col <= Math.min(j + 1, board[0].length - 1); col++) {
                // we skip the element we want to set the value since we have to count on neighbours
                if ((row == i) && (col == j)) continue;
                // living ones, so we cannot use == 1 since we use two bits to record the live status
                if ((board[row][col] & 1) == 1) count++;
            }
        }
        return count;
    }

}

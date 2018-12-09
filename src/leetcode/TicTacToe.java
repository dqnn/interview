package leetcode;
class TicTacToe {
/*
348. Design Tic-Tac-Toe
Design a Tic-tac-toe game that is played between two players on a n x n grid.

You may assume the following rules:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Example:
Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
Follow up:
Could you do better than O(n2) per move() operation?
 */
//  O(1) solution here, we have two array to store each rwo and col number
    //for (1,1), (1,2), we will +2 on row[1], so we will check the 4 parameters 
    //to see whether they are same line
    int[] row;
    int[] col;
    int dial;
    int antiDial;
    public TicTacToe(int n) {
        this.row = new int[n];
        this.col = new int[n];
        this.dial = 0;
        this.antiDial = 0;
    }
    
    
    public int move(int r, int c, int player) {
        int len  = row.length;
        int toAdd = player == 1 ? 1: -1;
        //every same row will +1 here
        row[r] += toAdd;
        //every same column will + 1 here
        col[c] += toAdd;
        if (r == c) dial += toAdd;
        if (r + c == len - 1) antiDial +=toAdd;
        if ( Math.abs(row[r]) == len
           ||Math.abs(col[c]) == len
           ||Math.abs(dial) == len
           || Math.abs(antiDial) == len) {
            return player;
        }
        return 0;
    }
    
    
    //Navie solution
    /** Initialize your data structure here. */
    int[][] b;
    public void TicTacToe2(int n) {
        this.b = new int[n][n];
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move2(int row, int col, int player) {
        b[row][col] = player;
        return win(row, col, player);
    }
    
    private int win(int r, int c, int player) {
        boolean h = false;
        int i =0;
        for(i = 0; i< b.length;i++) {
            if (b[r][i] != player) {
                break;
            }
        }
        if (i == b.length) return player;
        boolean v = false;
        for(i = 0; i< b.length;i++) {
            if (b[i][c] != player) {
                break;
            }
        }
        if (i == b.length) return player;
        boolean d = false;
        for(i = 0; i< b.length;i++) {
            if (b[i][i] != player) {
                break;
            }
        }
        if (i == b.length) return player;
        boolean e = false;
        for(i = 0; i< b.length;i++) {
            if (b[b.length - 1 - i][i] != player) {
                break;
            }
        }
        if (i == b.length) return player;
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
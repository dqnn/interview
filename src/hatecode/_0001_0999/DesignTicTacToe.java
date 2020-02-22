package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : DesignTicTacToe
 * Creator : duqiang
 * Date : Jan, 2018
 * Description : 348. Design Tic-Tac-Toe
 */
public class DesignTicTacToe {
    /**
     * Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

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


     cols[1,-2,3]

     * @param n
     */
    //rows adn cols will record how many cards for one play on row[i]
    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int antiDiagonal;
    private int size;

    public DesignTicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        this.size = n;
    }

    //time : O(1)
    //interview friendly: 
    //we use player 1 as +1, player 2 as -1, on rows and cols, diagonal, and antiDiagonal
    
    //we do not check whether the cell was occupied before, this will leave to the play to decide. 
    public int move(int i, int j, int player) {
        int toAdd = player == 1 ? 1 : -1;

        rows[i] += toAdd;
        cols[j] += toAdd;
        if (i == j) {
            diagonal += toAdd;
        }
        if (i + j == (cols.length - 1)) {
            antiDiagonal += toAdd;
        }
        if (Math.abs(rows[i]) == size || Math.abs(cols[j]) == size
                || Math.abs(diagonal) == size || Math.abs(antiDiagonal) == size) {
            return player;
        }
        return 0;
    }
}
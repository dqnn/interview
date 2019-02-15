package hatecode;
public class ValidTicTacToeState {
/*
794. Valid Tic-Tac-Toe State
A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.

Here are the rules of Tic-Tac-Toe:

Players take turns placing characters into empty squares (" ").
The first player always places "X" characters, while the second player always places "O" characters.
"X" and "O" characters are always placed into empty squares, never filled ones.
The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.
Example 1:
Input: board = ["O  ", "   ", "   "]
Output: false
Explanation: The first player always plays "X".
*/
    //this problem is just to validate the board is valid or not, we do not need to say which will win, 
    // so every time, x move first, o second, so no matter which time point t0, we only have 2 cases
    //1 is first move done, xCount = oCount + 1
    //2 is sec move done, they should be same
    public boolean validTicTacToe(String[] board) {
        int xCount = 0, oCount = 0;
        for(String row : board) {
            for(char c: row.toCharArray()) {
                if (c == 'X') xCount ++;
                if (c == 'O') oCount++;
            }
        }
        //if they are not equals, they 
        if (oCount != xCount && oCount != xCount - 1) return false;
        
        // = or xCount- 1 = oCount
        //here means O's turn to move
        if (helper(board, 'X') && oCount != xCount - 1) return false;
        //here means X 's turn to move
        if (helper(board, 'O') && oCount != xCount) return false;
        
        return true;
    }
    
    public boolean helper(String[] B, char ch) {
        
        for (int i = 0; i < 3; ++i) {
            // every column 
            if (ch == B[0].charAt(i) && ch == B[1].charAt(i) && ch == B[2].charAt(i))
                return true;
            // every row
            if (ch == B[i].charAt(0) && ch == B[i].charAt(1) && ch == B[i].charAt(2))
                return true;
        }
        // dial
        if (ch == B[0].charAt(0) && ch == B[1].charAt(1) && ch == B[2].charAt(2))
            return true;
        //anti-dialg
        if (ch == B[0].charAt(2) && ch == B[1].charAt(1) && ch == B[2].charAt(0))
            return true;
        return false;
    }
}
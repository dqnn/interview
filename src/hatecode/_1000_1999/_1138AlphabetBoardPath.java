package hatecode._1000_1999;

import java.util.Collections;

public class _1138AlphabetBoardPath {
/*
1138. Alphabet Board Path
On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].

Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.
We may make the following moves:

'U' moves our position up one row, if the position exists on the board;
'D' moves our position down one row, if the position exists on the board;
'L' moves our position left one column, if the position exists on the board;
'R' moves our position right one column, if the position exists on the board;
'!' adds the character board[r][c] at our current position (r, c) to the answer.
(Here, the only positions that exist on the board are positions with letters on them.)

Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.

 

Example 1:

Input: target = "leet"
Output: "DDR!UURRR!!DDD!"
*/
    //thinking process: O(n)/O(n)
    
    //the problem is to say: given a char table, each row has 5 char, so we 
    //would like to output its directions to find the same string as target string
    //! means print the character in current cell. 
    
    //
    public String alphabetBoardPath(String target) {
        if(target == null || target.length() < 1) return "";
          int x = 0, y = 0;
          StringBuilder sb = new StringBuilder();
          for (char ch : target.toCharArray()) {
            int x1 = (ch - 'a') % 5, y1 = (ch - 'a') / 5;
            sb.append(String.join("", Collections.nCopies(Math.max(0, y - y1), "U")) +
              String.join("", Collections.nCopies(Math.max(0, x1 - x), "R")) +
              String.join("", Collections.nCopies(Math.max(0, x - x1), "L")) +
              String.join("", Collections.nCopies(Math.max(0, y1 - y), "D")) + "!");
            x = x1; y = y1;
          }
          return sb.toString();
    }
    
         public String alphabetBoardPath_BestPerf(String target) {
            StringBuilder sb = new StringBuilder();
            for (int n = 0, i = 0, j = 0; n < target.length(); ++n) {
                int pos = target.charAt(n) - 'a', row = pos / 5, col = pos % 5;
                while (j > col) { // while loop of left (L) move before that of downward (D) move.
                    sb.append('L');
                    --j;
                }
                while (i > row) { // while loop of upward (U) move before that of right (R) move.
                    sb.append('U');
                    --i;
                }
                while (i < row) { // while loop of downward (D) move.
                    sb.append('D');
                    ++i;
                }
                while (j < col) { // while loop of right (R) move.
                    sb.append('R');
                    ++j;
                }
                sb.append('!');   
            }
            return sb.toString();   
    }
}
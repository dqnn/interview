package hatecode;
public class KnightDialer {
/*
935.
Knight Dialer
A chess knight can move as indicated in the chess diagram below:
1 2 3
4 5 6
7 8 9
-1 0 -1

Knight Has 8 directions

 

This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.

Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.

How many distinct numbers can you dial in this manner?

Since the answer may be large, output the answer modulo 10^9 + 7.

 

Example 1:

Input: 1
Output: 10
Example 2:

Input: 2
Output: 20
Example 3:

Input: 3
Output: 46
 

Note:

1 <= N <= 5000
 */
    
    //TLE solutions
    private long res = 0;
    public int knightDialer(int N) {
        if (N == 0) return 0;
        int[][] keyBoard = {{1,2,3}, {4,5,6}, {7,8,9}, {-1, 0, -1}};
        int r = keyBoard.length, c = keyBoard[0].length;
        for(int i = 0; i< r;i++) {
            for(int j = 0; j < c; j++) {
                if (keyBoard[i][j] > -1) {
                    helper(keyBoard, N-1, i, j);
                }
            }
        }
        return (int)(res % (1000000007));
    }
    
    private void helper(int[][] b, int n, int i, int j) {
        if (i < 0 || j < 0 || i >= b.length || j >= b[0].length
           || b[i][j] == -1 || n < 0) {
            return;
        }
        
        if (n == 0) {
            res++;
            return;
        }
        //int temp = b[i][j];
        //b[i][j] = -1;
        helper(b, n -1, i -2, j-1);
        helper(b, n -1, i -1, j-2);
        helper(b, n -1, i +1, j-2);
        helper(b, n -1, i +2, j-1);
        
        helper(b, n -1, i +2, j+1);
        helper(b, n -1, i +1, j+2);
        helper(b, n -1, i -1, j+2);
        helper(b, n -1, i -2, j+1);
        //b[i][j] = temp;
    }
}
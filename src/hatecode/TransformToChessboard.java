package hatecode;

import java.util.*;
public class TransformToChessboard {
/*
782. Transform to Chessboard
An N x N board contains only 0s and 1s. In each move, you can swap any 2 rows with each other, or any 2 columns with each other.

What is the minimum number of moves to transform the board into a "chessboard" - a board where no 0s and no 1s are 4-directionally adjacent? If the task is impossible, return -1.

Examples:
Input: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
Output: 2
*/
    //thinking process: 
    //given a chess board which only contains 1 and 0, we can swap two columns or rows at one time,make the chess have 
    //diff value compared to its adjacent chesses, so return the min swaps
    
    //
    public int movesToChessboard(int[][] b) {
        int n = b.length, rowSum = 0, colSum = 0, rowSwap = 0, colSwap = 0;
        //this is to verify that 4 edge points can be only 4 0 or 2 1 and  2 0.
        //
        for(int i = 0; i<n; i++) {
            for(int j = 0; j < n; j++) {
                if ((b[0][0] ^ b[i][0] ^ b[0][j] ^ b [i][j]) == 1) return -1;
            }
        }
        for(int i = 0; i< n; i++) {
            rowSum += b[0][i];
            colSum += b[i][0];
            if (b[i][0] == i % 2) rowSwap ++;
            if (b[0][i] == i % 2) colSwap ++ ;
        }
        
        if (rowSum != n / 2 && rowSum != (n + 1) / 2) return -1;
        if (colSum != n / 2 && colSum != (n + 1) / 2) return -1;
        if (n % 2 == 1) {
            if (colSwap % 2 == 1) colSwap = n - colSwap;
            if (rowSwap % 2 == 1) rowSwap = n - rowSwap;
        } else {
            colSwap = Math.min(n - colSwap, colSwap);
            rowSwap = Math.min(n - rowSwap, rowSwap);
        }
        return (colSwap + rowSwap) / 2;
    }
    
    //O(n^2)/O(n)
    public int movesToChessboard_Answer(int[][] board) {
        int n = board.length;

        // count[code] = v, where code is an integer
        // that represents the row in binary, and v
        // is the number of occurrences of that row
        Map<Integer, Integer> count = new HashMap<>();
        for (int[] row: board) {
            int code = 0;
            for (int x: row)
                code = 2 * code + x;
            count.put(code, count.getOrDefault(code, 0) + 1);
        }

        int k1 = analyzeCount(count, n);
        if (k1 == -1) return -1;

        // count[code], as before except with columns
        count = new HashMap<>();
        for (int c = 0; c < n; ++c) {
            int code = 0;
            for (int r = 0; r < n; ++r)
                code = 2 * code + board[r][c];
            count.put(code, count.getOrDefault(code, 0) + 1);
        }

        int k2 = analyzeCount(count, n);
        return k2 >= 0 ? k1 + k2 : -1;
    }

    public int analyzeCount(Map<Integer, Integer> count, int N) {
        // Return -1 if count is invalid
        // Otherwise, return number of swaps required
        if (count.size() != 2) return -1;

        List<Integer> keys = new ArrayList(count.keySet());
        int k1 = keys.get(0), k2 = keys.get(1);

        // If lines aren't in the right quantity
        if (!(count.get(k1) == N/2 && count.get(k2) == (N+1)/2) &&
                !(count.get(k2) == N/2 && count.get(k1) == (N+1)/2))
            return -1;
        // If lines aren't opposite
        if ((k1 ^ k2) != (1<<N) - 1)
            return -1;

        int Nones = (1 << N) - 1;
        int ones = Integer.bitCount(k1 & Nones);
        int cand = Integer.MAX_VALUE;
        if (N%2 == 0 || ones * 2 < N) // zero start
            cand = Math.min(cand, Integer.bitCount(k1 ^ 0xAAAAAAAA & Nones) / 2);

        if (N%2 == 0 || ones * 2 > N) // ones start
            cand = Math.min(cand, Integer.bitCount(k1 ^ 0x55555555 & Nones) / 2);

        return cand;
    }
}
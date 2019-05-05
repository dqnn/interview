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
    //diff value compared to its adjacent chess, so return the min swaps
/*
棋盘的每行的1的个数要么为 n/2，要么为 (n+1)/2，
只看行，我们发现只有两种情况 0110 和 1001，如果只看列，只有 0011 和 1100，不管棋盘有多长，都只有两种情况。
任意一个矩形的四个顶点只有三种情况，四个0或四个1或两个0两个1，那么四个顶点亦或在一起一定是0。
之后我们来统计首行和首列中的1个数，因为我们要让其满足之前提到的规律。统计完了首行首列1的个数，
先默认跟 10101 比较好了，之后再做优化处理。
分n的奇偶来讨论。如果n是奇数，需要偶数个错位。如果n是偶数，跟 n - rowDiff 比较取较小值。
列的处理跟行的处理完全一样。最终把行错位个数跟列错位个数相加，再除以2，得到最小的交换次数
 */
    //TODO: write more comments on this solution
    public int movesToChessboard(int[][] b) {
        int n = b.length, rowSum = 0, colSum = 0, rowSwap = 0, colSwap = 0;
        //this is to verify that 4 edge points can be only 4 0 or 2 1 and  2 0.
        //
        for(int i = 0; i<n; i++) {
            for(int j = 0; j < n; j++) {
                if ((b[0][0] ^ b[i][0] ^ b[0][j] ^ b [i][j]) == 1) return -1;
            }
        }
        //process each line
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
package hatecode._0001_0999;
import java.util.*;
public class _935KnightDialer {
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
    //thinking process: given phone 0-9, and its move restriction each step, find out for N, 
    //how many possible numbers can be formed. 
    
    //the brute force is DFS as TLE version, we start from different number, so there is no duplicate number, 
    //but it can definitely cache some temp variables TODO: add memo[i][j], it means: for length i, and now at j, how 
    //many possible numbers can be formed. 
    
    //memo function typical is to be result of recursive function, so 
    public int knightDialer(int N) {
        //0->{4,6}, 1->{6,8},map[i] means i can move to map[i][j]
        //this also gave us some hints that if the 2D matrix is small, we can simply the nexthopmap always
        int[][] nextHopMap = {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};
        //memo[i][j] means if we start from j, i moves, how many number can be formed
        int[][] memo = new int[N + 1][10];
        for (int i = 1; i <= N; i++)  Arrays.fill(memo[i], -1);
        
        int res = 0;
        for (int i = 0; i < 10; i++) {
            res += helper(N, i, nextHopMap, memo);
            res %= (int)1e9 + 7;
        }
        return res;
    }
    private int helper(int N, int start, int[][] nextHopMap, int[][] memo) {
        if (N == 1) {
            return 1;
        }
        if (memo[N][start] > -1) {
            return memo[N][start];
        }
        memo[N][start] = 0;
        //every current memo step is its successive hop sum
        for (int next : nextHopMap[start]) {
            memo[N][start] += helper(N - 1, next, nextHopMap, memo);
            memo[N][start] %= (int)1e9 + 7;
        }
        return memo[N][start];
    }
    
    //TLE solutions, to add memo[i][j]= helper(b, i, j, memo)
    private long res = 0;
    public int knightDialer2(int N) {
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
    
    
    public static final int max = (int) Math.pow(10, 9) + 7;
    
    public int knightDialer3(int n) {
       long s = 0;
       //do n hops from every i, j index (the very requirement of the problem)
       for(int i = 0; i < 4; i++) {
          for(int j = 0; j < 3; j++) {
             s = (s + paths(i, j, n)) % max;
          }
       }
       return (int) s;
    }

    private long paths(int i, int j, int n) {
       // if the knight hops outside of the matrix or to * return 0 
       //as there are no unique paths from here
       if(i < 0 || j < 0 || i >= 4 || j >= 3 || (i == 3 && j != 1)) return 0;
       //trivial case
       if(n == 1) return 1;
       //non trivial case
       long s = paths(i - 1, j - 2, n - 1) % max + // jump to a
                paths(i - 2, j - 1, n - 1) % max + // jump to b
                paths(i - 2, j + 1, n - 1) % max + // jump to c
                paths(i - 1, j + 2, n - 1) % max + // jump to d
                paths(i + 1, j + 2, n - 1) % max + // jump to e
                paths(i + 2, j + 1, n - 1) % max + // jump to f
                paths(i + 2, j - 1, n - 1) % max + // jump to g
                paths(i + 1, j - 2, n - 1) % max; // jump to h
       return s;
    }
}
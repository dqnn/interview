package hatecode._0001_0999;


/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : DungeonGame
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 174. Dungeon Game
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

 

Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)
 

Note:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the 
knight enters and the bottom-right room where the princess is imprisoned.
 */
public class DungeonGame {

    /**

     -2 -3  3
     -5 -10 1
     10 30 -5

     [7, 5, 2]
     [6, 11, 5]
     [1, 1, 6]

     time : O(m * n)
     space : O(m * n)

     * @param dungeon
     * @return
     */
  //Given the knight start from top left to right down, every time he has at least 1 HP, so if we use the traditional way 
    //dp[i][j] means the min HP in m[i][j] then it would have problem, because we don't know how much HP we have to lose in 
    //m[i+1][j+1], so dp[i][j] was decided by m[i+1][j+1], 
    
    //this typical question is to understand that the current state is decided by next matrix value, so we cannot find relation bewteen i and i-1,but insted, if we look back from last cell,  we can find that relation ship between i+1 and 1. 
/*
/ given the example dungeon, lets label cells as follows: 
// +-+-+-+
// |1|2|3|
// +-+-+-+
// |4|5|6|
// +-+-+-+
// |7|8|9|
// +-+-+-+

The dungeon:                              Initial HP knight needed:       
+-------+-------+-------+                 +-------+-------+-------+
|       |       |       |                 |       |       |       |
|  -2   |  -3   |   3   |                 |   7   |   5   |   2   |
|       |       |       |                 |       |       |       |
+-------+-------+-------+                 +-------+-------+-------+
|       |       |       |                 |       |       |       |
|  -5   |  -10  |   1   |                 |   6   |   11  |   5   |
|       |       |       |                 |       |       |       |
+-------+-------+-------+                 +-------+-------+-------+
|       |       |       |                 |       |       |       |
|  10   |  30   |  -5(P)|                 |   1   |   1   |   6   |
|       |       |       |                 |       |       |       |
+-------+-------+-------+                 +-------+-------+-------+

Knight HP: 

// To solve this problem, we can start with the simpliest cases.
// Les's say,

// If the knight starts from cell 9.
Initial HP: 6 (6 - 5 = 1), which means as long as the knight has 6 HP when reaching cell 9, he would be fine.

// If the knight starts from cell 6.
Initial HP: 5 (5 + 1 = 6), which means as long as the knight has 5 HP when reaching cell 6, he would be fine.

// If the knight starts from cell 8.
Initial HP: 1 (1 + 30 >= 6, HP needs to be at least 1, 0 means the knight is already dead), 
which means as long as the kinght has 1 HP when reaching cell 7, he would be fine.

// If the knight starts from cell 5.
Emm... the knight now has two options, going right or going down.
If go right (5 --> 6 --> 9), Initial HP(R): 15 (15 - 10 = 5)
If go down (5 --> 8 --> 9), Initial HP(D): 11 (11 - 10 = 1)
Hence, Initial HP = MIN(HP(R), HP(D)) = 11

// Sub-problems and state: 
Let dp[i][j] denote Initial HP needed if the knight starts from dungeon[i][j].

// recurrence relation:
dp[i][j] = min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
if(dp[i][j] <= 0) dp[i][j] = 1;
*/
  //interview friendly,
    //the brutal force solution is we visit all possible path to end, each path we should 
    //have a number, if it is smaller than 0, then we add a number to make it 1, then the number we added
    //is the minimal we want, the time complexity is O(2^(MN)), every node we have two ways 
    
    //remember: dp[i][j] means the least HP if starting from (i,j), 
    //1-m[i][j] means to keep alive, the least HP we need, if that column is -5, then you have 
    //to be 6, if 1, you have to be 1 at least alive
    public int calculateMinimumHP(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) return 0;

        int r = m.length;
        int c = m[0].length;
        int[][] dp = new int[r][c];
        //suppose we only have 1 cell, so if it is threat, then we need more HP
        dp[r - 1][c - 1] = Math.max(1 - m[r - 1][c - 1], 1);

        //initialize last column, starting from second last
        for (int i = r - 2; i >= 0; i--) {
            dp[i][c - 1] = Math.max(dp[i + 1][c - 1] - m[i][c - 1], 1);
        }
        //initialize last row, starting from 2nd last
        for (int i = c - 2; i >= 0; i--) {
            dp[r - 1][i] = Math.max(dp[r - 1][i + 1] - m[r - 1][i], 1);
        }

        for (int i = r - 2; i >= 0; i--) {
            for (int j = c - 2; j >= 0; j--) {
                int down = Math.max(dp[i + 1][j] - m[i][j], 1);
                int right = Math.max(dp[i][j + 1] - m[i][j], 1);
                dp[i][j] = Math.min(down, right);
            }
        }
        return dp[0][0];
    }

    public int calculateMinimumHP_Reference(int[][] m) {
        if (m == null || m.length < 1 || m[0].length < 1) return 0;
        int r = m.length, c= m[0].length;
        
        int[][] dp = new int[r][c];
        
        dp[r-1][c-1] = 1 - m[r-1][c-1] <= 0 ? 1 : 1 - m[r-1][c-1];
        
        for(int i = r-1; i>=0; i--) {
            for(int j = c-1; j>=0;j--) {
                if (i == r-1 && j == c-1) continue;
                //if last row we cannot down further, so we use max
                int down = i + 1 == r? Integer.MAX_VALUE : dp[i+1][j] - m[i][j];
                //if most right row we cannto right further, so we use max
                int right = j + 1 == c? Integer.MAX_VALUE:dp[i][j+1] - m[i][j];
                dp[i][j] = Math.min(down, right) <= 0 ? 1 : Math.min(down, right);
            }
        }
        
        return dp[0][0];
        
    }
}

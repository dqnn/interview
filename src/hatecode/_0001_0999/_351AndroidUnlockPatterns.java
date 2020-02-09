package hatecode._0001_0999;

/**
 * tags: DFS
 * 
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : AndroidUnlockPatterns
 * Creator : duqiang
 * Date : Oct, 2017
 * Description : 351. Android Unlock Patterns
 * 
 * Given an Android 3x3 key lock screen and two integers m and n, 
 * where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the 
 * Android lock screen, which consist of minimum of m keys and maximum n keys.

Rules for a valid pattern:

Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.
If the line connecting two consecutive keys in the pattern passes through 
any other keys, the other keys must have previously selected in the pattern. 
No jumps through non selected key is allowed.
The order of keys used matters.



Explanation:
| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |
Invalid move: 4 - 1 - 3 - 6
Line 1 - 3 passes through key 2 which had not been selected in the pattern.

Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.

Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.


 */
public class _351AndroidUnlockPatterns {
    /**
     * 这道题乍一看题目这么长以为是一个设计题，其实不是，这道题还是比较有意思的，
     * 起码跟实际结合的比较紧密。这道题说的是安卓机子的解锁方法，有9个数字键，
     * 如果密码的长度范围在[m, n]之间，问所有的解锁模式共有多少种，注意题目中给出的一些非法的滑动模式。
     * 那么我们先来看一下哪些是非法的，首先1不能直接到3，必须经过2，同理的有4到6，7到9，1到7，2到8，3到9，
     * 还有就是对角线必须经过5，例如1到9，3到7等。我们建立一个二维数组jumps，用来记录两个数字键之间是否有中间键，
     * 然后再用一个一位数组visited来记录某个键是否被访问过，然后我们用递归来解，我们先对1调用递归函数，在递归函数中
     * ，我们遍历1到9每个数字next，然后找他们之间是否有jump数字，如果next没被访问过，并且jump为0，或者jump被访问过，
     * 我们对next调用递归函数。数字1的模式个数算出来后，由于1,3,7,9是对称的，所以我们乘4即可，然后再对数字2调用递归函数，2,4,6,9也是对称的，
     * 再乘4，最后单独对5调用一次，然后把所有的加起来就是最终结果了，参见代码如下：
     * time : 不知道
     * space : 不知道
     * @param m
     * @param n
     * @return
     */
    
    
    
 // For numbers 1 - 9, 
    //this is best solution, so we optimize the DFS function, 
    //previous was for each i in [m, n], we will count from i->0, then we will have some 
    //pattern which would already visited, like from 0->m, we do not need to visit again for 
    //each number in [m,n], that's why here is fast
    private static final int DIGITS = 10;
    private static int[][] skip = new int[DIGITS][DIGITS];
    private static boolean[] visited = new boolean[DIGITS];
 // this problem is actually good ones, 
    //it may describe the DFS under restrictions, also it use visited array for dfs
    //to search for all possible combinations, after the search, and clear the visited flag. 
    //for this problem, we need to draw the recursive tree
    public int numberOfPatterns(int m, int n) {
        skip[1][3] = skip[3][1] = 2;
        skip[3][9] = skip[9][3] = 6;
        skip[9][7] = skip[7][9] = 8;
        skip[7][1] = skip[1][7] = 4;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
        int res = 0;
        
        // Default 0 to false so that each value in our skip array that isn't initialized will default to true.
        visited[0] = true;
        // 4 * is used for 1 & 2 since we can utilize symmetry to know that, e.g., 
        // whatever 1 gives us also applies to 3, 7 & 9. 2 would be for 4, 6 & 8
        res += 4 * dfs(1, 1, m, n);
        res += 4 * dfs(2, 1, m, n);
        res += dfs(5, 1, m, n);
        
        return res;
    }
    
    private int dfs(int start, int depth, int min, int max) {
        int res = 0;
        //here means we have done from 1->min, so we found a result, then res++
        if(depth >= min) res++;
        
        //here means we already reached the end of the pattern length, so we get out
        if(++depth > max) {
            return res;
        }
        
        visited[start] = true;
        for(int i = 1; i < DIGITS; i++) {
            // We haven't already visited this digit and 
            //if we are on non-adjacent numbers, we've visited the number in between.
            if(!visited[i] && visited[skip[start][i]]) {
               res += dfs(i, depth, min, max);
            }
        }

        // Backtrack
        visited[start] = false;
        
        return res;
    }

    public int numberOfPatterns_reference(int m, int n) {
        int[][] skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] 
                = skip[2][8] = skip[8][2] 
                = skip[3][7] = skip[7][3] 
                = skip[4][6] = skip[6][4] = 5;
        boolean[] visited = new boolean[10];
        int res = 0;
        for (int i = m; i <= n; i++) {
            res += DFS(visited, skip, 1, i - 1) * 4; // 1, 3, 7, 9， they have same ways
            res += DFS(visited, skip, 2, i - 1) * 4; // 2, 4, 6, 8
            res += DFS(visited, skip, 5, i - 1); // 5
        }
        return res;
    }

    public int DFS(boolean[] visited, int[][] skip, int cur, int remain) {
        //actually we do not need this
        if (remain < 0) return 0;
        //find one combinations
        if (remain == 0) return 1;
        visited[cur] = true;
        int res = 0;
        for (int i = 1; i <= 9; i++) {
            //we have not visited this number or we have visited the middle one
            if (!visited[i] && (skip[cur][i] == 0 || visited[skip[cur][i]])) {
                res += DFS(visited, skip, i, remain - 1);
            }
        }
        visited[cur] = false;
        return res;
    }
}

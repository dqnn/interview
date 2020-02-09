package hatecode._0001_0999;
import java.util.*;
public class MaximumVacationDays {
/*
568. Maximum Vacation Days
tags:DP-best
LeetCode wants to give one of its best employees the option to travel 
among N cities to collect algorithm problems. But all work and no play makes 
Jack a dull boy, you could take vacations in some particular cities and weeks. 
Your job is to schedule the traveling to maximize the number of vacation days 
you could take, but there are certain rules and restrictions you need to follow.

Rules and restrictions:
You can only travel among N cities, represented by indexes from 0 to N-1. Initially, 
you are in the city indexed 0 on Monday.

The cities are connected by flights. The flights are represented as a N*N matrix 
(not necessary symmetrical), called flights representing the airline status 
from the city i to the city j. If there is no flight from the city i to the city j, 
flights[i][j] = 0; Otherwise, flights[i][j] = 1. Also, flights[i][i] = 0 for all i.

You totally have K weeks (each week has 7 days) to travel. You can only take 
flights at most once per day and can only take flights on each week's Monday morning. 
Since flight time is so short, we don't consider the impact of flight time.
For each city, you can only have restricted vacation days in different weeks, 
given an N*K matrix called days representing this relationship. For the value 
of days[i][j], it represents the maximum days you could take vacation in the city 
i in the week j.
You're given the flights matrix and days matrix, and you need to output the maximum 
vacation days you could take during K weeks.

Example 1:
Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]
Output: 12
Explanation: 
Ans = 6 + 3 + 3 = 12. 

One of the best strategies is:
1st week : fly from city 0 to city 1 on Monday, and play 6 days and work 1 day. 
(Although you start at city 0, we could also fly to and start at other cities since it is Monday.) 
2nd week : fly from city 1 to city 2 on Monday, and play 3 days and work 4 days.
3rd week : stay at city 2, and play 3 days and work 4 days.
Example 2:
Input:flights = [[0,0,0],[0,0,0],[0,0,0]], days = [[1,1,1],[7,7,7],[7,7,7]]
Output: 3
Explanation: 
Ans = 1 + 1 + 1 = 3. 
*/
    //thinking process: the code is typical DFS->DFS+memo->DP question, very fit 
    //for interview
    /*DP. dp[i][j] stands for the max vacation days we can get in week i staying in city j. 
     * It's obvious that dp[i][j] = max(dp[i - 1][k] + days[j][i]) 
     * (k = 0...N - 1, if we can go from city k to city j). Also because values of week 
     * i only depends on week i - 1, so we can compress two dimensional dp array 
     * to one dimension. Time complexity O(K * N * N)/O(N) as we can easily figure out 
     * from the 3 level of loops.
     */
     public int maxVacationDays4(int[][] flights, int[][] days) {
         int N = flights.length;
         int K = days[0].length;
         int[] dp = new int[N];
         Arrays.fill(dp, Integer.MIN_VALUE);
         dp[0] = 0;
         
         for (int i = 0; i < K; i++) {
             int[] temp = new int[N];
             Arrays.fill(temp, Integer.MIN_VALUE);
             for (int j = 0; j < N; j++) {
                 for(int k = 0; k < N; k++) {
                     if (j == k || flights[k][j] == 1) {
                         temp[j] = Math.max(temp[j], dp[k] + days[j][i]);
                     }
                 }
             }
             dp = temp;
         }
         int max = 0;
         for (int v : dp) {
             max = Math.max(max, v);
         }
         return max;
     }
     //2D DP, dp[i][j] means in city i on j week, maxium days we can get
     //all will sum to dp[i][0], from back to front
     public int maxVacationDays(int[][] flights, int[][] days) {
         int n = flights.length;
         int m = days[0].length;
         int[][] dp = new int[n][m];
         int res = 0;
         for (int j = m - 1; j >= 0; --j) {
             for (int i = 0; i < n; ++i) {
                 //at least we can have vacation days in current city i for j week
                 dp[i][j] = days[i][j];
                 for (int p = 0; p < n; ++p) {
                     if ((i == p || flights[i][p] == 1) && j < m - 1) {
                         dp[i][j] = Math.max(dp[i][j], dp[p][j + 1] + days[i][j]);
                     }
                     if (j == 0 && (i == 0 || flights[0][i] == 1)) res = Math.max(res, dp[i][0]);
                 }
             }
         }
         return res;
     }
     //2D DP,dp[i][j]= max days when we i-th week and stay on city j
     //it has 2 cases: 1: previous city is j, 2: previous city is not j, but we should 
     //have flights from j->i
     //dp[i][j] = max(dp[i-1][j], 
     public int maxVacationDays5(int[][] flights, int[][] days) {
         int N = flights.length;
         int K = days[0].length;
         int[][] dp = new int[K][N];
         // initialize the start city and day 1
         dp[0][0] = days[0][0];
         //for week 0, it means can we start with city j, if yes, then vacation days
         //days[j][0]
         for (int dst = 1; dst < N; dst++) {
             dp[0][dst] = flights[0][dst] == 0 ? -1 : days[dst][0];
         }
         // thinking in this way, dp here just means maxium days when starting from week i
         //and now stay in city j, so how can we come to this state two cases:
         //one is we across all other cities dp[i-1][k], k =0,,N-1 + days[j][i],
         for (int week = 1; week < K; week++) {
             //initialize the dp[i] to -1
             Arrays.fill(dp[week], -1);
             // to city dst
             for (int dst = 0; dst < N; dst++) { 
              // go through all the cities and vacations in last week and update for the week
                 for (int depart = 0; depart < N; depart++) { 
                     if (dp[week - 1][depart] != -1 
                             && (depart == dst || flights[depart][dst] == 1)) {
                         dp[week][dst] = Math.max(dp[week - 1][depart] + days[dst][week], 
                                 dp[week][dst]);
                     }
                 }
             }
         }
         //pick the max one
         int res = 0;
         for(int city = 0; city < N; city++) {
             res = Math.max(res, dp[K-1][city]);
         }
         return res;
     }
    //O(n^k)/O(k)
     public int maxVacationDays2(int[][] flights, int[][] days) {
        return dfs(flights, days, 0, 0);
    }
    public int dfs(int[][] flights, int[][] days, int curCity, int week) {
        if (week == days[0].length)
            return 0;
        int maxDays = 0;
        for (int i = 0; i < flights.length; i++) {
            // i == curCity means do not take flights
            if (flights[curCity][i] == 1 || i == curCity) {
                int vac = days[i][week] + dfs(flights, days, i, week + 1);
                maxDays = Math.max(maxDays, vac);
            }
        }
        return maxDays;
    }
        //O(n^2 * k)/O(n *k), DFS with memo
        public int maxVacationDays3(int[][] flights, int[][] days) {
        int[][] memo = new int[flights.length][days[0].length];
        for (int[] l: memo)
            Arrays.fill(l, Integer.MIN_VALUE);
        return helper(flights, days, 0, 0, memo);
    }
    public int helper(int[][] flights, int[][] days, int cur_city, int weekno, int[][] memo) {
        if (weekno == days[0].length)
            return 0;
        if (memo[cur_city][weekno] != Integer.MIN_VALUE)
            return memo[cur_city][weekno];
        int maxvac = 0;
        for (int i = 0; i < flights.length; i++) {
            if (flights[cur_city][i] == 1 || i == cur_city) {
                int vac = days[i][weekno] + helper(flights, days, i, weekno + 1, memo);
                maxvac = Math.max(maxvac, vac);
            }
        }
        memo[cur_city][weekno] = maxvac;
        return maxvac;
    }
}
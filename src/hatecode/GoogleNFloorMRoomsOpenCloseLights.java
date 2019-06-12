package hatecode;

public class GoogleNFloorMRoomsOpenCloseLights {
/*
 * from google interview doc, not from LC
 * 一栋楼有n层，每层有m个房间加一个过道，每条过道两侧有楼梯，只能通过两侧的楼梯上下楼。
 * 现在这栋楼里有一部分房间开着灯，每走过一个房间的cost是1，现在要把所有的房间的灯关掉，
 * 求问minimum cost是多少。 
 * 
 * 这题可以用动态规划解，也可以用dfs解，思路就是只有两种情况，爬左边的楼梯或者爬右边的楼梯，
 * 也就是一个binary tree
 * 
 * How to Solve:
 * 如果不上楼，只下楼，用dp逐行扫描
dp[i][0]代表第i层从左边下楼的最小cost dp[i][1]第i层从右边下楼的最小cost，
dp[i][0] = dp[i - 1][1] + # of rooms in level i - 1 / dp[i - 1][0] + 2 * farthest room with light on
dp[i][1]同理
      2. 如果可以上下楼 （按照楼主描述，应该是不考虑上下楼）

 */
    //TODO: not understand the problem, actually 
}

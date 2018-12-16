package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PaintHouse
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 256. Paint House
 */
public class PaintHouse {
    /**
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
The cost of painting each house with a certain color is different. You have to paint all the houses such 
that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, 
costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 
with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: 
red   blue  green
17    2    17   house 0
16    16   5    house 1
14    3    19   house 2


Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue. 
             Minimum cost: 2 + 5 + 3 = 10.

     The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example,
     costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with
     color green, and so on... Find the minimum cost to paint all houses.

     costs[i][j]
     i : house
     j : color : 3

     time : O(n)
     space : O(1)


     * @param costs
     * @return
     */
    public int minCost(int[][] costs) {
        if (costs == null || costs[0].length < 1) return 0;
        // row based since they cannot ajacent, so we will choose the smaller one of the other two
        // so each house will be considered. 
        for (int i = 1; i < costs.length; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        int n = costs.length - 1;
        return Math.min(Math.min(costs[n][0], costs[n][1]), costs[n][2]);
    }
}

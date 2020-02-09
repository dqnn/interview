package hatecode._0001_0999;

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
     * There are a row of n houses, each house can be painted with one of the three
     * colors: red, blue or green. The cost of painting each house with a certain
     * color is different. You have to paint all the houses such that no two
     * adjacent houses have the same color.
     * 
     * The cost of painting each house with a certain color is represented by a n x
     * 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with
     * color red; costs[1][2] is the cost of painting house 1 with color green, and
     * so on... Find the minimum cost to paint all houses.
     * 
     * Note: All costs are positive integers.
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
    //cost[i][j] means paint house i with color j cost
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
    //easier to understand version
    ////thinking proess and this interview friendly
    //we use typical 1D DP problem with restrictions, the key is the formula, and 
    //we have leverage how the restriction contribute to the formula
    //it is pretty straightword and one thing need to know is about the iteration of 
    //lastG, lastB and lastR, they cannot be adjacent, like house robbery
    public int minCost2(int[][] costs) {
        if(costs.length==0) return 0;
        int lastR = costs[0][0];
        int lastG = costs[0][1];
        int lastB = costs[0][2];
        for(int i=1; i<costs.length; i++){
            int curR = Math.min(lastG,lastB)+costs[i][0];
            int curG = Math.min(lastR,lastB)+costs[i][1];
            int curB = Math.min(lastR,lastG)+costs[i][2];
            lastR = curR;
            lastG = curG;
            lastB = curB;
        }
        return Math.min(Math.min(lastR,lastG),lastB);
    }
}

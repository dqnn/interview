package hatecode;
import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PaintHouseII
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 265. Paint House II
 */
public class PaintHouseII {
    /**
     * There are a row of n houses, each house can be painted with one of the k colors.
     * The cost of painting each house with a certain color is different.
     * You have to paint all the houses such that no two adjacent houses have the same color.

     The cost of painting each house with a certain color is represented by a n x k cost matrix.
     For example, costs[0][0] is the cost of painting house 0 with color 0;
     costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

     Note:
     All costs are positive integers.


     time : O(n * k)
     space : O(1)


     * @param costs
     * @return
     */

    // this is typical DP problems, 
    //maintain the minimum two costs min1(smallest) and min2 (second to smallest) after painting i-th house.
    //dp[i][j] represents the min paint cost from house 0 to house i when house i use color j; 
    //The formula will be dp[i][j] = Math.min(any k!= j| dp[i-1][k]) + costs[i][j].
    //O(nk)/O(1)
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length;
        int k = costs[0].length;

        int min1 = -1, min2 = -1;
        for (int i = 0; i < n; i++) {
            int last1 = min1, last2 = min2;
            min1 = -1; min2 = -1;
            for (int j = 0; j < k; j++) {
                if (j != last1) {
                    costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
                } else {
                    costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
                }

                if (min1 < 0 || costs[i][j] < costs[i][min1]) {
                    min2 = min1;
                    min1 = j;
                } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }
        return costs[n - 1][min1];
    }
    
    
 // O(N*KlogK) time + O(N) space
    // dp[i][j] = Math.min(any k!= j | dp[i-1][k]) + costs[i][j]
    public int minCostII2(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) return 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int[] colors = new int[costs[0].length];
        System.arraycopy(costs[0], 0, colors, 0, costs[0].length);
        
        for (int i = 1; i < costs.length; i++) {
            for (int color : colors) heap.offer(color);

            int[] next = new int[colors.length];
            for (int j = 0; j < next.length; j++) { // O(KlogK)
                heap.remove(colors[j]);
                next[j] = heap.peek() + costs[i][j];
                heap.offer(colors[j]);
            }
            colors = next;
            heap.clear();
        }
        
        int min = colors[0];
        for (int i = 1; i < colors.length; i++)
            min = Math.min(min, colors[i]);
        return min;
    }
    
    
    public int minCostII3(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) return 0;
        int min1 = 0, min2 = 0, idx = -1;
        for (int i = 0; i < costs.length; i++) {
            int min1i = Integer.MAX_VALUE, min2i = min1i, idxi = 0;
            for (int j = 0; j < costs[i].length; j++) {
                int cost = costs[i][j] + (j != idx ? min1 : min2);
                if (cost < min1i) {
                    min2i = min1i; min1i = cost; idxi = j;
                } else if (cost < min2i)
                    min2i = cost;
            }
            min1 = min1i; min2 = min2i; idx = idxi;
        }
        return min1;
    }
}

package hatecode._1000_1999;

import java.util.*;
public class _1029TwoCityScheduling {
/*
1029. Two City Scheduling
There are 2N people a company is planning to interview. 
The cost of flying the i-th person to city A is costs[i][0], 
and the cost of flying the i-th person to city B is costs[i][1].

Return the minimum cost to fly every person to a 
city such that exactly N people arrive in each city.

 

Example 1:

Input: [[10,20],[30,200],[400,50],[30,20]]
Output: 110
*/
    //thinking process: O(nlgn)/O(1) 
    
    //given a 2d array A, each i-th person cost to city a A[i][0], cost to 
    /*
     * city b is A[i][1], return the minimal cost for all persons 
     * 
     * Greedy, 
     * 
     * [10, 100], we use 10-100 = -90, to see if choose B, how many we can save,
     * we sort by this number, first half we will choose A since save is minimal, 
     * , next half we choose B since B is cheaper
     */
    
    //we want to sort by how much saved if we go to b instead of a
    //[[10,20],[30,200],[400,50],[30,20]]->
    //10, 170, -350, -10, we just need to loop first half, because we can know 
    //so -350,-10 to b, since we saved most
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (a, b)->((a[1] - a[0]) - (b[1] - b[0])));
        int cost = 0;
        for (int i = 0; i < costs.length / 2; i++) {
            cost += costs[i][1] + costs[costs.length-i-1][0];
        }
        return cost;
    }
}
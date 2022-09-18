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
    //thinking process:
    //given a 2d array A, A[i][0] means i-th person cost to city a, 
    //A[i][1] means cost to b, so we want to know the minimal cost if 
    //we group them into 2 cities. 
    
    //we want to sort by how much saved if we go to b instead of a
    //[[10,20],[30,200],[400,50],[30,20]]->
    //10, 170, -350, -10, we just need to loop first half, because we can know 
    //so -350,-10 to b, since we saved most, 
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (a, b)->((a[1] - a[0]) - (b[1] - b[0])));
        int cost = 0;
        for (int i = 0; i < costs.length / 2; i++) {
            cost += costs[i][1] + costs[costs.length-i-1][0];
        }
        return cost;
    }
}
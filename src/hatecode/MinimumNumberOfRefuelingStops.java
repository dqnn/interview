package hatecode;
import java.util.*;
public class MinimumNumberOfRefuelingStops {
/*
871. Minimum Number of Refueling Stops
A car travels from a starting position to a destination which is target miles east of the starting position.

Along the way, there are gas stations.  Each station[i] represents a gas station that is station[i][0] miles east of the starting position, and has station[i][1] liters of gas.

The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.  It uses 1 liter of gas per 1 mile that it drives.

When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.

What is the least number of refueling stops the car must make in order to reach its destination?  If it cannot reach the destination, return -1.

Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.  If the car reaches the destination with 0 fuel left, it is still considered to have arrived.

 

Example 1:

Input: target = 1, startFuel = 1, stations = []
Output: 0
Explanation: We can reach the target without refueling.
*/
    
//interview friendly, so this is mimic how reful and stop looks like, 
   public int minRefuelStops(int target, int startFuel, int[][] stations) {
    int curFarthest = startFuel, stop = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
    for (int[] station : stations) {
        // check if we can reach this station
        // if we cannot reach this station, refuel the gas from the previous station with most gas
        // redo the operation until we get enough gas to reach this station
        while (curFarthest < station[0]) {
            if (pq.isEmpty()) return -1; // if we reful in each station but still cannot reach this station, return -1
            curFarthest += pq.poll();
            stop++;
        }
        pq.offer(station[1]);
    }
    // now we have reached the last station, check if we can reach the target
    while (curFarthest < target) {
        if (pq.isEmpty()) return -1;
        curFarthest += pq.poll();
        stop++;
    }
    return stop;
}
    
    public int minRefuelStops_DP(int target, int start, int[][] s) {
        int n = s.length;
        //since the notes say: stations[i][1] <= 10^9
        long[] dp = new long[n +1];
        dp[0] = start;
        for(int i = 0; i < s.length; i++) {
            //we back to original dp[t]
            for(int t = i; t>=0 && dp[t] >= s[i][0]; t--) {
                dp[t + 1] = Math.max(dp[t + 1], dp[t] + s[i][1]);
            }
        }
        
        for (int t = 0; t <= s.length; ++t)
            if (dp[t] >= target) return t;
        return -1;
    }
    
   
    
    
}
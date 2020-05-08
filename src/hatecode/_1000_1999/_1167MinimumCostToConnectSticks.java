package hatecode._1000_1999;

import java.util.*;
public class _1167MinimumCostToConnectSticks {
/*
1167. Minimum Cost to Connect Sticks
You have some sticks with positive integer lengths.

You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.  You perform this action until there is one stick remaining.

Return the minimum cost of connecting all the given sticks into one stick in this way.

 

Example 1:

Input: sticks = [2,4,3]
Output: 14
*/
    
    //thinking process: O(n)/O(n)
    
    //[2,4,3]->[4,5], cost is 5-->[], cost is 9, so overall: 5 + 9 = 14
    public int connectSticks(int[] sticks) {
        if (sticks == null || sticks.length <= 1) return 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)->(a-b));
        
        Arrays.stream(sticks).forEach(e->pq.offer(e));
        //System.out.println(pq.size());
        int res = 0;
        while(pq.size() > 1) {
            int sum = pq.poll() + pq.poll();
            res += sum;
            pq.offer(sum);
            //System.out.println(pq.size());
        }
        return res;
    }
    
    //following is the min heap solution
    
    int left = 0, right = 0, numSticks, numResults = 0, num;
    // Gets the minimum from InputSet and ResultSet
    private boolean getMin(int[] sticks) {
        // check if there are numbers available from InputSet and ResultSet
        boolean f = right < numSticks, s = left < numResults;
        // If number is available from both sets, choose the smallest
        if (f && s) num = (sticks[left] <= sticks[right]) ? sticks[left++] : sticks[right++];
        // If number is available from InputSet only
        else if (f) num = sticks[right++];
        // If number is available from ResultSet only
        else if (s) num = sticks[left++];
        return f || s; // Returns result saying if we could find a number from any one of the Sets.
    }
    
    public int connectSticks_MinHeap(int[] sticks) {
        numSticks = sticks.length;
        Arrays.sort(sticks); // Initial sort
        int result = 0, first, second; // Result and place holders to get the smallest two numbers.
        // Continue till you can get two numbers every time from the Sets.
        while (true) {
            if (!getMin(sticks)) break;
            first = num;
            if (!getMin(sticks)) break;
            second = num;
            result += sticks[numResults++] = first + second; // Store the sum back in the ResultSet
        }
        return result;
    }
}
package hatecode._1000_1999;

import java.util.*;

public class _1124LongestWellPerformingInterval {
/*
1124. Longest Well-Performing Interval
683. K Empty Slots
862. Shortest Subarray with Sum at Least K
962. Maximum Width Ramp
We are given hours, a list of the number of hours worked per day for a given employee.

A day is considered to be a tiring day if and only if the number of hours 
worked is (strictly) greater than 8.

A well-performing interval is an interval of days for which the number of 
tiring days is strictly larger than the number of non-tiring days.

Return the length of the longest well-performing interval.

 

Example 1:

Input: hours = [9,9,6,0,6,6,9]
Output: 3
*/
    //thinking process: O(n)/O(n)
    //given an array H, each integer stands for hours one employee works, so
    //if H[i] > 8, then it is well-performing day, well performing period means
    //in a consecutive sequence, well-performing days more than none performing days,
    
    //Brute-forth is O(n^2), just calculate each [i,j]and choose the longest
    
    //the optimized one is to record prefixSum like flag to indicate from 0 to here,
    //what's the characteristic of the array, so one possible case is we can concat two 
    //subarray together, but pre subarray sum < 0, like this.
    
    //we can convert to this problem: one array only contains 1, -1,
    //find the longest sub array sum > 0, 
    
    //so we want to record prefix sum, and save (sum, index) to map,
    //so each time, at current position i, sum, we just need to find whether we have 
    //sum - 1 in the map, if not then it is not, it is like to look for sum = 1 longest sub array
    
    
    //use <score, i> as entry, so every time, for H[i], if current score < 0, we will try to 
    //seek for previous score - 1 position, like subarray sum
    public int longestWPI(int[] H) {
        if (H == null || H.length < 1) return 0;
        
        int res = 0, score = 0, n = H.length;
        //<score，index>,no override,
        //we can store all score into map, but here is the optimization, that
        //we only need to record when score <= 0, because if score >0 which means
        //it could be longer at least 0, 
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < n; i++) {
            score += H[i] > 8 ? 1 : -1;
            if (score > 0)  res = i + 1;
            else {
                seen.putIfAbsent(score, i);
                if (seen.containsKey(score - 1))
                    res = Math.max(res, i - seen.get(score - 1));
            }
        }
        return res;
    }

    //
    public int longestWPI_BetterPerformance(int[] hours) {
        int len = hours.length;
        int[] preSum = new int[len+1];   // prefix Sum
        for (int i = 1; i <= len; i++) {
            preSum[i] = preSum[i-1] + (hours[i-1] > 8 ? 1 : -1);
        }
        //only put decreasing number into the array
        Deque<Integer> deque = new LinkedList<>();   // Deque (8ms) is much faster than Stack (18ms)
        for (int i = 0; i <= len; i++) {
            if (deque.isEmpty() || preSum[deque.peek()] > preSum[i]) {
                deque.push(i);
            }
        }
        //this is the pattern often being used in array to justify sub array
        //we loop in the array and to detect previous sum can satisfy the needs
        int res = 0;
        for (int j = len; j >= 0; j--) {  // start from end
            while (!deque.isEmpty() && preSum[deque.peek()] < preSum[j]) {
                res = Math.max(res, j-deque.pop());
            }
        }
        return res;
    }
}
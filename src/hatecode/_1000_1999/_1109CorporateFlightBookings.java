package hatecode._1000_1999;

import java.util.*;
public class _1109CorporateFlightBookings {
/*
1109. Corporate Flight Bookings
There are n flights, and they are labeled from 1 to n.

We have a list of flight bookings.  The i-th booking bookings[i] = [i, j, k] means that we booked k seats from flights labeled i to j inclusive.

Return an array answer of length n, representing the number of seats booked on each flight in order of their label.

 

Example 1:

Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
Output: [10,55,45,25,25]
*/
    
    //thinking process: O(n)/O(n)
    
    //given a flight 2D array as bookings,[i,j,k]->from i to j reserved k seats,
    //return an array which contains seats for all stops, all stops is another parameter n
    
    //this solution is to leverage [start, end, end+1] relationship, so this problem 
    //essentially is what to know for a range, how can we know the change on the edge easily,
    //i, j, eg, two case
    // i + 1 = j, so we have to substract the value in previous range, so the correct value 
    //on j can be correct. 
    // j -i > 1, this would be ok, because we will substract value on i + 1, and it would be 0 if there is no 
    //seats on this timepoint
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int[] b : bookings) {
            res[b[0] - 1] += b[2];
            if (b[1] < n) res[b[1]] -= b[2];
        }
        for (int i = 1; i < n; ++i) res[i] += res[i - 1];
        return res;
    }

    //TLE solution
    public int[] corpFlightBookings_TLE(int[][] bookings, int n) {
        if(bookings == null || bookings.length < 1) return new int[0];
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] booking : bookings) {
            for(int i = booking[0]; i<= booking[1];i++) {
                map.put(i, map.getOrDefault(i, 0) + booking[2]);
            }
        }
        int[] res = new int[n];
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res[entry.getKey() - 1] = entry.getValue();
        }
        
        return res;
    }
}
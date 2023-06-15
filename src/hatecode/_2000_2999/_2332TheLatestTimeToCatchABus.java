import java.util.*;

public class _2332TheLatestTimeToCatchABus {
    
    /*
    2332. The Latest Time to Catch a Bus

    You are given a 0-indexed integer array buses of length n, where buses[i] represents the departure time of the ith bus. You are also given a 0-indexed integer array passengers of length m, where passengers[j] represents the arrival time of the jth passenger. All bus departure times are unique. All passenger arrival times are unique.

You are given an integer capacity, which represents the maximum number of passengers that can get on each bus.

When a passenger arrives, they will wait in line for the next available bus. You can get on a bus that departs at x minutes if you arrive at y minutes where y <= x, and the bus is not full. Passengers with the earliest arrival times get on the bus first.

More formally when a bus arrives, either:

If capacity or fewer passengers are waiting for a bus, they will all get on the bus, or
The capacity passengers with the earliest arrival times will get on the bus.
Return the latest time you may arrive at the bus station to catch a bus. You cannot arrive at the same time as another passenger.

Note: The arrays buses and passengers are not necessarily sorted.

 

Example 1:

Input: buses = [10,20], passengers = [2,17,18,19], capacity = 2
Output: 16
     
     
    */    

   /*
      thinking process: O(nlgn + mlgm) 

      the problem is to say:  given passenger arrive time and bus depearture as integer array P and B, 
      each bus capacity is cap, return the latest time if you want to catch on a bus. 


      the problem is tricky because it is more on you how to solve this problem instead of algo or DS, 
      so the key is how to find the latest time to catch the bus.

      


   */
    public int latestTimeCatchTheBus(int[] B, int[] P, int cap) {
        if (B == null || B.length < 1 || P == null || P.length < 1 || cap < 1) return -1;
        Arrays.sort(B);
        Arrays.sort(P);
        
        int n = B.length, m = P.length;
        
        int c = 0;
        int j = 0;
        for(int t : B) {
            c = cap;
            while(c > 0 && j < m && P[j] <= t) {
                c--;
                j++;
            }
        }
        
        int res = c > 0 ? B[n-1] : P[j];
        
        while( j >=0 && res == P[j]) {
            j--;
            res--;
        }
        
        return res;
    }
    
}
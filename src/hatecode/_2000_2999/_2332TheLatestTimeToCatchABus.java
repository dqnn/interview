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


      we can think with exetrem case:

      1. suppose you are the only people, you only need to catch the last bus, the answer is last bus dep time
      2. suppose bus have infinite cap, you only need to catch the last bus, the answer is last bus dep time
      3. suppose bus cap is 1 and only 1 bus, you need to be first, the first passengae[0] - 1;
      

      so we will just need to focus on last bus, if it has enough cap, then bus depar time is the answer.
      if not, then we have to count back, if there is not successive numbers, first is the answer

      B = [10,20], 
      P = [2,17,18,19], 
      cap = 2

      10 will take 2, when 20 comes, it will only take 17,18, so the earliest is 16 

       B = [10,20], 
       P = [2,17], 
       cap = 2     
       
       it will be 20, just the bus time, 



   */
    public int latestTimeCatchTheBus(int[] B, int[] P, int cap) {
        if (B == null || B.length < 1 || P == null || P.length < 1 || cap < 1) return -1;
        Arrays.sort(B);
        Arrays.sort(P);
        
        int n = B.length, m = P.length;
        
        // c is capacity to know last bus is full or not, j is the pointer point to passenage not onboarding the bus
        int c = 0;
        int j = 0;
        for(int t : B) {
            c = cap;
            while(c > 0 && j < m && P[j] <= t) {
                c--;
                j++;
            }
        }
        
        //j step back to point to last bus last person
        j--;
        int res = c > 0 ? B[n-1] : P[j];
        
        // count back
        while( j >=0 && res == P[j]) {
            j--;
            res--;
        }
        
        return res;
    }
    
}
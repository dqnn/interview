package hatecode._0001_0999;
import java.util.*;
public class _881BoatsToSavePeople {
/*
881. Boats to Save People
The i-th person has weight people[i], and each boat can carry a maximum weight of limit.

Each boat carries at most 2 people at the same time, provided the sum of the weight of those people is at most limit.

Return the minimum number of boats to carry every given person.  (It is guaranteed each person can be carried by a boat.)

 

Example 1:

Input: people = [1,2], limit = 3
Output: 1
Explanation: 1 boat (1, 2)
*/
    
    //Two Pointers, so since one boat can only hold two persons
    
    //so if people[l] + people[r] <= limit then that's done, l ++ and r--
    //if > limit, then just r--, heavy can have his own boat
    
    //follow up: how about no people limit, how can we group them multiple packsack problems then
    //O(nlgn)/O(1)
    public int numRescueBoats_Greedy(int[] people, int limit) {
        if (people == null || people.length < 1) return 0;
        
        int N = people.length;
       
        Arrays.sort(people);
        int l = 0, r = people.length -1;
        int res = 0;
        while(l < r) {
            res++;
            if (people[l] + people[r] <= limit) l++;
            
            r--;
        }
        if (l ==r ) res++;
        return res;
    }
    
    //O(n)/O(M) but M should be constant
    
    //bucket sort, actually group concepts, so we put same weight people into same bucket 
    public int numRescueBoats(int[] people, int limit) {
        int[] buckets = new int[limit+1];
        Arrays.stream(people).forEach(e->buckets[e]++);
        
        int start = 0;
        int end = buckets.length - 1;
        int res = 0;
        while(start <= end) {
			//make sure the start always point to a valid number
            while(start <= end && buckets[start] <= 0) start++;
			//make sure end always point to valid number
            while(start <= end && buckets[end] <= 0) end--;
			//no one else left on the ship, hence break.
            if(buckets[start] <= 0 && buckets[end] <= 0) break;
            
            res++;
            if(start + end <= limit) buckets[start]--; // both start and end can carry on the boat
            buckets[end]--;
        }
        return res;
    }
    
}
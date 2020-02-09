package hatecode._1000_1999;

import java.util.*;
public class _1051HeightChecker {
/*
1051. Height Checker
Students are asked to stand in non-decreasing order of heights for an annual photo.

Return the minimum number of students not standing in the right positions.  (This is the number of students that must move in order for all students to be standing in non-decreasing order of height.)

 

Example 1:

Input: [1,1,4,2,1,3]
Output: 3
*/
    //thinking process:O(n)/O(max(hs[i]))
    //the problem is to say: given an array of heights of students, they should be 
    //in non-decrease order, now find out how many are not in correct order, 
    //original problem has one issue that, do we need to find out 
    //[1,2,1,2,1,1,1,2,1], if we sort and compare it would be 4, 
    //but we can move each 2 to end of the queue, it would be 3, so definition of the problem
    //would be how many elements are out of order
    
    //current solution is to get each integer frequency, then 
    //we move from 1 to max, note while loop in for
    //if we find hs[i] != curH means we found one not in correct place, so res++;
    //and we need decrease the count because we visit the element already
    public int heightChecker(int[] hs) {
        if(hs == null || hs.length < 1) return 0;
        
        int n = hs.length;
        int[] buckets = new int[101];
        Arrays.stream(hs).forEach(e->buckets[e]++);
        
        int curH = 0, res = 0;
        for(int i = 0; i <n; i++) {
            while(buckets[curH] == 0) curH++;
            
            if(hs[i] != curH) res++;
            //move one out of buckets and continue
            buckets[curH]--;
        }
        
        return res;
    }
}
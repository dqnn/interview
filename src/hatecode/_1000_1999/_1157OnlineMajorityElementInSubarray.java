package hatecode._1000_1999;

import java.util.*;
public class _1157OnlineMajorityElementInSubarray {
/*
 * 1157. Online Majority Element In Subarray
Implementing the class MajorityChecker, which has the following API:

MajorityChecker(int[] arr) constructs an instance of MajorityChecker with the given array arr;
int query(int left, int right, int threshold) has arguments such that:
0 <= left <= right < arr.length representing a subarray of arr;
2 * threshold > right - left + 1, ie. the threshold is always a strict majority of the length of the subarray
Each query(...) returns the element in arr[left], arr[left+1], ..., arr[right] that occurs at least threshold times, or -1 if no such element exists.

 

Example:

MajorityChecker majorityChecker = new MajorityChecker([1,1,2,2,1,1]);
majorityChecker.query(0,5,4); // returns 1
majorityChecker.query(0,3,3); // returns -1
majorityChecker.query(2,3,2); // returns 2
 

Constraints:

1 <= arr.length <= 20000
1 <= arr[i] <= 20000
For each query, 0 <= left <= right < len(arr)
For each query, 2 * threshold > right - left + 1
The number of queries is at most 10000
*/

    //thinking process: O(n+ lgn)/O(n)
    
    //this is the binary search solution
    Map<Integer, List<Integer>> map = new HashMap<>();
    int[] A;
    public _1157OnlineMajorityElementInSubarray(int[] arr) {
        A = arr;
        for(int i = 0; i < A.length; i++) {
            map.computeIfAbsent(A[i], v->new ArrayList<>()).add(i);
        }
    }
    
    private int find_low(List<Integer> list, int t) {
        int l = 0, r = list.size();
        while(l < r) {
            int m = (r - l) /2 + l;
            int idx = list.get(m);
            if(idx >= t) r = m;
            else l = m + 1;
        }
        return l;
    }
    
    private int find_high(List<Integer> list, int t) {
        int l = 0, r = list.size();
        while(l < r) {
            int m = (r - l) /2 + l;
            int idx = list.get(m);
            if(idx <= t) l = m + 1;
            else r = m;
        }
        return l-1;
    }
    
    public int query(int left, int right, int threshold) {
        Random rand_gen = new Random();

          for(int i=0;i<20;i++){
              int rand_index = rand_gen.nextInt(right-left+1) + left;
              int num = this.A[rand_index];
              
              int low_index = find_low(map.get(num), left);
              int high_index = find_high(map.get(num), right);
              
              if(high_index-low_index+1>=threshold){
                  //System.out.println(high_index + " " + low_index);
                  return num;
              }
          }
        
          return -1;
    }
}

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */
package hatecode._2000_2999;

import java.util.Arrays;
import java.util.TreeMap;

public class _2007FindOriginalArrayFromDoubledArray {
    
    
    /*
    2007. Find Original Array From Doubled Array
    An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.

Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.

 

Example 1:

Input: changed = [1,3,4,2,6,8]
Output: [1,3,4]
Explanation: One possible original array could be [1,3,4]:
- Twice the value of 1 is 1 * 2 = 2.
- Twice the value of 3 is 3 * 2 = 6.
- Twice the value of 4 is 4 * 2 = 8.
Other original arrays could be [4,3,1] or [3,1,4].
    
    */
    
    /*
    thinking process: O(n + k)

    the problem is to say: given one integer array A, each element will have its double value or it is doubled already.
    returen the orginal one

    we have 2 ways to solve this problem: 
    1. use frequency count, 
    2. use treemap num->count, so we can start from small to big numbers

    for example
    [1,3,4,2,6,8]
    
    1-1,2-1,3-1,4-1,6-1,8-1

    if we use freqency count, then we will start from 0 -> 8, for each element
    we will detect whether 2*i exist, if not, then return new int[0]; 
    if yes, then decrease f[2*i]--, f[i]--,  note: we should while on f[i] > 0, because we can 
    have multiple i


    */
    
    
    public int[] findOriginalArray_TC(int[] A) {
        if (A == null || A.length < 1 || A.length % 2 == 1) return new int[]{};
        
        int n = A.length;
        int max = Arrays.stream(A).max().getAsInt();
        int[] f = new int[2*max+1];
        Arrays.stream(A).forEach(a->f[a]++);
        
        int[] res = new int[n/2];
        int idx = 0;
        for(int i = 0; i< 2*max + 1; i++) {
            if (f[i] ==0 ) continue;
            while (f[i] > 0) {
                int temp = 2 * i;
                if (f[temp] > 0) {
                    f[temp]--;
                    res[idx++] = i;
                    f[i]--;
                } else {
                    return new int[0];
                }
            }
        }
        return res;     
    }
    
    
    /*
     space friendly
    */
    
     public int[] findOriginalArray(int[] A) {
        if (A == null || A.length < 1 || A.length % 2 == 1) return new int[0];
         
        int n = A.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
         
        Arrays.stream(A).forEach(a->map.put(a, map.getOrDefault(a, 0) + 1));
        int[] res = new int[n/2];
        if (map.size() == 1 && map.firstKey() == 0) return res;
        int index = 0;
        for(int k: map.keySet()) {
            if (map.get(k) == 0) continue;
            while (map.get(k) > 0) {
                int temp = 2 * k;
                if (map.getOrDefault(temp, 0) > 0) {
                    map.put(temp, map.get(temp) - 1);
                    res[index++] = k;
                    map.put(k, map.get(k)-1);
                } else return new int[0];
            }
        }
         
        return res;
     }
    
    
}

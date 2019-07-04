package hatecode;

import java.util.*;
public class ConfusingNumberII {
/*
1088. Confusing Number II
tags: recursive, digits
We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.

A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.(Note that the rotated number can be greater than the original number.)

Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.

 

Example 1:

Input: 20
Output: 6
Explanation: 
The confusing numbers are [6,9,10,16,18,19].
6 converts to 9.
9 converts to 6.
10 converts to 01 which is just 1.
16 converts to 91.
18 converts to 81.
19 converts to 61.
*/
    
    //O(5^n)/O()
    Map<Integer, Integer> map = new HashMap<>();
    int N = -1;
    int res = 0;
    public int confusingNumberII(int N) {
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);
        
        if (N == 1e9) {
            res++;
            N--;
        }
        
        this.N = N;
        helper(0, 0);
        return res;
    }
    
    private void helper(int p, int cur) {
        if(p > 9 || cur > N) return;
        
        if(isConfusing(cur)) res++;
        
        for(int d: map.keySet()) {
            if(p == 0 && d == 0) continue;
            helper(p+1, cur*10 + d);
        }
    }
    
    private boolean isConfusing(int d) {
        int remind = 0;
        int old = d;
        while(d > 0) {
            if(!map.containsKey(d%10)) return false;
            remind = remind * 10 + map.getOrDefault(d % 10, 0);
            d /= 10;
        }
        
        return remind != old;
    }
}
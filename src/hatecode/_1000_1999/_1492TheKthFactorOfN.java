package hatecode._1000_1999;

import java.util.*;

public class _1492TheKthFactorOfN {
    
    /*
    1492. The kth Factor of n

    You are given two positive integers n and k. A factor of an integer n is defined as an integer i where n % i == 0.

Consider a list of all factors of n sorted in ascending order, return the kth factor in this list or return -1 if n has less than k factors.

 

Example 1:

Input: n = 12, k = 3
Output: 3
    */

    /*
     * thinking process: 
     */
    public int kthFactor(int n, int k) {
        if (n <= 0) return -1;
         
         int cnt = 0;
         List<Integer> l = new ArrayList<>();
         for(int i = 1; i * i <=n; i++) {
             if (n % i == 0) {
                 if (i*i !=n) l.add(n/i);
                 if(++cnt == k) return i;
             }
             
         }
         
         if (l.size() + cnt < k) return -1;
         
         return l.get(l.size() - (k - cnt));
     }
    
    
    public int kthFactor_BF(int n, int k) {
        if (n <= 0) return -1;
        
        int cnt = 0;
        for(int i = 1; i * i<=n; i++) {
            if (n % i == 0) {
                cnt++;
                if (cnt== k) {
                    return i;
                }
            }
        }
        
        return -1;
    }
    
     
}
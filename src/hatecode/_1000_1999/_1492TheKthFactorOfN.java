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
     * thinking process: O(sqrt(n))/O(sqrt(n))
     * 
     * the problem is to say: given one integer n, return the kth factor. 
     * for example n = 12, k = 3, so factors list = [1, 2, 3, 4, 6, 12] return 3 
     * 
     * we use l to store all factors n/i when n %i = 0,  (i * i = n), but we do not include i, like 2*2 =4, we should not include 2 in l
     * because it will double count  in later 
     * 
     * n=12, k =5 
     * 
     * l = [12,6, 4]  cnt = 3
     * 
     * 
     */
    public int kthFactor(int n, int k) {
        if (n <= 0) return -1;
         
         int cnt = 0;
         List<Integer> l = new ArrayList<>();
         for(int i = 1; i * i <=n; i++) {
             if (n % i == 0) {
                 //because we only add  bigger half factors into l, while smaller ones will be in cnt
                 if (i*i !=n) l.add(n/i);
                 if(++cnt == k) return i;
             }
             
         }
         
         //because k is more than all factors count , for example n = 12, k = 10
         if (l.size() + cnt < k) return -1;
         
         //return the factor in latter half
         return l.get(l.size() - (k - cnt));
     }
    
    

     //brtual force 
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
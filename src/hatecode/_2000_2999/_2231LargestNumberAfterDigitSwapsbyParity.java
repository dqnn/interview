package hatecode._2000_2999;

import java.util.*;
public class _2231LargestNumberAfterDigitSwapsbyParity {
    /*
    2231. Largest Number After Digit Swaps by Parity
    You are given a positive integer num. You may swap any two digits of num that have the same parity (i.e. both odd digits or both even digits).

Return the largest possible value of num after any number of swaps.

 

Example 1:

Input: num = 1234
Output: 3412
    */

    
    //O(n^2)/O(1)
    public int largestInteger_BF(int num) {
        char[] A = String.valueOf(num).toCharArray();
        
        for(int i = 0; i<A.length; i++) {
            for(int j = i+1; j< A.length; j++) {
                if (A[j] > A[i] && (A[j] - A[i])% 2 == 0) {
                    char temp = A[j];
                    A[j] = A[i];
                    A[i] = temp;
                }
            }
        }
        return Integer.valueOf(new String(A));
    }
    
     // O(nlgn)/O(n)
     public int largestInteger_(int num) {
        PriorityQueue<Integer> q1 = new PriorityQueue<>((a, b)->(b - a));
        PriorityQueue<Integer> q2 = new PriorityQueue<>((a, b)->(b - a));
         
         return 0;
        
         
     }
}
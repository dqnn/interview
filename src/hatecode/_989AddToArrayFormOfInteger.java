package hatecode;

import java.util.*;
public class _989AddToArrayFormOfInteger {
/*
989. Add to Array-Form of Integer
For a non-negative integer X, the array-form of X is an array of its digits in left to right order.  For example, if X = 1231, then the array form is [1,2,3,1].

Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.

 

Example 1:

Input: A = [1,2,0,0], K = 34
Output: [1,2,3,4]
Explanation: 1200 + 34 = 1234
*/
    //thinking process: 
    //given an int array and a K, return the plus result as List<Integer> 
    //use K as carrier and every digit to add K, actually it is the same and fancy idea
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new ArrayList<>();
        
        for(int i = A.length - 1; i >=0; i--) {
            res.add(0, (A[i] + K) % 10);
            K = (A[i] + K) / 10;
        }
        
        while(K > 0) {
            res.add(0, K % 10);
            K /= 10;
        }
        
        return res;
    }
}
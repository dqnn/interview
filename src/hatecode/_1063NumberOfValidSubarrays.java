package hatecode;
import java.util.*;
public class _1063NumberOfValidSubarrays {
/*
1063. Number of Valid Subarrays
Given an array A of integers, return the number of non-empty continuous subarrays that satisfy the following condition:

The leftmost element of the subarray is not larger than other elements in the subarray.

 

Example 1:

Input: [1,4,2,5,3]
Output: 11
*/
    //thinking process: 
    //given an array, return the subarray count which leftmost element is smaller than any others in the array
    
    //since the smallest one is on leftmost, so we can easily think about stacks
    public int validSubarrays(int[] A) {
        if (A == null || A.length < 1) return 0;
        
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for(int num : A) {
            while (!stack.isEmpty() && num < stack.peek()) stack.pop();
            stack.push(num);
            res += stack.size();
        }
        return res;
    }
}
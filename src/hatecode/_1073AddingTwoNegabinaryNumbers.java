package hatecode;

import java.util.*;
public class _1073AddingTwoNegabinaryNumbers {
/*
1073. Adding Two Negabinary Numbers
Given two numbers arr1 and arr2 in base -2, return the result of adding them together.

Each number is given in array format:  as an array of 0s and 1s, from most significant bit to least significant bit.  For example, arr = [1,1,0,1] represents the number (-2)^3 + (-2)^2 + (-2)^0 = -3.  A number arr in array format is also guaranteed to have no leading zeros: either arr == [0] or arr[0] == 1.

Return the result of adding arr1 and arr2 in the same format: as an array of 0s and 1s with no leading zeros.

 

Example 1:

Input: arr1 = [1,1,1,1,1], arr2 = [1,0,1]
Output: [1,0,0,0,0]

Carry:          1 −1  0 −1  1 −1  0  0  0
First addend:         1  0  1  0  1  0  1
Second addend:        1  1  1  0  1  0  0 + addition
               --------------------------
Number:         1 −1  2  0  3 −1  2  0  1
-----------------------------------------
Bit (result):   1  1  0  0  1  1  0  0  1
 
( 1 + 1 = -2 = [1,1,0])
Carry:         1 -1
First addend:       1
Second addend:      1 + addition
               ------
Number:        1 -1 0
               ------
Bit (result):  1  1 0
**/
    //thinking process:
    
    //the problem is to say: given two arrays, from left to right,
    //-2^(len-1) + ...(-2)^0
    
    //return the sum of arr1 and arr2 as base 2
    //so right aligned, even position is positive, odd is negative,
    //
    //we visit from right to left, carry will change flag by each step
    //so if we found carry > 0, then next position should have opposite, because
    //number from - to + or + to -, 
    //
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int i = arr1.length - 1;
        int  j = arr2.length - 1;
        int carry = 0;
        Stack<Integer> stack = new Stack<>();
        
        while (i >= 0 || j >= 0 || carry != 0) {
           if (i >= 0) carry += arr1[i--];
           if (j >= 0) carry += arr2[j--];
            stack.push(carry & 1);
            carry = - (carry >> 1);
           
        }
        //remove leading 0 because we are right aligned, 
        //like [1,1,0,0], [1,0,0], this would become 0.
        while (!stack.isEmpty() && stack.peek() == 0) {
            stack.pop();
        }
        
        //just get them out, top is left aligned
        int[] res = new int[stack.size()];
        int index = 0;
        if (stack.isEmpty()) return new int[]{0};
        while (!stack.isEmpty()) {
            res[index++] = stack.pop();
        }
        return res;
    }
}
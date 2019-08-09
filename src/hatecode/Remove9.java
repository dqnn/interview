package hatecode;

import java.util.*;
class Remove9 {
/*
660. Remove 9
Start from integer 1, remove any integer that contains 9 such as 9, 19, 29...

So now, you will have a new integer sequence: 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, ...

Given a positive integer n, you need to return the n-th integer after removing. Note that 1 will be the first integer.

Example 1:
Input: 9
Output: 10
*/
    
    
    
    //thinking process: The problem is to say to give 1->n numbers, remove all numbers which contains 9 digit, then return the n-th number
    
    //the key is to calculate how many numbers which contains 9 from 1-> n, 
    //
    public int newInteger2(int n) {
        return 0;
    }
    public int newInteger(int n) {
        return Integer.parseInt(Integer.toString(n, 9));
    }
}
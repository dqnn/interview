package hatecode;
public class SumOfSquareNumbers {
/*
633. Sum of Square Numbers
Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:

Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
*/
    //O(n)/O(1)
    //thinking process: we search from 1->sqrt(c)space, and we use two pointers to find the value 
    //as fast as possible
    public boolean judgeSquareSum(int c) {
        if (c < 0) return false;
        
        int l = 0, r = (int)Math.sqrt(c);
        while(l <= r) {
            int cur = l * l + r * r;
            if(cur < c) l++;
            else if (cur > c) r--;
            else return true;
        }
        
        return false;
    }
}
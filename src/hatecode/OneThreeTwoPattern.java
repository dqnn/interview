package hatecode;

import java.util.*;
class OneThreeTwoPattern {
/*
456. 132 Pattern
Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False
*/
    //interview friendly, O(n) 
    //thinking process: it is easy to have a brute force, 3 for loops, the time complexity is 
    //O(n^3), another we can have a O(n^2) solution is to have min for o->i, from i+1->len we find 
    //one number nums[k] < nums[i] && min < nums[i]
    //now come to one pass O(n), we used a stack to store 
    public boolean find132pattern(int[] nums) {
        //s3 is the middle value
        int s3 = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            //we found the last one
            if (nums[i] < s3) return true;
            else {
                //here nums[i] is the biggest, we would like to pop all smaller in stack until
                //we the rest in stack are bigger than nums[i], so stack is increase from top to bottom
                //
                while (!stack.isEmpty() && nums[i] > stack.peek()) {
                    s3 = stack.pop();
                }
                stack.push(nums[i]);
            }
        }
        return false;
    }
    //O(n^2)
    public boolean find132pattern_Square(int[] nums) {
        for (int j = 0, min = Integer.MAX_VALUE; j < nums.length; j++) {
             min = Math.min(nums[j], min);
             if (min == nums[j]) continue;
             
             for (int k = nums.length - 1; k > j; k--) {
                 if (min < nums[k] && nums[k] < nums[j]) return true;
             }
         }
         
         return false;
    }
}
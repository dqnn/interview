package hatecode._0001_0999;
import java.util.*;
public class _946ValidateStackSequences {
/*
946. Validate Stack Sequences
Given two sequences pushed and popped with distinct values, return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.

 

Example 1:

Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
Output: true
Explanation: We might do the following sequence:
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
*/
  //this is the way how we simulate the stack operations
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed == null && popped == null) return true;
        if (pushed == null || popped == null || pushed.length != popped.length) return false;

        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for(int p : pushed) {
            stack.push(p);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            } 
        }
        
        return stack.isEmpty();
    }
}
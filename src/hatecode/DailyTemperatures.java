package hatecode;

import java.util.Stack;

public class DailyTemperatures {
/*
739. Daily Temperatures
Given a list of daily temperatures T, return a list such that, for each day in the input, 
tells you how many days you would have to wait until a warmer temperature. If there is no 
future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], 
your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. 
Each temperature will be an integer in the range [30, 100].
T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be 
    [1,  1,  4,   2, 1,   1,  0,  0]


*/
    //interview friendly
    //should remember how we handle the two dimension, value and index
    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length < 1) {
            return null;
        }
        int[] res = new int[T.length];
        //stack to store the idx
        Stack<Integer> stack = new Stack<>();
        //i = len -1, stack: len-1
        //i= len- 2,stack: 76 > 73, so stack pop there is null, then res = 0
        //stack is used to store the max number
        for(int i = res.length - 1; i>=0;i--) {
            //look for idx in stack which is smaller than current T[i]
            while(!stack.isEmpty() && T[i] >= T[stack.peek()]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0: stack.peek() - i;
            stack.push(i);
        }
        return res;
    }
}
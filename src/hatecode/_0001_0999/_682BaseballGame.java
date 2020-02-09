package hatecode._0001_0999;
import java.util.*;
public class _682BaseballGame {
/*
682. Baseball Game
You're now a baseball game point recorder.

Given a list of strings, each string can be one of the 4 following types:

Integer (one round's score): Directly represents the number of points you get in this round.
"+" (one round's score): Represents that the points you get in this round are the sum of the last two valid round's points.
"D" (one round's score): Represents that the points you get in this round are the doubled data of the last valid round's points.
"C" (an operation, which isn't a round's score): Represents the last valid round's points you get were invalid and should be removed.
Each round's operation is permanent and could have an impact on the round before and the round after.

You need to return the sum of the points you could get in all the rounds.

Example 1:
Input: ["5","2","C","D","+"]
Output: 30
*/
    public int calPoints(String[] ops) {
        if (ops == null || ops.length < 1) return 0;
        
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for(String str : ops) {
            if (str.equals("C")) {
                if (stack.isEmpty()) continue;
                res -= stack.pop();
            } else if(str.equals("D")) {
                if (stack.isEmpty()) continue;
                res += 2* stack.peek();
                stack.push(2*stack.peek());
            } else if (str.equals("+")) {
                if (stack.isEmpty()) continue;
                int last = stack.pop();
                int curRes = last + stack.peek();
                res += curRes;
                stack.push(last);
                stack.push(curRes);
            } else {
                Integer s = Integer.valueOf(str);
                stack.push(s);
                res += s;
            }
        }
        return res;
    }
}
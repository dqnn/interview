package hatecode;

import java.util.Arrays;
import java.util.Stack;

public class _735AsteroidCollision {
/*
 * 735. Asteroid Collision
We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the 
sign represents its direction (positive meaning right, negative meaning left). 
Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, 
the smaller one will explode. If both are the same size, both will explode. 
Two asteroids moving in the same direction will never meet.

Example 1:
Input: 
asteroids = [5, 10, -5]
Output: [5, 10]
Explanation: 
The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
*/
    //thinking process: the problem is to say if two number can disappear then what;s the last 
    // since they line up, index adjacent, so stack would be a wonderful place
    
    //we use stack to store the stone 1: cur > 0 or first element is current peek() < 0, 
    // and then 
    public int[] asteroidCollision(int[] a) {
        if (a == null || a.length < 1) return a;
        Stack<Integer> stack = new Stack<>();
        for(int cur: a) {
            if (cur > 0) {
                stack.push(cur);
            } else {
                while(!stack.isEmpty() && stack.peek() > 0 && -cur > stack.peek()) {
                    stack.pop();
                }
               
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(cur);
                } else if (stack.peek() == -cur) {
                    stack.pop();
                }
            }
        }
        return stack.stream().mapToInt(i->i).toArray();
    }
    //no storage solution, as follow up 
    public int[] asteroidCollision2(int[] asteroids) {
        if (asteroids.length < 2) {
            return asteroids;
        }

    int right = 1;
    int left =0;
    while (right < asteroids.length) {
        if(left==-1) {
            asteroids[0] = asteroids[right];
            left=0;
            right++;
            continue;
        } else {
            if (asteroids[left] > 0 && asteroids[right] < 0) {
                if (Math.abs(asteroids[left]) == Math.abs(asteroids[right])) {
                    left--;
                    right++;
                } else if (Math.abs(asteroids[left]) > Math.abs(asteroids[right])) {
                    right++;
                } else {
                    left--;
                }
            } else {
                left++;
                asteroids[left] = asteroids[right];
                right++;
            }
        }
    }
    return Arrays.copyOf(asteroids, left+1);
}
}
package leetcode;

import java.util.*;

class MaxStack {
/*
 * 716. Max Stack
Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. 
If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
 */

    /** initialize your data structure here. */
    Stack<Integer> stack;
    Stack<Integer> max;
    public MaxStack() {
        this.stack = new Stack<>();
        this.max = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        int pre = max.isEmpty() ? Integer.MIN_VALUE: max.peek();
        if (pre <= x) {
            max.push(x);
        }
    }
    
    public int pop() {
        int res = stack.pop();
        if(res == max.peek()) {
            max.pop();
        }
        return res;
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return max.peek();
    }
    
    public int popMax() {
        if (stack.peek() == max.peek()) return pop();
        Stack<Integer> temp = new Stack<>();
        while(top() != peekMax()) temp.push(pop());
        int res = pop();
        while(!temp.isEmpty()) {
            push(temp.pop());
        }
        return res;
    }
    public static void main(String[] args) {
        MaxStack ms = new MaxStack();
        ms.push(1);
        ms.push(1);
        ms.push(4);
        ms.push(2);
        ms.push(1);
        
        System.out.println(ms.popMax());
       
    }
}

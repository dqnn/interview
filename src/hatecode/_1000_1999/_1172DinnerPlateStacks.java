package hatecode._1000_1999;

import java.util.*;
public class _1172DinnerPlateStacks {
/*
1172. Dinner Plate Stacks
You have an infinite number of stacks arranged in a row and numbered (left to right) from 0, each of the stacks has the same maximum capacity.

Implement the DinnerPlates class:

DinnerPlates(int capacity) Initializes the object with the maximum capacity of the stacks.
void push(int val) Pushes the given positive integer val into the leftmost stack with size less than capacity.
int pop() Returns the value at the top of the rightmost non-empty stack and removes it from that stack, and returns -1 if all stacks are empty.
int popAtStack(int index) Returns the value at the top of the stack with the given index and removes it from that stack, and returns -1 if the stack with that given index is empty.
Example:

Input: 
["DinnerPlates","push","push","push","push","push","popAtStack","push","push","popAtStack","popAtStack","pop","pop","pop","pop","pop"]
[[2],[1],[2],[3],[4],[5],[0],[20],[21],[0],[2],[],[],[],[],[]]
Output: 
[null,null,null,null,null,null,2,null,null,20,21,5,4,3,1,-1]

*/
    
    //thinking process: 
    //the problem is to say: given pop, push, popAtStack(index), implement a 
    //infinite stack APIs, each stack has capacity limit, and it has several characteristic:
    //1: push always from left to right, first un-full stack
    //2. pop from right to left, if it is empty, then we should remove,
    //3 popAtStack() then we should stick there,
    
    //so we can have multiple patterns for above APIs, and pop, popAt they all require
    //to stop current stack, so we use a l to indicate current stack can fill in new 
    //elements, 
    
    //we use an arrayList to store all stacks so it can expand infinitely 
    
    int cap = 0;
    //l means current for index l stack, we can push an element
    int l =0;
    List<Stack<Integer>> stacks;
    public _1172DinnerPlateStacks(int capacity) {
        cap = capacity;
        stacks = new ArrayList<>();
    }
    
    //stack always have one empty stack in the end
    public void push(int val) {
       if(stacks.size()==l) stacks.add(new Stack<>());
        stacks.get(l).push(val);
        //l maybe move back in the middle of whole stacks, and stick there
        while(l<stacks.size()&&stacks.get(l).size()==cap) l++;
    }
    
    public int pop() {
        //we move right to left, and continue check each stack whether it is good to remove 
        int size=stacks.size();
        while(stacks.size()>0&&stacks.get(size-1).size()==0){
            stacks.remove(size-1);
            size--;
        }
        //edge case, we shold remove all
        if(stacks.size()==0) return -1;
        //correct position, then pop
        int res=stacks.get(size-1).pop();
        return res;
    }
    
    public int popAtStack(int index) {
        if(index<0||index>=stacks.size()) return -1;
        if(stacks.get(index).size()==0) return -1;
        int res=stacks.get(index).pop();
        if(l>index) l=index;
        return res;
        
    }
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */
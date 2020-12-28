package hatecode._1000_1999;

import java.util.*;
public class _1670DesignFrontMiddleBackQueue {
/*
1670. Design Front Middle Back Queue
Design a queue that supports push and pop operations in the front, middle, and back.

Implement the FrontMiddleBack class:

FrontMiddleBack() Initializes the queue.
void pushFront(int val) Adds val to the front of the queue.
void pushMiddle(int val) Adds val to the middle of the queue.
void pushBack(int val) Adds val to the back of the queue.
int popFront() Removes the front element of the queue and returns it. If the queue is empty, return -1.
int popMiddle() Removes the middle element of the queue and returns it. If the queue is empty, return -1.
int popBack() Removes the back element of the queue and returns it. If the queue is empty, return -1.
Notice that when there are two middle position choices, the operation is performed on the frontmost middle position choice. For example:

Pushing 6 into the middle of [1, 2, 3, 4, 5] results in [1, 2, 6, 3, 4, 5].
Popping the middle from [1, 2, 3, 4, 5, 6] returns 3 and results in [1, 2, 4, 5, 6].
*/

    //thinking process: O(1)/O(n)
    
    //the problem is to say: implement a ds, which could be O(1), and it has 6 operations 
    // at 3 locations of the queue, {Front, back, middle} x {pop, push}
    
    //middle is defined first element is size is even. 
    
    //brute force is to use double link list, main head, tail and middle pointer.
    
    //here is another tricky solution, we maintain two dequeues(l, r), which can operate easily for 
    //heads and tails, thinking about we breaking one queue into two parts, r should be more than 1 element
    // to left. pretty similiar to median value problem
    private Deque<Integer> l, r;
    public _1670DesignFrontMiddleBackQueue() {
        l = new LinkedList<>();
        r = new LinkedList<>();
    }
    
    public void pushFront(int val) {
        l.offerFirst(val);
        if (l.size() > r.size()) r.offerFirst(l.pollLast());
    }
    
    public void pushMiddle(int val) {
        if (l.size() == r.size()) r.offerFirst(val);
        else l.offerLast(val);
    }
    
    
    public void pushBack(int val) {
        if(l.size()<r.size()) l.offerLast(r.pollFirst());
        r.offerLast(val);
    }
    
    public int popFront() {
        if(l.size()<r.size()) l.offerLast(r.pollFirst());
        return l.isEmpty()?-1:l.pollFirst();
    }
    
    public int popMiddle() {
        if(l.size()==r.size()) return l.isEmpty()?-1:l.pollLast();
        else return r.isEmpty()?-1:r.pollFirst();
    }
    
    public int popBack() {
        if(l.size()==r.size()&&!l.isEmpty()) r.offerFirst(l.pollLast());
        return r.isEmpty()?-1:r.pollLast();
    }
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */
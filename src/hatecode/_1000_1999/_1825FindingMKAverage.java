package hatecode._1000_1999;

import java.util.*;

public class _1825FindingMKAverage {
/*
1825. Finding MK Average
You are given two integers, m and k, and a stream of integers. You are tasked to implement a data structure that calculates the MKAverage for the stream.

The MKAverage can be calculated using these steps:

If the number of the elements in the stream is less than m you should consider the MKAverage to be -1. Otherwise, copy the last m elements of the stream to a separate container.
Remove the smallest k elements and the largest k elements from the container.
Calculate the average value for the rest of the elements rounded down to the nearest integer.
Implement the MKAverage class:

MKAverage(int m, int k) Initializes the MKAverage object with an empty stream and the two integers m and k.
void addElement(int num) Inserts a new element num into the stream.
int calculateMKAverage() Calculates and returns the MKAverage for the current stream rounded down to the nearest integer.
 

Example 1:

Input
["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"]
[[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
Output
[null, null, null, -1, null, 3, null, null, null, 5]
*/


/*
 * interview friendly: 
 *   addElement O(lgk)
 *   calculateMKAverage O(1)
 * 
 * the problem is to say: given m and k, m is the window size, k is the k smallest or k biggest numbers 
 * 
 * when streaming of numbers come, we will only calculate numbers inside window size = m, 
 * remove k smallest and k biggest, calculate the left average
 * 
 *     l              mid         r
 * -------------    ------    ------------
 * 
 * 
 * q is linkedlist to keep m elements, 
 * 
 * l, mid, r are 3 treeset since we would like to contain the elements into 3 sorted set. 
 * 
 * add sequence 
 * when a new number like 3 come, it will always arrive l, if overflow(>k) then go to r, if r overflow, then goto mid
 * this way we can manage mid easier
 * 
 * but if window size > m, then we need to remove elements out of 
 * 
 * 
 */
    
    class Node {
        int val;
        int id;
        public Node(){}
        public Node(int val, int id) {
            this.val = val;
            this.id = id;
        }
    }
    
    Comparator<Node> compExp = (a, b) -> (a.val == b.val ? Integer.compare(a.id, b.id) : Integer.compare(a.val, b.val));
    TreeSet<Node> l = new TreeSet<Node>(compExp);
    TreeSet<Node>     r = new TreeSet<Node>(compExp);
    TreeSet<Node> mid = new TreeSet<Node>(compExp);
    
    int midSize = 0;
    int m = 0;
    int k = 0;
    Queue<Node> q = new LinkedList<>();
    int sum = 0;
    int id = 0;
    public _1825FindingMKAverage(int m, int k) {
        this.m = m;
        this.k = k;
        this.midSize = m - 2*k;
    }
    
    public void addElement(int num) {
        Node node = new Node(num, id++);
        q.offer(node);
        if (q.size() > m) {
            remove(q.poll());
        }
        add(node);
    }
    
    public int calculateMKAverage() {
        if (q.size() < m) return -1;
        
        return sum/midSize;
    }
    
    
    private void add(Node node) {
        l.add(node);
        if (l.size() > k) {
            Node rmd = l.pollLast();
            r.add(rmd);
        }
        
        if(r.size() > k) {
            Node rmd = r.pollFirst();
            sum += rmd.val;
            mid.add(rmd);
        }
    }
    
    
    private void remove(Node node) {
        if (l.contains(node)) {
            l.remove(node);
        } else if (mid.contains(node)) {
            mid.remove(node);
            sum -= node.val;
        } else {
            r.remove(node);
        }
        
        if (l.size() < k) {
            Node rmd = mid.pollFirst();
            sum -= rmd.val;
            l.add(rmd);
        }
        
        if (r.size() < k) {
            Node rmd = mid.pollLast();
            sum -= rmd.val;
            r.add(rmd);
        }
    }
}

/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */
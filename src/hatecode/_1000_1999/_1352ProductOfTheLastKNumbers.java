package hatecode._1000_1999;

import java.util.*;
public class _1352ProductOfTheLastKNumbers {
/*
1352. Product of the Last K Numbers
Design an algorithm that accepts a stream of integers and retrieves the product of the last k integers of the stream.

Implement the ProductOfNumbers class:

ProductOfNumbers() Initializes the object with an empty stream.
void add(int num) Appends the integer num to the stream.
int getProduct(int k) Returns the product of the last k numbers in the current list. You can assume that always the current list has at least k numbers.
The test cases are generated so that, at any time, the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.

 

Example:

Input
["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
[[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]

Output
[null,null,null,null,null,null,20,40,0,null,32]
*/

    //thinking process: O(1)/(n)
    
    //last K sum or product, so we use prefixProduct to store for 0->i the product
    //from 0->i, 
    List<Integer> A;
    public _1352ProductOfTheLastKNumbers() {
        A = new ArrayList<>();
        A.add(1);
    }
    
    public void add(int a) {
        if (a == 0) {
            A = new ArrayList<>();
            A.add(1);
        } else {
            int last = A.size() -1;
            A.add(A.get(last) * a);
        }
        
    }
    
    public int getProduct(int k) {
        int n = A.size();
        return k < n ? A.get(n-1)/A.get(n-k-1) : 0;
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
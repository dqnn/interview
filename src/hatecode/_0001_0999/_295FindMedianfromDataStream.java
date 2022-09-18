package hatecode._0001_0999;

import java.util.*;

/**
 * Date : Nov, 2017
 * Description : 295. Find Median from Data Stream
 * 
 * Median is the middle value in an ordered integer list. If the size of the list is even,
 *  there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
 */
public class _295FindMedianfromDataStream {
    /**
     * For example:

     addNum(1)
     addNum(2)
     findMedian() -> 1.5
     addNum(3)
     findMedian() -> 2

     1 2 -3 4
     -3 1 2 4

     small : 3 -1
     large : 2 4

     time : O(logn)
     space : O(n)
     */

    // here is a sorted list eg: 
    //https://chromium.googlesource.com/android_tools/+/refs/heads/master/sdk/sources/android-25/android/support/v7/util/SortedList.java?autodive=0%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F
    
    //interview friendly:
    //left is PQ sorted by desc, right is asc,
    //we always add number to left first, then if we found 
    /*
     * 1. left.size() > r.size() + 1, then we move biggest from left to right
     * 2. left.peek() > right.peek(). then move to right.
     * 
     * so we can always sure that left.size = right.size or left size = right size + 1
     */
    PriorityQueue<Integer> l, r;
    public _295FindMedianfromDataStream() {
        l = new PriorityQueue<>((a, b)->Integer.compare(b, a));
        r = new PriorityQueue<>();
    }
    
    public void addNum(int n) {
      l.add(n);
      if (l.size() > r.size() + 1) {
          r.add(l.poll());
      }
      
      while(!r.isEmpty() && l.peek() > r.peek()) {
          r.add(l.poll());
          l.add(r.poll());
      }
   // System.out.println("l =" + l +"  r=" + r);
        
    }
    
    public double findMedian() {
        if (l.size() == r.size()) {
            return 1.0* (l.peek() + r.peek())/2.0;
        } else {
            return 1.0* l.peek();
        }
    }
}

package hatecode._0001_0999;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FindMedianfromDataStream
 * Creator : duqiang
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
public class FindMedianfromDataStream {
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
    private PriorityQueue<Long> small;
    private PriorityQueue<Long> large;

    public FindMedianfromDataStream() {  // MedianFinder()
        small = new PriorityQueue<>();// they are all asc
        large = new PriorityQueue<>();// 
    }

    // the key is here, we use two list to store half of them, 
    // we do this like large only be allowed to be 1 more elment than small.
    // and the way how we preserve the small is awesome
    public void addNum(int num) {
        large.add((long)num);
        small.add(-large.poll());
        if (large.size() < small.size()) {
            large.add(-small.poll());
        }
    }

    public double findMedian() {
        return large.size() > small.size() ? large.peek() : (large.peek() - small.peek()) / 2.0;
    }
    
    //interview friendly:
    private Queue<Integer> min;
    private Queue<Integer> max;

    //so for the right, we always add the big number in minStack, this can help to 
    //keep the bigger number
    public void MedianFinder() {
        this.min = new PriorityQueue<>((a, b)->(a-b));
        this.max = new PriorityQueue<>((a, b)->(b-a));
    }
    
    public void addNum2(int num) {
        if(min.isEmpty()) min.offer(num);
        else if(min.peek() <= num) min.offer(num);
        else max.offer(num);
        
        rebalance();
    }
    //here is the key, min is on right side of the sorted array, so i prefer it has one more elment
    //than left, so this case will be normal, it will only rebalance is two cases:
    //1, right is 2 more elements than left
    //2 left has more elements than right
    private void rebalance() {
        if(min.size() > max.size() + 1) {
            max.offer(min.poll());
        } else if(max.size() > min.size()) min.offer(max.poll());
    }
    
    public double findMedian2() {
        if(min.size() == max.size()) return (min.peek() + max.peek()) / 2d;
        else return min.peek() * 1d;
    }
}

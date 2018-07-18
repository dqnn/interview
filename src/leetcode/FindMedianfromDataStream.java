package leetcode;

import java.util.PriorityQueue;

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
    
    // the more bruth-force way to find the data structure which can sort the list when added and get the median easily, but 
    // TreeSet and PriorityQueue, they do not have such interface to get the index of each element, we need a sortedList. 
    // they key of this problem is to ask for the sortedList.
    // merge sort or insert sort is wanted here. 
    
}

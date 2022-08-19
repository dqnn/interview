package hatecode._0001_0999;
import java.util.*;
import java.util.function.Supplier;
public class _480SlidingWindowMedian {
/*
480. Sliding Window Median
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
*/
    //store right part
    Queue<Integer> minHeap = new PriorityQueue<>();
    //store left part
    //(a,b)->(b-a) cannot pass the Integer.MAX or MIN, but this could
    Queue<Integer> maxHeap = new PriorityQueue<>(1, (a, b)->(b.compareTo(a)));
    
    //O(nk)/O(k), we can improve the remove() from O(k * (n -k)) O(1) by map A[i] to index
    //treeSet and BST cannot handle the unique elements
    public double[] medianSlidingWindow_PQ(int[] nums, int k) {
        int n = nums.length;
        //note,  we need n -k + 1 windows
        double[] medians = new double[n - k + 1];
        
        for(int i = 0; i < n; ++i) {
            addNum(nums[i]);
            // we have more number in both q, we need remove most left one
            if (i - k >= 0) removeNum(nums[i - k]);
            //just k elements, get the median
            if (i - k + 1 >=0) medians[i- k + 1] = getMedian();
        }
        
        return medians;
    }
    
    private void addNum(int n) {
        if (minHeap.isEmpty() || minHeap.peek() <= n) {
            minHeap.offer(n);
        } else {
            maxHeap.offer(n);
        }
        rebalance();
    }
    
    private void removeNum(int n) {
        if (minHeap.peek() <= n) {
            minHeap.remove(n);
        } else {
            maxHeap.remove(n);
        }
        rebalance();
    }
    
    private void rebalance() {
        if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.offer(minHeap.poll());
        } else if (maxHeap.size() > minHeap.size()) {
            minHeap.offer(maxHeap.poll());
        }
    }
    
    private double getMedian() {
        return minHeap.size() == maxHeap.size() ? (0.0 + minHeap.peek() + maxHeap.peek()) / 2 : minHeap.peek();
    }

    
    //O(Nlgk)/O(N)
    //thinking process: 
    //so given an integer array and integer k, return for each window size K in array, the median
    //return as array
    //we mainly use two priorityQueue to store the results, we cut the array into two parts 
    //and sort all in ascend order, so for right, first is smallest while for left, last is biggest
    //then median is middle or left.last or last.first
    
    //if the array has dup number, then we may not be able to use this
    public double[] medianSlidingWindow(int[] A, int k) {
        double[] res = new double[A.length -k + 1];
        TreeSet<Integer> left = getTreeSet(A), right = getTreeSet(A);
    
        //this way, we are guarnteed that left.size() == right.size() or 
        //left.size() = right.size() + 1
        for(int i = 0; i< A.length; i++) {
            left.add(i);
            right.add(left.pollLast());
            if (left.size() < right.size()) left.add(right.pollFirst());
            
            if (left.size() + right.size() == k) {
                Supplier<Double> median = left.size() == right.size() ? () ->A[left.last()]/2.0 + A[right.first()]/2.0 : ()->A[left.last()]/1.0;
                res[i-k+1]=  median.get();
                //res[i-k+1] = left.size() == right.size() ? A[left.last()]/2.0 + A[right.first()]/2.0 : A[left.last()]/1.0;
                if (!left.remove(i-k+1)) right.remove(i-k+1);
            }
        }
        
        return res;
    }
    
    private TreeSet<Integer> getTreeSet(int[] A) {
        //cannot use A[i]- A[j] because of overflow integers
        //return new TreeSet<>((i, j)->(A[i] == A[j] ? i - j : A[i]- A[j]));
        return new TreeSet<>((i, j)->(A[i] == A[j] ? i -j : Integer.compare(A[i], A[j])));
    }
    
    
}
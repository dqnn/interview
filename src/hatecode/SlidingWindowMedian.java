package hatecode;
import java.util.*;
public class SlidingWindowMedian {
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
        double[] medians = new double[n - k + 1];
        
        for(int i = 0; i < nums.length; ++i) {
            addNum(nums[i]);
            if (i - k >= 0) {
                removeNum(nums[i - k]);
            }
            if (i - k + 1 >=0) {
                medians[i- k + 1] = getMedian();
            }
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
    //so given a int array and integer k, return for each window size K in array, the median
    //return as array
    //we mainly use two priorityQueue to store the results, we cut the array into two parts 
    //and sort all in asc order, so for right, first is smallest while for left, last is biggest
    //then median is middle or left.last or last.first
    
    //if the array has dup number, then we may not be able to use this
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        
        //we add them into the treeset
        TreeSet<Integer> left = getSet(nums), right = getSet(nums);
        for(int i = 0; i< nums.length; i++) {
            if (left.size() <= right.size()) {
                right.add(i);
                int m = right.first();
                right.remove(m);
                left.add(m);
            } else {
                left.add(i);
                int m = left.last();
                left.remove(m);
                right.add(m);
            }
            
            //qualified window 
            if(left.size() + right.size() == k) {
                double med;
                if(left.size() == right.size())  med = ((double)nums[left.last()] + nums[right.first()]) / 2;
                else if (left.size() < right.size())  med = (double)nums[right.first()];
                else  med = (double)nums[left.last()];
                
                //first i = k - 1 
                int start = i - k + 1;
                res[start] = med;
                
                if (!left.remove(start))  right.remove(start);
            }
        }
        return res;
    }
    
    public TreeSet<Integer> getSet(int[] nums) {
        //(a, b)->(nums[a] == nums[b] ?(a - b) : (nums[a] < nums[b] ? -1:1)) is better than 
        //nums[a] - nums[b] because there will be overflow cases, always using -1 : 1
        return new TreeSet<>((a, b)->(nums[a] == nums[b] ?(a - b) : (nums[a] < nums[b] ? -1:1)));
    }
    
    
}
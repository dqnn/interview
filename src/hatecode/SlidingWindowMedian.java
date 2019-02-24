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
    //O(Nlgk)/O(N)
    //thinking process: 
    //so given a int array and integer k, return for each window size K in array, the median
    //return as array
    //we mainly use two priorityQueue to store the results, we cut the array into two parts 
    //and sort all in asc order, so for right, first is smallest while for left, last is biggest
    //then median is middle or left.last or last.first
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
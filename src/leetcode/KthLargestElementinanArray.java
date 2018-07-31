package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : KthLargestElementinanArray
 * Creator : duqiang
 * Date : June, 2018
 * Description : 215. Kth Largest Element in an Array
 */

public class KthLargestElementinanArray {

    // time : O(n) worst O(n^2) space : O(1)
    
    /*
     * Quicksort

In quicksort, in each iteration, we need to select a pivot and then partition the array into three parts:

Elements smaller than the pivot;
Elements equal to the pivot;
Elements larger than the pivot.
Now, let's do an example with the array [3, 2, 1, 5, 4, 6] in the problem statement. Let's assume in each time we select the leftmost element to be the pivot, in this case, 3. We then use it to partition the array into the above 3 parts, which results in [1, 2, 3, 5, 4, 6]. Now 3 is in the third position and we know that it is the third smallest element. Now, do you recognize that this subroutine can be used to solve this problem?

In fact, the above partition puts elements smaller than the pivot before the pivot and thus the pivot will then be the k-th smallest element if it is at the k-1-th position. Since the problem requires us to find the k-th largest element, we can simply modify the partition to put elements larger than the pivot before the pivot. That is, after partition, the array becomes [5, 6, 4, 3, 1, 2]. Now we know that 3 is the 4-th largest element. If we are asked to find the 2-th largest element, then we know it is left to 3. If we are asked to find the 5-th largest element, then we know it is right to 3. So, in the average sense, the problem is reduced to approximately half of its original size, giving the recursion T(n) = T(n/2) + O(n) in which O(n) is the time for partition. This recursion, once solved, gives T(n) = O(n) and thus we have a linear time solution. Note that since we only need to consider one half of the array, the time complexity is O(n). If we need to consider both the two halves of the array, like quicksort, then the recursion will be T(n) = 2T(n/2) + O(n) and the complexity will be O(nlogn).

Of course, O(n) is the average time complexity. In the worst case, the recursion may become T(n) = T(n - 1) + O(n) and the complexity will be O(n^2).

Now let's briefly write down the algorithm before writing our codes.

1. Initialize left to be 0 and right to be nums.size() - 1;
2. Partition the array, if the pivot is at the k-1-th position, return it (we are done);
3. If the pivot is right to the k-1-th position, update right to be the left neighbor of the pivot;
4. Else update left to be the right neighbor of the pivot.
5. Repeat 2.

     */
// one short words, we want a pivot so we put number bigger than pivot left, smaller on right
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0;
        int right = nums.length - 1;
        while (true) {
            int pos = partition(nums, left, right);
            if (pos + 1 == k) {
                return nums[pos];
            } else if (pos + 1 > k) {
                right = pos - 1;
            } else {
                left = pos + 1;
            }
        }
    }

    /**
     *   3,2,1,5,6,4  k = 3
     *   0 1 2 3 4 5
     pivot : 3  [3, 2, 1, 5, 6, 4]
     [3, 4, 6, 5, 1, 2]   3

     pivot : 5  [5, 4, 6, 3, 1, 2]
     [5, 6, 4, 3, 1, 2]   1

     pivot : 4  [6, 5, 4, 3, 1, 2]
     [6, 5, 4, 3, 1, 2]   2
     */

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int l = left + 1;
        int r = right;
        while (l <= r) {
            if (nums[l] < pivot && nums[r] > pivot) {
                swap(nums, l++, r--);
            }
            if (nums[l] >= pivot) l++;
            if (nums[r] <= pivot) r--;
        }
        swap(nums, left, r);
        return r;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    //so we shuffle the input to guarantee the O(n)
    public int findKthLargest5(int[] nums, int k) {

        shuffle(nums);
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

private void shuffle(int a[]) {

        final Random random = new Random();
        for(int ind = 1; ind < a.length; ind++) {
            final int r = random.nextInt(ind + 1);
            swap(a, ind, r);
        }
    }

    /**
     * time : O(nlogk) because it only contains k elements
     * space : O(n)
     *
     * @param nums
     * @param k
     * @return
     */

    
    public int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.poll(); //minHeap.peek() also work
    }
    
    
    
    
    //brute-force
    public int findKthLargest3(int[] nums, int k) {
        //edge case
        if (nums == null || nums.length < 1 || nums.length < k || k <= 0) {
            return -1;
        }
        
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
    
    
    
}

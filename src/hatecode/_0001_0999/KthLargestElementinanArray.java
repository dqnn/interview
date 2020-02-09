package hatecode._0001_0999;

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
    // so each visit to the element, we compare left and right with the pivot number, and properly switch
    //them, 
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0;
        int right = nums.length - 1;
        while (true) {
            //it returned the index which left are bigger and right are smallers
            int pos = partition(nums, left, right);
            if (pos + 1 == k) {
                return nums[pos];
            //means left we have too many bigger numbers,then we move sort upper bound to smaller 
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
        //TODO: why always left, i changed to others, it was incorrect
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

/*
对 n 个数进行随机：

首先我们考虑 n = 2 的情况，根据算法，显然有 1/2 的概率两个数交换，有 1/2 的概率两个数不交换，因此对 n = 2 的情况，
元素出现在每个位置的概率都是 1/2，满足随机性要求。

假设有 i 个数， i >= 2 时，算法随机性符合要求，即每个数出现在 i 个位置上每个位置的概率都是 1/i。

对于 i + 1 个数，按照我们的算法，在第一次循环时，每个数都有 1/(i+1) 的概率被交换到最末尾，
所以每个元素出现在最末一位的概率都是 1/(i+1) 。
而每个数也都有 i/(i+1) 的概率不被交换到最末尾，如果不被交换，从第二次循环开始还原成 i 个数随机，
根据 2. 的假设，它们出现在 i 个位置的概率是 1/i。因此每个数出现在前 i 
位任意一位的概率是 (i/(i+1)) * (1/i) = 1/(i+1)，也是 1/(i+1)。
综合 1. 2. 3. 得出，对于任意 n >= 2，经过这个算法，每个元素出现在 n 个位置任意一个位置的概率都是 1/n。
 */
    private void shuffle(int a[]) {
        final Random random = new Random();
        for (int i = 1; i < a.length; i++) {
            final int r = random.nextInt(i + 1);
            //always swap the last element
            swap(a, i, r);
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
/*
 * A heap is a specific tree based data structure in which all the nodes of tree are in a specific order. 
 * Let’s say if X is a parent node of Y, then the value of X follows some specific order with respect to 
 * value of Y and the same order will be followed across the tree.
 * 
 * binary heap
 *     5 (index = i)
      / \
(2Xi+1)3   4 (2x1 + 1)
    / \
   2   1 (2x2 + 1)
  /
 -3(2x3)
 Arr[5,3,4,2,1,-3]
 so child is smaller than parent, we can use array to store the tree, 
  If we are storing one element at index i in array Ar, then its parent will be stored at index 
  i/2 (unless its a root, as root has no parent) and can be access by Ar[ i ], 
  and its left child can be accessed by Ar[ 2 * i  + 1] and its right child can be accessed by Ar[ 2 * i +2 ]. 
 *
 */
    //brute force solutions
    public int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                // priorityQueue provide O(lgn) for poll, offer, remove(), add()
                // O(n) for contains() and remove(object)
                // internally it use priorityHeap
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

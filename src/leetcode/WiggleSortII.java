package leetcode;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WiggleSortII
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 324. Wiggle Sort II
 */
public class WiggleSortII {
    /**
     * Given an unsorted array nums, reorder it such that 
     * nums[0] < nums[1] > nums[2] < nums[3]....

     Example:
     (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
     (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

     Note:
     You may assume all input has valid answer.

     Follow Up:
     Can you do it in O(n) time and/or in-place with O(1) extra space?

     time : O(nlogn)
     space : O(n)

     * @param nums
     */
    //thking process:
    
    // so the question is to want to have 1 small number, 1 big number, 1 small 1 big
    // x1 < x2 > x3 < x4 > x5, so if we sort, we can see x1,x3, x5 are in a group because they 
    //all smaller then x2 and x4, so we use (len - 1) / 2 as mid so we can arrange the array
    // first one as 0 then last one(n - 1 -i) as second, we just loop to the mid then stop, 
    //and copy the element to original one
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int mid = (n - 1) / 2;
        int index = 0;
        int[] temp = new int[n];
        for (int i = 0; i <= mid; i++) {
            temp[index] = nums[mid - i];
            if (index + 1 < n) {
                temp[index + 1] = nums[n - 1 - i];
            }
            index += 2;
        }
        System.arraycopy(temp, 0, nums, 0, n);
    }

    /**
     Original idx:   0    1    2    3    4    5
     Mapped idx:     1    3    5    0    2    4
     Array:          13   6    4    5    2    5
                     5    6    4   13    2    5
                                    r
                                    i
                          l
     median = 5

     nums[] = 5

     大于中位数，左 - 右，奇
     小于中位数，右 - 左，偶

     time : O(n)
     space : O(1)


     * @param nums
     */
/*
Explanation
First I find a median using nth_element. That only guarantees O(n) average time complexity 
and I don't know about 
space complexity. I might write this myself using O(n) time and O(1) space, but that's 
not what I want to show here.

This post is about what comes after that. We can use three-way partitioning to arrange 
the numbers so that those 
larger than the median come first, then those equal to the median come next, and then 
those smaller than the median come last.

procedure three-way-partition(A : array of values, mid : value):
    i ← 0
    j ← 0
    n ← size of A - 1

    while j ≤ n:
        if A[j] < mid:
            swap A[i] and A[j]
            i ← i + 1
            j ← j + 1
        else if A[j] > mid:
            swap A[j] and A[n]
            n ← n - 1
        else:
            j ← j + 1


Ordinarily, you'd then use one more phase to bring the numbers to their final positions 
to reach the overall 
wiggle-property. But I don't know a nice O(1) space way for this. Instead, 
I embed this right into the partitioning 
algorithm. That algorithm simply works with indexes 0 to n-1 as usual, but sneaky as I am,
 I rewire those indexes 
where I want the numbers to actually end up. The partitioning-algorithm doesn't even know 
that I'm doing that, it just works like normal (it just uses A(x) instead of nums[x]).

Let's say nums is [10,11,...,19]. Then after nth_element and ordinary partitioning, 
we might have this (15 is my median):

index:     0  1  2  3   4   5  6  7  8  9
number:   18 17 19 16  15  11 14 10 13 12
I rewire it so that the first spot has index 5, the second spot has index 0, etc, 
so that I might get this instead:

index:     5  0  6  1  7  2  8  3  9  4
number:   11 18 14 17 10 19 13 16 12 15
And 11 18 14 17 10 19 13 16 12 15 is perfectly wiggly. And the whole partitioning-to-wiggly-arrangement 
(everything after finding the median) only takes O(n) time and O(1) space.

If the above description is unclear, maybe this explicit listing helps:

Accessing A(0) actually accesses nums[1].
Accessing A(1) actually accesses nums[3].
Accessing A(2) actually accesses nums[5].
Accessing A(3) actually accesses nums[7].
Accessing A(4) actually accesses nums[9].
Accessing A(5) actually accesses nums[0].
Accessing A(6) actually accesses nums[2].
Accessing A(7) actually accesses nums[4].
Accessing A(8) actually accesses nums[6].
Accessing A(9) actually accesses nums[8].

Props to apolloydy's solution, I knew the partitioning algorithm already 
but I didn't know the name. And apolloydy's idea to partition to reverse order happened 
to make the index rewiring simpler.



 */
    public void wiggleSort2(int[] nums) {
        int median = findKthLargest(nums, (nums.length + 1) / 2);
        int n = nums.length;
        int left = 0, right = n - 1;
        int index = 0;
        while (index <= right) {
            if (nums[newIndex(index, n)] > median) {
                swap(nums, newIndex(left++, n), newIndex(index++, n));
            } else if (nums[newIndex(index, n)] < median) {
                swap(nums, newIndex(right--, n), newIndex(index, n));
            } else {
                index++;
            }
        }
    }

    private int newIndex(int index, int n) {
        return (1 + 2 * index) % (n | 1);
    }

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
    //this function will partition the array, [10,11,....19]-->[19,18,17,16...11] so it would have 
    // 2 groups and then big and small group which means left are all bigger than right group
    // process: 
    // loop from left to right, if we find nums[left] < pivot nums[right] > pivot then 
    // we can swap them, and left++, right--, if element on left and right remain the correct group
    // then just move the pointer to next position, the return value means how many elements are 
    //bigger than value on left pointer
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
}

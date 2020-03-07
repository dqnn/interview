package hatecode._0001_0999;
import java.util.*;
public class _315ReversePairs {
/*
 315. Count of Smaller Numbers After Self is the same with this one
 
 493. Reverse Pairs
Given an array nums, we call (i, j) an important reverse pair if i < j 
and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
*/
    //TreeMap, 2Dimension, BIT, this is 2D sorting we want to keep its position while want to 
    //query its value
    //O(nlgn)
    public static int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length-1);
    }
    //[1,3,2,3,1]
  //     /    \
  // [1,3,2]   [3,1]
//    / \        / \
// [1,3]  [2]  [3]  [1]
//  /  \        
// [1]  [3]

//as above, mergeSort would go to [1] and [3], in such case, cnt = 0,  since s = e; 
 // and [1,3] would go to line 49, mid = 0, j = 1, i = 0, so only 1 time, and 1 / 2 != 3, so 
 //we did not find anything, then sort, [1,3] 
 //j actually is another list
 //it would go back to line 42, then mid = 1, we run mergeSort(nums, 2,2) so it just return 0. 
  //then we run the for loop, mid =1, i = 0, j =2, e = 2, but we did not find any, so we sort and reurn
// then it is time for [3,1], and we found 1, it merged as [1,3]
//last, we would merge, [1,2,3] and [1,3], 
//so i = 0, mid = 2, j = 3, e = 4,  so 1 would try 1 and 3, then 2 would try [1,3], no number is ok,
// then 3 would try [1,3], 3 is ok, so 
    private static int mergeSort(int[] nums, int s, int e){
        if(s>=e) return 0; 
        int mid = s + (e-s)/2; 
        int cnt = mergeSort(nums, s, mid) + mergeSort(nums, mid+1, e); 
        //position like: i mid j,  s<= i <= mid,that's to say, we want to compare
        //nums[i] and nums[j], if we can find one, then j need to j++, until loop break, then the 
        //number of qualified num is j - (mid +1) + 1 -1(last j is not qualified)
        
        //for example [1,3,2,3,1], when the loop it would be cnt = 0. s = 0, e= 1,
        //i = 0 <= mid, j = mid + 1 means the first on another half. so we would only loop 1 time
        // and then we would sort [0, 2(e + 1)] this part and return. 
        
        //
        for (int i = s, j = mid + 1; i <= mid; i++) {
            while (j <= e && nums[i] / 2.0 > nums[j])
                j++;
            cnt += j - (mid + 1);
        }
        Arrays.sort(nums, s, e+1); 
        return cnt; 
    }
    //another merge sort solution
    public int reversePairs2(int[] nums) {
        return reversePairsSub(nums, 0, nums.length - 1);
    }
        
    private int reversePairsSub(int[] nums, int l, int r) {
        if (l >= r) return 0;
            
        int m = l + ((r - l) >> 1);
        int res = reversePairsSub(nums, l, m) + reversePairsSub(nums, m + 1, r);
            
        int i = l, j = m + 1, k = 0, p = m + 1;
        int[] merge = new int[r - l + 1];
            
        while (i <= m) {
            while (p <= r && nums[i] > 2L * nums[p]) p++;
            res += p - (m + 1);
                    
            while (j <= r && nums[i] >= nums[j]) merge[k++] = nums[j++];
            merge[k++] = nums[i++];
        }
        while (j <= r) merge[k++] = nums[j++];
        System.arraycopy(merge, 0, nums, l, merge.length);
            
        return res;
    }
    
    //BIT solution
/*
explanation for the BIT-based solution:

We want the elements to be sorted so there is a sorted version of the 
input array which is copy.

The bit is built upon this sorted array. Its length is one greater than that 
of the copy array to account for the root.

Initially the bit is empty and we start doing a sequential scan of the input 
array. For each element being scanned, we first search the bit to find all 
elements greater than twice of it and add the result to res. We then 
insert the element itself into the bit for future search.

Note that conventionally searching of the bit involves traversing towards 
the root from some index of the bit, which will yield a predefined running 
total of the copy array up to the corresponding index. For insertion, 
the traversing direction will be opposite and go from some index towards 
the end of the bit array.

For each scanned element of the input array, its searching index will be 
given by the index of the first element in the copy array that is greater than 
twice of it (shifted up by 1 to account for the root), while its insertion 
index will be the index of the first element in the copy array that is no 
less than itself (again shifted up by 1). This is what the index function is for.

For our case, the running total is simply the number of elements encountered 
during the traversal process. If we stick to the convention above, the running 
total will be the number of elements smaller than the one at the given index, 
since the copy array is sorted in ascending order. However, we'd actually like 
to find the number of elements greater than some value (i.e., twice of the element 
being scanned), therefore we need to flip the convention. This is what you see 
inside the search and insert functions: the former traversing towards the end of 
the bit while the latter towards the root.
 */
    private int search(int[] bit, int i) {
        int sum = 0;

        while (i < bit.length) {
            sum += bit[i];
            i += i & -i;
        }

        return sum;
    }

    private void insert(int[] bit, int i) {
        while (i > 0) {
            bit[i] += 1;
            i -= i & -i;
        }
    }
    public int reversePairs3(int[] nums) {
        int res = 0;
        int[] copy = Arrays.copyOf(nums, nums.length);
        int[] bit = new int[copy.length + 1];

        Arrays.sort(copy);
        for (int ele : nums) {
            res += search(bit, index(copy, 2L * ele + 1));
            insert(bit, index(copy, ele));
        }

        return res;
    }

    private int index(int[] arr, long val) {
        int l = 0, r = arr.length - 1, m = 0;
        //binary search, last would be r + 1 = l
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (arr[m] >= val) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l + 1;
    }
    
    public static void main(String[] args) {
        int[] in = {1,3,2,3,1};
        System.out.println(reversePairs(in));
    }
}
package hatecode._1000_1999;
public class _1144DecreaseElementsToMakeArrayZigzag {
/*
1144. Decrease Elements To Make Array Zigzag
Given an array nums of integers, a move consists of choosing any element and decreasing it by 1.

An array A is a zigzag array if either:

Every even-indexed element is greater than adjacent elements, ie. A[0] > A[1] < A[2] > A[3] < A[4] > ...
OR, every odd-indexed element is greater than adjacent elements, ie. A[0] < A[1] > A[2] < A[3] > A[4] < ...
Return the minimum number of moves to transform the given array nums into a zigzag array.

 

Example 1:

Input: nums = [1,2,3]
Output: 2

1 <= nums.length <= 1000
1 <= nums[i] <= 1000
*/
    //thinking process:O(n)/O(1)
    
    //we look for the diff
    public int movesToMakeZigzag(int[] A) {
        if(A == null || A.length < 1) return 0;
        
        int res[] = new int[2],  n = A.length, l, r;
        for (int i = 0; i < n; ++i) {
            l = i > 0 ? A[i - 1] : 1001;
            r = i + 1 < n ? A[i + 1] : 1001;
            res[i % 2] += Math.max(0, A[i] - Math.min(l, r) + 1);
        }
        return Math.min(res[0], res[1]);
    }
    
    //
    public int movesToMakeZigzag_BF(int[] nums) {
        int e = 0, o = 0, n = nums.length;
        for(int i=1; i < n; i+= 2){
            int min = Math.min(nums[i-1], i+1 < n ? nums[i+1] : 1000);
            if(min <= nums[i])
                e += (nums[i]-min+1);
        }
        for(int i=0; i < n; i+= 2){
            int min = Math.min(i > 0 ? nums[i-1] : 1000, i+1 < n ? nums[i+1] : 1000);
            if(min <= nums[i])
                o += (nums[i]-min+1);
        }
        return Math.min(e,o);
    }
}
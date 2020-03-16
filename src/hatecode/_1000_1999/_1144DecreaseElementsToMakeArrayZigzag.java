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

Input: nums = [9,6,1,6,2]
Output: 4

1 <= nums.length <= 1000
1 <= nums[i] <= 1000
*/
    //thinking process:O(n)/O(1)
    
    //the problem is to say:  given an integer arr, if we want to make decrease 1 for 
    //one element in array as 1 move, to make the arr as zigzag array, return the min 
    //move
    
    //for 3 adjacent elements in array, we can try to do 2 things
    //1. to make ^ pattern, what's the min move?
    //2. to make V pattern, what;s the min move?
    //[9,6,1,6,2], for 9, 6, 1, if for 1, we can make 9 to 5, then 4 moves, 
    //for 2, 1->7, then 6 moves, then we contniue this working until the end
    public int movesToMakeZigzag(int[] A) {
        if(A == null || A.length < 1) return 0;
        
        int res[] = new int[2],  n = A.length, l, r;
        for (int i = 0; i < n; i++) {
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
package hatecode._1000_1999;
public class _1053PreviousPermutationWithOneSwap {
/*
1053. Previous Permutation With One Swap
Given an array A of positive integers (not necessarily distinct), return the 
lexicographically largest permutation that is smaller than A, that can be made
 with one swap (A swap exchanges the positions of two numbers A[i] and A[j]). 
  If it cannot be done, then return the same array.

 

Example 1:

Input: [3,2,1]
Output: [3,1,2]
*/
    //thinking process:O(n)/O(1)
    
    //next permutation has some similar thinking like this
    //[3,2,1] return the previous smaller array, [3,1,2], the opposite of 31
    
    //same thoughts process to 31,
    
    //the difference is 31 require re-sort on right part, this does not 
    //sort, just switch will be enough, 
   
    //first we want to make sure the sequence has decrease part, if it always increasing then return -1
    //previous will find l index which needs to switch, then 
    //2nd step is to find right can be switch, 
    //A[r] should be smaller than A[l], and it should be closest. 
    
    //[1,9,4,6,7] --> 1,7,4,6,9
    
    //[1,9,4,6,7]--> 1,9,4,7,6 next greater permutation
    public int[] prevPermOpt1(int[] A) {
        if(A == null || A.length < 1) return new int[0];
        
        int n = A.length, l = n - 2, r = n - 1, tmp;
        //looking for 1，4，2，5，6，7, left = 1 and stop
        while (l >= 0  && A[l] <= A[l + 1]) l--;
        //means A is increasing sequence, so previous was itself
        if (l < 0) return A;
        
        //look for 2,5,6,7, smaller than 4,right = 2
        while (A[l] <= A[r]) r--;
        //while(l < r && A[l]<= A[r]) r--;
        //process the equal value case, like 1,4,2,2,5,6,7
        while (A[r - 1] == A[r]) r--;
        //switch
        tmp = A[l]; 
        A[l] = A[r]; 
        A[r] = tmp;
        
        return A;
    }
    
    
}
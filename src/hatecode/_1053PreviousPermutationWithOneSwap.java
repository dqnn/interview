package hatecode;
public class _1053PreviousPermutationWithOneSwap {
/*
1053. Previous Permutation With One Swap
Given an array A of positive integers (not necessarily distinct), return the lexicographically largest permutation that is smaller than A, that can be made with one swap (A swap exchanges the positions of two numbers A[i] and A[j]).  If it cannot be done, then return the same array.

 

Example 1:

Input: [3,2,1]
Output: [3,1,2]
*/
    //thinking process:O(n)/O(1)
    
    //next permutation has some similiar thinking like this
    //[3,2,1] return the previous smaller array, [3,1,2], the opposite of 31
    
    //same thoughts process to 31,
    
    //
    public int[] prevPermOpt1(int[] A) {
        if(A == null || A.length < 1) return new int[0];
        
        int n = A.length, left = n - 2, right = n - 1, tmp;
        //looking for 1，4，2，5，6，7, left = 1 and stop
        while (left >= 0  && A[left] <= A[left + 1]) left--;
        //means A is increasing sequence, so previous was itself
        if (left < 0) return A;
        
        //look for 2,5,6,7, smaller than 4,right = 2
        while (A[left] <= A[right]) right--;
        //process the equal value case, like 1,4,2,2,5,6,7
        while (A[right - 1] == A[right]) right--;
        //switch
        tmp = A[left]; 
        A[left] = A[right]; 
        A[right] = tmp;
        
        return A;
    }
    
    
}
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
    
    //
    public int[] prevPermOpt1(int[] A) {
        if(A == null || A.length < 1) return new int[0];
        int n = A.length, left = n - 2, right = n - 1, tmp;
        while (left >= 0  && A[left] <= A[left + 1]) left--;
        if (left < 0) return A;
        while (A[left] <= A[right]) right--;
        while (A[right - 1] == A[right]) right--;
        tmp = A[left]; A[left] = A[right]; A[right] = tmp;
        return A;
        
    }
    
    
}
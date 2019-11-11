package hatecode;
public class _1089DuplicateZeros {
    /*
     * 1089. Duplicate Zeros 
     * Given a fixed length array arr of integers, duplicate
     * each occurrence of zero, shifting the remaining elements to the right.
     * 
     * Note that elements beyond the length of the original array are not written.
     * 
     * Do the above modifications to the input array in place, do not return
     * anything from your function.
     * 
     * Example 1:
     * 
     * Input: [1,0,2,3,0,4,5,0]
     * Explanation: After calling your function, the input array is modified 
     * to: [1,0,0,2,3,0,0,4]
     */
    //thinking process: O(n)/O(1)
    //given an integer array A, if element is 0, then duplicate it, put it aside, 
    //move the all other right elements to back, just move in place
    
    //A = [1,0,2,3,0,4,5,0] 
    //cnt = 3，wp = 8 + 3 - 1 = 10, r means the pointer in A
    //
    public void duplicateZeros(int[] A) {
        if(A == null || A.length< 1) return;
        int n = A.length, cnt = 0;
        //how many 0 in array
        for (int num : A) if (num == 0) cnt++;
        //pointer inside A, 
        int r = n - 1;
        //last element in the new position, wp= write position, A[wp]= A[i]
        int wp = n + cnt - 1;

        while (r >= 0 && wp >= 0) {
            if (A[r] != 0) { // Non-zero, copy A[i] to A[wp]
                if (wp < n) A[wp] = A[r];
            } else { // Zero found, write it in twice if we can
                if (wp < n)  A[wp] = A[r];
                wp--;
                if (wp < n) A[wp] = A[r];
            }

            r--;
            wp--;
        }
    }
}
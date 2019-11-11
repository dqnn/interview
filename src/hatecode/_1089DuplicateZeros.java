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
    
    //TODO: add easier to understand text here
    public void duplicateZeros(int[] A) {
        if(A == null || A.length< 1) return;
        int n = A.length, count = 0;
        //how many 0 in array
        for (int num : A) {
            if (num == 0) count++;
        }
        int i = n - 1;
        int write = n + count - 1;

        while (i >= 0 && write >= 0) {
            if (A[i] != 0) { // Non-zero, just write it in
                if (write < n) A[write] = A[i];
            } else { // Zero found, write it in twice if we can
                if (write < n)  A[write] = A[i];
                write--;
                if (write < n) A[write] = A[i];
            }

            i--;
            write--;
        }
    }
}
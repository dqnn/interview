package hatecode;
public class DuplicateZeros {
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
     */
    //thinking process: 
    //this is same as re-arrange the arrays
    public void duplicateZeros(int[] A) {
        if(A == null || A.length< 1) return;
        int n = A.length, count = 0;
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
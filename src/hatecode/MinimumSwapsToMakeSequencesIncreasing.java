package hatecode;
public class MinimumSwapsToMakeSequencesIncreasing {
/*
801. Minimum Swaps To Make Sequences Increasing
swapRecord means for the ith element in A and B, the minimum swaps if we swap A[i] and B[i]
fixRecord means for the ith element in A and B, the minimum swaps if we DONOT swap A[i] and B[i]

We have two integer sequences A and B of the same non-zero length.

We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.

At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)

Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.

Example:
Input: A = [1,3,5,4], B = [1,2,3,7]
Output: 1
*/
    //thinking process:
    //A=[1,3,5,4], B = [1,2,3,7]
/*
Obviously, swapRecord[0] = 1 and fixRecord[0] = 0.

For i = 1, either swap or fix is OK. So we take the minimum previous result, min = min(swapRecord[0], fixRecord[0]) = 0. swapRecord[1] = min + 1 = 1, fixRecord[1] = min = 0
For i = 2, notice that A[1] >= B[2], which means the manipulation of A[2] and B[2] should be same as A[1] and B[1], if A[1] and B[1] swap, A[2] and B[2] should swap, vice versa. Make sense, right? So swapRecord[2] = swapRecord[1] + 1 and fixRecord[2] = fixRecord[1]
For i = 3, notice that A[2] >= A[3], which mean the manipulation of A[3] and B[3] and A[2] and B[2] should be opposite. In this case, swapRecord[3] = fixRecord[2] + 1 and fixRecord[3] = swapRecord[2]
 */
     public int minSwap(int[] A, int[] B) {
        int swapRecord = 1, fixRecord = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] >= B[i] || B[i - 1] >= A[i]) {
		// In this case, the ith manipulation should be same as the i-1th manipulation
                // fixRecord = fixRecord;
                swapRecord++;
            } else if (A[i - 1] >= A[i] || B[i - 1] >= B[i]) {
		// In this case, the ith manipulation should be the opposite of the i-1th manipulation
                int temp = swapRecord;
                swapRecord = fixRecord + 1;
                fixRecord = temp;
            } else {
                // Either swap or fix is OK. Let's keep the minimum one
                int min = Math.min(swapRecord, fixRecord);
                swapRecord = min + 1;
                fixRecord = min;
            }
        }
        return Math.min(swapRecord, fixRecord);
    }
}
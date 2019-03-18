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
     public int minSwap_Best(int[] A, int[] B) {
         //initialize 
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
     //swap
     public int minSwap(int[] A, int[] B) {
         int n = A.length;
         
         /* 
          state[i][0] is min swaps too make A[0..i] and B[0..i] increasing if we do not swap A[i] and B[i]; 
          state[i][1] is min swaps too make A[0..i] and B[0..i] increasing if we swap A[i] and B[i].
          */
         int[][] state = new int[n][2];
         //even they increase, we still want swap, so we mark the swap move +1
         state[0][1] = 1;
         
         for (int i = 1; i < n; i++) {
             boolean areBothSelfIncreasing = A[i - 1] < A[i] && B[i - 1] < B[i];
             boolean areInterchangeIncreasing = A[i - 1] < B[i] && B[i - 1] < A[i];
             
             if (areBothSelfIncreasing && areInterchangeIncreasing) {
                 state[i][0] = Math.min(state[i - 1][0], state[i - 1][1]);
                 state[i][1] = Math.min(state[i - 1][0], state[i - 1][1]) + 1;
             } else if (areBothSelfIncreasing) {
                 state[i][0] = state[i - 1][0];
                 state[i][1] = state[i - 1][1] + 1;
             } else { // if (areInterchangeIncreasing)
                 state[i][0] = state[i - 1][1];
                 state[i][1] = state[i - 1][0] + 1;
             }
         }
         
         return Math.min(state[n - 1][0], state[n - 1][1]);
     }
     
     
     
     
     /*
     A.... 2 3
     B.... 1 4
   we have 4 cases, TODO
   1.  same as original, nothing to do, so keep[i] = keep[i-1], swap[i] = swap[i-1] + 1
   2. if A[i-1] < B[i] && A[i] > B[i-1], means we can swap i or swap i-1, so swap[i] = min(swap[i], keep[i-1] + 1), keep[i] = min(keep[i], swap[i-1])
  */
      public int minSwap_1D(int[] A, int[] B) {
          int N = A.length;
          int[] swap = new int[N+1];
          int[] keep = new int[N+1];
          swap[0] = 1;
          keep[0] = 0;
          for (int i = 1; i < N; ++i) {
              keep[i] = swap[i] = N;
              if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                  keep[i] = keep[i - 1];
                  //we swap i and i-1, they are still increase, 
                  swap[i] = swap[i - 1] + 1;
              }
              if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
                  keep[i] = Math.min(keep[i], swap[i - 1]);
                  swap[i] = Math.min(swap[i], keep[i - 1] + 1);
              }
          }
          return Math.min(swap[N - 1], keep[N - 1]);
      }
}
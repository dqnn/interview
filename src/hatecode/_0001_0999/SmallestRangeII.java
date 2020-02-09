package hatecode._0001_0999;

import java.util.Arrays;

public class SmallestRangeII {
/*
 * 910. Smallest Range II
 * Author Denny 
 * Sep 2018
 */
/*
 Given an array A of integers, for each integer A[i] we need to 
 choose either x = -K or x = K, and add x to A[i] (only once).

After this process, we have some array B.

Return the smallest possible difference between the maximum value of B and the minimum value of B.

 

Example 1:

Input: A = [1], K = 0
Output: 0
Explanation: B = [1]
Example 2:

Input: A = [0,10], K = 2
Output: 6
Explanation: B = [2,8]
Example 3:

Input: A = [1,3,6], K = 3
Output: 3
Explanation: B = [4,6,3]
 

Note:

1 <= A.length <= 10000
0 <= A[i] <= 10000
0 <= K <= 10000
 */
//Assuming there is a point, on the left of the point, all elements add K, 
//on the right of the point, all elements 
//substract K, check each possible point. (a point is between two numbers).
//thinking process:
    // each element can +k or -k so we can have 2 ^ n arrays, so we want to
    //find smallest between min and max in the same array
    
    // so thinking about this way, there are 3 use cases that smallest diff exists
    //1 all +k, then it is same as current
    //2 all -k then it is same as current
    //3 partially +k, partially -k, so we want to have a cut in the array, left +k, right
    // -k, so n elements have n-1 slots, i < len -1, 
    
    // so res default to be max - min. max = right, min = left 
    // suppose cut from 0 to n - 1, 
    // left +k while right -k so we can get smallest diff
    public int smallestRangeII(int[] A, int k) {
        if (A == null || A.length < 1) {
            return 0;
        }
        
        Arrays.sort(A);
        int left = A[0] + k, right = A[A.length - 1] - k;
        int res = A[A.length - 1] - A[0];
        for(int i = 0; i < A.length - 1; i++) {
            int max = Math.max(right, A[i] + k);
            int min = Math.min(left, A[i+1] - k);
            res = Math.min(res, max - min);
        }
        return res;
    }
/*
Intuition:
For each integer A[i],
we may choose either x = -K or x = K.

If we add K to all B[i], the result won't change.

It's the same as:
For each integer A[i], we may choose either x = 0 or x = 2 * K.

Explanation:
We sort the A first, and we choose to add x = 0 to all A[i].
Now we have res = A[n - 1] - A[0].
Starting from the smallest of A, we add 2 * K to A[i],
hoping this process will reduce the difference.

Update the new mx = max(mx, A[i] + 2 * K)
Update the new mn = min(A[i + 1], A[0] + 2 * K)
Update the res = min(res, mx - mn)

Time Complexity:
O(NlogN), in both of the worst and the best cases.
In the Extending Reading part, I improve this to O(N) in half 
of cases.
 */
    
    public int smallestRangeII2(int[] A, int K) {
        Arrays.sort(A);
        int n = A.length, mx = A[n - 1], mn = A[0], res = mx - mn;
        for (int i = 0; i < n - 1; ++i) {
            mx = Math.max(mx, A[i] + 2 * K);
            mn = Math.min(A[i + 1], A[0] + 2 * K);
            res = Math.min(res, mx - mn);
        }
        return res;
    }
/*
Extending Reading:
I notice that no other post mention this part,
So I decided to complete it myself.

Q: Do we have to sort the list?
A: In general cases, Yes. and this can be easily proved.

For example A = [0,1,4,5,6,7,8,9], K = 5
result = 2 * K - max(gap between numbers) = 2 * 5 - (4 - 1) = 7

To get the gap of consecutive numbers (in sorted order),
so in fact, this problem has no difference from 164. Maximum Gap

I don't have a better idea than sort.
But we don't have to use promitif O(NlogN) sort.
Other O(N) sort like bucket sort can be apllied to this problem.

Q: Except the general cases, do we any corner case?
A: Yes
if max(A) - min(A) >= 4 * K, return max(A) - min(A) - 2 * K
if max(A) - min(A) <= K, return max(A) - min(A)
Otherwise, we have to sort.

Q: Can we optimise this O(NlogN) solution?
A:
Optimization 1， Apply O(N) sort
Reference to 164. Maximum Gap.

Optimization 2， Sort only one part of whole list
To be easier understood, I sort the whole list here.
But we don't have to.
We can only take and sort he number between [max(A) - 2 * K, 
min(A) + 2 * K]

This will help improve the complexity a lot.
In 30 of 68 total cases, we solve the problem in O(N).
In ther rest cases, we solve in O(KlogK) where K <= N.
 */
}

package hatecode;
import java.util.*;
public class MaximumSumCircularSubarray {
/*
918. Maximum Sum Circular Subarray
Input: [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3
Input: [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
*/
    //this is some tricky answer, not good in interview, but good to show
//max(prefix+suffix) = max(total sum - subarray) = total sum + max(-subarray) = total sum - min(subarray)
/*
那么碰到循环数组的问题，无外乎三个套路。
拆分（HOUSE ROBBER那道，循环道路时用的是这个技巧）
倍增（就是在原数组后补到2倍长度，在2倍长数组的里处理，那么原本的N长的数组，我们可以变成N个新的长度为N的数组）
0->N-1， 1- >N， 2- >N+1 .....    N-1 -> 2*n-2
分别对每种循环的可能做处理，最后汇总得到解，这题可以运用这个策略，但是时间复杂度为N^2
取反（求最大，变成求最小）
在这道题里的运用，就是如果是2端加起来最大。那么必定我们需要在中间找一个最小。然后用SUM去减掉中间最小。就得到2端最大。

 */
    public int maxSubarraySumCircular_Show(int[] A) {
        int total = 0, maxSum = -30000, curMax = 0, minSum = 30000, curMin = 0;
        for (int a : A) {
            curMax = Math.max(curMax + a, a);
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(curMin + a, a);
            minSum = Math.min(minSum, curMin);
            total += a;
        }
        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }

    //the third solution
    public int maxSubarraySumCircular(int[] A) {
        int sum = 0;
        for(int i : A) sum+=i;
        int min = Integer.MAX_VALUE;
        int tot = 0;
        for(int i : A) {
            tot += i;
            min = Math.min(tot,min);
            if(tot > 0) tot = 0;
            
        }
        int tot2 = 0;
        int max = Integer.MIN_VALUE;
        for(int i : A) {
            tot2 += i;
            max = Math.max(tot2,max);
            if(tot2 < 0) tot2 = 0;
        }
        if(max < 0) return max;
        //System.out.println(max);
        return Math.max(max,sum-min);
    }

    
   public int maxSubarraySumCircular_Solution(int[] A) {
        int N = A.length;

        // Compute P[j] = B[0] + B[1] + ... + B[j-1]
        // for fixed array B = A+A
        int[] P = new int[2*N+1];
        for (int i = 0; i < 2*N; ++i)
            P[i+1] = P[i] + A[i % N];

        // Want largest P[j] - P[i] with 1 <= j-i <= N
        // For each j, want smallest P[i] with i >= j-N
        int ans = A[0];
        // deque: i's, increasing by P[i]
        Deque<Integer> deque = new ArrayDeque();
        deque.offer(0);

        for (int j = 1; j <= 2*N; ++j) {
            // If the smallest i is too small, remove it.
            if (deque.peekFirst() < j-N)
                deque.pollFirst();

            // The optimal i is deque[0], for cand. answer P[j] - P[i].
            ans = Math.max(ans, P[j] - P[deque.peekFirst()]);

            // Remove any i1's with P[i2] <= P[i1].
            while (!deque.isEmpty() && P[j] <= P[deque.peekLast()])
                deque.pollLast();

            deque.offerLast(j);
        }

        return ans;
    }
}
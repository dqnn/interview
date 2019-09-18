package hatecode;
public class _1014BestSightseeingPair {
/*
1014. Best Sightseeing Pair
Given an array A of positive integers, A[i] represents the value of the i-th sightseeing spot, and two sightseeing spots i and j have distance j - i between them.

The score of a pair (i < j) of sightseeing spots is (A[i] + A[j] + i - j) : the sum of the values of the sightseeing spots, minus the distance between them.

Return the maximum score of a pair of sightseeing spots.

 
Example 1:

Input: [8,1,5,2,6]
Output: 11
Explanation: i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
*/
    //thinking process:
    public int maxScoreSightseeingPair(int[] A) {
        int res = 0, cur = 0;
        for (int a: A) {
            res = Math.max(res, cur + a);
            cur = Math.max(cur, a) - 1;
        }
        return res;
    }
    //TLE
    public int maxScoreSightseeingPair_TLE(int[] A) {
        if (A == null || A.length < 1) return 0;
        
        int max = Integer.MIN_VALUE;
        int n = A.length;
        for(int i = 0; i<n; i++) {
            for( int j = i +1; j< n; j++) {
                max = Math.max(max, A[i] + A[j] + i -j);
            }
        }
        return max;
    }
}
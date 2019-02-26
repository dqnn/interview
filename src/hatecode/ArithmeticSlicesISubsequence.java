package hatecode;
import java.util.*;
class ArithmeticSlicesISubsequence {
/*
446. Arithmetic Slices II - Subsequence
A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequences:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array 
is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.

A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], 
A[P1], ..., A[Pk-1], A[Pk] is arithmetic. In particular, this means that k ≥ 2.

The function should return the number of arithmetic subsequence slices in the array A.

The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 ≤ N ≤ 1000. The output is guaranteed to be less than 231-1.


Example:

Input: [2, 4, 6, 8, 10]

Output: 7

Explanation:
All arithmetic subsequence slices are:
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]
*/
    
  //the same as  128. Longest Consecutive Sequence
    //O(n^2)
    
    //original and easy to understand DP solution, 
    //假设dp[i][j]表示以A[i]结尾的子序列(P0, P1, …, Pi)构成的数列，序列中的元素之差为j。
    //而dp[i][j]=dp[k][j]>0?dp[k][j]+1:1，0<=i<A.length()，0<=k<i。
    //其中dp[k][j]为0的时候需要加1，也就是仅存在两个数的数列时dp[i][j]需要加1，
    //这是因为再之后第3个数匹配时可以形成一个正确数列，然后1加到总数里。
    
    //so A[i][j] = Sum(A[i][0] + ...A[i][k]) k = 0,.....j-1
    
    //basically we use one map to store each integer and its index in a list
    //then we use formula A[k] + A[i] = 2* A[j] to look for A[k] if we found it, 
    //then we loop for multiple possible k
    public int numberOfArithmeticSlices_DP(int[] A) {
        if (A == null || A.length < 3) return 0;
        
        int n = A.length;
        int[][] dp = new int[n][n];
        Map<Long, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.computeIfAbsent((long)A[i], v->new ArrayList<>()).add(i);
        
        int res = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j< i; j++) {
                long target = 2*(long)A[j] - A[i];
                if (map.containsKey(target)) {
                    for(int k : map.get(target)) {
                        if (k < j) dp[i][j] += (dp[j][k] + 1);
                    }
                }
                res += dp[i][j];
            }
        }
        return res;
    }
    
    //DP, 
    //f[i][d] denotes the number of arithmetic subsequences that ends 
    //with A[i] and its common difference is d.
    //for all j < i, f[i][A[i] - A[j]] += f[j][A[i] - A[j]] j = 0,1,2,,,,i-1
    //every arithmetic slice is defined with start number and its delta. 
    //this is DP solution with pretty simplify code
    
    //why we use map? because if we dp, we may have to deal with negative index, 
    //
    public static int numberOfArithmeticSlices(int[] A) {
        int res = 0;
        List<Map<Integer, Integer>> map = new ArrayList<>();

        for (int i = 0; i < A.length; i++) {
            map.add(new HashMap<>());

            for (int j = 0; j < i; j++) {
                long diff = (long) A[i] - A[j];
                if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE)
                    continue;
                // first when i scan the map, suppose [2,4,6,8,10], i =1, j = 0;
                // then d = 2, c1 = 0, c2= 0, so we would put(2, 1) into map[i],
                // i moved to i = 2, j = 0, again, d= 4, map[2] = (4,1), in map[1]
                // c2 = 1, why we use c2 is because when i moved, we found that
                // idx={0,1,2}, their diff is the same
                int delta = (int) diff;
                //current maps diff count
                int c1 = map.get(i).getOrDefault(delta, 0);
                //prev map same diff count, this is dp[i][diff] 
                int c2 = map.get(j).getOrDefault(delta, 0);
                res += c2;
                //every time, j will from 0->i-1, this is dp formula
                map.get(i).put(delta, c1 + c2 + 1);
            }
/*
the map will be looks like this:
{}
{2=1}
{2=2, 4=1}
{2=3, 4=1, 6=1}
{2=4, 4=2, 6=1, 8=1}
 */
           System.out.println(map.get(map.size() -1));
        }

        return res;
    }
    //O(2^n)/O(n)
    public int numberOfArithmeticSlices2(int[] A) {
        if (A == null || A.length < 3) return 0;
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), A, 0);
       
        return res.size();
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int[]A, int start) {
        if (start > A.length) {
            return;
        }
        //here is the logic to examine whther current integer list is a arithmetic slice
        if (list.size() >= 3) {
            //System.out.println("list" + list);
            int i = 1;
            while(i < list.size() - 1 && ((long)list.get(i+1) - list.get(i) == list.get(i) - list.get(i-1)) 
                  && ((long)list.get(i+1) - list.get(i) <= Integer.MAX_VALUE)
                  && ((long)list.get(i+1) - list.get(i) >= Integer.MIN_VALUE)
                  && ((long)list.get(i) - list.get(i - 1) <= Integer.MAX_VALUE)
                  && ((long)list.get(i) - list.get(i - 1) >= Integer.MIN_VALUE)) {
                i++;
            }
            //note, if it is correct, we continue, else return, since we need to remove this element
            if (i != list.size() - 1)  return;
            else res.add(new ArrayList<>(list));
            //System.out.println("res" + res);
        }
        for(int i = start; i< A.length; i++) {
            list.add(A[i]);
            helper(res, list, A, i + 1);
            list.remove(list.size() - 1);
        }
    }
    
    public static void main(String[] args) {
        int[] in = {2,4,6,8,10};
        System.out.println(numberOfArithmeticSlices(in));
    }
}
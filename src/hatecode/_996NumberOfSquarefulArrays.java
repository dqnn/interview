package hatecode;

import java.util.*;
public class _996NumberOfSquarefulArrays {
/*
996. Number of Squareful Arrays
Given an array A of non-negative integers, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.

Return the number of permutations of A that are squareful.  Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].

 

Example 1:

Input: [1,17,8]
Output: 2
*/
    
    //thinking process: O(n^n)/O(n)
    int cnt;
    public int numSquarefulPerms(int[] A) {
        int n = A.length;
        Arrays.sort(A);
        findPerm(A, new boolean[n], new ArrayList<Integer>());
        return cnt;
    }
    
    private void findPerm(int[] A, boolean[] used, List<Integer> cur) {
        int n = A.length;
        if (cur.size() == n) {
            cnt++;
            return;
        }
        int lastE = (cur.size() == 0 ? -1 : cur.get(cur.size() - 1));
        for (int i = 0; i < n; i++) {
            if (used[i] || (i > 0 && A[i] == A[i - 1] && !used[i - 1])) {
                continue;
            }
            if (lastE == -1 || isSquarePair(lastE, A[i])) {
                cur.add(A[i]);
                used[i] = true;
                findPerm(A, used, cur);
                used[i] = false;
                cur.remove(cur.size() - 1);
            }
        } 
    }
    
    private boolean isSquarePair(int a, int b) {
        int c = (int)Math.sqrt(a + b);
        return c * c == a + b; //why this works... but a + b == c * c not work... precision???
    }
    
    //O(n*2^n)/O(n*2^n)
    //TODO:to understand this
    public int numSquarefulPerms_DP(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        
        boolean[][] s = new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int sum = a[i] + a[j];
                int sqrt = (int)(Math.sqrt(sum)+0.1);
                s[i][j] = sqrt * sqrt == sum;
            }
        }
        
        int ans = 0;
        int[][] dp = new int[1<<n][n];
        for(int i=0;i<n;){
            dp[1<<i][i] = 1;
            while(i+1 < n && a[i+1] == a[i]) i++;
            i++;
        }
        
        for(int i=0;i<(1<<n);i++){
            for(int j=0;j<n;j++){
                if(dp[i][j] > 0){
                    for(int k=0;k<n;k++){
                        if((i&(1<<k))==0 && s[j][k]){
                            if(k==0 || a[k] != a[k-1] || (i&(1<<(k-1))) > 0){
                                dp[i|(1<<k)][k] += dp[i][j];
                            }
                        }
                    }
                }
            }
        }
        
        for(int i=0;i<n;i++) ans += dp[(1<<n)-1][i];
        return ans;
    }
}
package hatecode._0001_0999;
import java.util.*;
public class _646MaximumLengthOfPairChain {
/*
646. Maximum Length of Pair Chain
You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
*/
    public int findLongestChain(int[][] p) {
        if (p == null || p.length < 1 || p[0].length < 1) return 0;
        
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b)->(a[1]-b[1]));
        for(int[] arr : p) q.offer(arr);
        
        int res = 1;
        int[] first = q.poll();
        int end = first[1];
        while(!q.isEmpty()) {
            int[] arr = q.poll();
            if (end >= arr[0]) continue;
            else {
                end = arr[1];
                res++;
            }
        }
        return res; 
    }
    
     public int findLongestChain_DP(int[][] pairs) {
        if (pairs == null || pairs.length == 0) return 0;
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], pairs[i][0] > pairs[j][1]? dp[j] + 1 : dp[j]);
            }
        }
        return dp[pairs.length - 1];
    }
    
    public int findLongestChain_Best_Performance(int[][] pairs) {
        sort(pairs,0,pairs.length);
        // print(pairs);
        int total = 1;
        int []cur = pairs[0];
        for(int i=1;i<pairs.length;++i) {
            int []p = pairs[i];
            if(p[0] > cur[1]) {
                total++;
                cur = p;
            } else {
                if(p[1]<cur[1]) {
                    cur = p;
                }
            }
        }
        return total;
    }
    
    private void sort(int[][] a,int s,int e) {
        if(s>=e) return;
        int p = partition(a,s,e);
        sort(a,s,p);
        sort(a,p+1,e);
    }
    
    private int partition(int[][] a,int s,int e) {
        int[] base = a[s];
        int j = s;
        for(int i=j+1;i<e;++i) {
            if(a[i][0]< base[0] || a[i][0]==base[0] && a[i][1]<=base[1]) {
                j++;
                swap(a,i,j);
            }
        }
        swap(a,s,j);
        return j;
    }
    
    private void swap(int [][]a, int i, int j) {
        int[]tmp = new int[2];
        tmp[0] = a[i][0];
        tmp[1] = a[i][1];
        
        a[i][0] = a[j][0];
        a[i][1] = a[j][1];
        
        a[j][0] = tmp[0];
        a[j][1] = tmp[1];
    }
    
    private void print(int [][]a) {
        for(int i=0;i<a.length;++i) {
            System.out.println(" "+a[i][0]+" "+a[i][1]);
        }
    }
}
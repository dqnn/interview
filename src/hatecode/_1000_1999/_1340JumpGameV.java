package hatecode._1000_1999;

import java.util.*;
import java.util.stream.IntStream;

public class _1340JumpGameV {
    /*
    1340. Jump Game V
    Given an array of integers arr and an integer d. In one step you can jump from index i to index:
   
   i + x where: i + x < arr.length and 0 < x <= d.
   i - x where: i - x >= 0 and 0 < x <= d.
   In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k < max(i, j)).
   
   You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.
    */
       
    /*
     * thinking process: O(kn)/O(n)
     * 
     * the problem is to
     */
       public int maxJumps(int[] A, int d) {
           int[] dp = new int[A.length];//default 0
           for (int i = 0; i < A.length; i++) {
               helper(i, dp, d, A);
           }
           return Arrays.stream(dp).max().getAsInt();
       }

       private int helper(int i, int[] dp, int d, int[] A) {
           if (dp[i] != 0) return dp[i];//already calculated, return it
           dp[i] = 1;
           for (int j = i + 1; j < A.length && j <= i + d; j++) {
               if (A[i] > A[j]) dp[i] = Math.max(dp[i], 1 + helper(j, dp, d, A));
               else break;
           }
           for (int j = i - 1; 0 <= j && j >= i - d; j--) {
               if (A[i] > A[j]) dp[i] = Math.max(dp[i], 1 + helper(j, dp, d, A));
               else break;
           }
           return dp[i];
       }
       
       
       public int maxJumps_BFS(int[] A, int d) {
           int n = A.length;
           
           Map<Integer, List<Integer>> map = new HashMap<>();
           
           for(int i = 0; i<n; i++) {
               List<Integer> left = new ArrayList<>();
               for(int j = Math.max(i-1, 0);j >= 0 && j >= i -d; j--) {
                   if (A[i] > A[j]) {
                       left.add(j);
                   } else break;
               }
               
               List<Integer> right = new ArrayList<>();
               for(int j = i+1; j <=i+d && j < n; j++) {
                   if (A[i] > A[j]) {
                       right.add(j);
                   } else break;
               }
               
               map.computeIfAbsent(i, v->new ArrayList<>()).addAll(left);
               map.computeIfAbsent(i, v->new ArrayList<>()).addAll(right);
           }
           
           //System.out.println(map);
           
           Queue<int[]> q = new LinkedList<>();
           IntStream.range(0, n).forEach(i->q.offer(new int[]{i, 1}));
           int max = Integer.MIN_VALUE;
           while(!q.isEmpty()) {
               //System.out.println("line = ");
               //for(int[] element : q) System.out.println(Arrays.toString(element));
               //System.out.println("line done ");
               int size = q.size();
               while(size-- > 0) {
                   int[] e = q.poll();
                   int p = e[0], step = e[1];
                   max = Math.max(max, step);
                   if (map.get(p) == null || map.get(p).size() == 0) continue;
                   for(int next : map.get(p)) {
                       q.offer(new int[]{next, step + 1});
                   }
                   
               }
           }
           
           return max; 
       }
   }
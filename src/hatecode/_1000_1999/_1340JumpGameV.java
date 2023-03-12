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
     * the problem is to say, given one integer array and integer d, 
     * you can start every step, and jump to any element within [i-d, i+d] within array boundary
     * also for each element in that range, all elements are smaller than A[i]
     * 
     * return the max steps you can jump
     */
    public int maxJumps(int[] A, int d) {
        int n = A.length;
        int[] dp = new int[n];
        //Arrays.fill(dp, 1);
        for(int i = 0; i<n; i++) {
            helper(A, i, dp, d);
        }
        
        return Arrays.stream(dp).max().getAsInt();
    }
    private int helper(int[] A, int i, int[] dp, int d) {
       if ( i == A.length) return 0;
        
       if (dp[i] != 0) return dp[i];
        dp[i] = 1;
        for(int j = i-1; j >=0 && j >= i-d; j--) {
            if (A[j] < A[i]) {
                dp[i] = Math.max(dp[i], 1 + helper(A, j, dp, d));
            } else break;
        }
        
        for(int j = i+1; j<A.length && j<= i+d; j++) {
            if (A[j] < A[i]) {
                dp[i] = Math.max(dp[i], 1 + helper(A, j, dp, d));
            }else break;
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
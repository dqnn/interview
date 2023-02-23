package hatecode._1000_1999;

import java.util.*;

public class _1871JumpGameVII {
    /*
    1871. Jump Game VII
    You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:
    
    i + minJump <= j <= min(i + maxJump, s.length - 1), and
    s[j] == '0'.
    Return true if you can reach index s.length - 1 in s, or false otherwise.
    
     
    
    Example 1:
    
    Input: s = "011010", minJump = 2, maxJump = 3
    Output: true
    Explanation:
    In the first step, move from index 0 to index 3. 
    In the second step, move from index 3 to index 5.
    Example 2:
    
    Input: s = "01101110", minJump = 2, maxJump = 3
    Output: false
    */
    /*
     * thinking process: O(n)/O(n)
     * 
     * the problem is to say: given string s ('0' and '1' only), int min, int max, 
     * you start from index 0, you can jump from i + min to i + max with A[j] = '0'
     * 
     * return if it possible to reach last element
     * 
     * s = "000010", minJump = 1, maxJump = 3
     * 
     * if use traditional way to visit the array, we will see when i = 0 and i = 2 we visit 
     * index 2 and 3 for 2 times, so if one string all 0, "0000000000", then Time complexity will be 
     * O(n^2), 
     * 
     * to reduce the redundant visit, we can use nextStart to denote the next starting point, 
     * which means all elements before that are all visited. 
     * 
     * 000010
     *  ---      loop when i = 0  
     *   ---     loop when i = 1
     * 000010
     *  ---        loop when i = 0  nextStart = 0
     *     ---     loop when i = 1  nextStart = 4
     * 
     *     
     */
    public boolean canReach(String s, int min, int max) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        if(chs[n-1] =='1') return false;
         
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        int nextStart = 0;
        while(!q.isEmpty()) {
            //System.out.println(q);
            int i = q.poll();
            if (i == n-1) return true;
            
            for(int j = Math.max(nextStart, i + min); j < n && j <= i + max; j++) {
                if (chs[j] =='0') {
                    q.offer(j);
                }
            }
            // update nextStart
            nextStart = i + max + 1;
            
        }
        return false;
     }
    
    public boolean canReach_Recursive(String s, int minJump, int maxJump) {
            char[] chs = s.toCharArray();
            
            if (chs[chs.length - 1] != '0') return false;
            
            boolean[] visited = new boolean[chs.length];
            if (helper(chs, 0, minJump, maxJump, visited)) return true;
            return false;
        }
        
        
        private boolean helper(char[] A, int pos, int min, int max, boolean[] visited) {
            if (pos >= A.length) return false;
            if (pos == A.length - 1) return true;
            
            if (visited[pos]) return visited[pos];
            visited[pos] = true;
            for(int i = pos + min; i < A.length && i <= pos+ max; i++) {
                if (A[i]=='0' && !visited[i]) {
                    // visited[i] = true;
                    if (helper(A, i, min, max, visited)) return true;
                }
            }
            
            return false;
        }
 
        
        public boolean canReach_DP(String s, int minJ, int maxJ) {
            int n = s.length(), pre = 0;
            boolean[] dp = new boolean[n];
            dp[0] = true;
            for (int i = 1; i < n; ++i) {
                if (i >= minJ && dp[i - minJ])
                    pre++;
                if (i > maxJ && dp[i - maxJ - 1])
                    pre--;
                dp[i] = pre > 0 && s.charAt(i) == '0';
            }
            return dp[n - 1];
        }
    }

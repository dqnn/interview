package hatecode;
import java.util.*;
public class RaceCar {
    /*
     * 818. Race Car Your car starts at position 0 and speed +1 on an infinite
     * number line. (Your car can go into negative positions.)
     * 
     * Your car drives automatically according to a sequence of instructions A
     * (accelerate) and R (reverse).
     * 
     * When you get an instruction "A", your car does the following: position +=
     * speed, speed *= 2.
     * 
     * When you get an instruction "R", your car does the following: if your speed
     * is positive then speed = -1 , otherwise speed = 1. (Your position stays the
     * same.)
     * 
     * For example, after commands "AAR", your car goes to positions 0->1->3->3, and
     * your speed goes to 1->2->4->-1.
     * 
     * Now for some target position, say the length of the shortest sequence of
     * instructions to get there.
     * 
     * Example 1: Input: target = 3 Output: 2 Explanation: The shortest instruction
     * sequence is "AA". Your position goes from 0->1->3.
     */
    
    //dp solution, top down 
    public int racecar(int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, 1, dp.length, -1);
        return racecar(target, dp);
    }

private int racecar(int i, int[] dp) {
    if (dp[i] >= 0) {
        return dp[i];
    }
    
    dp[i] = Integer.MAX_VALUE;
    
    int m = 1, j = 1;
    
    for (; j < i; j = (1 << ++m) - 1) {
        for (int q = 0, p = 0; p < j; p = (1 << ++q) - 1) {
            dp[i] = Math.min(dp[i],  m + 1 + q + 1 + racecar(i - (j - p), dp));
        }
    }
    
    dp[i] = Math.min(dp[i], m + (i == j ? 0 : 1 + racecar(j - i, dp)));
    
    return dp[i];
}
    //bottom up
    public int racecar_DP_Bottom_UP(int target) {
    int[] dp = new int[target + 1];
    
    for (int i = 1; i <= target; i++) {
        dp[i] = Integer.MAX_VALUE;
        
        int m = 1, j = 1;
        
        for (; j < i; j = (1 << ++m) - 1) {
            for (int q = 0, p = 0; p < j; p = (1 << ++q) - 1) {
                dp[i] = Math.min(dp[i], m + 1 + q + 1 + dp[i - (j - p)]);
            }
        }
        
        dp[i] = Math.min(dp[i], m + (i == j ? 0 : 1 + dp[j - i]));
    }
    
    return dp[target];
}
    
  //DP, O(t^2)/O(t)
  //m[t][d] : min steps to reach t and facing d (0 = right, 1 = left). speed direction
  private static int[][] m;
  public int racecar_DP2D(int target) {
    if (m == null) {
      final int kMaxT = 10000;
      m = new int[kMaxT + 1][2];
      for (int t = 1; t <= kMaxT; ++t) {
        int n = (int)Math.ceil(Math.log(t + 1) / Math.log(2));
        if (1 << n == t + 1) {
          m[t][0] = n;
          m[t][1] = n + 1;
          continue;
        }
        int l = (1 << n) - 1 - t;
        m[t][0] = n + 1 + Math.min(m[l][1], m[l][0] + 1);
        m[t][1] = n + 1 + Math.min(m[l][0], m[l][1] + 1);
        for (int i = 1; i < t; ++i) 
          for (int d = 0; d <= 1; d++)
            m[t][d] = Math.min(m[t][d], Math.min(
                m[i][0] + 2 + m[t - i][d],
                m[i][1] + 1 + m[t - i][d]));
      }
    } 
    return Math.min(m[target][0], m[target][1]);
  }
    
    //BSF solution, O(target * log(target))/O(target * log(target))
    //The reasoning is as follows: in the worst case, all positions in the range [-target, target] will be visited and for each position there can be as many as 2 * log(target) different speeds.
    public int racecar_BSF(int t) {
        Deque<int[]> q = new LinkedList<>();
        q.offerLast(new int[]{0,1});
        
        Set<String> visited = new HashSet<>();
        visited.add(getKey(new int[]{0,1}));
        
        int level = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i< size; i++) {
                int[] cur = q.pollFirst();
                if (cur[0] == t) return level;
                
                int[] next = new int[] {cur[0] + cur[1], 2 * cur[1] };  // accelerate instruction
                String key = getKey(next);
                if (!visited.contains(key) && next[0] > 0 && next[0] < (t << 1)) {
                    q.offerLast(next);
                    visited.add(key);
                }
                //reverse use case
                next = new int[] {cur[0], cur[1] > 0 ? -1 : 1};
                key = getKey(next);
                if (!visited.contains(key) && next[0] > 0 && next[0] < (t << 1)) {
                    q.offerLast(next);
                    visited.add(key);
                }
            }
            level +=1;
        }
        return -1;
    }
    
    private String getKey(int[] temp) {
        return temp[0] + " " + temp[1];
    }
}
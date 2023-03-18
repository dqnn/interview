package hatecode._0001_0999;
import java.util.*;
public class _818RaceCar {
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
    
    
     public int racecar_DP(int e) {
        // Create a DP array to store solutions to subproblems
        int[] dp = new int[e + 1];
        // Call the DP function to find the minimum number of steps to reach the target
        return helper(e, dp);
    }

    // DP function to find the minimum number of steps to reach the target
    private int helper(int e, int[] dp) {
        // Base case: if the target is 0, return 0
        if (e == 0) {
            return 0;
        }

        // If we have already solved this subproblem, return the solution from the DP array
        if (dp[e] > 0) {
            return dp[e];
        }

        // Find the minimum number of steps to reach the target using the reverse strategy
        int n = (int) (Math.log(e) / Math.log(2)) + 1;

        // If we can reach the target by accelerating only once, return that number of steps
        if (1 << n == e + 1) {
            dp[e] = n;
        } else {
            // Otherwise, find the minimum number of steps to reach the target using a combination of
            // accelerating and reversing

            // First, reverse to reach a point before the target
            dp[e] = n + 1 + helper((1 << n) - 1 - e, dp);

            // Then, for each possible number of times to accelerate and reverse (up to n-1 times),
            // find the minimum number of steps required to reach the target from the current point
            for (int m = 0; m < n - 1; m++) {
                int current = (1 << (n - 1)) - (1 << m);
                dp[e] = Math.min(dp[e], helper(e - current, dp) + n + m + 1);
            }
        }

        // Save the solution to this subproblem in the DP array and return it
        return dp[e];
    }
    
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
  //m[t][d] : min steps to reach target and facing d (0 = right, 1 = left). speed direction
  private static int[][] dp;
  public int racecar_DP2D(int target) {
    if (dp == null) {
      //target will be less than 10000
      int kMaxT = 10000;
      dp = new int[kMaxT + 1][2];
      for (int t = 1; t <= kMaxT; ++t) {
        int n = (int)Math.ceil(Math.log(t + 1) / Math.log(2));
        //the position would be 0 ,1, 3, 7, 15, 31... 2^n - 1. n means steps
        //so this would mean we reached the target, every possible 2^t how many possible combinations
        //it is very likely coin changes problems
        if ((1 << n) - 1 == t) {
          dp[t][0] = n;
          //if we face left means we have turned around at position target, position not 
          //change but just turn around, so need to +1
          dp[t][1] = n + 1;
          continue;
        }
        //l means the distance between t closet 2^n, t<= 2^n - 1, so l >=0, and means
       //            |-> l<-|
        //|----------|------|
       //            t      2^n - 1
        //how many steps can reach this part of distance
        int l = (1 << n) - 1 - t;
        //dp[l][1] means at l position face left, 
        dp[t][0] = n + 1 + Math.min(dp[l][1], dp[l][0] + 1);
        dp[t][1] = n + 1 + Math.min(dp[l][0], dp[l][1] + 1);
        for (int i = 1; i < t; ++i) 
          for (int d = 0; d <= 1; d++)
            //m[t][d] = min(m[i][0] + 2 +m[t-i][d], m[i][1] + 1 + m[t-i][d])
            dp[t][d] = Math.min(dp[t][d], Math.min(
                dp[i][0] + 2 + dp[t - i][d],
                dp[i][1] + 1 + dp[t - i][d]));
      }
    } 
    return Math.min(dp[target][0], dp[target][1]);
  }
    
    //BSF solution, O(t*lg(t))/O(t*lg(t)), each step we have 2 options A or R
    //The reasoning is as follows: in the worst case, all positions in the range [-target, target] 
    //will be visited and for each position there can be as many as 2 * log(target) 
    //different speeds.
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
                //target
                /*
                 * next[0] < (t << 1)
                 * ==> Math.abs(next[0] - target) < target
                 * it make sure the point is in the range of (0, target)
                 */
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
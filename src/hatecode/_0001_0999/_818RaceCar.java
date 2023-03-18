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
    
    /*
     * interview friendly, thinking process: O(elge)/O(e)
     * 
     * 这道题我们首先要认识到一件事，就是position的变化规律。


 position: 0 -> 1 -> 3 -> 7 -> 15
 speed:    1 -> 2 -> 4 -> 8 -> 16
可以看到，position的以上这些变化其实就是 2^n - 1 ，(n = 0, 1, 2, 3, 4)，所以我们对position的加减操作也是只能基于值为 2^n - 1 的这些数来操作的。

现在我们开始考虑如何到达 target。基于这些可以操作的数，我们到达target一共有三种可能的情况。

target本身就是 2^n-1，那我们只需要走n步,即使用n个A就可以到达；

我们走 n 步，越过了 target，这时候我们再通过 R 回头，然后我们此时和target的距离就是 (2^n - 1) - target。因为往前走的距离是 2^n-1，此时已经超过了 target，那么还需要走的距离就是 (2^n-1-target),我们只需要再得到回头走的这段距离需要的步数，加上 n + 1，就是到达 target 所需要的步数。为什么需要n + 1 呢？因为我们回头了嘛，需要一个 R。
注意，此时重复子问题已经出现了，dp妖娆的身姿若隐若现。

我们走 n 步，没有越过 target，这时候我们就先回头，往回走一点，假设这时回头走了back步，back肯定是小于 n 的，不然我们刚开始就白走了。但是要回头走多少呢？我们肯定没法直接决定出一个精确的数值，所以需要在这里循环，试往回走多少能用的步数最小。之前走了 n 步，然后又走了back步，这时候距离target还剩 target - ((2^n-1) - (2^back-1)) 要走。
此时要到达target，我们需要走的步数就是 n + 1 + back + 1 + (走 target - ((2^n-1) - (2^back-1)) 所需要的步数)，同理，加的两个1是两次回头所需要的R。
现在来考虑dp方程怎么写。

我们设dp[i]就等于 target = i时，需要的最小步数。

对应的我们上面分析的三种情况如下：

i = 2^n - 1, 即走n步直接到达i：dp[i] = n

先走 forward 步越过了i,再回头根据上面的分析，我们需要回头走的距离是2^forward - 1 - i。
dp[i] = min(dp[i], forward + 1 + dp[2^forward - 1 - i])

先走 forward 步，此时还没有到 i，直接回头，走一段之后再回头向前走到达i。我们先回头走的距离是 2^back-1，然后再回头走到i的距离是 i - ((2^forward-1)-(2^back-1))
dp[i] = min(dp[i], forward + 1 + back + 1 + dp[i - ((2^forward-1)-(2^back-1))])

     */
    public int racecar_DP(int e) {
        //处理边界
        if (e <= 0) {
            return 0;
        }

        int[] dp = new int[e + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 1; i <= e; i++) {
            //先向前走 forward 步
            for (int fwdStep = 1; (1 << fwdStep) - 1 < 2 * i; fwdStep++) {
                //fwdDis
                int fwdDis = (1 << fwdStep) - 1;
                //对应第一种情况，走了forward步直接到达i
                if (fwdDis == i) {
                    dp[i] = fwdStep;
                } else if (fwdDis > i) { //对应第二种情况，越过了i
                    // +1 是因为回头需要一个R指令
                    dp[i] = Math.min(dp[i], 
                            fwdStep + 1 + dp[fwdDis - i]);
                } else { //对应第三种情况，没有越过i
                    //先回头走backward步
                    for (int backStep = 0; backStep < fwdStep; backStep++) {
                        int backwardDis = (1 << backStep) - 1;
                        //第一个+1是还没到达i，先回头，使用一个R
                        //第二个+1是回头走了backwardDistance，再使用R回头走向i
                        /*                           i
                                                     |
                         * ---------------------------------------
                         *                  |
                         *                 fwd
                         */
                        dp[i] = Math.min(dp[i], 
                                fwdStep + 1 + backStep + 1 + dp[i - fwdDis + backwardDis]);
                    }
                }
            }
        }
        return dp[e];
    }
     
     public int racecar_DP_UpDown(int e) {
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

        /*
         * we have 3 cases:
         * 
         */

        // case 1, for example: e = 15, then n = 4, it is smallest length
        if (1 << n == e + 1) {
            dp[e] = n;
        } else {
            // case 2: passed the e, rewind back to some point and start
            /*             <--
             * ---------|-----|
             *          e     2^n
             * n + 1 means steps taken to position n, and reverse direction at n take 1 (R)
             * 2^n - 1 - e: thinking about we start from last point with speed =0, it is like 
             * mirror when we start from 0 with speed 1, it is the same as we start from 0 to e - 1
             * since distance is the same
             * we need to - 1 because 
             */
            dp[e] = n + 1 + helper((1 << n) - 1 - e, dp);

            //case 3: in some middle point,[0, e], speed direction reverse towards to e
        /*
         * //            |->cur<-|
        //    |----------|-------|---
       //                m       e   2^n 
        
         dp[e]: face right
         helper(e - cur, dp)
         */
            for (int back = 0; back < n - 1; back++) {
                int cur = (1 << (n - 1)) - (1 << back);
                dp[e] = Math.min(dp[e], helper(e - cur, dp) + n + back + 1);
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
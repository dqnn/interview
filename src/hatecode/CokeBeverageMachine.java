package hatecode;

import java.util.*;
public class CokeBeverageMachine {
    /*
     * from Goole interview doc: https://docs.google.com/document/d/
     * 1qxA2wps0IhVRWULulQ55W4SGPMu2AE5MkBB37h8Dr58/edit#
     * 
     * problem statement:
     * 有一系列按钮，每个按钮按下去会得到一定体积范围的可乐。先给定一个目标体积范围，问不限制按按钮次数，
     * 能否确定一定能得到目标范围内的可乐？
     * 
     * 举例：有三个按钮，按下去得到的范围是[100, 120], [200, 240], [400, 410], 
     * 假设目标是[100, 110],那答案是不能。因为按下一，可能得到120体积的可乐，不在目标范围里。 
     * 假设目标是[90, 120]，那答案是可以。因为按下一，一定可以得到此范围内的可乐。
     * 假设目标是[300, 360], 那答案是可以，因为按下一再按二，一定可以得到此范围内 
     * 假设目标是[310, 360],那答案是不能，因为按下一再按二，有可能得到300，永远没可能确定得到这个范围内的可乐。 
     * 假设目标是[1,9999999999]，那答案是可以。随便按一个都确定满足此范围
     * 
     */
    class Button{
        int lo;
        int hi;
    }
    //so the problem is to say to give  
    public boolean isPossible(List<Button> buttons, int lower, int upper) {
        if (buttons == null || buttons.size() < 1 || lower > upper) return false;
        
        Map<String, Boolean> visited = new HashMap<>();
        return helper(buttons, 0, 0, lower, upper, visited);
    }
    
    private boolean helper(List<Button> buttons, int lo, int hi, int lower, int upper, Map<String, Boolean> visited) {
        Boolean res = visited.get(lo + "->" + hi);
        if (res != null) return res;
        if (lo >= lower && hi <= upper) return true;
        //why we did not detect lower?
        if (hi > upper) return false;
        for(Button b : buttons) {
            if (helper(buttons, lo + b.lo, hi + b.hi, lower, upper, visited)) {
                visited.put(lo + "->" + hi, true);
                return true;
            }
        }
        visited.put(lo + "->" + hi, false);
        return false;
    }
    //also another DP solution,
    //
    public static boolean helper2(List<List<Integer>> buttons, List<Integer> target) {
        int m = target.get(0);
        int n = target.get(1);
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        //Init, if target-(lo and hi) are in the range, then dp[i][j] are true
        for (int i = 0; i <= m; ++i) {
          for (int j = 0; j <= n; ++j) {
            for (List<Integer> button: buttons) {
              if (i <= button.get(0) && j >= button.get(1)) {
                dp[i][j] = true;
                break;
              }
            }
          }
        }
        for (int i = 0; i <= m; ++i) {
          for (int j = 0; j <= n; ++j) {
            //like knapsacks problem? 
            for (List<Integer> button: buttons) {
              int preL = i - button.get(0);
              int preR = j - button.get(1);
              if (preL >= 0 && preR >= 0 && dp[preL][preR]) {
                dp[i][j] = true;
               break;
              }
            }
          }
        }
        return dp[m][n];
      }

    public static void main(String[] args) {
    }

}

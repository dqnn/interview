package hatecode;
public class FreedomTrail {
/*
514. Freedom Trail
Input: ring = "godding", key = "gd"
Output: 4
Explanation:
For the first key character 'g', since it is already in place, we just need 1 step to spell this character. 
For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
Also, we need 1 more step for spelling.
So the final output is 4.
*/
   public int findRotateSteps(String ring, String key) {
       int m = key.length(); 
       int n = ring.length();
        
        /*dp[i][j] is conceptually the minimum steps needed to to complete 
         * key[i:], while starting at ring[j]. (ignore the steps for spelling, 
         * which is added at the end => 'm').
Thus, in the inner-most loop, we looking for all indexes 'k' in ring s.t. ring[k] == key[i]. 
We try to go to all such index 'k' from 'j', calculate the minimum distance to get there => 'step', and the total cost will be 'step' + dp[i + 1][k]', where the later is the minimum cost of constructing the remaining key[i+1:] starting from 'k'. */
        int[][] dp = new int[m+1][n];
        //Start by solving key[i:m-1] -> smallest instance is key[m-1:m-1]
        for (int i = m-1; i>= 0; i--){
            //Solve the problem for key[i:m-1] and when the ring arrow points at index j.
            for (int j = 0; j < n; j++){
                dp[i][j] = Integer.MAX_VALUE;
                //Try out every type of spin (by 0, 1, 2, 3, and choose the best choice)
                for (int k = 0; k < n; k++){
                    if (ring.charAt(k) == key.charAt(i)){
                        //Using greedy logic that we should just use the min spin 
                        int diff = Math.abs(j-k);
                        //Choose the min of moving clockwise or counterclockwise
                        int step = Math.min(diff, n-diff);
                        //dp[i+1][k] = Solve the subproblem from key[i+1: m-1] and 
                        //while our pointer points to k now since
                        //we have rotated the dial there. key[i]-->ring[k]
                        dp[i][j] = Math.min(dp[i][j], step + dp[i+1][k]);
                    }
                }
            }
        }
        
        return dp[0][0] + m; //We will press the dial button m times in total no matter what.
    }
    
    //memmo solutions
    public int findRotateSteps_Memo(String ring, String key) {
        char[] ringArr = ring.toCharArray();
        char[] keyArr = key.toCharArray();
        
        return dfs(ringArr, keyArr, 0, 0, new int[ringArr.length][keyArr.length]);
    }
    private  int dfs(char[] ring, char[] target, int targetIndex, int ringIndex, int[][] memo){
        if(targetIndex == target.length)    return 0;
        
        if(memo[ringIndex][targetIndex] != 0)   return memo[ringIndex][targetIndex];
        
        int min = Integer.MAX_VALUE;
        for(int i = 0;i < ring.length;i++){
            if(ring[i] != target[targetIndex])  continue;
            
            int dif = Math.abs(i - ringIndex);
            int distance =  1 + Math.min(dif, ring.length - dif) + dfs(ring, target, targetIndex + 1, i, memo);
            min = Math.min(min, distance);            
        }
        
        memo[ringIndex][targetIndex] = min;
        return min;
    }
}
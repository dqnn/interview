package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : DistinctSubsequences
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 115. Distinct Subsequences
 */
public class DistinctSubsequences {
    /**
     * Given a string S and a string T, count the number of distinct subsequences of
     * S which equals T.
     * 
     * A subsequence of a string is a new string which is formed from the original
     * string by deleting some (can be none) of the characters without disturbing
     * the relative positions of the remaining characters. (ie, "ACE" is a
     * subsequence of "ABCDE" while "AEC" is not).
     * 
     * Here is an example: S = "rabbbit", T = "rabbit"
     * 
     * Return 3.
     * 
     * int[][] dp
     * 
     * 1, S.charAt(i) != T.charAt(j) dp[i][j] = dp[i-1][j]
     * 
     * 2, S.charAt(i) == T.charAt(j) dp[i][j] = dp[i-1][j] + dp[i-1][j-1]
     * 
     *    r  a  b  b  i  t
       1, 0, 0, 0, 0, 0, 0
     r 1, 1, 0, 0, 0, 0, 0
     a 1, 1, 1, 0, 0, 0, 0
     b 1, 1, 1, 1, 0, 0, 0
     b 1, 1, 1, 2, 1, 0, 0
     b 1, 1, 1, 3, 3, 0, 0
     i 1, 1, 1, 3, 3, 3, 0
     t 1, 1, 1, 3, 3, 3, 3
     * 
     * time : O(m * n) space : O(m * n)
     */

    /*
     * 令 X=<x1,x2,...,xm> 和 Y=<y1,y2,...,yn> 为两个序列，Z=<z1,z2,z3,...,zk>为X和Y的任意LCS。则
     * 
     * 如果xm=yn，则zk=xm=yn且Zk−1是Xm−1和Yn−1的一个LCS。
     * 
     * 意思就是说 如果两个序列最后一个值相等，那么我们Zk 肯定是在LCS 中，并且没有Zk的话 那么Zk-1 也必须是 两个输入串都减去最后一个，在这种情况下
     * +1 就是Zk的最大长度
     * 
     * 如果两个输入串最后一个值不等， 那么我们把亮哥输入串最后一个去掉也不会影响LCS 结果集合 所以这样的话我们可以取两个的最大值，因为LCS的长度不变
     * 我们取两个的最大长度因为很可能x 或则 y last element is in LCS, but we don't know
     * 
     * 如果xm≠yn，那么zk≠xm，意味着Z是Xm−1和Y的一个LCS。
     * 
     * 如果xm≠yn，那么zk≠yn，意味着Z是X和Yn−1的一个LCS。
     * 
     * 从上述的结论可以看出，两个序列的LCS问题包含两个序列的前缀的LCS，因此，LCS问题具有最优子结构性质。在设计递归算法时，
     * 不难看出递归算法具有子问题重叠的性质。
     * 设C[i,j]表示Xi和Yj的最长公共子序列LCS的长度。如果i=0或j=0,即一个序列长度为0时，那么LCS的长度为0。根据LCS问题的最优子结构性质，
     * 可得如下公式：
     * 
     * C[i,j]= 0，当i=0或j=0
     * 
     * = C[i−1,j−1]+1， 当i,j>0且xi=yj
     * 
     * = MAX(C[i,j−1],C[i−1,j])当i,j>0且xi≠yj
     * 
     * 
     * @param S
     * 
     * @param T
     * 
     * @return
     */
    // this is LCS， classical algorithms seires
    // original is to find the longest common sequence
    // s == t
    // dp[i,j] = dp[i-1][j-1] + 1
    // s != t-->dp[i, j] = max(dp[i-1, j], dp[i][j -1])
    // this question is to want to have the count of different sequences in S, T is the target
    //physical procedure: we visit S and T from 0 to b=n, from this process, what and where and how the 
    //value can be re-used. 
    // the two questions are totally different, so 
    //  we have to keep target because if we remove any element from target then result will be wrong
    //if si = tj, which means suppose source remove last element, dp[i-1][j], this means 
    // how many LCS in si-1 with tj, so last si just follow to be added in the result, it does not change.
    // another form is we both do not need last element, this will be dp[i-1][j-1], the element sj impact 
    // will be only these 2  froms. 
    
    // when si != tj, which means last element impact actaully is the same with si-1 because we have to keep
    // the sequence, dp[i-1][j]
    
    
    // about Initial value
    // some times we will initialize the dp[0][0] as 0, some times will be 1
    //LCS initialize as 0 because they want to find LCS or length of LCS, there is no meaning to be 1
    // here we initialize as 1 because if t = "" which means 
    // we don't need to do anything so it would equals T.
    public static int numDistinct(String s, String t) {
        //dp[i][j] means S[0,i] has dp[i][j] sequence equals to T[0-j]
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        // 因为我们只要不选S中任何的字符,就能成为T。所以用dp做时,dp[i][0]我们都设为1.
        for (int i = 0; i < s.length(); i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    //so this is the key point, if s[i-1] = t[j-1]
                    //那么S[0...i-1]包含的T[0...j-1]的数量 = S[0...i-2]包含T[0...j-1]的数量
                    //加上**S[0...i-2]包含T[0...j-2]的数量
                    //换句话, 当S[i-1]==T[j-1]时, 情况可以被分为T带S[i]玩和不带S[i]玩的情况, 
                    //两种情况的可能结果加起来就是总共的 :
                    //eg: s = "baggg", t= bag, when s[i] = "baggg" and t[j] = 'bag'
                    //1. we want to know s[i-1]='bagg' contains how many t[j] = 'bag'
                    //2. remove last char both, s[i-1] = 'bagg', t[j-1] = 'ba', how many
                    //times s[i-1] contains t[j-1] because last one is the same, this means
                    //2 gs is replacable
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
                } else {
                //so s="raaaaar" and t="r", so we can see when we visit "a", no matter 
                //which, the value is 1, the same as left in matrix, so we use dp[i-1][j]
                //also means s[i-1] is no use
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[s.length()][t.length()];
    }
    // this is DFS solution
    //space O(m*n), Time ？
    public int numDistinct2(String s, String t) {
        int[][] dp = new int[s.length()][t.length()];
        
        for(int i = 0; i < s.length(); ++i) {
            for(int j = 0; j < t.length(); ++j) {
                dp[i][j] = -1;
            }
        }
        
        return numDistinctDFS(s,t, 0,0, dp);
    }
    
    int numDistinctDFS(String s, String t, int i, int j, int[][] dp) {
        if(j >= t.length())
            return 1;
        
        if(i >= s.length())
            return 0;
        
        if(dp[i][j] >= 0)
            return dp[i][j];
        
        int count = 0; 
        for(int k = i; k < s.length(); ++k) {
            if(s.charAt(k) == t.charAt(j)) {
                count += numDistinctDFS(s,t, k+1, j+1, dp);
            }
            
        }
        
        dp[i][j] = count;
        
        return count;
    }
}

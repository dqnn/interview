package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : IntegerBreak
 * Creator : professorX
 * Date : Jan, 2018
 * Description : 343. Integer Break
 */
public class _343IntegerBreak {
    /**
     * Given a positive integer n, break it into the sum of at least two positive integers and
     * maximize the product of those integers. Return the maximum product you can get.

     For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

     2 : 1 + 1
     3 : 2 + 1
     4 : 2 + 2
     5 : 3 + 2
     6 : 3 + 3
     7 : 3 + 4
     8 : 3 + 3 + 2
     9 : 3 + 3 + 3
     10 : 3 + 3 + 4

     time : < O(n) O(1)
     space : O(1)

     * @param n
     * @return
     */
    // this is the best solutions
    // so n = p1* p2 * p3..Pn, Pi can be changed to 2 * (Pi -2) > Pi--> pi > 4, 
    // 3 * (Pi - 3) > pi --> Pi > 4.5, so each number(n) if bigger than 4, 5 should be changed to 
    // 2 * (n -2), but 2 or 3 which is better, 3(n - 3) > 2 (n - 2)--> n > 5, if n bigger than 5, we favor 
    // 3 more than 2 always, --> n = 3 * a + 2 * b, to calculate how many 3 and 2s. 
    // but we always favor 3 instead of 2 until n is smaller than 5
    //Math.pow() is O(1), see https://stackoverflow.com/questions/32418731/java-math-powa-b-time-complexity
    public int integerBreak3(int n) {
        if(n == 2)
            return 1;
        else if(n == 3)
            return 2;
        else if(n%3 == 0)
            return (int)Math.pow(3, n/3);
        else if(n%3 == 1)
            return 2 * 2 * (int) Math.pow(3, (n - 4) / 3);
        else // here is n % 3 == 2
            return 2 * (int) Math.pow(3, n/3);
    }
    
    // math background:
    //  n into (n / x) x's, then the product will be xn/x,
    //derivatives, n * x ^ (n/x-2) * (1 - ln(x)), so x= e, the function will climb its max, 
    // we want to factors are all around e so we can get biggest product
    // number under 4 is flaws, 6 = 2 + 2 + 2 = 3 + 3, but latter is better
    public int integerBreak(int n) {
        if (n == 2 || n == 3) return n - 1;
        int res = 1;
        //If an optimal product contains a factor f >= 4, then you can replace it with factors 2 and f-2 
        //without losing optimality, as 2*(f-2) = 2f-4 >= f. So you never need a factor greater than or 
        //equal to 4, meaning you only need factors 1, 2 and 3 (and 1 is of course wasteful 
        //and you'd only use it for n=2 and n=3, where it's needed).
        while (n > 4) {
            res *= 3;
            n -= 3;
        }
        return res * n;
    }
    
    /*
    This is a typical knapsack problem. We can assume that the volume of the knapsack is n. 
    The items we can choose range from 1 to n - 1(because we must divide n into 
    at least two positive parts). The point is that we can choose each item many times.
The first loop means the items we can choose(i means first i items).
And in the second loop, j means the sum of items that we are going to choose.
For each item, we have two choices, pick it up or not. And we should choose the max result.
    */
    public int integerBreak2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int max = 0;
        //from 1 because we have n = 2 cases, 
        for(int i = 1; i < n; i++){
            for(int j = i; j <= n; j++){
                // dp[j - i] * i means we loop from a + b = n
                // a = [1, n-1), b = [1, ]
                dp[j] = Math.max(dp[j], dp[j - i] * i);
                if(j == n){
                    if(max < dp[j]) max = dp[j];
                }
            }
        }
        return max;
    }
}

package hatecode._1000_1999;
public class _1227AirplaneSeatAssignmentProbability {
/*
1227. Airplane Seat Assignment Probability
n passengers board an airplane with exactly n seats. The first passenger has lost the ticket and picks a seat randomly. But after that, the rest of passengers will:

Take their own seat if it is still available, 
Pick other seats randomly when they find their seat occupied 
What is the probability that the n-th person can get his own seat?

 

Example 1:

Input: n = 1
Output: 1.00000

Input: n = 2
Output: 0.50000
*/
    
    //thinking process: O(1)/O(1), pure math
    /*
     Let f(n) be the probability that the n-th passenger will get his own seat.
If the 1st passenger get the 1st seat, then everyone will get their own seats, 
so the n-th passenger gets his own seat with probability: 1/n
If the 1st passenger get the 2nd seat, with probability 1/n, then n-1 seats left. 
Here, the 2nd passenger faces the same situation of the 1st passenger, he can just 
randomly choose a seat. So it is the same question but n becomes n-1. In this situation, 
the probability that the n-th passenger gets his seat is f(n-1). So the final probability is f(n-1)*(1/n)
If the 1st passenger get the 3rd seat, ..., the probability is f(n-2)*(1/n)
...
....
......
Then f(n) = 1/n + f(n-1)*(1/n)+ f(n-2)*(1/n) + ... + f(2)*(1/n)
Let's solve this recursive formula:
nf(n) = 1+ f(n-1)+ f(n-2) + ... + f(2) ...... Equation 1
(n-1)f(n-1) = 1+ f(n-2)+ ... + f(2) ...... Equation 2
Use Equation 1 - Equation 2
nf(n) - (n-1)f(n-1) = f(n-1)
so
f(n) = f(n-1) when n>=3
We already know f(2)=0.5
So f(n) = 0.5 when n>=2
     */
    
    public double nthPersonGetsNthSeat(int n) {
        if (n == 1) return 1.0;
        return 1.0/2;
    }
    /*
     * Based on the code in part 1, we have the following formula:
通过条件概率，构造递推公式，假设 n 个人的答案为 f(n)。

n = 1 or n choose seat n
已知 f(1)=1。假设现在有 n 个人，如果第一个人选了 1 号座位，则第 n 个人必定会坐到自己的座位上。这个事件发生的概率为 1/n。
如果第一个人选了 n 号座位，则第 n 个人永远不会坐到自己的座位上。这个事件发生的概率也为1/n。 

n in [2, n-1]
接下来，不妨假设第一个人选了 j 号座位，其中 2≤j≤n−1，则[2, j-1] 个人都会坐到自己的座位上，
第 j 个人到第 n 个人的座位有可能被打乱。此时，如果第 j 个人选择了第一个人的座位，则第 n 个人必定会坐到自己的座位上。
如果第 j 个人选了 n 号座位，则第 n 个人永远不会坐到自己的座位上，所以规模变成了 n−j+1 个人的问题。
综上，当 n≥2 时，递推公式可以为 f(n)=1/n⋅1 + 1/n⋅0+ 1/n⋅(f(n-1) + f(n-2) + f(2)) = 1/n (f(1) + ... + f(n-1))

     */
    public double nthPersonGetsNthSeat_Recursive(int n) {
        if (n == 1) return 1.0d;
        return 1d / n + (n - 2d) / n * nthPersonGetsNthSeat(n - 1);
    }
}
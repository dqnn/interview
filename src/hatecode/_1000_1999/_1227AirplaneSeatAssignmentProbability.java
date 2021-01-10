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
    
    //thinking process:
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

f(n) = 1 / n + (n - 2) / n * f(n - 1)
Part2: Proof when n > 1, the f(n) is 1/2
n = 2, we have f(2) = 1/2; the assumption holds;
Suppose n = k we have f(k) = 1/2, when n = k + 1,
f(k + 1) = 1 / (k + 1) + (k + 1 - 2) / (k + 1) * f(k)
         = 2 / (2 * (k + 1)) + (k - 1) / (k + 1) * 1/2
         = 1 / 2
That is, f(k + 1) = 1 / 2 also holds.
     */
    public double nthPersonGetsNthSeat_Recursive(int n) {
        if (n == 1) return 1.0d;
        return 1d / n + (n - 2d) / n * nthPersonGetsNthSeat(n - 1);
    }
}
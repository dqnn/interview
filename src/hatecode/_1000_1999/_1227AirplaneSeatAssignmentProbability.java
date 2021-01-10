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
     * f(n) = 1/n                                    -> 1st person picks his own seat, all others including the nth person is going to get their own seats
    + 1/n * 0                                 -> 1st person picks last one's seat, there's no chance the nth peroson is going to get his own seat
    + (n-2)/n * (                        ->1st person picks one of seat from 2nd to (n-1)th
        1/(n-2) * f(n-1) +                 -> 1st person picks 2nd seat, see explanation 1 below
        1/(n-2) * f(n-2) +                 -> 1st person picks 3rd seat see explanation 2 below
        ......
        1/(n-2) * f(2)                     -> 1st person pick (n-1)th's seat
    )

f(n) = 1/n + 0 + 1/n * (f(n-1) + f(n-2) + ... + f(2)) = 1/n * (f(n-1) + f(n-2) + ... + f(2) + 1) = 1/n * (f(n-1) + f(n-2) + ... + f(2) + f(1)) because f(1) = 1 

Explanation 1:
1st person picks 2nd seat, then the 2nd person becomes the new "careless" person, and he can pick 1st, 3rd, 4th ... nth seat. To him, the number of seats is n-1. This is a subproblem.
Explanation 2:
1st person picks 3rd seat, then the 2nd person will sit at his own seat, and the 3rd person becomes the new "careless" peroson, and he can pick 1st, 4th, 5th .., nth seat. To him, the number of seats is n-2. This is a subproblem.
     */
    
    public double nthPersonGetsNthSeat(int n) {
        if (n == 1) return 1.0;
        return 1.0/2;
    }
    
    public double nthPersonGetsNthSeat_Recursive(int n) {
        if (n == 1) return 1.0d;
        return 1d / n + (n - 2d) / n * nthPersonGetsNthSeat(n - 1);
    }
}
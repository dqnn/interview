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
     * 
     */
    
    public double nthPersonGetsNthSeat(int n) {
        if (n == 1) return 1.0;
        return 1.0/2;
    }
}
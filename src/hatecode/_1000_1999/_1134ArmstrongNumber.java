package hatecode._1000_1999;
public class _1134ArmstrongNumber {
/*
1134. Armstrong Number
The k-digit number N is an Armstrong number if and only if the k-th power of each digit sums to N.

Given a positive integer N, return true if and only if it is an Armstrong number.

 

Example 1:

Input: 153
153 = 1^3 + 5^3 + 3^3
Output: true
*/
    //thinking process: O(logN)/O(1)
    
    //directly solution
    public boolean isArmstrong(int N) {
        if(N <= 0) return false;
        //number of digits in N
        int k = (int) (Math.log10(N) + 1);
        //temporary variable (so we dont modify N)
        int x = N;
        //to hold sum
        int sum = 0;
        //get each digit
        while (x != 0) {
            //add this digit^k to sum
            sum += Math.pow(x % 10, k);
            //get next digit
            x /= 10;
        }
        return sum == N;
    }
}
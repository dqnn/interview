package hatecode._1000_1999;
public class _1362ClosestDivisors {
/*
1362. Closest Divisors
Given an integer num, find the closest two integers in absolute difference whose product equals num + 1 or num + 2.

Return the two integers in any order.

 

Example 1:

Input: num = 8
Output: [3,3]
*/
    
    //thinking process: O(sqrt(A)/O(1)); math problem
    
    //the problem is to say: given one integer A, return two number
    //which are closest and their product equals A+1 or A=2
    
    //one is we use sqrt (A+2) and back to 1 to try, greedy thinking
    public int[] closestDivisors(int A) {
        int sqrt = (int)Math.sqrt(A+2);
        for(int i = sqrt; i > 0; i--) {
            if( (A+1) % i == 0) {
                return new int[]{i, (A+1)/i};
            }
            if ((A+2)%i == 0) {
                return new int[]{i, (A+2)/i};
            }
        }
        
        return new int[]{};
    }
}
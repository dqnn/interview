package hatecode;
public class _1006ClumsyFactorial {
/*
1006. Clumsy Factorial
Normally, the factorial of a positive integer n is the product of all positive integers less than or equal to n.  For example, factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1.

We instead make a clumsy factorial: using the integers in decreasing order, we swap out the multiply operations for a fixed rotation of operations: multiply (*), divide (/), add (+) and subtract (-) in this order.

For example, clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1.  However, these operations are still applied using the usual order of operations of arithmetic: we do all multiplication and division steps before any addition or subtraction steps, and multiplication and division steps are processed left to right.

Additionally, the division that we use is floor division such that 10 * 9 / 8 equals 11.  This guarantees the result is an integer.

Implement the clumsy function as defined above: given an integer N, it returns the clumsy factorial of N.

 

Example 1:

Input: 4
Output: 7
*/
    
    //O(1) solution, 
    //Math solution, i(i-1)/(i-2) = i + 1
    public int clumsy_Math(int N) {
        int[] magic = new int[]{1, 2, 2, -1, 0, 0, 3, 3};
        return N + ((N > 4)? magic[N%4] : magic[N+3]);
    }
    //BF, O(n)/O(1)
    public int clumsy(int N) {
        if(N == 1) return 1;
        if(N == 2) return 2;
        if(N == 3) return 3*2/1;
        int sum = 0;
        if(N>=4){
            sum += N*(N-1)/(N-2)+(N-3);
            N -= 4;
        }
        for(int i = N;i>=4;i-=4){
            sum += -N*(N-1)/(N-2)+(N-3);
            N -= 4;
        }
        if(N == 1) sum -= 1;
        if(N == 2) sum -= 2;
        if(N == 3) sum -= 3*2/1;
        return sum;
    }
}
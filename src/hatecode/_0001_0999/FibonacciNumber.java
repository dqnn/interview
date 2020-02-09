package hatecode._0001_0999;

class FibonacciNumber {
/*
509. Fibonacci Number
The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number
is the sum of the two preceding ones, starting from 0 and 1. That is,

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), for N > 1.
Given N, calculate F(N).


*/
    
    //O(n)/O(n)
    public int fib_recursive(int n) {
        if (n <= 0) return 0;
        if (n == 1)  return 1;
        
        return fib(n-1) + fib(n-2);
    }
    //O(n)/O(1)
    public int fib(int n) {
        if (n <= 0) return 0;
        if (n == 1)  return 1;
        
        int a = 0, b = 1;

        while(n-- > 1) {
			int sum = a + b;
			a = b;
			b = sum;
		}
        return b;
    }
}
package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : IntegerReplacement
 * Creator : professorX
 * Date : July, 2018
 * Description : 397. Integer Replacement
 */
public class _397IntegerReplacement {
    /**
     * Given a positive integer n and you can do operations as follow:

     If n is even, replace n with n/2.
     If n is odd, you can replace n with either n + 1 or n - 1.
     What is the minimum number of replacements needed for n to become 1?

     Example 1:

     Input:
     8

     Output:
     3

     Explanation:
     8 -> 4 -> 2 -> 1
     Example 2:

     Input:
     7

     Output:
     4

     Explanation:
     7 -> 8 -> 4 -> 2 -> 1
     or
     7 -> 6 -> 3 -> 2 -> 1

     time : O(logn)
     space : O(1)
     * @param n
     * @return
     */
    /*
     * so if thinking from binary String of n, eg: 11, remove 1 as many as possible, -1 always
     * 111011 -> 111010 -> 11101 -> 11100 -> 1110 -> 111 -> 1000 -> 100 -> 10 -> 1
     * not good than:
     * 111011 -> 111100 -> 11110 -> 1111 -> 10000 -> 1000 -> 100 -> 10 -> 1 
     * so we should compare how many 1's in n - 1 and n + 1, choose the one with less 1, this means we are 
     * closer to pow(2, n), n =1,2,3...
     * actually only two cases, 11 and 01, so best is to 00, if 01 n-1, if 11, +1
     * If n is even, halve it.
       If n=3 or n-1 has less 1's than n+1, decrement n. Otherwise, increment n.
     */
    public int integerReplacement(int n) {
        long N = n;
        int res = 0;
        while (N != 1) {
            if (N % 2 == 0) {
                N >>= 1;
            } else {
                if (N == 3) {
                    res += 2;
                    break;
                }
                N = (N & 2) == 2 ? N + 1 : N - 1;
            }
        }
        return res;
    }
    
    //same as previous one, just $ 3 not 2 just want to prove 11 and 01 cases
    public int integerReplacement3(int n) {        
        if (n <= 1) {
            return 0;
        }
        int res = 0;
        long N = (long)n;
        while (N > 1) {
            if (N % 2 == 0) {
                N = N / 2;
                res++;
            } else {
                if (N == 3) {
                    return res + 2;
                }
                N = (N & 3) == 3 ? N+1:N-1;
                res++;
            }
        }
        
        
        return res;
    }

    /**
     * time : O(logn)
     * space : O(1)

     * @param n
     * @return
     */
/*
 * For any odd number k >= 3, f(k-1) <= f(k) and f(k+1) <= f(k), where f denotes "integerReplacement(int n)".
In another words, for two adjacent numbers the even one needs less or equal steps to get to 1 than that of the odd one.

This can be proven by induction:

for 2,3,4,5,6 we have f(2) = 1, f(3) = 2, f(4) = 2, f(5) = 3, f(6) = 3 which agree with the statement
for and odd number k let's prove f(k-1) <= f(k) (f(k+1) < f(k) can be proven in the same manner):
for k-1: k-1 -> (k-1)/2
for k: a. k -> k-1 -> (k-1)/2 OR
b. k -> k+1->(k+1)/2
if we take path a, it's obvious that k takes one more step than k-1 to get (k-1)/2 so f(k-1) < f(k)
if we take path b,
if (k+1)/2 is even and (k-1)/2 is odd, then for k-1 we can also take two step to reach (k+1)/2 by k-1 -> (k-1)/2 - > (k+1)/2, so f(k-1) = f(k)
if (k+1)/2 is odd number, by induction we know f[(k-1)/2] <= f[(k+1)/2], so overall f(k-1) < f(k) (because it takes one step from k-1 to (k-1)/2 but two steps from k to (k+1)/2)
So in all the cases f(k-1) <= f(k)
Corollary:
For our problem: if we have an odd number we need increase or decrease to make it be 4n. The reason is for an odd number after two steps it could become an odd or even number differed by 1 and the theorm above tell us you better become an even number after two steps.
Why 3 is an exception? The theorem only applies for odd numbers >= 3 because f(2) > f(1) is an exception!!
 */
    
    // another proof of this algorithms:
    
    /*
     * The greedy heuristic of the above solution: the "best" case of this problem is to divide as much as possible.
Each time we divide by 2 we shift right one bit. We can only divide when the right most bit is 0 (n is even).
When the right most bit is 1, we have 2 choices: n+1 or n-1. We choose n+1 when we can eliminate more 1-bit so we have a longer trailing 0 (so we can divide more).
Example:
1011 -> 1100
1011 -> 1010

Proof:
Let n = 2k+1. Let count is the number of steps.

Option 1: n+1 == 2k+2; (n+1) / 2 == k+1 (count: 2)
Option 2: n-1 == 2k; (n-1) / 2 == k (count: 2)

If (n+1) % 4 == 0, then (k+1) % 2 == 0 -> k will be odd.
Let k = 2h + 1
Continue option 1: (k+1) / 2 == h+1 (count: 3)
Continue option 2:

if we choose to +1 here: k+1 == 2h+2; (k+1) / 2 == h+1 (count: 4) -> worse than option 1
if we choose to -1 here: k-1 == 2h; (k-1) / 2 == h (count: 4) -> need 4 steps to reach h, while option 1 need 3 steps to reach h+1. If h is even, option 1 can keep on dividing. If h is odd, option 1 can simply -1 to reach h (count: 4), so it will never be worse than option 2.
Therefore, when (n+1) % 4 == 4, using option 1 (n+1) is always better or equally good compare with using option 2 (n-1).

The proof is a little sloppy and it hasn't fully proved the correctness. However, I hope it give you the idea.
     */
    public int integerReplacement2(int n) {
        if (n == Integer.MAX_VALUE) return 32;
        int res = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                if ( (n + 1) % 4 == 0 && (n - 1 != 2)) {
                    n++;
                } else n--;
            }
            res++;
        }
        return res;
    }
}

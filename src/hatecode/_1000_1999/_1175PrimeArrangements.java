package hatecode._1000_1999;

import java.util.*;
public class _1175PrimeArrangements {

    /*
    1175. Prime Arrangements
    Return the number of permutations of 1 to n so that prime numbers are at prime indices (1-indexed.)

(Recall that an integer is prime if and only if it is greater than 1, and cannot be written as a product of two positive integers both smaller than it.)

Since the answer may be large, return the answer modulo 10^9 + 7.

 

Example 1:

Input: n = 5
Output: 12
    */
    
    //thinking process: O(nlglgn)/O(n), see https://en.m.wikipedia.org/w/index.php?title=Sieve_of_Eratosthenes&action=edit&section=8
    
    //the problem is to say: given a positive integer n(1~n), if all prime numbers 
    //are at prime indexes, like 1 @ 1 or 1 @ 3 position, then return how many 
    //permutations it could have, mod = 10^9 + 7
    
    //so we just need to make sure all prime number at prime index then all should be ok, 
    
    //suppse there are m prime numbers from 1->n, then there are 
    //P(m, m) * P(n-m, n-m), P(m,m) means m position, how many permuations there are
    
    //sieve_of_eratosthenes
    public int numPrimeArrangements(int n) {
        int MOD = (int) (1e9 + 7);
        boolean[] prime = new boolean[n + 1]; 
        //assign true from 2 to n, n+1 is exclusive, 2 is inclusive
        Arrays.fill(prime, 2, n+1, true); 
        //this means max prime  in [2, n] is sqrt(n),for each possible prime 
        //number, we start from i * i, step is i to mark it is not prime number
        for(int i=2; i * i <= n; i++) {
            if (prime[i]) 
                for(int j=i*i; j <= n; j += i) 
                    prime[j] = false;
        }
        
        //cnt is prime numbers from 1 to n, starting from 2, so res = 1
        long res = 1;
        int cnt = 0;
        for (boolean b : prime)  cnt += (b ? 1 : 0);
        //(a*b)%D = (a %D *b)%D,
        //proof: 
        //suppose a = mk + n, (a * b) % k = (mkb + nb) % k = (n*b) % k. 
        //As a % k = n, ((a%k)b)%k = (nb) % k.
        for (int i=2; i <= cnt; i++)  res = (res * i) % MOD;
        for (int i=2; i <= n - cnt; i++) res = (res * i) % MOD;
        return (int) res;
    }
    
}
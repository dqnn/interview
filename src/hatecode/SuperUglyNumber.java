package hatecode;

import java.util.PriorityQueue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SuperUglyNumber
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 313. Super Ugly Number
 */
public class SuperUglyNumber {
    /**
     * Write a program to find the nth super ugly number.

     Super ugly numbers are positive numbers whose all prime factors are in the given prime list
     primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32]
     is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

     PriorityQueue kth

     time : O(n * logk)
     space : O(max(n,k))

     * @param n
     * @param primes
     * @return
     */
    //thinking process: so given primes array, we want to find 
    // n-th number which all factors are in the prime array. 
    
    //so we use a queue to store the super ugly number and sorted naturaly
    // if a = b ^n * c ^m, and d = a^ k, then d must be b and c combinations 
    // so we do not need to trace back to each prime number so we can accerlerate 
    //the computation, we use res as array progressively to get result
    //this problems just to remember the for and while loop 
    
    //also should be small one thats why we use PQ to poll smallest 
    /*
     * n = 12, primes = [2,7,13,19], 
     * first we add [2,1,2], [7,1,7], [13,1,13], [19,1,19] to pq
     * then we poll first, res[1] = 2, then we add [4, 2, 2] into queue, and found this is smallest then continue
     * res[2] = 4, then we will add[16, 3, 2],
     * for each res[i], 
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] res = new int[n];
        res[0] = 1;

        PriorityQueue<Num> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));
        //
        for (int i = 0; i < primes.length; i++) {
            pq.add(new Num(primes[i], 1, primes[i]));
        }
        //for loop contains a while loop, templates 
        for (int i = 1; i < n; i++) {
            res[i] = pq.peek().val;
            while (pq.peek().val == res[i]) {
                Num next = pq.poll();
                pq.add(new Num(next.prime * res[next.index], next.index + 1, next.prime));
            }
        }

        return res[n - 1];
    }

    class Num {
        int val;
        int index;
        int prime;

        public Num(int val, int index, int prime) {
            this.val = val;
            this.index = index;
            this.prime = prime;
        }
    }
}

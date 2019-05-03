package hatecode;
public class KthSymbolInGrammar {
    /*
779. K-th Symbol in Grammar

On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.

Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).

Examples:
Input: N = 1, K = 1
Output: 0

Input: N = 2, K = 1
Output: 0

Input: N = 2, K = 2
Output: 1

Input: N = 4, K = 5
Output: 1

Explanation:
row 1: 0
row 2: 01
row 3: 0110
row 4: 01101001


Updates: (all index discussed below are 0-based)
Observation 1: N does not matter as long as "K will be an integer in the range [1, 2^(N-1)]". We can ignore N.

Observation 2: let f(k) be the value of kth position (0-based), then:
f(2 * k) = 0 {if f(k) = 0} or, 1 {if f(k) = 1} => f(2 * k) = f(k) xor 0
f(2 * k + 1) = 0 {if f(k) = 1} or 1 {if f(k) = 0} => f(2 * k + 1) = f(k) xor 1

Obervation 3: if binary string of k is used, let k = 1001010, then we have:
f(1001010) = f(100101) ^ 0 = f(10010) ^ 1 ^ 0 = f(1001) ^ 0 ^ 1 ^ 0 = ... = f(0) ^ 1 ^ 0 ^ 0 ^1 ^ 0 ^ 1 ^ 0 = 1 ^ 0 ^ 0 ^1 ^ 0 ^ 1 ^ 0
So, the result is the xor operation on all bits of k. Since 0 does not change xor result, we can ignore all 0s.
f(1001010) = 1 ^ 1 ^ 1 = (1^1) ^ 1 = 0 ^ 1 = 1
f(11110011) = 1 ^ 1^ 1 ^ 1 ^ 1 ^1 = (1 ^ 1) ^ (1 ^ 1) ^ (1 ^1) = 0
Now, it's easy to tell f(k) = 0 if k has even number of 1s in binary representation, and f(k) = 1 when k has odd number of 1s
    */
    public int kthGrammar_Best(int N, int K) {
        return Integer.bitCount(K-1) & 1;
    }
    
    public int kthGrammar(int N, int K) {
		// N is useless, as the value of K index is static
        boolean flip = false;
        K--;         // count index from 0 to make it easy to calculate
        while(K>0){
            if(K%2==1)flip=!flip;
            K/=2;
        }
        if(flip)return 1;
        else return 0;
    }
}
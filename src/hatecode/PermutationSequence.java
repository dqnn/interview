package hatecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PermutationSequence
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 60. Permutation Sequence
 */
public class PermutationSequence {
    /**
     * The set [1,2,3,…,n] contains a total of n! unique permutations.

     By listing and labeling all of the permutations in order,
     We get the following sequence (ie, for n = 3):

     "123"
     "132"
     "213"
     "231"
     "312"
     "321"
     Given n and k, return the kth permutation sequence.

    Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"

     1， 2， 3， 4:

     1 + {2, 3, 4}
     2 + {1, 3, 4}
     3 + {1, 2, 4}
     4 + {1, 2, 3}

     18 : 3421

     res  : 1 2
     fact : 1 1 2 6

     k = 17

     i = 4    index = 17 / 6 = 2 k = 17 % 6 = 5
     i = 3    index = 5 / 2 = 2 k = 5 % 2 = 1
     i = 2    index = 1 / 1 = 1 k = 1 % 1 = 0

     4 3 2 1
     3 4 2 1

     time : O(n)
     space : O(n)

     * @param n
     * @param k
     * @return
     */
/*
The logic is as follows: for n numbers the permutations can be divided to n 个 (n-1)! groups, 

 Thus k/(n-1)! indicates the index of 
current number, 

and k%(n-1)! denotes remaining index for the remaining n-1 numbers.
We keep doing this until n reaches 0, then we get n numbers permutations that is kth.


 */
    public static String getPermutation(int n, int k) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            res.add(i);
        }
        int[] fact = new int[n];
        fact[0] = 1;
        for (int i = 1; i < n; i++) {
            fact[i] = i * fact[i - 1];
        }
        // array is from 0, so k = k - 1
        k = k - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--) {
            // k / (i - 1)! so 
            int index = k / fact[i - 1];
            k = k % fact[i - 1];
            sb.append(res.get(index));
            // since it already used, so we remove it
            res.remove(index);
        }
        return sb.toString();
    }
}

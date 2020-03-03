package hatecode._0001_0999;
import java.util.*;
public class _421MaximumXOROfTwoNumbersInAnArray {
/*
421. Maximum XOR of Two Numbers in an Array
Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.
*/
   //interview friendly: 
   
   //follow up: return distance less than K, the pairs
   // XOR, diff can be 1, so we always look for different digits
   public int findMaximumXOR_Interview(int[] nums) {
       if (nums.length == 0) {
           return 0;
       }

       TrieNode root = new TrieNode();

       // 建树
       // 每一个数字循环32次，反正输入没有负数
       // 从最高位开始处理
       for (int i : nums) {
           TrieNode node = root;
           for (int j = 31; j >= 0; j--) {
               int bit = (i >> j) & 1;
               if (bit == 1) {
                   if (node.one == null) {// one的位置是null，就新建
                       node.one = new TrieNode();
                   }
                   node = node.one;
               } else {
                   if (node.zero == null) {
                       node.zero = new TrieNode();
                   }
                   node = node.zero;
               }
           }
       }

       int max = Integer.MIN_VALUE;

       //找每一个数字和别的数字异或出来的最大值！
       //所有最大值，取其中最大的
       for (int i : nums) {
           TrieNode node = root;
           int XOR = 0;// 异或结果,的累加。按位累加也是牛逼
           for (int j = 31; j >= 0; j--) {
               int bit = (i >> j) & 1;
               //如果这一位是1，为了异或最大，最好使用0，这样异或出来是1
               //如果0不存在就只能使用1，这样这一位异或出来就是0
               //如果这一位是0，为了异或最大，最好使用1，这样异或出来是1
               //如果1不存在就只能使用0，这样这一位异或出来就是0
               if (bit == 1) {
                   if (node.zero != null) {
                       node = node.zero;
                       XOR += 1 << j;
                   } else {
                       node = node.one;
                       XOR += 0 << j;// 0没用为了明确还是写上
                   }
               } else {
                   if (node.one != null) {
                       node = node.one;
                       XOR += 1 << j;
                   } else {
                       node = node.zero;
                       XOR += 0 << j;
                   }
               }
           }
           
           max = max > XOR ? max : XOR;
       }

       return max;
   }

class TrieNode {
   TrieNode zero;
   TrieNode one;
}

//so we use 32 * len to calculate tthe max and prefix number
// we use each mask to get each number's the bit 
//tricky one
public int findMaximumXOR(int[] nums) {
    int max = 0, mask = 0;
    for(int i = 31; i >= 0; i--){
        mask = mask | (1 << i);
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num & mask);
        }
        int tmp = max | (1 << i);
        for(int prefix : set){
            if(set.contains(tmp ^ prefix)) {
                max = tmp;
                break;
            }
        }
    }
    return max;
}
}
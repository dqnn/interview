package leetcode;

import java.util.HashMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : StrobogrammaticNumber
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 246. Strobogrammatic Number
 */
public class StrobogrammaticNumber {
    /**
     * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

     Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:

Input:  "69"
Output: true
Example 2:

Input:  "88"
Output: true
Example 3:

Input:  "962"
Output: false


     time : O(n)
     space : O(n)

     * @param num
     * @return
     */
    //thinking process:
    
    //the problems is to say rotate all numbers 180, so look at upside, it would be same as before
    public boolean isStrobogrammatic(String num) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('8', '8');
        map.put('1', '1');
        int left = 0, right = num.length() - 1;
        while (left <= right) {
            if (!map.containsKey(num.charAt(left))) {
                return false;
            }
            if (map.get(num.charAt(left)) != num.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

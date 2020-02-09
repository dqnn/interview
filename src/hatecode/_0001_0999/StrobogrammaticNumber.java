package hatecode._0001_0999;

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
    
    //the problems is to say rotate all numbers 180 wholly, the same as look at upside, it would be same as before
    //so given a number, output it is strobogrammatic number or not
    
    //we use a map to store all possible strobogrammatic numbers, and for this number, we compare left and right
    //they either to be the same or in map, or it is not. left and right move step to middle in each iteration
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() < 1) return true;
        
        HashMap<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('8', '8');
        map.put('1', '1');
        int left = 0, right = num.length() - 1;
        // left == right means the len of the String is odd, if we use left < right, then we will miss the middle 
        //number is strobogrammatic or not.
        while (left <= right) {
            //if (!(map.containsKey(s.charAt(l) +  ""))  || !map.containsKey(s.charAt(r) + "")) also work
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

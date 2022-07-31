package hatecode._0001_0999;

import java.util.HashMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : StrobogrammaticNumber
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 246. Strobogrammatic Number
 */
public class _246StrobogrammaticNumber {
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
    public boolean isStrobogrammatic(String A) {
        if (A == null || A.length() < 1) return true;
        
        HashMap<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('8', '8');
        map.put('1', '1');
        int l = 0, r = A.length() - 1;
        //need to care when A.length = odd numbers
        while(l <= r) {
            char left = A.charAt(l);
            char right = A.charAt(r);
            if (!map.containsKey(left) || !map.containsKey(right)) return false;
            
            //here is better, because like "6", "9", we require at least
            //A.length = 2, so we can avoid the long condition compute
            if (map.get(left) != right) {
              return false;
            }
            l++;
            r--;
        }
        return true;
    }
}

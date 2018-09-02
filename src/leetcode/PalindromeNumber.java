package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PalindromeNumber
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 */
public class PalindromeNumber {

    /**
     * 9. Palindrome Number

     time : O(n) space : O(1)
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        // x must be > 0. 
        // x cannot be 0
        // x cannot end with 0
        if (x < 0 || x != 0 && x % 10 == 0) {
            return false;
        }
        int palind = x;
        int rev = 0;
        // we get each number by divide 10, 
        while (x > 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        // compare the two numbers and return
        return palind == rev;
    }
    
    public boolean isPalindrome2(int x) {
        if(x<0) return false;
        if(x < 10) return true;
        
        String copyX = String.valueOf(x);
        int len = copyX.length();
        for(int i =0; i <= len/2; i++) {
            if(copyX.charAt(i) == copyX.charAt(len -i-1)) {
                continue;
            } else {
                return false;
            }
        }
        
        return true;
    }
}

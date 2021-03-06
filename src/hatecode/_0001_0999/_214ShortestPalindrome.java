package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ShortestPalindrome
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 214. Shortest Palindrome
 */
public class _214ShortestPalindrome {
    /**
     * Given a string S, you are allowed to convert it to a palindrome by 
     * adding characters in front of it.
     * Find and return the shortest palindrome you can find by 
     * performing this transformation.

     For example:

     Given "aacecaaa", return "aaa-".

     Given "abcd", return "dcbabcd".

     I agree for smaller strings charAt function may be fast. 
     But when coming to big strings,
     I think the scenario is different. Because when I see the code for string 
     last night
     I have found that toCharArray uses function System.arraycopy.
     We know System.arraycopy(I think it is native call) is faster.

     So when we use for loop based operations on String, we are directly moving to index 
     for char array whereas we need to call the function for charAt. So for Bigger strings 
     calling the function multiple times may reduce the performance of the method.


     * @param s
     * @return
     */

    // time :  O(n^2) for aaaaabcaaaaa
    // thinking process: so we want to examine the original string, how palidrome it is, 
    //so since we can only add str in front of the string, so most expensive ops will be 
    //reverse the string and put before the original string
    // and so if there are some string can be reused, so we can try to compare from left to right
    // if we found some chars are not equals to each other, then left will move to idx 0 and 
    // end-- and right will move this location, so the char will be used in the new extra string
    //
    
    //we need to find end index which indicates padindrom and non-palidrome boundary
    public String shortestPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        int end = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                left = 0;
                end--;
                right = end;
            }
        }
        return new StringBuilder(s.substring(end + 1)).reverse().toString() + s;
    }
}

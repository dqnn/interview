package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ShortestPalindrome
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 214. Shortest Palindrome
 */
public class ShortestPalindrome {
    /**
     * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
     * Find and return the shortest palindrome you can find by performing this transformation.

     For example:

     Given "aacecaaa", return "aaacecaaa".

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
    //so since we can only add str in front of the strng, so most expensive ops will be 
    //reverse the string and put before the original string
    // and so if there are some string can be reused, so we can try to comapre from left to right
    // if we found some chars are not equals to each other, then left will move to idx 0 and 
    // end-- and right will move this location, so the char will be used in the new extra string
    //
    public String shortestPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        int end = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                i = 0;
                end--;
                j = end;
            }
        }
        return new StringBuilder(s.substring(end + 1)).reverse().toString() + s;
    }
}

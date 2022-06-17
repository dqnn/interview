package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ReverseWordsinaStringII
 * Creator : professorX
 * Date : Sep, 2018
 * Description : TODO
 */
public class _186ReverseWordsinaStringII {
    /**
     * 186. Reverse Words in a String II
Given an input string , reverse the string word by word. 

Example:

Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
Note: 

A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.
Follow up: Could you do it in-place without allocating extra space?

     time : O(n) space : O(1)
     * @param s
     */
    // so the key is we reverse all string then reverse each one 
    //when we encounter a space
    public void reverseWords(char[] s) {
        reverse(s, 0, s.length - 1);
        int r = 0;
        //we scan from left to right and stop when we encounter ' '
        while (r < s.length) {
            int l = r;
            // this is help to detect right position
            while (r < s.length && s[r] != ' ') {
                r++;
            }
            // note r - 1
            reverse(s, l, r - 1);
            //skip the space
            r++;
        }
    }
    private void reverse(char[] s, int i, int j) {
        while (i < j) {
            char temp = s[i];
            s[i++] = s[j];
            s[j--] = temp;
        }
    }
}

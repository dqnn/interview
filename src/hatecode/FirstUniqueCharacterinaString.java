package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FirstUniqueCharacterinaString
 * Creator : duqiang
 * Date : Oct, 2017
 * Description : 387. First Unique Character in a String
 */
public class FirstUniqueCharacterinaString {
    /**
     * 
     * Given a string, find the first non-repeating character in it and return it's
     * index. If it doesn't exist, return -1.
     * 
     * Examples:
     * 
     * s = "leetcode" return 0.
     * 
     * s = "loveleetcode", return 2. Note: You may assume the string contain only
     * lowercase letters.
     * 
     * time : O(n); space : O(1);
     * 
     * @param s
     * @return
     */
    //thinking process:
    //interview friendly: 
    //use char count to whether this char is unique or not
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}

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
    
    // so we first create a visited table and store the char we visited
    // and then we loop again to find the chat in original string index
    // but as we can see the indexOf is redundant, this solution should be abandoned
    public int firstUniqChar2(String s) {
        if (s == null || s.length() < 1) {
            return -1;
        }
        
        int[] visited = new int[26];
        for(int i = 0; i < s.length(); i++) {
            visited[s.charAt(i) - 'a']++;
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < visited.length; i++) {
            if (visited[i] == 1) {
                int idx = s.indexOf(i + 'a');
                if (idx >= 0) {
                    min = Math.min(min, idx);
                } 
            }
        }
        
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}

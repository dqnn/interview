package hatecode._1000_1999;
public class _1160FindWordsThatCanBeFormedByCharacters {
/*
1160. Find Words That Can Be Formed by Characters
You are given an array of strings words and a string chars.

A string is good if it can be formed by characters from chars (each character can only be used once).

Return the sum of lengths of all good strings in words.

 

Example 1:

Input: words = ["cat","bt","hat","tree"], chars = "atach"
Output: 6
*/
    //thinking process: O(n + 1)/O(1), actually we use 26 char space
    
    //the real intention of the problem is to use bloom filter
    //TODO: to use bloom filter to implement this
    public int countCharacters(String[] words, String str) {
        if(words == null || words.length < 1 || str == null || str.length() < 1) return 0;
        
        int[] count = new int[26];
        for(char c: str.toCharArray()) count[c - 'a']++;
        
        int res = 0;
        for(String w: words) {
            int[] cnt = count.clone();
            boolean match = true;
            for(char c : w.toCharArray()) {
                if (--cnt[c- 'a'] < 0) {
                    match = false;
                    break;
                }
            }
            if (match) res += w.length();
        }
        return res;
    }
}
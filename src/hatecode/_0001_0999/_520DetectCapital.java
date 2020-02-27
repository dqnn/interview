package hatecode._0001_0999;
public class _520DetectCapital {
/*
520. Detect Capital
Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital if it has more than one letter, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
Example 1:
Input: "USA"
Output: True
*/
    
    public boolean detectCapitalUse_Best(String word) {
        int cnt = 0;
        for(char c: word.toCharArray()) if('Z' - c >= 0) cnt++;
        return ((cnt==0 || cnt==word.length()) || (cnt==1 && 'Z' - word.charAt(0)>=0));
    }
    
    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() < 1) return true;
        
        String up = word.toUpperCase();
        String lower = word.toLowerCase();
        String firstUp = Character.toUpperCase(word.charAt(0)) + word.substring(1, word.length()).toLowerCase();
        return up.equals(word) || lower.equals(word) || firstUp.equals(word);
    }
}
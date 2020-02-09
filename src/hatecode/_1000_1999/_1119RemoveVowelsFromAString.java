package hatecode._1000_1999;
public class _1119RemoveVowelsFromAString {
/*
1119. Remove Vowels from a String
Given a string S, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.

 

Example 1:

Input: "leetcodeisacommunityforcoders"
Output: "ltcdscmmntyfrcdrs"
*/
    //thinking process: maybe upper case
    public String removeVowels(String s) {
        if(s == null || s.length() < 1) return s;
        char[] chs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : chs) {
            if (c == 'a' || c=='e' || c=='i' || c == 'o' || c == 'u') continue;
            sb.append(c);
        }
        return sb.toString();
    }
}
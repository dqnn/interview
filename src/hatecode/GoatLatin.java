package hatecode;
public class GoatLatin {
/*
824. Goat Latin 
A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.

We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

The rules of Goat Latin are as follows:

If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
For example, the word 'apple' becomes 'applema'.
 
If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
For example, the word "goat" becomes "oatgma".
 
Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
Return the final sentence representing the conversion from S to Goat Latin. 

 

Example 1:

Input: "I speak Goat Latin"
Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
*/
    public String toGoatLatin(String s) {
        if (s == null || s.length() < 1) return s;
        
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<strs.length;i++) {
            if ("aeiouAEIOU".contains(strs[i].substring(0,1))) {
                sb.append(strs[i] + "ma");
            } else {
                sb.append(strs[i].substring(1) + strs[i].substring(0,1) + "ma");
            }
            
            for(int j = 0; j<= i;j++) {
                sb.append("a");
            }
            if (i < strs.length -1 ) sb.append(" ");
        }
        return sb.toString();
    }
}
package hatecode;
import java.util.*;
class UniqueMorseCodeWords {
/*
804. Unique Morse Code Words
Example:
Input: words = ["gin", "zen", "gig", "msg"]
Output: 2
*/
    public int uniqueMorseRepresentations(String[] words) {
        String[] d = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        HashSet<String> s = new HashSet<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (char c : word.toCharArray()) sb.append(d[c - 'a']);
            s.add(sb.toString());
        }
        return s.size();
    }
}
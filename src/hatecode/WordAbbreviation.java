package hatecode;
import java.util.*;
public class WordAbbreviation {
    /*
     * 527. Word Abbreviation Given an array of n distinct non-empty strings, you
     * need to generate minimal possible abbreviations for every word following
     * rules below.
     * 
     * Begin with the first character and then the number of characters abbreviated,
     * which followed by the last character. If there are any conflict, that is more
     * than one words share the same abbreviation, a longer prefix is used instead
     * of only the first character until making the map from word to abbreviation
     * become unique. In other words, a final abbreviation cannot map to more than
     * one original words. If the abbreviation doesn't make the word shorter, then
     * keep it as original. Example: Input: ["like", "god", "internal", "me",
     * "internet", "interval", "intension", "face", "intrusion"] Output:
     * ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
     */
    //O(C)/O(C) C is number of all chars
    
    //interview friendly: key is connect Trie with current problem
    
    //the problem itself is to say abbreivate list of words,if they are the same then we can 
    // try to resolve the conflicting words, the way is to add one new character, until full word
    
    //this operation is like trie operation, along with character, count means 
    //how many abbreivated words has same string
    //so if we found one, we re-create the abbrevation string and put into result,
    public static List<String> wordsAbbreviation(List<String> words) {
        Map<String, List<IndexedWord>> groups = new HashMap<>();
        String[] ans = new String[words.size()];

        int index = 0;
        for (String word: words) {
            String ab = abbrev(word, 0);
            groups.computeIfAbsent(ab, V->new ArrayList<>()).add(new IndexedWord(word, index++));
        }

        for (List<IndexedWord> group: groups.values()) {
            Trie trie = new Trie();
            for (IndexedWord iw: group) {
                Trie cur = trie;
                //ignore first character and build the trie tree
                for (char letter: iw.word.substring(1).toCharArray()) {
                    if (cur.children[letter - 'a'] == null)
                        cur.children[letter - 'a'] = new Trie();
                    cur.count++;
                    cur = cur.children[letter - 'a'];
                }
            }

            for (IndexedWord iw: group) {
                Trie cur = trie;
                int i = 1;
                //if count = 1 means there will be only 1 word, so we stop
                //otherwise we continue, beause we want to know on which character
                //count will be 1 then we can abbreviate  
                for (char letter: iw.word.substring(1).toCharArray()) {
                    if (cur.count == 1) break;
                    cur = cur.children[letter - 'a'];
                    i++;
                }
                ans[iw.index] = abbrev(iw.word, i-1);
            }
        }

        return Arrays.asList(ans);
    }
    // i means i+1 char as prefix will be remain in abbreviated string
    public static String abbrev(String word, int i) {
        int N = word.length();
        if (N - i <= 3) return word;
        return word.substring(0, i+1) + (N - i - 2) + word.charAt(N-1);
    }
}

class Trie {
    Trie[] children;
    int count;
    Trie() {
        children = new Trie[26];
        count = 0;
    }
}
 class IndexedWord {
    String word;
    int index;
    IndexedWord(String w, int i) {
        word = w;
        index = i;
    }
}
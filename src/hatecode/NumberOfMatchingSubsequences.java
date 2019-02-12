package hatecode;
import java.util.*;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
public class NumberOfMatchingSubsequences {
/*792. Number of Matching Subsequences
Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

Example :
Input: 
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
*/
    
    //need to understand more a little bit
    public int numMatchingSubseq3(String S, String[] words) {
    List<StringCharacterIterator>[] waiting = new List[128];
    for (int c = 0; c <= 'z'; c++) waiting[c] = new ArrayList<>();
    
    for (String w : words) waiting[w.charAt(0)].add(new StringCharacterIterator(w));
    
    for (char c : S.toCharArray()) {
        List<StringCharacterIterator> advance = waiting[c];
        waiting[c] = new ArrayList<>();
        // DONE,字符型，当迭代器已到达文本末尾或开始处时返回的常量。
        for (StringCharacterIterator it : advance)  waiting[it.next() % CharacterIterator.DONE].add(it);
    }
    return waiting[0].size();
}
    
    //really good solutions, we map all string words first and then we go from S, 
    //for each chars in S, we can find all strings in words starts with the char, so we can reduce the complexity
    public int numMatchingSubseq(String S, String[] words) {
        Map<Character, Deque<String>> map = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            map.putIfAbsent(c, new LinkedList<String>());
        }
        for (String word : words) {
            map.get(word.charAt(0)).addLast(word);
        }

        int count = 0;
        for (char c : S.toCharArray()) {
            Deque<String> queue = map.get(c);
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.removeFirst();
                if (word.length() == 1) {
                    count++;
                } else {
                    map.get(word.charAt(1)).addLast(word.substring(1));
                }
            }
        }
        return count;
    }
    
    //myself solutions, one time accepted. can be used as brute force solutions
    public int numMatchingSubseq2(String S, String[] words) {
        if (S == null || words == null || words.length < 1) return 0;
        Map<Character, List<Integer>> map = new HashMap<>();
        for(int i = 0; i< S.length(); i++) {
            char ch = S.charAt(i);
            map.computeIfAbsent(ch, v->new ArrayList<>()).add(i);
        }
        int count = 0;
        for(String t : words) {
            if (isSequence(map, t)) count +=1;
        }
        return count;
    }
    
    public boolean isSequence( Map<Character, List<Integer>> map, String sub) {
        int prev = -1;
        for(char ch : sub.toCharArray()) {
            if (map.get(ch) == null || map.get(ch).size() == 0) return false;
            List<Integer> list = map.get(ch);
            for(int index = 0; index < list.size(); index++) {
                if (index == list.size() - 1 && list.get(index) <= prev) {
                    return false;
                } else if (list.get(index) > prev) {
                    prev = list.get(index);
                    break;
                } 
            }
        }
        return true;
    }
}
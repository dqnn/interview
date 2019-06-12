package hatecode;
public class VerifyingAnAlienDictionary {
    /*
     * 953. Verifying an Alien Dictionary In an alien language, surprisingly they
     * also use english lowercase letters, but possibly in a different order. The
     * order of the alphabet is some permutation of lowercase letters.
     * 
     * Given a sequence of words written in the alien language, and the order of the
     * alphabet, return true if and only if the given words are sorted
     * lexicographicaly in this alien language.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
     * Output: true Explanation: As 'h' comes before 'l' in this language, then the
     * sequence is sorted.
     */
    //interview friendly: 
    // thinking process: we think each word as object, so it is like sorting,so we sort
    //each string object, and we import compare function 
    
    //two points:
    //1 is we compare each chaacter as in dictionary, each of them should be = or < next one
    //2: this is the key, like "apple" vs "app", if they are all the same, then shorter 
    //should appear first
    
    //actually this is to verify the lexi order
    int[] mapping = new int[26];
    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 0; i < order.length(); i++)
            mapping[order.charAt(i) - 'a'] = i;
        for (int i = 1; i < words.length; i++)
            if (compare(words[i - 1], words[i]) > 0)
                return false;
        return true;
    }

    int compare(String s1, String s2) {
        int n = s1.length(), m = s2.length(), cmp = 0;
        for (int i = 0, j = 0; i < n && j < m && cmp == 0; i++, j++) {
            cmp = mapping[s1.charAt(i) - 'a'] - mapping[s2.charAt(j) - 'a'];
        }
        //consider "apple" vs "app", app should appear first since it is shorter
        return cmp == 0 ? n - m : cmp;
    }
    
    
    public boolean isAlienSorted2(String[] words, String order) {
        if (order == null || order.length() < 1 || words == null || words.length < 1) {
            return true;
        }
        
        int[] se = new int[26];
        for(int i = 0; i< order.length(); i++) {
            char ch = order.charAt(i);
            se[ch-'a'] = i;
        }
        
        for(int i = 1; i < words.length;i++) {
            String first = words[i-1];
            String sec = words[i];
            int idx = 0, minLen = Math.min(first.length(), sec.length());
            while(idx < minLen) {
                if (first.charAt(idx) == sec.charAt(idx)) {
                    idx++;
                } else if (se[first.charAt(idx)-'a'] < se[sec.charAt(idx) - 'a']){
                    break;
                } else {
                    return false;
                }
            }
            if (idx == minLen && first.length() > sec.length()) {
                return false;
            }
        }
        return true;
    }
}
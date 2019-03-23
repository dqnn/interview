package hatecode;
import java.util.*;
class FindAndReplaceInString {
    /*
     * 833. Find And Replace in String To some string S, we will perform some
     * replacement operations that replace groups of letters with new ones (not
     * necessarily the same size).
     * 
     * Each replacement operation has 3 parameters: a starting index i, a source
     * word x and a target word y. The rule is that if x starts at position i in the
     * original string S, then we will replace that occurrence of x with y. If not,
     * we do nothing.
     * 
     * For example, if we have S = "abcd" and we have some replacement operation i =
     * 2, x = "cd", y = "ffff", then because "cd" starts at position 2 in the
     * original string S, we will replace it with "ffff".
     * 
     * Using another example on S = "abcd", if we have both the replacement
     * operation i = 0, x = "ab", y = "eee", as well as another replacement
     * operation i = 2, x = "ec", y = "ffff", this second operation does nothing
     * because in the original string S[2] = 'c', which doesn't match x[0] = 'e'.
     * 
     * All these operations occur simultaneously. It's guaranteed that there won't
     * be any overlap in replacement: for example, S = "abc", indexes = [0, 1],
     * sources = ["ab","bc"] is not a valid test case.
     * 
     * Example 1:
     * 
     * Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets =
     * ["eee","ffff"] Output: "eeebffff" Explanation: "a" starts at index 0 in S, so
     * it's replaced by "eee". "cd" starts at index 2 in S, so it's replaced by
     * "ffff".
     */
     //from right to left, we wont worry about the start index wil change, 
    //becasue we only change the latter part
    //O(SN)/O(S) n = len(S)
     public static String findReplaceString2(String S, int[] indexes, String[] sources, String[] targets) {
        List<int[]> sorted = new ArrayList<>();
        for (int i = 0 ; i < indexes.length; i++) sorted.add(new int[]{indexes[i], i});
        //sort by -i[0] field, which means 
        //Collections.sort(sorted, Comparator.comparing(i -> -i[0]));
         Collections.sort(sorted, (a,b)->(b[0] -a[0]));
        for (int[] ind: sorted) {
            int i = ind[0], j = ind[1];
            String s = sources[j], t = targets[j];
            if (S.substring(i, i + s.length()).equals(s)) S = S.substring(0, i) + t + S.substring(i + s.length());
        }
        return S;
    }
    
    //O(nlgn)/O(n) 
    public static String findReplaceString3(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder(S);
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 0; i < indexes.length; ++i) { tm.put(indexes[i], i); }
        //desc sort, because if we change from left, then some index will change.
        for (int key : tm.descendingKeySet()) {
            int i = tm.get(key);
            if (S.substring(indexes[i]).startsWith(sources[i])) {
                sb.replace(indexes[i], indexes[i] + sources[i].length(), targets[i]);
            }
        } 
        return sb.toString();
    }
    
    // 
     public static String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder(S);
        int len = S.length();
        int[] indexValInvert = new int[len];
        Arrays.fill(indexValInvert, -1);
        for (int i = 0; i < indexes.length; ++i) { indexValInvert[indexes[i]] = i; }
         //from right to left
         for (int i = len - 1; i >= 0; --i) {
            int j = indexValInvert[i];
            if (j < 0) { continue; }
            if (S.substring(indexes[j]).startsWith(sources[j])) {
                sb.replace(indexes[j], indexes[j] + sources[j].length(), targets[j]);
            }
        } 
        return sb.toString();
    }

}
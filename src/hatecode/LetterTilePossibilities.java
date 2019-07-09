package hatecode;

import java.util.*;
public class LetterTilePossibilities {
    /*
     * 1079. Letter Tile Possibilities
     * tags: backtracking, subsetII 
     * You have a set of tiles, where each tile has
     * one letter tiles[i] printed on it. Return the number of possible non-empty
     * sequences of letters you can make.

     * Example 1:
     * 
     * Input: "AAB" Output: 8 Explanation: The possible sequences are "A", "B",
     * "AA", "AB", "BA", "AAB", "ABA", "BAA".
     */
    //interview friendly, really brilliant solution
    public int numTilePossibilities(String t) {
        if(t == null || t.length() < 1) return 0;
        
        int[] count = new int[26];
        for(char c : t.toCharArray()) {
            count[c-'A']++;
        }
        return helper(count);
    }
    
    private int helper(int[] count) {
        int sum = 0;
        for(int i = 0; i< 26; i++) {
            if(count[i] == 0) continue;
            
            sum++;
            count[i]--;
            sum += helper(count);
            count[i]++;
        }
        return sum;
    }
    
    //  subsetII solution
    //   the number of unique permutations n!/a!b!...
    // ----------------
    // in discussion a lot of people just generate and count all the unique permutations.
    //   this can be done in the frequence space to get the unique permutations.
    //
    public int numTilePossibilities2(String tiles) {
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        return helper(chars, 0, chars.length, 1, 1, 0, new int['Z' - 'A' + 1]);
    }
    
    // a/b is the number of unique permutations given characters in counts.
    // m is sum(counts).
    static int helper(char[] chars, int i, int n, int a, int b, int m, int[] counts) {
        int result = 0;
        a *= (++m); // add one to m, and update a == m!.
        for(int j = i; j < n; ++j) {
            int index = chars[j] - 'A';
            if (j == i || chars[j] != chars[j-1]) { // generate unique subset.
                // b maintains the product of counts[i]! for each counts[i] != 0.
                b *= (++counts[index]);
                result += a / b + helper(chars, j + 1, n, a, b, m, counts);
                b /= (counts[index]--);
            }
        }
        return result;
    }
}
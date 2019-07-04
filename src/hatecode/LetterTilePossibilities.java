package hatecode;
public class LetterTilePossibilities {
    /*
     * 1079. Letter Tile Possibilities You have a set of tiles, where each tile has
     * one letter tiles[i] printed on it. Return the number of possible non-empty
     * sequences of letters you can make.
     * 
     * 
     * 
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
}
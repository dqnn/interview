package hatecode._0001_0999;

import java.util.*;
public class _748ShortestCompletingWord {
/*
748. Shortest Completing Word
Example 1:
Input: licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
Output: "steps"
Explanation: The smallest length word that contains the letters "S", "P", "S", and "T".
Note that the answer is not "step", because the letter "s" must occur in the word twice.
Also note that we ignored case for the purposes of comparing whether a letter exists in the word.
*/
    class Node{
        String w;
        int index;
        
        public Node(String w, int index) {
            this.w =w;
            this.index = index;
        }
    }
    public String shortestCompletingWord(String licensePlate, String[] words) {
        if (licensePlate == null || words == null || licensePlate.length() < 1 || words.length < 1) return "";
        
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : licensePlate.toLowerCase().toCharArray()) {
            if (ch >='a' && ch <= 'z') {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }
        } 
        //System.out.println(map);
        Node res = new Node("1234567890123456", Integer.MAX_VALUE);
        for(String w : words){
            Map<Character, Integer> copy = new HashMap<>(map);
            int pos = 0;
            int minPos = -1;
            for(char ch : w.toLowerCase().toCharArray()) {
                if (ch < 'a' || ch > 'z' || !copy.containsKey(ch)) continue;
                int cnt = copy.get(ch);
                if (cnt == 1) copy.remove(ch);
                else copy.put(ch, cnt-1);
                
                if (minPos == -1) minPos = pos;
                pos++;
            }
            if (copy.size() == 0 && w.length() <= res.w.length()) {
                if (w.length() == res.w.length() && minPos < res.index || w.length() < res.w.length()) {
                    res.w = w;
                    res.index = minPos;
                }
            }
        }
        
        return res.w;
    }
    //this is just to show off, no production use
    private static final int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103}; 
    
    public String shortestCompletingWord2(String licensePlate, String[] words) {
        long charProduct = getCharProduct(licensePlate.toLowerCase());
        String shortest = "aaaaaaaaaaaaaaaaaaaa"; // 16 a's
        for(String word : words)
            if (word.length() < shortest.length() && getCharProduct(word) % charProduct == 0)
                    shortest = word;
        return shortest;
    }
    
    private long getCharProduct(String plate) {
        long product = 1L;
        for(char c : plate.toCharArray()) {
            int index = c - 'a';
            if (0 <= index && index <= 25) 
                product *= primes[index];
        }
        return product;
    }
}
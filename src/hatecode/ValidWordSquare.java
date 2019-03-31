package hatecode;
import java.util.*;
public class ValidWordSquare {
/*
422. Valid Word Square
Given a sequence of words, check whether it forms a valid word square.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
Example 1:

Input:
[
  "abcd",
  "bnrt",
  "crmy",
  "dtye"
]

Output:
true

*/
    public boolean validWordSquare(List<String> words) {
        if (words == null || words.size() < 1) return true;
        
        int n = words.size();
        
        for(int i = 0; i< n; i++) {
            String word = words.get(i);
            for(int j = 0; j< word.length(); j++) {
                if (j>=n || words.get(j).length() <= i || words.get(j).charAt(i) != words.get(i).charAt(j)) return false;
            }
        }
        
        return true;
    }
}
package hatecode._0001_0999;
import java.util.*;
public class JewelsAndStones {
/*
771. Jewels and Stones
You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".

Example 1:

Input: J = "aA", S = "aAAbbbb"
Output: 3
*/
    public int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<>();
        for(char ch : J.toCharArray()) set.add(ch);
        int res = 0;
        for(char ch : S.toCharArray()) {
            if (set.contains(ch)) res +=1;
        }
        return res;
    }
}
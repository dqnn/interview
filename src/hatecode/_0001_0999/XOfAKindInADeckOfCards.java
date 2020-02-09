package hatecode._0001_0999;
import java.util.*;
class XOfAKindInADeckOfCards {
/*
914. X of a Kind in a Deck of Cards
In a deck of cards, each card has an integer written on it.

Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:

Each group has exactly X cards.
All the cards in each group have the same integer.
 
*/
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length <= 1) return false;
        
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(deck).forEach(ele->map.put(ele, map.getOrDefault(ele,0) + 1));
        int pre = -1;
        for(int val : map.values()) {
            if (pre == -1) {
                pre = val;
                continue;
            }
            if (!(pre >=2 && (gcd(pre, val) >= 2))) return false;
        }
        return true;
    }
    
    public int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    } 
    
     public boolean hasGroupsSizeX2(int[] deck) {
        Map<Integer, Integer> count = new HashMap<>();
        int res = 0;
        for (int i : deck) count.put(i, count.getOrDefault(i, 0) + 1);
        for (int i : count.values()) res = gcd(i, res);
        return res > 1;
    }
}
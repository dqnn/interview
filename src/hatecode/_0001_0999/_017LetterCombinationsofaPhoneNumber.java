package hatecode._0001_0999;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LetterCombinationsofaPhoneNumber
 * Creator : professorX
 * Date : Oct, 2017
 * Description : 17. Letter Combinations of a Phone Number
 */
public class _017LetterCombinationsofaPhoneNumber {
    /**
     * 
     * example: 
     * 23->[ad,ae,af,bd,be,bf,cd,ce,cf]
     * time : O(3^n)
     * space : O(n)
     */

    String[] map = new String[] {
            "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() < 1) {
            return res;
        }

        helper(res, digits, "", 0);
        return res;
    }

    public void helper(List<String> res, String digits, String pre, int idx) {
        if (idx == digits.length()) {
            res.add(pre);
            return;
        }
        String letters = map[digits.charAt(idx) - '0'];
        // back tracking
        for (int i = 0; i < letters.length(); i++) {
            helper(res, digits, pre + letters.charAt(i), idx + 1);
        }
    }
    // non recursive way, this templates is the same 
    //as prime number example, Super ugly number
    public List<String> letterCombinations2(String digits) {
        LinkedList<String> res = new LinkedList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        res.add("");
        for (int i = 0; i < digits.length(); i++) {
            int num = digits.charAt(i) - '0';
            //this is the key which to say the first string length
            //is the same as i，it is like a gate
            while (res.peek().length() == i) {
                String t = res.remove();
                for (char s : mapping[num].toCharArray()) {
                    res.add(t + s);
                }
            }
        }
        return res;
    }
    //another DFS way
    public List<String> letterCombinations3(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() < 1) {
            return res;
        }
        Map<String, List<String>> map = new HashMap<>() {
            {
                put("2", new ArrayList<>(Arrays.asList("a", "b", "c")));
                put("3", new ArrayList<>(Arrays.asList("d", "e", "f")));
                put("4", new ArrayList<>(Arrays.asList("g", "h", "i")));
                put("5", new ArrayList<>(Arrays.asList("j", "k", "l")));
                put("6", new ArrayList<>(Arrays.asList("m", "n", "o")));
                put("7", new ArrayList<>(Arrays.asList("p", "q", "r", "s")));
                put("8", new ArrayList<>(Arrays.asList("t", "u", "v")));
                put("9", new ArrayList<>(Arrays.asList("w", "x", "y", "z")));
            }
        };
        
        helper(res, new StringBuilder(), digits, map, 0);
            
        return res;
    }
    
    private void helper(List<String> res, StringBuilder sb, String str, Map<String, List<String>> map, int pos) {
        if(pos == str.length()) {
            res.add(sb.toString());
            return;
        }
        for(String c : map.get("" + str.charAt(pos))) {
            sb.append(c);
            helper(res, sb, str, map, pos+1);
            sb.deleteCharAt(sb.length() -1);
        }
    }


    //the 3rd vesion without removing elements in list
    /*
     * interview friendly.
     * thinking process: O(m^n)/O(n) m = avg length of each charater mapping, n = s.length()
     */
    public List<String> letterCombinations_v3(String s) {
        if(s == null || s.length() < 1) return new ArrayList<>();
        
        Map<Character, Set<Character>> map = new HashMap<>(){{
            put('2', new HashSet<>(Arrays.asList('a', 'b', 'c')));  
            put('3', new HashSet<>(Arrays.asList('d', 'e', 'f')));  
            put('4', new HashSet<>(Arrays.asList('g', 'h', 'i')));  
            put('5', new HashSet<>(Arrays.asList('j', 'k', 'l')));  
            put('6', new HashSet<>(Arrays.asList('m', 'n', 'o')));  
            put('7', new HashSet<>(Arrays.asList('p', 'q', 'r', 's')));  
            put('8', new HashSet<>(Arrays.asList('t', 'u', 'v')));  
            put('9', new HashSet<>(Arrays.asList('w', 'x', 'y', 'z')));            
        }};
        
        List<String> res = new ArrayList<>();
        helper(s, 0, map, res, "");
        
        return res;
    }
    
    
    private void helper(String s, int pos, Map<Character, Set<Character>> map, List<String> res, String cur) {
        if (pos == s.length()) {
            res.add(cur);
            return;
        }
        
        for(char next: map.get(s.charAt(pos))) {
            helper(s, pos+1, map, res, cur+next);
        }
    }

}

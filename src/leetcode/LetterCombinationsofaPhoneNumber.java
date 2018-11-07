package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LetterCombinationsofaPhoneNumber
 * Creator : duqiang
 * Date : Oct, 2017
 * Description : 17. Letter Combinations of a Phone Number
 */
public class LetterCombinationsofaPhoneNumber {
    /**
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
    // non recursive way 
    public List<String> letterCombinations2(String digits) {
        LinkedList<String> res = new LinkedList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        res.add("");
        for (int i = 0; i < digits.length(); i++) {
            int num = digits.charAt(i) - '0';
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
        Map<String, List<String>> map = new HashMap<>();
        map.put("2", new ArrayList<>(Arrays.asList("a", "b", "c")));
        map.put("3", new ArrayList<>(Arrays.asList("d", "e", "f")));
        map.put("4", new ArrayList<>(Arrays.asList("g", "h", "i")));
        map.put("5", new ArrayList<>(Arrays.asList("j", "k", "l")));
        map.put("6", new ArrayList<>(Arrays.asList("m", "n", "o")));
        map.put("7", new ArrayList<>(Arrays.asList("p", "q", "r", "s")));
        map.put("8", new ArrayList<>(Arrays.asList("t", "u", "v")));
        map.put("9", new ArrayList<>(Arrays.asList("w", "x", "y", "z")));
        
        helper(res, new StringBuilder(), digits, map, 0);
            
        return res;
    }
    
    private void helper(List<String> res, StringBuilder temp, String origin, Map<String, List<String>> map, int index) {
        if (index == origin.length()) {
            res.add(temp.toString());
            return;
        }
        // 2-> a,b,c  3-> d,e,f
        //
        List<String> charStr = map.get(String.valueOf(origin.charAt(index)));
        for(String str : charStr) {
                temp.append(str);
                helper(res, temp, origin, map, index + 1);
                temp.deleteCharAt(temp.length() - 1);
        }
    }

}

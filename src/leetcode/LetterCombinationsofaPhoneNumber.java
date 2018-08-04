package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

}

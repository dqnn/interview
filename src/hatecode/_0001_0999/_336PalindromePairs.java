package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PalindromePairs
 * Creator : professorX
 * Date : Aug, 2018
 * Description : 336. Palindrome Pairs
 */
public class _336PalindromePairs {
    /**
     * Given a list of unique words, find all pairs of distinct indices (i, j) 
     * in the given list,
     * so that the concatenation of the two words, i.e. words[i] + words[j] 
     * is a palindrome.

     Example 1:
     Given words = ["bat", "tab", "cat"]
     Return [[0, 1], [1, 0]]
     The palindromes are ["battab", "tabbat"]
     Example 2:
     Given words = ["abcd", "dcba", "lls", "s", "sssll"]
     Return [[0, 1], [1, 0], [3, 2], [2, 4]]
     The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]

     "abcdc" "ab"

     ab"cdc ab"
     "a" a

     time : O(n * k^2)
     space : O(n)

     * @param words
     * @return
     */
    //thinking process:
    
    //given a string array, we want to know whether their permutation of any 2 string concatting can be 
    //a palindrome
    
    //1. we add all strings into a map<String, index>
    //2. str2.length() != 0 this is to say, we cut the string into two parts, one maybe the whole string,
    //we just need to make sure we try to use whole string once, we can add the condition for check str1 or 
    //str2, current we add to str1
    //3. the model is to say, we break the string into two parts, one is  
    // Str1 Str2 ---> Str2RvS Str1 Str2, so we want to check whether we can find str2 reversed string
    // Str1 Str2 ---> Str1 Str2 Str1Rvs so we want to check whether we can find str2 reversed string in map  
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 2) return res;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
     // we break down the current w[i] string into 2 parts, if each one can be palind, and we will add them to map and 
        // compare the index, if it is not i which means other string is palindrome
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) {
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    String str2rvs = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
                        res.add(Arrays.asList(map.get(str2rvs), i));
                    }
                }
                if (str2.length() != 0 && isPalindrome(str2)) {
                    String str1rvs = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(str1rvs) && map.get(str1rvs) != i) {
                        res.add(Arrays.asList(i, map.get(str1rvs)));
                    }
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l <= r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
    
    //TLE version, just for reference, we will compare str1 + str2 and str2 + str1
    public List<List<Integer>> palindromePairs2(String[] w) {
        List<List<Integer>> res = new ArrayList<>();
        if (w == null || w.length < 1) {
            return res;
        }
        
        int len = w.length;
        for(int i = 0; i < len;i++) {
            for(int j = i + 1; j< len; j++) {
                int t = isPalind(w, i, j);
                List<Integer> list = new ArrayList<>();
                switch(t) {
                    case 1: 
                        list.add(i);
                        list.add(j);
                        res.add(list);
                        break;
                    case 2: 
                        list.add(j);
                        list.add(i);
                        res.add(list);
                        break;
                    case 3: 
                        list.add(i);
                        list.add(j);
                        res.add(list);
                        List<Integer> anotherlist = new ArrayList<>();
                        anotherlist.add(j);
                        anotherlist.add(i);
                        res.add(anotherlist);
                        break;
                    default: 
                        break;
                        
                }
            }
           
        }
        return res;
    }
    
    public int isPalind(String[] w, int i , int j) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        String t1 = sb1.append(w[i]).append(w[j]).toString(), t_1 = sb1.reverse().toString();
        String t2 = sb2.append(w[j]).append(w[i]).toString(), t_2 = sb2.reverse().toString();
        
        if (t1.equals(t_1) && t2.equals(t_2)) {
            return 3;
        } else if (t1.equals(t_1)){
            return 1;
        } else if (t2.equals(t_2)){
            return 2;
        } else {
            return 0;
        }
    }
}

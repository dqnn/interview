package hatecode;

import java.util.*;
public class _996VowelSpellchecker {
/*
966. Vowel Spellchecker
Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.

For a given query word, the spell checker handles two categories of spelling mistakes:

Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.

Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.


Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
*/
    //thinking process: 
    //given a dictionary, with some queries, rules:
    //1 if match with query and all character case match, returns that
    //2. if character match while case not match,  return first
    //3. all aeoiu match to any character, return first
    
    
    //just logic, no algortithms,
    //another thing is we can try bloom filter in search
    public String[] spellchecker(String[] wordlist, String[] queries) {
        if(wordlist == null || wordlist.length < 1 || queries == null || queries.length < 1) {
            return new String[0];
        }
        
        Map<String, String> dic = new HashMap<>();
        Set<String> set = new HashSet<>(Arrays.asList(wordlist));
        for (String w: wordlist) {
            String low = w.toLowerCase();
            dic.putIfAbsent(low, w);
            dic.putIfAbsent(normalize(low), w);
        }
        
        for(int i = 0; i< queries.length; i++) {
            if(set.contains(queries[i])) continue;
            String low = queries[i].toLowerCase(), normal = normalize(normalize(queries[i]));
            
            if(dic.containsKey(low)) {
                queries[i] = dic.get(low);
            } else if(dic.containsKey(normal)) {
                queries[i] = dic.get(normal);
            } else queries[i] = "";
        }
        
        return queries;
    }
    
    private String normalize(String in) {
        if(in == null || in.length() < 1) return in;
        
        char[] chs = in.toLowerCase().toCharArray();
        for(int i = 0; i< chs.length; i++) {
            if(chs[i] == 'a' || chs[i] == 'o' ||chs[i] == 'e' ||chs[i] == 'i' ||chs[i] == 'u') chs[i] = '#';    
        }
        
        return new String(chs);
        
    }
}
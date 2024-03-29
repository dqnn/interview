package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RepeatedDNASequences
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 187. Repeated DNA Sequences
 */
public class _187RepeatedDNASequences {
    /**
     * All DNA is composed of a series of nucleotides abbreviated 
     * as A, C, G, and T,
     * for example: "ACGAATTCCG". When studying DNA, it is sometimes 
     * useful to identify repeated sequences within the DNA.

     Write a function to find all the 10-letter-long sequences (substrings) 
     that occur more than once in a DNA molecule.

     For example,

     Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

     Return:
     ["AAAAACCCCC", "CCCCCAAAAA"].

     time : O(n)
     space : O(n)

     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 10) {
            return res;
        }
        HashSet<String> seen = new HashSet<>();
        HashSet<String> repeated = new HashSet<>();
        for (int i = 0; i < s.length() - 9; i++) {
            String temp = s.substring(i, i + 10);
            if (!seen.add(temp)) {
                repeated.add(temp);
            }
        }
        return new ArrayList<>(repeated);
    }
}

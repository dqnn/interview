package hatecode;

import java.util.stream.IntStream;
public class _1061LexicographicallySmallestEquivalentString {
/*
1061. Lexicographically Smallest Equivalent String
Given strings A and B of the same length, we say A[i] and B[i] are equivalent characters. For example, if A = "abc" and B = "cde", then we have 'a' == 'c', 'b' == 'd', 'c' == 'e'.

Equivalent characters follow the usual rules of any equivalence relation:

Reflexivity: 'a' == 'a'
Symmetry: 'a' == 'b' implies 'b' == 'a'
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'
For example, given the equivalency information from A and B above, S = "eed", "acd", and "aab" are equivalent strings, and "aab" is the lexicographically smallest equivalent string of S.

Return the lexicographically smallest equivalent string of S by using the equivalency information from A and B.

 

Example 1:

Input: A = "parker", B = "morris", S = "parser"
Output: "makkek"
*/
    //thinking process: O(a(n) + n)/O(1)
    
    //the problem is to say, to give two same length strings of A, B,
    //each char will be equal, so given another string S, return the smallest 
    //lexi string
    
    //we use union find to find the smallest char as each group root
    public String smallestEquivalentString(String A, String B, String S) {
        
        StringBuilder sb = new StringBuilder();
        DSU dsu = new DSU(26);
        for (int i = 0;i < A.length();i++) {
            char ch1 = A.charAt(i);
            char ch2 = B.charAt(i);
            dsu.union(ch1 - 'a', ch2 - 'a');
        }
        
        for (char ch:S.toCharArray()) {
            int p = dsu.find(ch - 'a');
            sb.append((char) (p + 'a'));
        }
        return sb.toString();
    }
    
    class DSU {
        int[] parent;
        public DSU(int n) {
            parent = new int[n];
            IntStream.range(0,n).forEach(e->parent[e] = e);
        }
        public void union(int c1, int c2) {
            int p1 = find(c1);
            int p2 = find(c2);
            if(p1 < p2) parent[p2] = p1;
            else parent[p1] = p2;
        }
        
        public int find(int c) {
            if(c != parent[c]) {
                parent[c] = find(parent[c]);
            }
            return parent[c];
        }
    }
    
   
}
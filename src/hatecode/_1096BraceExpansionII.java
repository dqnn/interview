package hatecode;

import java.util.*;
public class _1096BraceExpansionII {
/*
1096. Brace Expansion II
Grammar can best be understood through simple examples:

Single letters represent a singleton set containing that word.
R("a") = {"a"}
R("w") = {"w"}
When we take a comma delimited list of 2 or more expressions, we take the union of possibilities.
R("{a,b,c}") = {"a","b","c"}
R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.
R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
R("{a{b,c}}{{d,e},f{g,h}}") = R("{ab,ac}{dfg,dfh,efg,efh}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
Formally, the 3 rules for our grammar:

For every lowercase letter x, we have R(x) = {x}
For expressions e_1, e_2, ... , e_k with k >= 2, we have R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
For expressions e_1 and e_2, we have R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}, where + denotes concatenation, and × denotes the cartesian product.
Given an expression representing a set of words under the given grammar, return the sorted list of words that the expression represents.

 

Example 1:

Input: "{a,b}{c{d,e}}"
Output: ["acd","ace","bcd","bce"]
*/
   public List<String> braceExpansionII(String expression) {
        List<List<List<String>>> groups = new ArrayList<>();
        groups.add(new ArrayList<>());
        int level = 0;
        int start = -1;
        //we only have 4 types of character, {, }, ',' and letter
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '{') {
                if (level == 0) {
                    start = i + 1;
                }
                level++;
            } else if (c == '}') {
                level--;
                if (level == 0) {
                    String subExpression = expression.substring(start, i);
                    List<String> subRes = braceExpansionII(subExpression);
                    groups.get(groups.size() - 1).add(subRes);
                }
            } else if (c == ',' && level == 0) {
                groups.add(new ArrayList<>());
            //process letter
            } else if (level == 0) {
                groups.get(groups.size() - 1).add(Arrays.asList(String.valueOf(c)));
            }
        }

        Set<String> set = new HashSet<>();
        for (List<List<String>> group : groups) {
            List<String> pre = new ArrayList<>();
            pre.add("");
            for (List<String> g : group) {
                List<String> tmp = new ArrayList<>();
                for (String pStr : pre) {
                    for (String gStr : g) tmp.add(pStr + gStr);
                }
                pre = tmp;
            }
            set.addAll(pre);
        }
        
        List<String> res = new ArrayList<>(set);
        Collections.sort(res);
        return res;
    }
}
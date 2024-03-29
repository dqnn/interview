package hatecode._1000_1999;
public class _1021RemoveOutermostParentheses {
/*
1021. Remove Outermost Parentheses
A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.  For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.

A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B, with A and B nonempty valid parentheses strings.

Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.

Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.

 

Example 1:

Input: "(()())(())"
Output: "()()()"
*/
    //thinking process: 
    //the problem is to say: given a valid parenthese string s, 
    //for each substring valid parentheses, if we removed the outest parenthese
 // the rest still valid, just return it. 
 //the primitive string of parentheses is to say it is the cleanest, no way to remove any parenthese, 
 //(()())(()) ->(()()) + (()), just for each primitive, remove the outest parentheses and return.
 // 
    //outest parentness, like ()()->""
    //this is used opened as indicator we can remove outest parentness
    public String removeOuterParentheses(String S) {
        if (S == null || S.length() < 1) return S;
        StringBuilder s = new StringBuilder();
        int opened = 0;
        for (char c : S.toCharArray()) {
            if (c == '(' && opened++ > 0) s.append(c);
            if (c == ')' && opened-- > 1) s.append(c);
        }
        return s.toString();
    }
}

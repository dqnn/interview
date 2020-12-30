package hatecode._1000_1999;
public class _1209RemoveAllAdjacentDuplicatesInStringII {
/*
1209. Remove All Adjacent Duplicates in String II
Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made.

It is guaranteed that the answer is unique.

 

Example 1:

Input: s = "abcd", k = 2
Output: "abcd"
*/
    //thinking process: O(n)/O(n)
    
    //the problem is to say: given one string and integer k, we remove continuous k chars,
    //return the final string.
    
    //we can loop the string, if we find k continuous chars, then we create a new string and dfs same function
    //this can be one exit, if we cannot find the s already is the asked one
    public String removeDuplicates(String s, int k) {
        if (s.length() < k) return s;
        char c = s.charAt(0);
        int cnt = 1;
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == c) cnt++;
            else {
                c = s.charAt(i);
                cnt = 1;
            }
            if (cnt == k) {
                return removeDuplicates(s.substring(0, i-k+1) + s.substring(i+1), k);
            }
        }
        return s;
    }
    
    public String removeDuplicates_TP(String s, int k) {
        int i = 0, n = s.length(), count[] = new int[n];
        char[] stack = s.toCharArray();
        for (int j = 0; j < n; ++j, ++i) {
            stack[i] = stack[j];
            count[i] = i > 0 && stack[i - 1] == stack[j] ? count[i - 1] + 1 : 1;
            if (count[i] == k) i -= k;
        }
        return new String(stack, 0, i);
    }
    
    public String removeDuplicates_StringBuilder(String s, int k) {
        int[] count = new int[s.length()];
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            sb.append(c);
            int last = sb.length()-1;
            count[last] = 1 + (last > 0 && sb.charAt(last) == sb.charAt(last-1) ? count[last-1] : 0);
            if(count[last] >= k) sb.delete(sb.length()-k, sb.length());
        }
        return sb.toString();
    }
    
    
}
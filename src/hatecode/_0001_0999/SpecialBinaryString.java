package hatecode._0001_0999;
import java.util.*;
public class SpecialBinaryString {
/*
761. Special Binary String
Special binary strings are binary strings with the following two properties:

The number of 0's is equal to the number of 1's.
Every prefix of the binary string has at least as many 1's as 0's.
Given a special string S, a move consists of choosing two consecutive, non-empty, special substrings of S, and swapping them. (Two strings are consecutive if the last character of the first string is exactly one index before the first character of the second string.)

At the end of any number of moves, what is the lexicographically largest resulting string possible?

Example 1:
Input: S = "11011000"
Output: "11100100"
*/
 
    //O(n^2)/O(n)
    //thinking process: give a binary string which only contains 0 and 1, according to 
    //statements, the string is like a mountain, suppose 1 is up, 0 is down, so it would reach
    //horizonte finally, so it is balanced, we would like to make the mountain left side climb
    //as fast as possible so we want to sort all possible small mountains inside S. 
    //below code showed how this works by sorting all small mountains in S
    public String makeLargestSpecial3(String S) {
        if (S == null || S.length() == 0) return S;
        //starts meas balance string starts, 
        //bal means strings are balanced which 1 and 0 are the same 
        int start = 0, bal = 0;
        //is to save  mountain substring in S
        List<String> mountains = new ArrayList<>();
        for (int i = 0; i < S.length(); ++i) {
            bal += S.charAt(i) == '1' ? 1 : -1;
            if (bal == 0) {
                //note here s[i] should be 0
                mountains.add("1" + makeLargestSpecial(S.substring(start+1, i)) + "0");
                //string next start index
                start = i+1;
            }
        }
        //sort strings by reverse order, first one has max lexi order O(n^2)
        Collections.sort(mountains, Collections.reverseOrder());
        StringBuilder ans = new StringBuilder();
        for (String mtn: mountains) ans.append(mtn);
        return ans.toString();
    }
    
    //stack + sort solutions,
    public String makeLargestSpecial(String S) {
        if (S == null || S.length() <= 2) return S;

        Stack<List<String>> stack = new Stack<>();
        stack.push(new ArrayList<>());

        for (char c: S.toCharArray()) {
            if (c == '1')  stack.push(new ArrayList<>());
            else {
                List<String> list = stack.pop();
                sortStr(list);
                stack.peek().add("1" + getStr(list) + "0");
            }
        }
        List<String> list = stack.peek();
        //Collections.sort(list, Collections.reverseOrder()); 
        //Collections.sort(list, (a,b)->(b.compareTo(a)));
        //it can be replaced by above code, but it gave an example how to write comparator 
        //also we can use lambda
        sortStr(list);
        return getStr(list);
    }

    private void sortStr(List<String> list) {
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
    }

    private String getStr(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str: list) {
            sb.append(str);
        }
        return sb.toString();
    }
    
    public String makeLargestSpecial2(String S) {
        String max = new String(S);
        int n = S.length();
        //here we record all special strings starting from index i->List(special Strings)
        Map<Integer, List<String>> map = new HashMap<>();
        char[] cs = S.toCharArray();
        for (int i = 0; i < n; ++i) {
            if (cs[i] == '1') {
                int j = i + 1 + 1; 
                while (j <= n) {
                    if (isSpecial(S.substring(i, j)))
                            map.computeIfAbsent(i, k -> new ArrayList<>()).add(S.substring(i, j));
                    //each time we move forward twice per problems statements
                    j += 2;
                }
            }
        }
        //loop on each strings
        for (int i : map.keySet()) {
            //we got all special strings for this index
            List<String> firs = map.get(i);
            //each special string
            for (String fir : firs) {
                //the end of the string, j
                int j = i + fir.length();
                //starting from j we found other special strings
                if (map.containsKey(j)) {
                    for (String sec : map.get(j)) {
                        //we swap these two spcial strings in S
                        String cnd = swap(S, i, fir.length(), j, sec.length());
                        //lexically order
                        if (max.compareTo(cnd) < 0) {
                            max = cnd;
                        }
                    }
                }
            }
        }
        //we found all, and return all possible max
        if (max.equals(S)) return max;
        // if in S we cannot find the max one, then look for it in max because max is already the string we can get from S
        else return makeLargestSpecial(max);
    }

    String swap(String S, int i, int n1, int j, int n2) {
        String ans = S.substring(0, i);
        ans += S.substring(j, j + n2);
        ans += S.substring(i + n1, j);
        ans += S.substring(i, i + n1);
        ans += S.substring(j + n2);
        return ans;
    }

    public boolean isSpecial(String S){
        char[] cs = S.toCharArray();
        int count = 0;
        for(int i = 0; i < S.length(); i++){
            if (cs[i] == '1') count ++;
            else count --;
            if (count < 0) return false;
        }
        return count == 0;
    }
}
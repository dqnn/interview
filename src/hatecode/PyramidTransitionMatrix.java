package hatecode;

import java.util.*;
public class PyramidTransitionMatrix {
/*
756. Pyramid Transition Matrix
We are stacking blocks to form a pyramid. Each block has a color which is a one letter string, like `'Z'`.

For every block of color `C` we place not in the bottom row, we are placing it on top of a left block of color `A` and right block of color `B`. We are allowed to place the block there only if `(A, B, C)` is an allowed triple.

We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed. Each allowed triple is represented as a string of length 3.

Return true if we can build the pyramid all the way to the top, otherwise false.

Example 1:
Input: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
Output: true
Explanation:
We can stack the pyramid like this:
    A
   / \
  D   E
 / \ / \
X   Y   Z

This works because ('X', 'Y', 'D'), ('Y', 'Z', 'E'), and ('D', 'E', 'A') are allowed triples.
*/
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s: allowed) {
            String key = s.substring(0, 2);
            map.computeIfAbsent(key, v->new ArrayList<>()).add(s.substring(2));
        }
        System.out.println(map);
        return helper(bottom, map);
    }
    
    private boolean helper(String bottom, Map<String, List<String>> map) {
        if (bottom.length() == 1) return true;
        
        for(int i = 0;i < bottom.length() - 1; i++) {
            if (!map.containsKey(bottom.substring(i, i+2))) return false;
        }
        
        List<String> ls = new ArrayList<>();
        getList(bottom, 0, new StringBuilder(), ls, map);
        for(String s: ls) {
            if (helper(s, map)) return true;
        }
        return false;
    }
    
    
    private void getList(String bottom, int pos, StringBuilder sb, List<String> ls, Map<String, List<String>> map) {
        if (pos == bottom.length() - 1) {
            ls.add(sb.toString());
            return;
        }
        
        for(String s: map.get(bottom.substring(pos, pos + 2))) {
            sb.append(s);
            getList(bottom, pos+1, sb, ls, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
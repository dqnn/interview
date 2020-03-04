package hatecode._0001_0999;

import java.util.*;
public class _756PyramidTransitionMatrix {
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
    //thinking process:
    //given the bottom string, we want to traverse the tree by post order, and finally we can form a pyramid,
    //return true or false with given the string array
    
    //there are two levels concepts of string match, one is bottom string for each level, 
    //the bottom one already fixed, so we need to generate all possible bottom string for the next level
    //bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
    //we can generate next level bottom list=[DE]
    //from DE we can continue to use the map to generate which is only A
    //so if we have a large input, then bottom string list will be more than 1,then we need a loop to try each one
    //since each level;s problem is the same, so this is recursive problem, we have to define two recursive function to solve
    //each one, first one is to generate next level all possible bottom strings, second one is to validate the pyramid it generated
    //by this bottom string, and these 2 are nested
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
        //if the string does not contain the bottom string then we cannot form the pyramid, return false
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
    
    //this method is to backtracking from bottom string, the outpu ls is the all possible 
    //string for the level above bottom
    //bottom level, then we 
    private void getList(String bottom, int pos, StringBuilder sb, List<String> ls, Map<String, List<String>> map) {
        //why we stop at last position is because each key is 2 chars, we cannot move on 
        if (pos == bottom.length() - 1) {
            ls.add(sb.toString());
            return;
        }
        //add each string into sb if we can go to the last position
        for(String s: map.get(bottom.substring(pos, pos + 2))) {
            sb.append(s);
            getList(bottom, pos+1, sb, ls, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
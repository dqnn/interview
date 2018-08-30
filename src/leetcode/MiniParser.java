package leetcode;

import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MiniParser
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 385. Mini Parser
 */
public class MiniParser {
    /**
     * Given a nested list of integers represented as a string, implement a parser to deserialize it.

     Each element is either an integer, or a list -- whose elements may also be integers or other lists.

     Note: You may assume that the string is well-formed:

     String is non-empty.
     String does not contain white spaces.
     String contains only digits 0-9, [, - ,, ].
     Example 1:

     Given s = "324",

     You should return a NestedInteger object which contains a single integer 324.
     Example 2:

     Given s = "[123,[456,[789]]]",

     Return a NestedInteger object containing a nested list with 2 elements:

     1. An integer containing value 123.
     2. A nested list containing two elements:
     i.  An integer containing value 456.
     ii. A nested list with one element:
     a. An integer containing value 789.

     Stack : [: push
            , ] : pop + Integer
            number : s.substring valueOf

     s = "[123,[456,[789]]]"

     res : [123,[456,[789]]]
     stack:  123   [456,[789]]

     time : O(n)
     space : O(n)

     * @param s
     * @return
     */
 // whole return is a NestedInteger, so we can try stack and using peek() to always  
    // pick the previous integer context and hook them together
    
    // [123,[456,[789]]], as example, 
    // firstly we add new empty NestedInteger into stack, and we start scan from 1st character,
    // if we encounter [, means second NestedInteger, so we are in a new NestedInteger context
    // so we add a new empty NestedInteger into stack, 
    // if we encounter , we need to add this integer from start position to i so we use stack.peek().add(the integer)
    public NestedInteger deserialize(String s) {
        NestedInteger res = new NestedInteger();
        if (s == null) {
           return res;
        }
        // NestedInteger must begin with [ or it just integer
        if (s.charAt(0) != '[') {
            res.setInteger(Integer.valueOf(s));
        }
        // we push the integer into stack so in scan process, we can use stack.peek to refer to this NestedInteger
        Stack<NestedInteger> stack = new Stack<>();
        stack.push(res);
        
        int start = 1;
        for(int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '[') {
                NestedInteger nestedInteger = new NestedInteger();
                stack.peek().add(nestedInteger);
                stack.push(nestedInteger);
                start = i + 1;
            } else if ( ch == ',' || ch == ']') {
                if (i > start) {
                    // i here point to , or ]
                    Integer val = Integer.valueOf(s.substring(start, i));
                    stack.peek().add(new NestedInteger(val));
                }
                // we re-recongnize the new number
                start = i + 1;
                //[123,[456], 789] this case, we need eject the [456], so if we encounter ] we have 
                // to pop previous one
                if (ch == ']') {
                    stack.pop();
                }
            }
        }
        return res;
    }
}

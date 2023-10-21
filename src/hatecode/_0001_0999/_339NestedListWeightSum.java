package hatecode._0001_0999;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : NestedListWeightSum
 * Date : Aug, 2018
 * Description : TODO
 */
public class _339NestedListWeightSum {
    /**
     * 339. Nested List Weight Sum
     * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

     Each element is either an integer, or a list -- whose elements may also be integers or other lists.

     Example 1:
     Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

     Example 2:
     Given the list [1,[4,[6]]], return 27.
     (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)


     time : O(n)
     space : O(n);
     * @param nestedList
     * @return
     */


    // DFS
    
    // need to understand what's NestedInteger means, it means 
    //1 it must starts with [, and end with ] || or just 1 number
    // 2 it means every element in nestedInteger may be a number or a list, this is different compared to MinParser
    
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        return helper(nestedList, 1);
    }

    public int helper(List<NestedInteger> nestedList, int depth) {
        int res = 0;
        for (NestedInteger nest : nestedList) {
            if (nest.isInteger()) {
                res += nest.getInteger() * depth;
            } else {
                res += helper(nest.getList(), depth + 1);
            }
        }
        return res;
    }

    // BFS
    // interview friendly, every recursive, we have a stack way to solve
    public int depthSum2(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        int depth = 1;
        int res = 0;
        Queue<NestedInteger> queue = new LinkedList<>(nestedList);
        while (!queue.isEmpty()) {
            int size = queue.size();
            //broad scan the elements
            for (int i = 0; i < size; i++) {
                NestedInteger nest = queue.poll();
                if (nest.isInteger()) {
                    res += nest.getInteger() * depth;
                } else {
                    queue.addAll(nest.getList());
                }
            }
            depth++;
        }
        return res;
    }


    public int depthSum3(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        int depth = 1;
        int res = 0;
        while (nestedList.size() != 0) {
            List<NestedInteger> nextList = new LinkedList<>();
            for (NestedInteger nest : nestedList) {
                if (nest.isInteger()) {
                    res += nest.getInteger() * depth;
                } else {
                    nextList.addAll(nest.getList());
                }
            }
            depth++;
            nestedList = nextList;
        }
        return res;
    }


    /*
     * interview question; 
     * "3,4,[1,2,[7],8]" = 3 +4 + 2* (1+2+3*7) + 8
     * 
     * "1, [2]"
     * 1,[[2],[1,2]]
     * 
     */
   
    public static int calc2(String A) {
        if (A == null || A.length() < 1) return 0;
        String[] ss = A.split(",");

        Stack<Integer> st = new Stack<>();
        int level = 1;
        for(String s : ss) {
            for(int i = 0; i< s.length(); i++) {
                char c = s.charAt(i);
                if(Character.isDigit(c)) {
                    int num = c- '0';
                    int j = i + 1;
                    while( j < s.length() && Character.isDigit(s.charAt(j))) {
                        num = num * 10 + s.charAt(j) - '0';
                        j++;
                    }
                    i = j -1;
                    st.push(level * num);
                } else if (c=='[') {
                    level += 1;
                    st.push(Integer.MIN_VALUE);
                } else {
                    int temp = 0;
                    while(!st.isEmpty() && st.peek() != Integer.MIN_VALUE) {
                        temp += st.pop();
                    }

                    st.pop();
                    st.push(temp);
                    level -=1;
                }
            }
        }

        int res = 0;
        while(!st.isEmpty()) res += st.pop();

        return res;
    }




    
    public static int calc(String A) {
        if(A == null || A.length()< 1) return 0;

        String[] ss = A.split(",");
        
        int level = 1;
        Stack<Integer> st = new Stack<>();

        for(String s: ss) {
            for(int i = 0; i <s.length(); i++) {
                char c = s.charAt(i);
                if(Character.isDigit(c)) {
                    int num = c - '0';
                    int j = i + 1;
                    while( j <s.length() && Character.isDigit(j)) {
                        num = num * 10 + s.charAt(j) -'0';
                        j++;
                    }
                    i=j-1;
                    st.push(num*level);
                } else if (c == '[') {
                    level +=1;
                    st.push(Integer.MIN_VALUE);
                } else if (c ==']') {
                    int temp = 0;
                    while(st.peek() != Integer.MIN_VALUE) {
                        temp += st.pop();
                    }
                    st.pop();
                    st.push(temp);
                    level -=1;
                }
            }
        }


        int res = 0;
        while(!st.isEmpty()) {
            res += st.pop();
        }
        return res;
     }

     public static void main(String[] args) {
        //System.out.println(calc("1,2"));
        System.out.println(calc2("1,[[2],[1,2]]")); 
        System.out.println(calc2("[[[1,2]]]")); 

        System.out.println(calc("1,[[2],[1,2]]")); 
        System.out.println(calc("[[[1,2]]]")); 
        //                         1  + 3 * 2 + 3 *1 + 3 *2 = 
     }
}

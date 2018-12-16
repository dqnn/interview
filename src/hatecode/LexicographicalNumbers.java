package hatecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LexicographicalNumbers
 * Creator : duqiang
 * Date : Oct, 2017
 * Description : 386. Lexicographical Numbers
 */
public class LexicographicalNumbers {
    /**
     * Given an integer n, return 1 - n in lexicographical order.

     For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

     Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.

     time : O(n);
     space : O(n);

     * @param n
     * @return
     */
    /*
    The basic idea is to find the next number to add.
    Take 45 for example: 
    if the current number is 45, the next one will be 450 (450 == 45 * 10)(if 450 <= n), 
    or 46 (46 == 45 + 1) (if 46 <= n) or 5 (5 == 45 / 10 + 1)(5 is less than 45 so it is for sure less than n).
    We should also consider n = 600, and the current number = 499, the next number is 5 because there are all "9"s after "4" in "499" so we should divide 499 by 10 until the last digit is not "9".
    It is like a tree, and we are easy to get a sibling, a left most child and the parent of any node.
 */
        public List<Integer> lexicalOrder(int n) {
            List<Integer> res = new ArrayList<>();
            if (n < 1) {
                return res;
            }
            
            int cur = 1;
            
            //added a few comments, to view this as a preorder tree traversal, each tree node may have at most 
            //10 children (last digit is from 0~9), the difference is that we don't have to go rom left child to 
            //the right child through parent, because we know how to get child from child (which is add one), 
            //it's like we linked the level nodes before doing a preorder tree traversal
            for(int i = 1; i <= n; i++) {
                res.add(cur);
                if (cur * 10 <= n) {
                    cur *= 10;////going to the left most leaf as far as possible
                } else if (cur % 10 != 9 && cur + 1 <= n) {
                    cur++;//found the left most, doing level traversal (all children from left to right)
                }else {
                    //notice that when dispatching into else statement, we are currently on the last 
                    // level of the tree (the tree can not be unbalanced because of the continuity of nodes value), 
                    // one reason for dispatching is that we have reached the n(we can't stop here, n may not be the 
                    // last node in lexical order), the other is that we have reached the right most leaf of lowest 
                    //ancester node.
                    while((cur/10) % 10 == 9) {////we hit the right most node on this level
                        cur /= 10;//go back to the upper level
                    }
                    cur = cur/10 + 1;//preparing to do the level traversal again on upper level
                }
                
            }
            return res;
        }
        
        /*
        The idea is pretty simple. If we look at the order we can find out we just keep adding digit from 0 to 9 to every digit and make it a tree.
        Then we visit every node in pre-order. 
               1        2        3    ...
              /\        /\       /\
           10 ...19  20...29  30...39   ....
          /
        100...    199

        */
    public List<Integer> lexicalOrder2(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; ++i) {
            dfs(i, n, res);
        }
        return res;
    }

    public void dfs(int cur, int n, List<Integer> res) {
        if (cur > n) {
            return;
        } else {
            res.add(cur);
            // this is templates code
            for (int i = 0; i < 10; ++i) {
                if (10 * cur + i > n) {
                    return;
                }
                // backtracking and DFS
                dfs(10 * cur + i, n, res);
            }
        }
    }
}

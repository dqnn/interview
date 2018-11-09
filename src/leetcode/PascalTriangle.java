package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PascalTriangle
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 118. Pascal's Triangle
 */
public class PascalTriangle {
    /**
     * For example, given numRows = 5,
     Return

     [
         [1],
        [1,1],
       [1,2,1],
      [1,3,3,1],
     [1,4,6,4,1]
     ]


     time : O(n^2)
     space : O(n^2)

     * @param numRows
     * @return
     */
/*
//f(0,0) = 1, f(1, 0) = 1....

// f(i, j) = 

//                  f(i-1, j)  j =0;    0< j < n  = f(i-1, j-1) + f(i-1, j); j = n, f(i-1, n);

 */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            // this structure is good
            //we insert 1 into head of list
            list.add(0, 1);
            //the loop is to add middle parts except first and last element
            for (int j = 1; j < list.size() - 1; j++) {
                list.set(j, list.get(j) + list.get(j + 1));
            }
            res.add(new ArrayList<>(list));
        }
        return res;
    }
}

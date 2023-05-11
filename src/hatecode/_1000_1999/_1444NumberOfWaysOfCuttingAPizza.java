package _1001_1999;

import java.util.*;

public class _1444NumberOfWaysOfCuttingAPizza {
    /*
    1444. Number of Ways of Cutting a Pizza
    Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts. 
    
    For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.
    
    Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.
    
     
    
    Example 1:
    
    
    
    Input: pizza = ["A..","AAA","..."], k = 3
    Output: 3 
    Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.
    Example 2:
    
    Input: pizza = ["A..","AA.","..."], k = 3
    Output: 1
    Example 3:
    
    Input: pizza = ["A..","A..","..."], k = 1
    Output: 1
    */


    /*
     * thinking process: O(nmk)
     * TODO: spend time thinking
     */
        public int ways_2(String[] A, int k) {
            return helper(A, 0,0, k-1);
        }
        
        
        private int helper(String[] A, int i, int j, int k) {
            System.out.println("i=" + i + "  j=" + j + "  k=" + k);
            
            if (k == 0) {
                for(int r = i; i< A.length; i++) {
                    if (A[r].substring(j).indexOf('A') > -1) return 1;
                }
                return 0;
            }
            
            int cr = i, cc = j;
            
            for(;cr < A.length; cr++) {
                if (A[cr].substring(j).indexOf("A")> -1) break;
            }
        
            for(; cc < A[0].length();cc++) {
                int tempr = cr;
                while (tempr < A.length && A[tempr].charAt(cc) != 'A') {
                    tempr++;
                }
                if (tempr < A.length ) break;
            }
            
        
            
            int res = 0;
            for(int vr = cr + 1; vr < A.length; vr++) {
                res = (res + helper(A, vr, cc, k-1)) % 1_000_000_007;
            }
            
            for(int vc = cc + 1; vc < A[0].length(); vc++) {
                res = (res + helper(A, cr, vc, k-1)) % 1_000_000_007;
            }
            
            
            return res;
            
        }
        
        public int ways(String[] pizza, int k) {
            return count(pizza, 0, 0, k-1);
        }
    
    private int count(String[] pizza, int row, int col, int k) {
        if (k == 0) {
            for (int r = row; r < pizza.length; r++) {
                if (pizza[r].substring(col).indexOf('A') != -1) return 1;
            }
            return 0;
        }
        int ri = row, ci = col;
        //找最上面有苹果的行
        while (ri < pizza.length) {
            if (pizza[ri].substring(col).indexOf('A') == -1) ri++;
            else break;
        }
        
        
        
        //找最左边有苹果的列
        for(; ci < pizza[0].length();ci++) {
                int tempr = ri;
                while (tempr < pizza.length && pizza[tempr].charAt(ci) != 'A') {
                    tempr++;
                }
                if (tempr < pizza.length ) break;
        }
        /*
        while (ci < pizza[0].length()) {
            boolean hasA = false;
            //已经求得最上边有苹果的行，就从这里开始，可以省点事
            int r = ri;
            while (r < pizza.length) {
                if (pizza[r].charAt(ci) == 'A') {
                    hasA = true;
                    break;
                }
                r++;
            }
            if (hasA) break;
            else ci++;
        }
        */
        int res = 0;
        //横着切
        for (int i = ri + 1; i < pizza.length; i++) {
            res = (res + count(pizza, i, ci, k-1)) % 1_000_000_007;;
        }
        //竖着切
        for (int i = ci + 1; i < pizza[0].length(); i++) {
            res = (res + count(pizza, ri, i, k-1)) % 1_000_000_007;;
        }
        return res;
    }
    }
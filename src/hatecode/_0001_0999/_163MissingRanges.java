package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MissingRanges
 * Creator : professorX
 * Date : Aug, 2018
 * Description : 163. Missing Ranges
 */
public class _163MissingRanges {
    /**
Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.
For example, given [0, 1, 3, 50, 75], return [“2”, “4->49”, “51->74”, “76->99”]

     For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].

     [2147483647] 0 2147483647
     ["0->2147483646"]
     ["0->2147483646","-2147483648->2147483647"]

     time : O(n)
     space : O(1)

     * @param A
     * @param lower
     * @param upper
     * @return
     */
    // so for the range missing, we use a point point to lower, so 
    // when we scan the array, we wil process
    // if lowe < nums[i], we need to append lower-> nums[i] - 1. here we need to differ 
    // on nums[i] and nums[i] - 1
    
    //interview friendly,  O(n)/O(n) pure logic coding problem
    //find missing ranges given an array and lower, upper bound
    
    //so since nums already sorted, so lets consider what's the relationship between low variable num with num
    //[0, 1, 3, 50, 75],0, 99, two numbers we have 3 cases, 
    //1. equals, then we should continue; 
    //2. low < num, low + 1 == num, then we just 1 number
    //3. low + 1 < num, then we need a range, low ->nums-1
    
    //so we continue this process until over but we need to detect the upper, also two cases same as above. 
    //if low == upper, just 1 number, if not, it will be a range
    public List<String> findMissingRanges(int[] A, int l, int r) {
        List<String> res = new ArrayList<>();
        
        for(int a : A) {
            if (a == l) l += 1;
            else if (a > l) {
                if (l + 1 == a) res.add(l+"");
                else res.add(l + "->" + (a-1));
                l = a + 1;
            }
            
            //terminate early
            if (a >= r || l > r) break;
            
        }
        
        //we already went through the list, so we want to 
        //compare the r with the l
        //for example:  A= [3, 5] l = 2,  r =8, 
        //when we visited A, l = 6, r = 8, then we need to add rest to result strings
        if (l == r) res.add("" + l);
        else if (l < r) res.add(l + "->" + r);
        return res;
    }

    /*
     * 
     */
    public List<List<Integer>> findMissingRanges_latest(int[] A, int l, int h) {
        
        List<List<Integer>> res = new ArrayList<>();
        if(A == null || A.length < 1) {
            res.add(Arrays.asList(l,h));
            return res;
        }
        
        if(l  < A[0]) {
            res.add(Arrays.asList(l, A[0] - 1));
        }
        int i  =0;
        for(;i <A.length-1; i++) {
            if(A[i]+1 == A[i+1]) continue;
            res.add(Arrays.asList(A[i]+1, A[i+1] -1));
        }
        
        if(A[i] < h) {
            res.add(Arrays.asList(A[i] + 1, h));
        }
        
        
        return res;
        
    }
}

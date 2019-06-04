package hatecode;
import java.util.*;
public class RandomPickWithWeight {
    /*
     * 528. Random Pick with Weight Given an array w of positive integers, where
     * w[i] describes the weight of index i, write a function pickIndex which
     * randomly picks an index in proportion to its weight.
     * 
     * Note:
     * 
     * 1 <= w.length <= 10000 1 <= w[i] <= 10^5 pickIndex will be called at most
     * 10000 times. Example 1:
     * 
     * Input: ["Solution","pickIndex"] [[[1]],[]] Output: [null,0] Example 2:
     * 
     * Input:
     * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
     * [[[1,3]],[],[],[],[],[]] Output: [null,0,1,1,1,0]
     */
    //Standard solution
    Random random;
    int[] sum;
    //image w [1,3]->[1,1,1,1] so 3 should have 3 times appearance, 
    // we have prefix as [1,4], so idx use a random function which help to 
    //to produce 0 - 3 as index, we cannot use index % 2, because  if index = 2
    //then we should return 0 which is incorrect, so we need binary search
    //to determine which is right index should return, we 
    public RandomPickWithWeight(int[] w) {
        this.random = new Random();
        for(int i=1; i<w.length; ++i)
            w[i] += w[i-1];
        this.sum = w;
    }
    //if i use 3rd templates,what is going to do?
/*
w[] = {2,5,3,4} => wsum[] = {2,7,10,14}
idx in [1,2] return 0
idx in [3,7] return 1
idx in [8,10] return 2
idx in [11,14] return 3
idx is in range [1,14]
so we can directly search for idx in sum array using templates 2

 */
    //BS final result left = right, so no matter sum[left] idx 
    public int pickIndex() {
        int len = sum.length;
        // why idx + 1? because originally it would be 0 - sum[len-1] -1, 
        //but the value spectrum is 1-> sum[len-1],so we want to binary search in
        //this value interval
        int idx = random.nextInt(sum[len - 1]) + 1;
        int l = 0, r = len;
        // search position
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (sum[mid] == idx)
                return mid;
            else if (sum[mid] < idx)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }
}
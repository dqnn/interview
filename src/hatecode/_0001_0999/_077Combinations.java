package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;

/**
 * Date : Aug, 2017
 * Description : TODO
 */
public class _077Combinations {

    /**
     * 77. Combinations
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

     For example,
     If n = 4 and k = 2, a solution is:

     [
     [2,4],
     [3,4],
     [2,3],
     [1,2],
     [1,3],
     [1,4],
     ]
     [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]

     time : O(n^min{k,n-k})
     space : O(n);
     http://stackoverflow.com/questions/31120402/complexity-when-generating-all-combinations
     * @param n
     * @param k
     * @return
     */

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), n, k, 1);
        return res;
    }

    // this is a templates for permuations
    public void helper(List<List<Integer>> res, List<Integer> list, int n, int k, int start) {
        if (k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        
        // we do not have to go to n, because we only need n - k + 1 more elements
        //for (int i = start; i <= n; i++) {
        for (int i = start; i <= n - k + 1; i++) {
            list.add(i);
            helper(res, list, n, k - 1, i + 1);// i + 1 means no duplications
            list.remove(list.size() - 1);
        }
    }
}

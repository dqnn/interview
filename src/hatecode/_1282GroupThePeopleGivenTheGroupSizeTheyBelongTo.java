package hatecode;

import java.util.*;
public class _1282GroupThePeopleGivenTheGroupSizeTheyBelongTo {  
/*
1282. Group the People Given the Group Size They Belong To
There are n people whose IDs go from 0 to n - 1 and each person belongs exactly to one group. Given the array groupSizes of length n telling the group size each person belongs to, return the groups there are and the people's IDs each group includes.

You can return any solution in any order and the same applies for IDs. Also, it is guaranteed that there exists at least one solution. 

 

Example 1:

Input: groupSizes = [3,3,3,3,3,1,3]
Output: [[5],[0,1,2],[3,4,6]]
Explanation: 
Other possible solutions are [[2,1,6],[5],[0,4,3]] and [[5],[0,6,2],[4,3,1]].
*/
    //thinking process: O(n)/O(n)
    
    //the problem is to say, given int[] gz, it means for i = 0->n-1, each i 
    //belong a group, the group size, foe example [3,3,3,3,3,1,3] means 0 belongs to a 
    //group has 3 people, so return a list of list which contains all Ids,
    
    //
    public List<List<Integer>> groupThePeople(int[] gz) {
      List<List<Integer>> res = new ArrayList();
      Map<Integer, List<Integer>> groups = new HashMap<>();
      for (int i = 0; i < gz.length; ++i) {
        List<Integer> list = groups.computeIfAbsent(gz[i], k -> new ArrayList());
        list.add(i);
        if (list.size() == gz[i]) {
          res.add(list);
          groups.put(gz[i], new ArrayList());
        }
      }
      return res;
    }
    
    public List<List<Integer>> groupThePeople_Naive(int[] groupSizes) {
        List<List<Integer>> result = new ArrayList<>();
        int n = groupSizes.length;

        List<Integer>[] arr = new List[n+1];
        for(int i=0;i<n+1;i++) {
            arr[i]=new ArrayList<>();
        }
        for(int i=0;i<n;i++) {
            arr[groupSizes[i]].add(i);
        }
        for(int i=1;i<n+1;i++) {
            if(arr[i].size()>=i) {
                for(int k=0;k<arr[i].size();k+=i) {
                    List<Integer> list = (arr[i].subList(k,k+i));
                    result.add(list);
                }
            }
        }
        return result;
    }
}
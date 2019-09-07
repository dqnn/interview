package hatecode;

import java.util.*;
public class _526BeautifulArrangement {
/*
526. Beautiful Arrangement
Suppose you have N integers from 1 to N. 
We define a beautiful arrangement as an array that is constructed by these 
N numbers successfully if one of the following is true for the ith position 
(1 <= i <= N) in this array:

The number at the ith position is divisible by i.
i is divisible by the number at the ith position.
 

Now given N, how many beautiful arrangements can you construct?

Example 1:

Input: 2
Output: 2
*/
    //thinking process: 
    //given an array, it contains value 1..N, we need to find how many arrays have such arrangement
    //that each value % i == 0 || i % value == 0
    //
    //TODO: O(k)/O(N), k is count of permutation numbers
    int res = 0;
    public int countArrangement_BackTRACKING(int N) {
        if(N == 0) return res;
        helper(N, 1, new boolean[N+1]);
        return res;
    }
    //pos means the position index
    private void helper(int N, int pos, boolean[] visited) {
        if (pos > N) {
            res++;
            return ;
        }
        //here is the loop, we want to test each position can be divisible
        //we mark each position to be used, if not successful, then we rollback
        for(int i =1; i<=N; i++) {
            if (!visited[i] && (i % pos == 0 || pos % i == 0)) {
                visited[i] = true;
                helper(N, pos + 1, visited);
                visited[i] =false;
            }
        }
    }
    //DP
    int dp=0;
    HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
    public int countArrangement(int N) {
        dp=(int)Math.pow(2,N)-1;
        return recurse(N,1);
    }
    public int recurse(int n,int level){
        if(level==n+1)return 1;
        if(map.containsKey(dp)) return map.get(dp);
        int mask=1,count=0;
        for(int i=1;i<=n;i++){
            if((i%level==0||level%i==0)&&(dp&mask)!=0){
                dp^=mask;
                count+=recurse(n,level+1);
                dp|=mask;
            }
            mask<<=1;
        }
        map.put(dp,count);
        return count;
    }
    
    //use memorization to improve the backtracking since there are many overlap subproblems. For example, 
    //N = 10, index 1 choose 4 and 2 choose 8 vs 1 choose 8 and 2 choose 4, 
    //the left subproblems are overlaped.
    //this is improvement
    public int countArrangement_MEMO(int N) {
        char[] currState = new char[N + 1];
        Arrays.fill(currState, 'f');  // f means not selected, t means selected
        return helper(new HashMap<String, Integer>(), currState, 1);
    }
    
    public int helper(Map<String, Integer> map, char[] currState, int index) {
        if(index == currState.length) return 1;
        //this is one interesting way to construct the index
        String key = String.valueOf(currState);
        if(map.containsKey(key)) return map.get(key);
        int res = 0;
        for(int i = 1; i < currState.length; i++) {
            if(currState[i] == 'f' && (i % index == 0 || index % i == 0)) {
                currState[i] = 't';
                res += helper(map, currState, index + 1);
                currState[i] = 'f';
            }
        }
        map.put(key, res);
        return res;
    }
}
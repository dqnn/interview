package hatecode._0001_0999;
import java.util.*;
public class _710RandomPickWithBlacklist {
/*
710. Random Pick with Blacklist
Given a blacklist B containing unique integers from [0, N), write a function to return a uniform random integer from [0, N) which is NOT in B.

Optimize it such that it minimizes the call to system’s Math.random().

Note:

1 <= N <= 1000000000
0 <= B.length < min(100000, N)
[0, N) does NOT include N. See interval notation.
Example 1:

Input: 
["Solution","pick","pick","pick"]
[[1,[]],[],[],[]]
Output: [null,0,0,0]
 */
    int M;
    Random r;
    Map<Integer, Integer> map;
    
    //O(B)/O(B), 
    //so given 0->N-1 number, we have a black list, so we want to random pick number same probability while not in blacklist
    
    //so considering the length M = N -b.length, so rnd.nextInt(M) should give us good indicator where it is. 
    //so suppse N = 5, B =1, and M = 4, think about this way, if there is no blacklist, then nextInt is the answer, 
    //if blacklist contains someInteger, then we have to shift the the nextInt(M), so we have a map to map to next correct answer,
    //to save cost, we do this in the constructor. this is the mechanism, 
    
    //when  call pick. we choose one random number from 0-M-1, you can think this is the index of numbers in 0-N-1, the correct 
    //thinking is that 3->4 while 5->6 next un-blacklist number is the target. 
    // 
    public _710RandomPickWithBlacklist(int N, int[] blacklist) {
        map = new HashMap<>();
        Arrays.stream(blacklist).forEach(e->map.put(e, -1));
        
        M = N - map.size();
        //N=10, blacklist=[3, 5, 8, 9], re-map 3 and 5 to 7 and 6.
        // M = 6,  for 3 and 5, we will map the position originally belong to blacklist then shift to 
        //elements large than M. this is the key
        for (int b : blacklist) { // O(B)
            if (b < M) { // re-mapping
                while (map.containsKey(N - 1)) N--;
                map.put(b, --N);
            }
        }
        
        r = new Random();
    }
    
    public int pick() {
        int p = r.nextInt(M);
        return map.getOrDefault(p, p);
    }
}
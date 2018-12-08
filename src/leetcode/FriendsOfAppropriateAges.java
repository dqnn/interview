package leetcode;
import java.util.*;
public class FriendsOfAppropriateAges {
/*
 825. Friends Of Appropriate Ages
Some people will make friend requests. The list of their ages is 
given and ages[i] is the age of the ith person. 

Person A will NOT friend request person B (B != A) if any of the following 
conditions are true:

age[B] <= 0.5 * age[A] + 7
age[B] > age[A]
age[B] > 100 && age[A] < 100
Otherwise, A will friend request B.

Note that if A requests B, B does not necessarily request A.  Also, people will 
not friend request themselves.

How many total friend requests are made?

Example 1:

Input: [16,16]
Output: 2
Explanation: 2 people friend request each other.
 */
    
    public int numFriendRequests(int[] ages) {
        if (ages == null || ages.length < 1) return 0;
        int res = 0;
        int[] countInAge = new int[121], sumInAge = new int[121];
        
        for(int i : ages) 
            countInAge[i] ++;
        
        for(int i = 1; i <= 120; ++i) 
            sumInAge[i] = countInAge[i] + sumInAge[i - 1];
        ////In order to get at least 1 valid age value from (0.5 * A + 7, A], 
        //A has to be at lease 15.
        for(int i = 15; i <= 120; ++i) {
            if(countInAge[i] == 0) continue;
            int count = sumInAge[i] - sumInAge[i / 2 + 7];
            res += count * countInAge[i] - countInAge[i]; //people will not friend request themselves, so  - numInAge[i]
        }
        return res;
    }
    
    public int numFriendRequests3(int[] ages) {
        int[] ageCount = new int[121];
        int[] ageSum = new int[121];
        for (int i = 0; i < ages.length; i++) {
            ageCount[ages[i]]++;
        }
        int sum = 0;
        int res = 0;
        //In order to get at least 1 valid age value from (0.5 * A + 7, A], 
        //A has to be at lease 15.

        for (int i = 15; i <= 120; i++) {//
            sum += ageCount[i];
            ageSum[i] = sum;
            if (ageCount[i] > 0) {
                res += ((ageSum[i] - ageSum[(int)(i * .5 + 7)] - 1) * ageCount[i]);
            }
        }
        return res;
    }
    
    //TLE solution
    public int numFriendRequests2(int[] ages) {
        if (ages == null || ages.length < 1) return 0;
        Map<String, Integer> map = new HashMap<>();
        int res = 0;
        for(int i = 0; i< ages.length; i++) {
            for(int j = i+1; j< ages.length; j++) {
                res += computeRequest(ages[i], ages[j], map) 
                      + computeRequest(ages[j], ages[i], map);
            }
        }
        return res;
    }
    
    private int computeRequest(int from, int to, Map<String, Integer> map) {
        int res = 0;
        String id = from + "->" + to;
        if (map.containsKey(id)) {
                    res += map.get(id);
        } else {
                    int request = 0;
                    if (to <= 0.5 * from + 7 || to >from
                      || to > 100 && from < 100) {
                        request = 0;
                    } else {
                        request = 1;
                    }
                    map.put(id, request);
                    res += request;
        }
    return res;
}
    
    
}
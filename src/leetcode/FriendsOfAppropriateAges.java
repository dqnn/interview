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
    //interview friendly: 
    //thinking process: given an array which represents age, and we have rules on these ages,
    // like above, so one person will make friend request to another,so how many request totally?
    
    //first it is bruth-force, but would be O(N^2)/O(n), so how can we reduce the time complexity?
    //for person i, we want to know how many people before i he can make friends, and we can sum 
    //them up. 
    public int numFriendRequests(int[] ages) {
        if (ages == null || ages.length < 1) return 0;
       //countInAge means how many peope in age i
        //sumInAge means how many people ages <= i
        int[] countInAge = new int[121], sumInAge = new int[121];
        //frequency on age, like 36, 36, countInAge[36] = 2
        for(int i : ages) 
            countInAge[i] ++;
        ////In order to get at least 1 valid age value from (0.5 * A + 7, A], 
        //A has to be at lease 15.
       //age[B] <= 0.5 * age[A] + 7 this means if anyone under 15, like 14, no one would send 
        //msg to him, one would not make friends who is older and under 100 would not make friends 
        //above 100. 
        int res = 0;
        int sum = 0;
        for(int i = 15; i <= 120; ++i) {
            //we need this to assign value to sumInAge
            sum += countInAge[i];
            sumInAge[i] = sum;
            if(countInAge[i] == 0) continue;
            // [16,16]->countInAge, countInAge[16] = 2, sumInAge[16] = 2
            //so here count = 2 - sumInAge[15] = 2
            //for person i, how many other people for i to make friends
            //sumInAge include i, so this will exclude i/2 + 7
            int count = sumInAge[i] - sumInAge[i / 2 + 7];
          //for each i, countInAge[i]  means how many people in this age i, and 
            //will not friend request themselves, so  - numInAge[i]
            res += count * countInAge[i] - countInAge[i]; 
        }
        return res;
    }
    //follow up: how to improve space complexity, so reduce sumInAge array to sum
    public int numFriendRequests3(int[] ages) {
        int[] countInAge = new int[121];
        int[] sumInAge = new int[121];
        //frequency on ages
        for(int i : ages) 
            countInAge[i] ++;
        int sum = 0;
        int res = 0;
        //In order to get at least 1 valid age value from (0.5 * A + 7, A], 
        //A has to be at lease 15.

        for (int i = 15; i <= 120; i++) {//
            sum += countInAge[i];
            sumInAge[i] = sum;
            if (countInAge[i] > 0) {
                res += ((sumInAge[i] - sumInAge[(int)(i /2 + 7)]) * countInAge[i]) - countInAge[i];
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
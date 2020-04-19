package hatecode._1000_1999;

import java.util.*;
public class _1156SwapForLongestRepeatedCharacterSubstring {
 /*
 1156. Swap For Longest Repeated Character Substring
 Given a string text, we are allowed to swap two of the characters 
 in the string. Find the length of the longest substring with repeated characters.

 

Example 1:

Input: text = "ababa"
Output: 3
 */
    //thinking process: O(n)/O(n)
    
    //the problem is to say: given a string s, if we can swap max 2 chars in s, return 
    //longest substring length in the string, only swap 1 time
    
    //we use a map<char, List<Integer>> to store char<->{1,3} indexes of char, then 
    //for each list, like we removed the different char from string, but keep same left on each 
    //index, so we can see there are 2 cases: 
    //1 whether they are continuous or not? 
    //2 if not contious, like aabaaa, so we need to see if we put a in first b or second b will be 
    //better,so we need to store current max and continue next,
    
    //we use cur to record the length of repeated chars, pre means the previous repeated
    //chars, 
    public static int maxRepOpt1(String s) {
        if(s == null || s.length() < 1) return 0;
        
        HashMap<Character,List<Integer>> map=new HashMap<>();
        for(int i=0;i<s.length();i++){
            map.computeIfAbsent(s.charAt(i), v->new ArrayList<>()).add(i);
        }
        
        int res = 0;
        for (List<Integer> list : map.values()) {
            //pre means previous repeated char segment length
            int cur = 1, pre = 0, sum = 1;
            for (int i = 1; i < list.size(); i++) {
                //adjacent repeated chars
                if (list.get(i) == list.get(i - 1) + 1) cur++;
                else {
                    //there is one char not repeated, we store cur->pre, if not
                    //we update pre->0, cur= 1
                    //for example: aaabaaa, we can know previous segment has 3 chars
                    pre = list.get(i) == list.get(i - 1) + 2 ? cur : 0;
                    cur = 1;
                }
                //in current position, we want to know max length
                sum = Math.max(sum, cur + pre);
            }
            //for string like "ccbbccccc", we will find finally cur = 5, pre = 0, sum = 5
            //but list.size() == 7, so we can swap one to make  longest. 
            res = Math.max(res, sum + (sum < list.size() ? 1 : 0));
        }
        return res;
    }
    
    //sliding window solution
    //thinking process: 
    
    //thinking about a window, 
    /*
     * 1. why sliding window can solve this problem?
     * 2. how to move left and right pointer?
     * 
     * so the purpose of sliding window is: it always seek for longest substring under restrictions,
     * like 
     * when we add a char into winMap means r moves right 
     * when we found winMap has more than 2 distinct chars || 
     */
    public int maxRepOpt1_SlidingWin(String text) {
        int len = text.length();

        int[] dict = new int[26];
        for (int i = 0; i < len; ++i) ++dict[text.charAt(i) - 'a'];

        Map<Character, Integer> winMap = new HashMap<>();
        int res = 0, sizeMoreThanTwo = 0;

        for (int l = 0, r = 0; r < len; ++r) {
            char c = text.charAt(r);

            winMap.put(c, winMap.getOrDefault(c, 0) + 1);
            if (winMap.get(c) == 2) ++sizeMoreThanTwo;
            
            //like ababa, winMap.size() ==2 but we still able to move right, 
            //but when there are 2 chars in map, we want to move l to right
            while (winMap.size() > 2 || sizeMoreThanTwo > 1) {
                //move left pointer, so window size will be less than 1
                c = text.charAt(l++);
                winMap.put(c, winMap.getOrDefault(c, 0) - 1);
                
                if (winMap.get(c) == 1) --sizeMoreThanTwo;
                if (winMap.get(c) == 0) winMap.remove(c);
            }
            
            //means if there are more char in string we can switch for [l, r] range
            for (Character _c : winMap.keySet()) {
                res = (winMap.size() == 1 || dict[_c - 'a'] > winMap.get(_c)) 
                        ? Math.max(res, r - l + 1): Math.max(res, r - l);
            }
        }

        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(maxRepOpt1("aabca"));
    }
}
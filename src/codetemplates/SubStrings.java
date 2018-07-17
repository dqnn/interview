package codetemplates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SubStrings {
    
    
    public List<Integer> slidingWindowTemplate(String s, String t) {
        //init a collection or int value to save the result according the question.
        List<Integer> result = new LinkedList<>();
        if(t.length()> s.length()) return result;
        
        //create a hashmap to save the Characters of the target substring.
        //(K, V) = (Character, Frequence of the Characters)
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        //maintain a counter to check whether match the target string.
        int counter = map.size();//must be the map size, NOT the string size because the char may be duplicate.
        
        //Two Pointers: begin - left pointer of the window; end - right pointer of the window
        int begin = 0, end = 0;
        
        //the length of the substring which match the target string.
        int len = Integer.MAX_VALUE; 
        
        //loop at the begining of the source string
        while(end < s.length()){
            
            char c = s.charAt(end);//get a character
            
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);// plus or minus one
                if(map.get(c) == 0) counter--;//modify the counter according the requirement(different condition).
            }
            end++;
            
            //increase begin pointer to make it invalid/valid again
            while(counter == 0 /* counter condition. different question may have different condition */){
                
                char tempc = s.charAt(begin);//***be careful here: choose the char at begin pointer, NOT the end pointer
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);//plus or minus one
                    if(map.get(tempc) > 0) counter++;//modify the counter according the requirement(different condition).
                }
                
                /* save / update(min/max) the result if find a target*/
                // result collections or result int value
                
                begin++;
            }
        }
        return result;
    }
    
    
    
    
    public List<Integer> findAnagrams(String s, String p) {
       ///We will use sliding window template
       
       ArrayList<Integer> soln = new ArrayList<Integer>();
       
       //Check for bad input
       if (s.length() == 0 || p.length() == 0 || s.length() < p.length()){
           return new ArrayList<Integer>();
       }
       
       //Set up character hash
       //Keep track of how many times each character appears
       int[] chars = new int[26];
       for (Character c : p.toCharArray()){
           //Increment to setup hash of all characters currently in the window
           //Later on, these get DECREMENTED when a character is found
           //A positive count later on means that the character is still "needed" in the anagram
           //A negative count means that either the character was found more times than necessary
           //Or that it isn't needed at all
           chars[c-'a']++;
       }
       
       //Start = start poniter, end = end pointer,
       //len = length of anagram to find
       //diff = length of currently found anagram. If it equals
       //the length of anagram to find, it must have been found
       int start = 0, end = 0, len = p.length(), diff = len;
       
       char temp;
       //Before we begin this, the "window" has a length of 0, start and
       //end pointers both at 0
       for (end = 0; end < len; end++){
           //Process current char
           temp = s.charAt(end);
           
           //As discussed earlier, decrement it
           chars[temp-'a']--;
           
           //If it's still >= 0, the anagram still "needed" it so we count it towards the anagram by
           //decrementing diff
           if (chars[temp-'a'] >= 0){
               diff--;
           }
       }
       
       //This would mean that s began with an anagram of p
       if (diff == 0){
           soln.add(0);
       }
       
       //At this point, start remains at 0, end has moved so that the window is the length of the anagram
       //from this point on we are going to be moving start AND end on each iteration, to shift the window
       //along the string
       while (end < s.length()){
           
           //Temp represents the current first character of the window. The character that is
           //going to be "left behind" as the window moves. 
           temp = s.charAt(start);
           
           //If it's not negative, this means that the character WAS part of the anagram. That means we
           //are one step "farther away" from completing an anagram. So we must increment diff.
           if (chars[temp-'a'] >= 0){
               diff++;
           }
           
           //Increment the hash value for this character, because it is no longer contained in the window
           chars[temp-'a']++;
           
           //Increment start to start shifting the window over by 1
           start++;
           
           //Temp represents the last character of the window, the "new" character from the window shift.
           //This character "replaces" the one we removed before so the window stays the same length (p.length())
           temp = s.charAt(end);
           
           //Decrement hash value for this character, because it is now a part of the window
           chars[temp-'a']--;
           
           //Again, if it's not negative it is part of the anagram. So decrement diff
           if (chars[temp-'a'] >= 0){
               diff--;
           }
           
           //If diff has reached zero, that means for the last p.length() iterations, diff was decremented and
           //NOT decremented, which means every one of those characters was in the anagram, so it must be an anagram
           
           //Note: If many windows in a row find anagrams, then each iteration will have diff incremented then decremented again
           if (diff == 0){
               soln.add(start);
           }
           
           //Increment for next iteration
           end++;
           
       }
       return soln;
    }
}
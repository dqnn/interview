package hatecode._1000_1999;

import java.util.*;
public class _1286CombinationIterator {
/*
 * 1286. Iterator for Combination
Design the CombinationIterator class:

CombinationIterator(string characters, int combinationLength) Initializes the object with a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
next() Returns the next combination of length combinationLength in lexicographical order.
hasNext() Returns true if and only if there exists a next combination.
 

Example 1:

Input
["CombinationIterator", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[["abc", 2], [], [], [], [], [], []]
Output
[null, "ab", true, "ac", true, "bc", false]
*/

    //thinking process: O(n)
    
    //the problem is to say: given one string and one integer, implement a next function,
    //it will return the next combination of lex ordered sequence, which the length is the integer,
    //original string is ordered already. if none, then return null;
    
    
    // we have 3 ways:
    //1. brute force, generate all combination by dfs
    //2. use a stack and map to generate (interview friendly)
    //3 use bitmap, n = string.length, we use one integer, each bit represent we have one character there.
    
    List<String> list = new ArrayList<>();
    int comLen = 0;
    String str = "";
    int curIdx = 0;
    public _1286CombinationIterator(String chs, int comblen) {
        this.comLen = comblen;
        this.str = chs;
        helper(0,0,"");
    }
    
    private void helper(int start, int curLen, String curStr) {
        if (curLen > comLen) {
            return;
        }
        if(curLen == this.comLen) {
            this.list.add(curStr);
            return;
        }
        for(int i = start; i< str.length(); i++) {
            helper(i+1, curLen + 1, curStr + str.charAt(i));
        }
        
    }
    
    public String next() {
        if (hasNext()) return list.get(curIdx++);
        else return null;
    }
    
    public boolean hasNext() {
        if (curIdx < list.size()) return true;
        return false;
    }
    
    //no need for precomputation
    class CombinationIterator_No_precomputation {
        
        Stack<Character> st; // stack to store the characters leading to the creation of a single combination
        Map<Character, Integer> ch2Idx; // map to store character to index
        String result, str; // str - same as characters. result -- the result string representing a combination
        int combLength; // same as combinationLength
     
        public CombinationIterator_No_precomputation(String characters, int combinationLength) {
            combLength = combinationLength;
            ch2Idx = new HashMap<>();
            str = characters;
            st = new Stack<>();
            result = "";
            // create the first combination
            for (Character ch : characters.toCharArray()) {
                st.push(ch);
                result = result + ch;
                if (st.size()==combinationLength) break;
            }
            int idx = 0;
            // set up the mapping between character --> index
            for (Character ch : characters.toCharArray()) {
                ch2Idx.put(ch, idx++);
            }
        }
        
        public String next() {
            String currResult = result;
            // process the next result
          
            int idx = str.length()-1;
            // keep on removing the last character from the stack/result till the position where idx can be moved ahead
            while (!st.isEmpty() && ch2Idx.get(st.peek())==idx) {
                st.pop();
                idx--;
                result = result.substring(0, result.length()-1);  
            }
            if (st.isEmpty()) return currResult; // there is no next result to pre-process
            
            idx = ch2Idx.get(st.pop()); // we need to add elements after this idx
            result = result.substring(0, result.length()-1);
            
            while (st.size()!=combLength) {
                Character temp = str.charAt(++idx);
                result+=temp;
                st.push(temp);
            }
            
            return currResult;
        }
        
        public boolean hasNext() {
            return !st.isEmpty();
        }
    }
    
    //use integer n to represent the character, 
    
    //we start from each digit as 1, then for example, 111-->110, then always,we will output 
    //the more significant position digits just be the same as lex order of characters.
    
    
    class CombinationIterator_Bits {
        String chars;
        int n;
        int bitmask;
        int len;

        public CombinationIterator_Bits(String characters, int combinationLength) {
            
            chars = characters;
            n = characters.length();
            
            //this is to make each digi is 1
            bitmask = (1 << n) - 1;
            len = combinationLength;
                  
        }
        
        public String next() {
            //Maybe this loop is redundant as next() seems to be called on valid value
            while(bitmask >= 0 && Integer.bitCount(bitmask) != len)
                bitmask--;

            StringBuilder curr = new StringBuilder();
            // 1 << (n - 1 - i))) this is to get each digit on every position
            for(int i=0; i<n; ++i){
                if((bitmask & ( 1 << (n - 1 - i))) != 0)
                    curr.append(chars.charAt(i));
            }
            
            bitmask--; // this is important else we get same string each time
            return curr.toString();
            
        }
        
        public boolean hasNext() {
            while(bitmask >= 0 && Integer.bitCount(bitmask) != len)
                bitmask--;
            
            return bitmask >= 0;
        }
    }
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
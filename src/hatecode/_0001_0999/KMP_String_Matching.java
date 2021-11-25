package hatecode._0001_0999;
// JAVA program for implementation of KMP pattern 

import java.util.Arrays;

// substring searching algorithm, this is just for being familiar with KMP
//O(n)
public class KMP_String_Matching {
    /* 
    * Runtime complexity - O(m + n) where m is length of text and n is length of pattern
    * Space complexity - O(n)
    */

       /**
        * brute force
        */
       public boolean hasSubstring(char[] text, char[] pattern){
           int i=0;
           int j=0;
           int k = 0;
           while(i < text.length && j < pattern.length){
               if(text[i] == pattern[j]){
                   i++;
                   j++;
               }else{
                   j=0;
                   k++;
                   i = k;
               }
           }
           if(j == pattern.length){
               return true;
           }
           return false;
       }
       
       /**
        * Compute temporary array to maintain size of suffix which is same as prefix
        * Time/space complexity is O(size of pattern)
        */
       //"abcdabcy"-->[0, 0, 0, 0, 1, 2, 3, 0], 
       //if index and i are all distinct then res are all 0. 
       //suppose we find same as p[index] == p[i] then next[i] = index + 1;
       //next[i] means in 0->i, the longest length of same prefix or suffix
       //
       public static int[] computeNextArray(char pattern[]){
           int[] next = new int[pattern.length];
           int index = 0;
           for(int i=1; i < pattern.length;){
               if(pattern[i] == pattern[index]){
                   next[i] = index + 1;
                   index++;
                   i++;
               } else {
                   //index = 4, next[4-1] = 0, so it rewind to 0
                   //“aaaaaaaaab”, when index = 9, index will rewind to index = 8
                   if(index != 0) index = next[index-1];
                   else{
                       next[i] = 0;
                       i++;
                   }
               }
           }
           return next;
       }
       
       /**
        * KMP algorithm of pattern matching.
        */
       public boolean KMP(char []text, char[] pattern){
           
           int[] next = computeNextArray(pattern);
           int i=0, j=0;
           while(i < text.length && j < pattern.length){
               if (text[i] == pattern[j]) {
                   i++; j++;
               } else {
                   //if not equals then j should retreat to which position
                   if(j!=0) j = next[j-1];
                   else i++;
               }
           }
           if(j == pattern.length) return true;
           return false;
       }
           
       public static void main(String args[]){
           //for example, when 'y' != 'd', next time, it will be 
           // "abcxabcdabcdabcy"
           //         "abcdabcy"
           String str =   "abcxabcdabcdabcy";
           String subString = "abcdabcy";
           //[0, 0, 0, 0, 1, 2, 3, 0]
           System.out.println(Arrays.toString(computeNextArray(subString.toCharArray())));
           System.out.println(Arrays.toString(computeNextArray(new char[] {'a','a','a','a','a','a','a','b'})));
           KMP_String_Matching ss = new KMP_String_Matching();
           boolean result = ss.KMP(str.toCharArray(), subString.toCharArray());
           System.out.print(result);
           
       }
}
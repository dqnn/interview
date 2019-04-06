package hatecode;
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
       //"abcdabcy"-->[0, 0, 0, 0, 1, 2, 3, 0]
       //if index and i are all distinct then res are all 0. 
       //suppose we find same as res[index] == res[i] then 
       public static int[] computeNextArray(char pattern[]){
           int [] res = new int[pattern.length];
           int index = 0;
           for(int i=1; i < pattern.length;){
               if(pattern[i] == pattern[index]){
                   res[i] = index + 1;
                   index++;
                   i++;
               } else {
                   if(index != 0) index = res[index-1];
                   else{
                       res[i] = 0;
                       i++;
                   }
               }
           }
           return res;
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
                   if(j!=0) j = next[j-1];
                   else i++;
               }
           }
           if(j == pattern.length) return true;
           return false;
       }
           
       public static void main(String args[]){
           String str = "abcxabcdabcdabcy";
           String subString = "abcdabcy";
           //[0, 0, 0, 0, 1, 2, 3, 0]
           System.out.println(Arrays.toString(computeNextArray(subString.toCharArray())));
           KMP_String_Matching ss = new KMP_String_Matching();
           boolean result = ss.KMP(str.toCharArray(), subString.toCharArray());
           System.out.print(result);
           
       }
}
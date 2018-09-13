package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ReverseVowelsofaString
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 */
public class ReverseVowelsofaString {
    /**
     * 345. Reverse Vowels of a String
     * Write a function that takes a string as input and 
     * reverse only the vowels of a string.

     Example 1:
     Given s = "hello", return "holle".

     Example 2:
     Given s = "leetcode", return "leotcede".

     time : O(n);
     space : O(n);
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) return s;
        String vowels = "aeiouAEIOU";
        char[] array = s.toCharArray();
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            while (start < end && vowels.indexOf(array[start]) == -1) {
                start++;
            }
            while (start < end && vowels.indexOf(array[end]) == -1) {
                end--;
            }
            char temp = array[start];
            array[start++] = array[end];
            array[end--] = temp;
        }
        return String.valueOf(array);
    }
    // use an array to store the vowels and others are the same 
    //as pervious problems
    public String reverseVowels2(String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        
        int[] idxes = new int[s.length()];
        String vowels = "aeiouAEIOU";
        int idx = 0;
        char[] chs = s.toCharArray();
        for(int i = 0; i< chs.length; i++) {
            if (vowels.indexOf(chs[i]) >= 0) {
                idxes[idx++] = i;
            }
        }
        int left = 0, right = idx - 1;
       while(left < right){
            char temp = chs[idxes[left]];
            chs[idxes[left++]] = chs[idxes[right]];
            chs[idxes[right--]] = temp;
        }
        
        return String.valueOf(chs);
    }
}

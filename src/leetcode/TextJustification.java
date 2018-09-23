package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : TextJustification
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 68. Text Justification
 */
public class TextJustification {
    /**
     * Given an array of words and a length L, format the text such that each line has exactly
     * L characters and is fully (left and right) justified.

     You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
     Pad extra spaces ' ' when necessary so that each line has exactly L characters.

     Extra spaces between words should be distributed as evenly as possible. If the number of spaces on
     a line do not divide evenly between words, the empty slots on the left will be assigned more spaces
     than the slots on the right.

     For the last line of text, it should be left justified and no extra space is inserted between words.

     For example,
     words: ["This", "is", "an", "example", "of", "text", "justification."]
     L: 16.

     Return the formatted lines as:
     [
     "This    is    an",
     "example  of text",
     "justification.  "
     ]

     time : O(n)
     space : O(n)

     * @param words
     * @param L
     * @return
     */
  //thinking process: 
    // the problem is word array and length of each line, so we want to form a list of strings, each string has max width, and 
    //there is spaces between strings, and maybe more spaces than any other strings in on line
    
    // so first we need to know some edge cases, then return list of "" 
    // then we use index as pointer to indicate whther we have reached to the end of the string
    // inside first loop, we have another pointer last to indicate for the result, we have scan from index to last, 
    
    // after inside loop, we want to 
    // detect whthe we reached to end of string w, if yes, then we just add to result and return; 
    //if not the last line, then we need to know how many width we left, use maxWidth - len of string including spaces. 
    // so from here we could know if we append string in w into stringbuilder that's not efficient, so fistly we calc index and last
    // and how many spaces are there, if spacesNUm % num != 0 which means some are more than another so we want to add each space more than another
    
    //with all these above info together, we can try to write the code 
    public List<String> fullJustify(String[] w, int maxWidth) {
        List<String> res = new ArrayList<>();
        if (w == null || w.length < 1 || maxWidth < 1) {
            return res;
        }
        
        int index = 0;
        while(index < w.length) {
            int count = w[index].length();
            int last = index + 1;
            while(last < w.length) {
                // we want to detect the count first so 
                //last is in the correct place
                if (w[last].length() + count + 1 > maxWidth) {
                    break;
                }
                // + 1 because we want to calc the space
                count += 1 + w[last++].length();
            }
            StringBuilder sb = new StringBuilder();
            //each line, we always append first word
            sb.append(w[index]);
            //how many cut we can use for spaces, 
            //last - index means how many words
            //diff = 0 means 1 word
            int diff = last - index - 1;
            if (last == w.length || diff == 0) {
                //since we already append index, so we start from index + 1
                for (int i = index + 1; i < last; i++) {
                    sb.append(" " + w[i]);
                }
                //append rest of all spaces
                for(int i = sb.length(); i < maxWidth; i++) {
                    sb.append(" ");
                }
            } else {
                // if not last line, 
                int spaces = (maxWidth - count) / diff;
                //how many extra space
                int r = (maxWidth - count) % diff;
                for (int i = index + 1; i < last; i++) {
                    //append avg space
                    for(int k = spaces; k > 0; k--) {
                        sb.append(" ");
                    }
                    //we append extra space
                    if (r-- > 0) {
                        sb.append(" ");
                    }
                    //append one more space
                    sb.append(" ");
                    //append the word
                    sb.append(w[i]);
                }
            }
            res.add(sb.toString());
            index = last;
        }
        return res;
    }
}

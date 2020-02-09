package hatecode._0001_0999;

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
  
    //   0   1  2    3
    // "This is an example..."
    //  i=0, j=3, width=8, space=(16-8)/(3-0-1)=4, extra=0
    // ------------------------------------------------------
    //   3      4   5        6
    // "example of text justification."
    //  i=3, j=6, width=13, space=(16-13)/(3-0-1)=1, extra=1
    // ------------------------------------------------------
    // "justification."
    //  i=6, j=7, space=1, extra=0
    
    //this is better solution, less code
    public List<String> fullJustify_Better(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        for (int i = 0, j; i < words.length; ) {
            int width = 0;                                  // width of words without space
            for (j = i; j < words.length && width + words[j].length() + (j - i) <= maxWidth; j++)
                width += words[j].length();                 /* j is the next word */
            
            int space = 1, extra = 0;                       // for last line, space=1
            if (j - i != 1 && j != words.length) {          // not 1 word (div-by-zero) and not last line
                space = (maxWidth - width) / (j - i - 1);   // minus 1 to exclude skip last word
                extra = (maxWidth - width) % (j - i - 1);
            }
            
            StringBuilder line = new StringBuilder(words[i]);
            for (i = i + 1; i < j; i++) {                   // move i to j finally
                for (int s = space; s > 0; s--) line.append(" ");
                if (extra-- > 0) line.append(" ");
                line.append(words[i]);
            }
            for (int s = maxWidth - line.length(); s > 0; s--) line.append(" ");
            result.add(line.toString());
        }
        return result;
    }
    
    //thinking process: 
    // the problem is given string array and length of each line, so we want to form a list of strings, 
    //each string has max width, and 
    //there is spaces between each word, and maybe more spaces than any other strings in on line
    //each word has to be one same line, last line will be left justified
    
    // so first we need to know some edge cases, then return list of "" 
    // then we use index as pointer to indicate whether we have reached to the end of the string
    // inside first loop, we have another pointer last to indicate for the result, we have scan from index to last, 
    
    // after inside loop, we want to 
    // detect whether we reached to end of string w, if yes, then we just add to result and return; 
    //if not the last line, then we need to know how many width we left, use maxWidth - len of string including spaces. 
    // so from here we could know if we append string in w into stringbuilder that's not efficient, so firstly 
    //we calc index and last and how many spaces are there, if spacesNUm % num != 0 which means some 
    //are more than another so we want to add each space more than another
    
    //with all these above info together, we can try to write the code 
    public List<String> fullJustify(String[] w, int maxWidth) {
        List<String> res = new ArrayList<>();
        if (w == null || w.length < 1 || maxWidth < 1) {
            return res;
        }
        //global pointer, word index
        int idx = 0;
        while(idx < w.length) {
            int count = w[idx].length();
            //last word in same line with idx
            int last = idx + 1;
            while(last < w.length) {
                // we want to detect the count first so 
                //last is in the correct place
                if (w[last].length() + count + 1 > maxWidth) {
                    break;
                }
                // + 1 because we want to calc the blank space
                count += 1 + w[last++].length();
            }
            StringBuilder sb = new StringBuilder();
            //each line, we always append first word
            sb.append(w[idx]);
            //how many cut we can use for spaces, 
            //last - index means how many words
            //diff = 0 means 1 word
            int diff = last - idx - 1;
            if (last == w.length || diff == 0) {
                //since we already append index, so we start from index + 1
                for (int i = idx + 1; i < last; i++) {
                    sb.append(" " + w[i]);
                }
                //append rest of all spaces
                for(int i = sb.length(); i < maxWidth; i++) {
                    sb.append(" ");
                }
            //not last line or first line,
            } else {
                // except first word, spaces means how many space does each word have after 
                int spaces = (maxWidth - count) / diff;
                //how many extra space each word has
                int r = (maxWidth - count) % diff;
                for (int i = idx + 1; i < last; i++) {
                    //append avg space
                    for(int k = spaces; k > 0; k--) {
                        sb.append(" ");
                    }
                    //we append extra space
                    if (r-- > 0)  sb.append(" ");
                    
                    //append one more space because previous count already plus 1 space
                    sb.append(" ");
                    //append the word
                    sb.append(w[i]);
                }
            }
            res.add(sb.toString());
            idx = last;
        }
        return res;
    }
}

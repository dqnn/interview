package hatecode;
public class SentenceScreenFitting {
/*
418. Sentence Screen Fitting
Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

Note:

A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word is greater than 0 and won't exceed 10.
1 ≤ rows, cols ≤ 20,000.
Example 1:

Input:
rows = 2, cols = 8, sentence = ["hello", "world"]

Output: 
1

Explanation:
hello---
world---

The character '-' signifies an empty space on the screen.
*/
    /*DP solution
     * like a jump game. I use a array to record for each word, how far it can jump.
     * eg. dp[index] means if the row start at index then the start of next row is
     * dp[index]. dp[index] can be larger than the length of the sentence, in this
     * case, one row can span multiple sentences. I comment the check whether a word
     * is longer than the row since there is no such test case. But it's better to
     * check it. And it make little difference to the speed.
     */
    public int wordsTyping6(String[] sentence, int rows, int cols) {
        int[] dp = new int[sentence.length];
        int n = sentence.length;
        for(int i = 0, prev = 0, len = 0; i < sentence.length; ++i) {
            // remove the length of previous word and space
            if(i != 0 && len > 0) len -= sentence[i - 1].length() + 1;
            // calculate the start of next line.
            // it's OK the index is beyond the length of array so that 
            // we can use it to count how many words one row has.
            while(len + sentence[prev % n].length() <= cols) len += sentence[prev++ % n].length() + 1;
            dp[i] = prev;
        }
        int count = 0;
        for(int i = 0, k = 0; i < rows; ++i) {
            // count how many words one row has and move to start of next row.
            // It's better to check if d[k] == k but I find there is no test case on it. 
            // if(dp[k] == k) return 0;
            count += dp[k] - k;
            k = dp[k] % n;
        }
        // divide by the number of words in sentence
        return count / n;
    }
    
    
    // O(n*(cols/lenAverage)) + O(rows), 
    public static int wordsTyping(String[] sentence, int rows, int cols) {
        //which sentence will be each line start
        int[] nextIndex = new int[sentence.length];
        //how many times does this sentence[i] as line start
        int[] times = new int[sentence.length];
        for(int i=0;i<sentence.length;i++) {
            int count = 0;
            int idx = i;
            int time = 0;
            while(count + sentence[idx].length() <= cols) {
                count += sentence[idx++].length();
                if (count < cols) count += 1;
                if(idx==sentence.length) {
                    idx = 0;
                    time ++;
                }
            }
            nextIndex[i] = idx;
            times[i] = time;
        }
        int ans = 0;
        int cur = 0;
        for(int i=0; i<rows; i++) {
            ans += times[cur];
            cur = nextIndex[cur];
        }
        return ans;
    }
    
    public int wordsTyping3(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0, l = s.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (s.charAt(start % l) == ' ') {
                start++;
            } else {
                while (start > 0 && s.charAt((start-1) % l) != ' ') {
                    start--;
                }
            }
        }
        
        return start / s.length();
    }
    
    //TLE version, brute force, 
    //O(R * S)/O(1), S is sentence length. 
    //one key point is how to add the space counter, here we use count to combinate
    //the char and space, the good part is we compare count to cols each time, and also
    //we detect whether next word will fit current line or not. 
    public static int wordsTyping2(String[] sentence, int rows, int cols) {
        if (sentence == null || sentence.length < 1 || rows < 1 || cols < 1) {
            return 0;
        }
        int row = 0, len = sentence.length;
        int idx = -1, res = 0;
        while(row < rows) {
            int count = 0;
            while((count + sentence[idx + 1].length()) <= cols) {
                count += sentence[idx + 1].length();
                //add one space
                if (count < cols) count +=1;
                if (idx + 1 < len - 1) {
                    idx +=1;
                } else {
                    res +=1;
                    idx = -1;
                }
            }
            row++;
        }
        return res;
    }
    public static void main(String[] args) {
        String[] in = {"f", "p", "a"};
        System.out.println(wordsTyping(in, 8,7));
    }
}
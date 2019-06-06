package hatecode;
public class SentenceScreenFitting {
    /*
     * 418. Sentence Screen Fitting Given a rows x cols screen and a sentence
     * represented by a list of non-empty words, find how many times the given
     * sentence can be fitted on the screen.
     * 
     * Note:
     * 
     * A word cannot be split into two lines. The order of words in the sentence
     * must remain unchanged. Two consecutive words in a line must be separated by a
     * single space. Total words in the sentence won't exceed 100. Length of each
     * word is greater than 0 and won't exceed 10. 1 ≤ rows, cols ≤ 20,000. Example
     * 1:
     * 
     * Input: rows = 2, cols = 8, sentence = ["hello", "world"]
     * 
     * Output: 1
     * 
     * Explanation: hello--- world---
     * 
     * The character '-' signifies an empty space on the screen.
     */
    /*DP solution
     * like a jump game. I use a array to record for each word, how far it can jump.
     * eg. dp[i] means if the row start at index then the start of next row is
     * dp[i].i is  index of sentence, dp[index] can be larger than the length of the sentence, in this
     * case, one row can span multiple sentences. I comment the check whether a word
     * is longer than the row since there is no such test case. But it's better to
     * check it. And it make little difference to the speed.
     */
    public static int wordsTyping6(String[] sentence, int rows, int cols) {
        int[] dp = new int[sentence.length];
        int n = sentence.length;
        //pretty elegant code,
        //len means: 
        //prev: 
        for(int i = 0, prev = 0, len = 0; i < sentence.length; ++i) {
            // remove the length of previous word and space
            if(i != 0 && len > 0) len -= sentence[i - 1].length() + 1;
            // calculate the start index of next line.
            // it's OK the index is beyond the length of array so that 
            // we can use it to count how many words one row has.
            while(len + sentence[prev % n].length() <= cols) 
                len += sentence[prev++ % n].length() + 1;
            
            //prev is next row start index in sentence
            dp[i] = prev;
        }
//dp=[4,5,6], sentence=["a", "b", "c'] rows = 8, cols = 7
        
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
    //this is very special solution, unique perspective,
    //suppose we have big enough rows, then every string in stence have the chance to be 
    //start in lines, so it will be a cycle for the start idx of each line. 
    
    //start from each word, to see whether if we start word words[i], then next line 
    //will start words[words[idx]], 
 /*
 words = [a, b, c], rows = 8, cols = 7
          a   b   c
nextInt   1   2   0
time      1   1   2
a_b_c_a
b_c_a_b
c_a_b_a
a_b_c_a
b_c_a_b
so nextInt will store next line start word index
time will store how many times words has been printed if start as words[i], actually this 
is accumulated results
  */
    public static int wordsTyping(String[] words, int rows, int cols) {
        //which sentence will be each line start
        int[] nextIndex = new int[words.length];
        //how many times does this sentence[i] as line start
        int[] times = new int[words.length];
        for(int i=0;i<words.length;i++) {
            int count = 0;
            int idx = i;
            int time = 0;
            while(count + words[idx].length() <= cols) {
                count += words[idx++].length();
                if (count < cols) count += 1;
                if(idx == words.length) {
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
    //this is to say assume no space in sentence, and we concat them to be one string,
    //cols is like a ruler, we use a ruler to measure the times of the sentence show times,
    //if we detect the end is " " then we continue, if not, then means we measure too much, the 
    //word is truncated, so we retreat back until we meet the space
    public int wordsTyping3(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0, l = s.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            //
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
    
    
    
  //Imagine an infinite sentence that are concatenated by words from the given sentence, 
    //infiStr. We want to cut the infiStr properly and put a piece at each row of the screen.
    //We maintain a pointer ptr. The ptr points to a position at infiStr, where next row 
    //will start. Cutting the infiStr and putting a piece at a row can be simulated as 
    //advancing the pointer by cols positions.
    //After advancing the pointer, if ptr points to a space, it means the piece can 
    //fit in row perfectly. If ptr points to the middle of a word, we must retreat the 
    //pointer to the beginning of the word, because a word cannot be split into two lines.
    //the same as previous one
    public int wordsTyping4(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int[] map = new int[s.length()];
        for(int i=1; i<s.length(); i++){
            if(s.charAt(i) == ' ') map[i] = 1;
            else map[i] = map[i-1]-1;
        }
        int count = 0;
        //s => " hello world "
        //count => 0+8 = 8
        //so now at 8%13 = 8th position in the string is w.. 
        //we cnt put just w in one line and rest next line.. 
        //we fill with spaces thats why we store negative values for that position 
        //in the map.
        for(int i=0; i<rows; i++){
            count += cols;
            count += map[count%s.length()];
        }
        
        //At last, the count represents the length of the repeated lines in which each word is separated by exactly one space. And since the joined sentence s has its words separated by 1 space as well, the number of repeats we can get is count / s.length().
        return count/s.length();
    }
    public static void main(String[] args) {
        String[] in = {"a", "b", "c"};
        System.out.println(wordsTyping6(in, 8,7));
    }
    
    //this question is from Goole interview, similiar to this one so post here:
    /*
     * 已知screen的高和宽，给你最小和最大的fontSize，要求给定一个string，将string用尽可能大的fontSize显示在screen里。
     * 已知两个API getHeight(int fontSize), getWidth(char c, int fontSize)
     * ，可以得到每个character在不同fontSize下的高和宽。和面试官交流后，确认string可以拆分成几行显示在screen中
     * 
     */
      
}
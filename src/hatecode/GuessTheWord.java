package hatecode;

import java.util.*;

public class GuessTheWord {
/*
843. Guess the Word
We are given a word list of unique words, each word is 6 letters long, 
and one word in this list is chosen as secret.

You may call master.guess(word) to guess a word.  The guessed word should 
have type string and must be from the original list with 6 lowercase letters.

This function returns an integer type, representing the number of exact 
matches (value and position) of your guess to the secret word.  Also, 
if your guess is not in the given wordlist, it will return -1 instead.

For each test case, you have 10 guesses to guess the word. At the end of 
any number of calls, if you have made 10 or less calls to master.guess 
and at least one of these guesses was the secret, you pass the testcase.

Besides the example test case below, there will be 5 additional test 
cases, each with 100 words in the word list.  The letters of each 
word in those testcases were chosen independently at random from 'a' to 'z', 
such that every word in the given word lists is unique.

Example 1:
Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]

Explanation:

master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.

We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
 */
    interface Master {
      public int guess(String word);
  }
    //random guess，we tried max=10 times to random choose one in the wordList, 
    //each time, we only call 1 time to get x, how many chars are on same position + same character
    //when we get x, then we will try to scan all words in wordsList, and if other words are have same 
    //'x' mapping then we add to the list and replace the dictionary
    
    //so each time the size of dictionary will become smaller and smaller
    public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
            String guess = wordlist[new Random().nextInt(wordlist.length)];
            x = master.guess(guess);
            List<String> wordlist2 = new ArrayList<>();
            for (String w : wordlist)
                if (match(guess, w) == x) wordlist2.add(w);
            wordlist = wordlist2.toArray(new String[wordlist2.size()]);
        }
    }
    
    private int match(String a, String b) {
        int matches = 0;
        for (int i = 0; i < a.length(); ++i) if (a.charAt(i) == b.charAt(i)) matches ++;
        return matches;
    }
//We start by taking all words as potential candidates. If we guess a word, 
    //we're given its distance from the secret, which allows us to eliminate words 
    //whose distance from the guess is different. That is, if we know that the secret 
    //is 4 characters away from the guess, then we can eliminate all words whose 
    //distance from the guess is not 4, because they can't be the secret.
/*
当一个单词和其他单词match number为0的次数越多，那么这个单词越不好，
因为match number为0时我们减少搜索空间的速度最慢。

假如现在有无限多长度为6的单词，对于word X，和他match number为0的单词有25^6这么多个，
然而和X match number为1的单词则减少到了25^5 * 6这么多个，为2时为 C(6, 2) * 25^4，
以此类推，match number越大我们下一轮的搜索空间会越小，所以这里我们每一轮都挑选出当前搜索空间中和其他
单词match number为0的次数最少的单词作为guess word来猜，这样minimize了每次猜词的worse case。
 */
public void findSecretWord2(String[] wordlist, Master master) {
        List<String> words = new ArrayList<>(Arrays.asList(wordlist));
        
        for (int i = 0; i < 10; i++) {
            String s = pickGuess(words);
            int overlap = master.guess(s);
            if (overlap == 6) return;
            List<String> list = new ArrayList<>();
            for (String word : words) {
                if (match(word, s) == overlap) {
                    list.add(word);
                }
            }
            words = list;
        }
    }
    //every time we choose the highest possibility
    private String pickGuess(List<String> words) {
        int minMaxPeak = Integer.MAX_VALUE;
        String res = "";
        for (String word : words) {
            int curPeak = histogramPeak(word, words);
            if (curPeak < minMaxPeak) {
                minMaxPeak = curPeak;
                res = word;
            }
        }
        return res;
    }
    //we want to know for word in wordlist, the biggest times match 
    private int histogramPeak(String word, List<String> words) {
        //to show for each word, 
        int[] hist = new int[7];
        int maxPeak = 0;
        for (String s : words) {
            //to check each word 's biggest overlap
            int match = match(s, word);
            hist[match]++;
            maxPeak = Math.max(maxPeak, hist[match]);
        }
        return maxPeak;
    }
    //O(n)/O(n)
    //thinking process:
    //we want to reduce the guess time, so every time we need to pick the string which has highest 
    //probability the secret. 
    //so we pre-process the wordlist, we translate the list into dictionary of chars
    //we build the dictionary with all chars, the value is the count of 'a', 'b' like..
    
    //the key is to reduce the search space: first it will be 26^6 strings, after one guess, suppose 
    //we have a number 2, C(6,2) * 26^4, 
    //and we believe the higer character count, the higher probability showed up in strings
    
    //then start with full set of strings, pick() always pick the best string, the best string is:
    //same match count with previous guess, and with highest character count among all these strings. 
    public void findSecretWord_Best(String[] wordlist, Master master) {
        int[] dict = new int[26];
          Set<String> set = new HashSet<>();
          for(String str: wordlist) {
              set.add(str);
              for(char c: str.toCharArray()) {
                  dict[c-'a']++;
              }
          }

          for(int i=0; i<10; i++) {
              String guess = pick(set, dict);
              int x = master.guess(guess);
              if (x == 6) return;
              
              Iterator<String> it = set.iterator();
              while(it.hasNext()) {
                  if(match(guess, it.next()) != x) it.remove();
              }
          }
   }
   
    private String pick(Set<String> list, int[] dict) {
        int max = 0;
        String res = null;
        for(String str: list) {
            int temp = 0;
            for(char c: str.toCharArray()) {
                temp += dict[c-'a'];
            }
            if(temp>max) {
                max = temp;
                res = str;
            }
        }
        return res;
    }
    
    //modified version from goo interview doc, not from LC
    /*
     * 给一个矩阵，在里面找一个gift，不知道gift坐标，然后每次pick一个点，告诉你是更近了还是更远了还是距离不变,
     * 距离是曼哈顿距离，要求用最少的猜测次数找到gift
     * 
     * 思路：KD tree算法
     * 从（0，0）开始，先二分列坐标，看(0, 4)，如果04比00近，
     * 说明gift在j = 2的右边矩阵中，如果04比00远，在j = 2的左边矩阵中，
     * 如果相等，说明gift在j=2这条线上一直二分到 l == r找到gift所在列, 然后二分行坐标，直到找到gift
     */
    private int guess(int i1, int j1, int i2, int j2) {
        int x = 3;
        int y = 1;
        int dist1 = Math.abs(i1 - x) + Math.abs(j1 - y);
        int dist2 = Math.abs(i2 - x) + Math.abs(j2 - y);
        return dist1 - dist2;
    }
    public void guessGift(int rows, int cols) {
        int left = 0, right = cols - 1;
        while (left < right) {
            int res = guess(0, left, 0, right);
            int mid = left + (right - left) / 2;
            if(res == 0) {
                left = mid;
                break;
            } else if(res < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        int top = 0, bot = rows - 1;
        while (top < bot) {
            int mid = top + (bot - top) / 2;
            int res = guess(top, left, bot, left);
            if(res == 0) {
                break;
            } else if(res < 0) {
                bot = mid  - 1;
            } else {
                top = mid + 1;
            }
        }
        System.out.println(top + ", " + left);
    }

}
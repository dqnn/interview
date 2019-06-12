package hatecode;

import java.util.*;
public class GoogleLyricsToWords {
/*
 * from Google interview doc, not from LC
 * 给两个 string 分别是lyric 和 word，问能不能用lyric 构成 word
比如 lyric "HIT ME BABY ONE MORE TIME" word = "BOOM" 那么是可以的
 */
    //thinking process: 
    //Map<Character, TreeSet<Integer>>， key is char, value is index in a treeSet
    private List<String> isSub(String a, String[] words) {
        Map<Character, TreeSet<Integer>> map = new HashMap<>();
        //split by space
        String[] src = a.split("\\s+");
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < src.length; i++) {
            for (char ch : src[i].toCharArray()) {
                map.computeIfAbsent(ch, v->new TreeSet<>()).add(i);
            }
        }

        for (String word : words) {
            int prev = -1;
            char[] charArray = word.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                if (!map.containsKey(charArray[i])) {
                    break;
                }
                TreeSet<Integer> set = map.get(charArray[i]);
                if (set.ceiling(prev) != null) {
                    if (i == charArray.length - 1) res.add(word);
                    //update previous index
                    prev = set.ceiling(prev) + 1;
                } else break;
            }
        }
        return res;

    }
}

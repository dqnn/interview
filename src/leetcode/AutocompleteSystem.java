package leetcode;
import java.util.*;

/*
642. Design Search Autocomplete System
Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, then just return as many as you can.
When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
Your job is to implement the following functions:

The constructor function:

AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.

Now, the user wants to input a new sentence. The following function will provide the next character the user types:

List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.


Example:
Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], 
[5,3,2,2]) 
The system have already tracked down the following sentences and their corresponding times: 
"i love you" : 5 times 
"island" : 3 times 
"ironman" : 2 times 
"i love leetcode" : 2 times 
Now, the user begins another search: 

Operation: input('i') 
Output: ["i love you", "island","i love leetcode"] 
Explanation: 
There are four sentences that have prefix "i". Among them, "ironman" and 
"i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' 
has ASCII code 114, "i love leetcode" should be in front of "ironman". 
Also we only need to output top 3 hot sentences, so "ironman" will be ignored. 

Operation: input(' ') 
Output: ["i love you","i love leetcode"] 
Explanation: 
There are only two sentences that have prefix "i ". 

Operation: input('a') 
Output: [] 
Explanation: 
There are no sentences that have prefix "i a". 

Operation: input('#') 
Output: [] 
Explanation: 
The user finished the input, the sentence "i a" should be saved as a 
historical sentence in system. And the following input will be counted 
as a new search. 

 */
class AutocompleteSystem {
    //mapping setup, sentence, times, arr index is first character offset on 'a'
    HashMap <String, Integer> [] arr;
    class Node {
        Node(String st, int t) {
            sentence = st;
            times = t;
        }
        String sentence;
        int times;
    }
    //setup the mapping 
    public AutocompleteSystem(String[] sentences, int[] times) {
        arr = new HashMap[26];
        for (int i = 0; i < 26; i++)
            arr[i] = new HashMap<>();
        //for each  chatacter in sentence, if duplicate one ,then it would override
        for (int i = 0; i < sentences.length; i++)
            arr[sentences[i].charAt(0) - 'a'].put(sentences[i], times[i]);
    }
    
    String cur_sent = "";
    public List<String> input(char c) {
        List < String > res = new ArrayList < > ();
        if (c == '#') {
            arr[cur_sent.charAt(0) - 'a'].put(cur_sent, arr[cur_sent.charAt(0) - 'a'].getOrDefault(cur_sent, 0) + 1);
            cur_sent = "";
        } else {
            List <Node> list = new ArrayList<>();
            cur_sent += c;
            for (String key: arr[cur_sent.charAt(0) - 'a'].keySet()) {
                if (key.indexOf(cur_sent) == 0) {
                    list.add(new Node(key, arr[cur_sent.charAt(0) - 'a'].get(key)));
                }
            }
            //result sort
            Collections.sort(list, (a, b) -> a.times == b.times ? 
                    a.sentence.compareTo(b.sentence) : b.times - a.times);
            for (int i = 0; i < Math.min(3, list.size()); i++)
                res.add(list.get(i).sentence);
        }
        return res;
    }
    
    //V2 auto complete
    public class AutocompleteSystemV2 {
        class TrieNode {
            Map<Character, TrieNode> children;
            Map<String, Integer> counts;
            boolean isWord;
            public TrieNode() {
                children = new HashMap<Character, TrieNode>();
                counts = new HashMap<String, Integer>();
                isWord = false;
            }
        }
        
        class Pair {
            String s;
            int c;
            public Pair(String s, int c) {
                this.s = s; this.c = c;
            }
        }
        
        TrieNode root;
        String prefix;
        
        
        public AutocompleteSystemV2(String[] sentences, int[] times) {
            root = new TrieNode();
            prefix = "";
            
            for (int i = 0; i < sentences.length; i++) {
                add(sentences[i], times[i]);
            }
        }
        
        private void add(String s, int count) {
            TrieNode curr = root;
            for (char c : s.toCharArray()) {
                TrieNode next = curr.children.get(c);
                if (next == null) {
                    next = new TrieNode();
                    curr.children.put(c, next);
                }
                curr = next;
                curr.counts.put(s, curr.counts.getOrDefault(s, 0) + count);
            }
            curr.isWord = true;
        }
        
        public List<String> input(char c) {
            if (c == '#') {
                add(prefix, 1);
                prefix = "";
                return new ArrayList<String>();
            }
            
            prefix = prefix + c;
            TrieNode curr = root;
            for (char cc : prefix.toCharArray()) {
                TrieNode next = curr.children.get(cc);
                if (next == null) {
                    return new ArrayList<String>();
                }
                curr = next;
            }
            
            PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> (a.c == b.c ? a.s.compareTo(b.s) : b.c - a.c));
            for (String s : curr.counts.keySet()) {
                pq.add(new Pair(s, curr.counts.get(s)));
            }

            List<String> res = new ArrayList<String>();
            for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
                res.add(pq.poll().s);
            }
            return res;
        }
    }
}


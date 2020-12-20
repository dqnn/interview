package hatecode._0001_0999;
import java.util.*;
public class AutocompleteSystemV2 {

/*
 * the problem is to say: given a list of popular search query with its
 * frequency(times), return the queries which prefix match.
 * 
 * for examples, current we have this and that in the data base, then when we type
 * 't'--> this, that
 * 'th' -> this, that
 * 'thi'-> this
 * 'thit'->''
 */
class TrieNode {
    Map<Character, TrieNode> children;
    Map<String, Integer> counts;
    boolean isWord;
    public TrieNode() {
        //each char-> TrieNode
        children = new HashMap<Character, TrieNode>();
        counts = new HashMap<String, Integer>();
        isWord = false;
    }
}

class Pair {
    //sentence
    String s;
    //count
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
    // i love--> i's map tp get TrieNode for each character, we setup the trieNode tree
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
        //add this word into the trieNode
        add(prefix, 1);
        prefix = "";
        return new ArrayList<>();
    }
    
    prefix = prefix + c;
    TrieNode curr = root;
    //add to TrieNode tree, for the new input like i l, we dfs to the 
    //deepest node
    for (char cc : prefix.toCharArray()) {
        TrieNode next = curr.children.get(cc);
        if (next == null) {
            return new ArrayList<String>();
        }
        curr = next;
    }
    //add the New Pair into the heap,current all possible result, it is sorting
    PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> 
    (a.c == b.c ? a.s.compareTo(b.s) : b.c - a.c));
    for (String s : curr.counts.keySet()) {
        pq.add(new Pair(s, curr.counts.get(s)));
    }
    
    //only need first 3
    List<String> res = new ArrayList<String>();
    for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
        res.add(pq.poll().s);
    }
    return res;
}
public static void main(String[] args) {
    String[] in = {"this", "that"};
    AutocompleteSystemV2 V2 = new AutocompleteSystemV2(in, new int[] {1,2});
}
}

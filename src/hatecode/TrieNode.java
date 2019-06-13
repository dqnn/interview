package hatecode;

public class TrieNode {

    TrieNode[] children;
    boolean isWord;
    String word;

    public TrieNode() {
        children = new TrieNode[26];
        isWord = false;
        word = "";
    }
}

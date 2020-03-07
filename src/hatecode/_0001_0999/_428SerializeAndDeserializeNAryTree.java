package hatecode._0001_0999;
import java.util.*;
public class _428SerializeAndDeserializeNAryTree {
/*
428. Serialize and Deserialize N-ary Tree
Serialization is the process of converting a data structure or object into a sequence 
of bits so that it can be stored in a file or memory buffer, or transmitted across 
a network connection link to be reconstructed later in the same or another computer 
environment.

Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a 
rooted tree in which each node has no more than N children. There is no restriction 
on how your serialization/deserialization algorithm should work. You just need to 
ensure that an N-ary tree can be serialized to a string and this string can be 
deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree
*/

    // Encodes a tree to a single string.
    public String serialize2(Node root) {
        if (root == null) return "";
        List<String> list = new ArrayList<>();
        helper(root, list);
        return String.join(",", list);
    }
    
    private void helper(Node node, List<String> list) {
        if (node == null) return;
        list.add(node.val + "");
        int size = node.children == null ? 0 : node.children.size();
        list.add(size + "");
        for(int i = 0; i< size; i++) {
            helper(node.children.get(i), list);
        }
            
    }

    // Decodes your encoded data to tree.
    //[1,3,3,2,5,0,6,0,2,0,4,0]
    public Node deserialize2(String data) {
        if (data == null || data.length() <3) return null;
        String[] strs = data.split(",");
        Queue<String> q = new LinkedList<>(Arrays.asList(strs));
        return deHelper(q);
    }
    
    private Node deHelper(Queue<String> q) {
        int val = Integer.valueOf(q.poll());
        int size = Integer.valueOf(q.poll());
        Node node = new Node(val, new ArrayList<>());
        
        for(int i = 0; i< size;i++) {
            node.children.add(deHelper(q));
        }
        return node;
    }
    
    //stack solution, think about why i didnot write the code with this idea
    public String serialize(Node root) {
        return serialize(root, new StringBuilder());
    }
    //5[1[2,3]4[]]
    String serialize(Node root, StringBuilder sb) {
        if (root == null) return "";
        else {
            sb.append(root.val + "[");
            for (Node child : root.children) serialize(child, sb);
            sb.append("]");
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    //this is pretty good solution, compressed
    public Node deserialize(String data) {
        int i = 0, len = data.length();
        Node root = null;
        Stack<Node> stack = new Stack<>();
        while (i < len) {
            int start = i;
            while (i < len && Character.isDigit(data.charAt(i))) i++;
            //means it is not digit, it is [ or ]
            if (start == i) {
                Node child = stack.pop();
                if (stack.isEmpty()) {
                    root = child;
                    break;
                }
                //in this case, current node is previous child
                stack.peek().children.add(child);
            } else
                stack.push(new Node(Integer.valueOf(data.substring(start, i)), new ArrayList<Node>()));
            i++;
        }
	return root;
    }
}
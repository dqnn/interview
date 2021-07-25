package hatecode._1000_1999;

import java.util.Arrays;

public class OA_BinaryAutoComplete {

    //OA 
    //the problem is to say: O
    //given list of string as commands, return each command index which has longest 
    //common prefix, for example,
    //["000", "1110", "01", "001", "110", "11"]
    //"000" since no command previous, so it is 0
    //"1110", no common prefix, it would show last command, so it return 1, 1st command
    //"01", shared "0" with 1st one, so this is 1. but itself will be 3
    //"001", shared "00" with 1st one, return 1, but itself will be 4
    //"110", shared with "11" with 2nd command, so it will return 2. itself will be 5.
    //"11", shared with "11" with 2nd and 5th command, but choose recent one, so it will be 5, itself will be 6 command, 
    
    //it would return [0,1,1,1,2,5]
    
    
    class BNode{
        int lastIndex = -1;
        BNode l;
        BNode r;
        char c;
        
        BNode(char ch){
            this.c = ch;
        }
        BNode(){}
    }
    private BNode root = new BNode();
    int[] getCommandIndexArray(String[] cds) {
        if (cds == null || cds.length < 1) return new int[0];
        
        int[] res = new int[cds.length];
        for(int i = 0; i < cds.length; i++) {
            build(res, i, cds[i]);
        }
        
        return res;
    }
    
    
     void build(int[] res, int idx, String cmd) {
        if (cmd == null || cmd.length() < 1) return;
        
        BNode curNode = root;
        
        boolean noMatch = false;
        for(int i = 0; i<cmd.length(); i++) {
            char c = cmd.charAt(i);
            if(i == 0 && (curNode.l == null && c =='0' 
                       || curNode.r == null && c =='1')){
                           noMatch = true;
                       }
            
            if (c == '0') {
                if (curNode.l == null) curNode.l = new BNode(c);
                curNode = curNode.l;
            } else {
                if (curNode.r == null) curNode.r = new BNode(c);
                curNode = curNode.r;
            }
            //always update the idx
            if (noMatch) res[idx] = idx;
            else if (curNode.lastIndex != -1) res[idx]= curNode.lastIndex;
            curNode.lastIndex=idx + 1;
        }
    }
    public static void main(String[] args) {
        OA_BinaryAutoComplete t = new OA_BinaryAutoComplete();
        String[] input = {"000", "1110", "01", "001", "110", "11"};
        System.out.println(Arrays.toString(t.getCommandIndexArray(input)));
    }

}

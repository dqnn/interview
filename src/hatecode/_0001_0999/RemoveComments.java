package hatecode._0001_0999;
import java.util.*;
public class RemoveComments {
/*
722. Remove Comments
*/
    //thinking process: 
    //given C++ source code as string array, remove all comments and return the source code as arraylist
    
    //TODO: finish the other 5 cases
    public List<String> removeComments2(String[] src) {
        List<String> res = new ArrayList<>();
        if (src == null || src.length < 1) return res;
        boolean isStarExist = false;
        for(String source : src) {
            //we have 2^3 cases
            int startIndex = source.indexOf("\\/\\*"), comIndex = source.indexOf("\\/\\/"), endStart = source.indexOf("\\*\\/");
            if (isStarExist) continue;
            else {
                if (startIndex < 0 && comIndex < 0 && endStart < 0) {
                    res.add(source);
                } else if (startIndex >= 0 && comIndex < 0 && endStart < 0) {
                    res.add(source.substring(0, startIndex));
                } else if (startIndex >= 0 && comIndex >=0  && endStart < 0) {
                    if (startIndex > comIndex) {
                        
                    }
                }
            }
        }
        return res;
    }
    //workable solutions.so this solution does not utilize indexOf, but indexOf should be better solution
    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();     
        boolean mode = false;
        for (String s : source) {
            for (int i = 0; i < s.length(); i++) {
                if (mode) {
                    if (s.charAt(i) == '*' && i < s.length() - 1 && s.charAt(i + 1) == '/') {
                        mode = false;
                        i++;        //skip '/' on next iteration of i
                    }
                } else {
                    //ignore remaining characters on line s
                    if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '/')  
                        break;     
                    else if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '*') {
                        mode = true;
                        //skip '*' on next iteration of i
                        i++;          
                    }
                    else sb.append(s.charAt(i));     //not a comment
                }
            }
            if (!mode && sb.length() > 0) {
                res.add(sb.toString());
                sb = new StringBuilder();   //reset for next line of source code
            }
        }
        return res;
    }
}
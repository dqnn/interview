package hatecode;
public class DesignCompressedStringIterator {
/*
604. Design Compressed String Iterator
Example:

StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

iterator.next(); // return 'L'
iterator.next(); // return 'e'
iterator.next(); // return 'e'
iterator.next(); // return 't'
iterator.next(); // return 'C'
iterator.next(); // return 'o'
iterator.next(); // return 'd'
iterator.hasNext(); // return true
iterator.next(); // return 'e'
iterator.hasNext(); // return false
iterator.next(); // return ' '
*/
    
    String res;
    //idx is index in comparess string, num is how many character left 
    int idx = 0, num = 0;
    //point to current character
    char ch = ' ';
    public void StringIterator(String s) {
        res = s;
    }
    public char next() {
        if (!hasNext())
            return ' ';
        if (num == 0) {
            ch = res.charAt(idx++);
            while (idx < res.length() && Character.isDigit(res.charAt(idx))) {
                num = num * 10 + res.charAt(idx) - '0';
                idx++;
            }
        }
        num--;
        return ch;
    }
    //idx could reach len -1 but cannot len -2 
    public boolean hasNext() {
        return idx < res.length() || num > 0;
    }
}
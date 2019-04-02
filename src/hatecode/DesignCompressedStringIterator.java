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
    int idx = 0, num = 0;
    char ch = ' ';
    public DesignCompressedStringIterator(String s) {
        res = s;
    }
    public char next() {
        if (!hasNext())
            return ' ';
        if (num == 0) {
            ch = res.charAt(idx++);
            while (idx < res.length() && Character.isDigit(res.charAt(idx))) {
                num = num * 10 + res.charAt(idx++) - '0';
            }
        }
        num--;
        return ch;
    }
    public boolean hasNext() {
        return idx != res.length() || num != 0;
    }
}

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
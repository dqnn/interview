package hatecode;
public class _1056ConfusingNumber {
/*
1056. Confusing Number
Given a number N, return true if and only if it is a confusing number, which satisfies the following condition:

We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid. A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
Input: 6
Output: true
*/
    //thinking process: 
    //the problem is to judge whether a number is a confusing number or not
    public boolean confusingNumber(int n) {

        char[] ch = String.valueOf(n).toCharArray();

        boolean isDifferent = false;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '2' || ch[i] == '3' || ch[i] == '4' || ch[i] == '5' || ch[i] == '7') {
                return false;
            }
            if ((ch[i] == '6' && ch[ch.length - 1 - i] != '9') || (ch[i] == '9' && ch[ch.length - 1 - i] != '6')
                    || (ch[i] == '0' && ch[ch.length - 1 - i] != '0') || (ch[i] == '1' && ch[ch.length - 1 - i] != '1')
                    || (ch[i] == '8' && ch[ch.length - 1 - i] != '8')) {
                isDifferent = true;
            }
        }

        return isDifferent;
    }
    
    public boolean confusingNumber_Iteravtive(int N) {
        int temp = N;
        if (temp == 0) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        while (temp != 0) {
            int digit = temp % 10;
            if (digit == 1) {
                sb.append(1);
            } else if (digit == 6) {
                sb.append(9);
            } else if (digit == 9) {
                sb.append(6);
            } else if (digit == 0) {
                sb.append(0);
            } else if (digit == 8) {
                sb.append(8);
            } else {
                return false;
            }
            temp /= 10;
        }
        return Integer.parseInt(sb.toString()) != N;
        
    }
}
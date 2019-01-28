package hatecode;
public class SolveTheEquation {
/*
640. Solve the Equation
Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.

If there is no solution for the equation, return "No solution".

If there are infinite solutions for the equation, return "Infinite solutions".

If there is exactly one solution for the equation, we ensure that the value of x is an integer.
*/
    //thinking process:
    //we split the string left right by =
    //then we split left or right by - using replace "+-" so we can use Integer.parse
    //and we use left to substract right, then get the answer
    public String solveEquation(String equation) {
        if (equation == null || equation.length() < 3) {
            return "No solution";
        }
        String[] equations = equation.split("=");
        String[] l = equations[0].replaceAll("-", "+-").split("\\+");
        String[] r = equations[1].replaceAll("-", "+-").split("\\+");
        //sum[0] means x's coefficient, sum[1] means other digits in the equation
        int[] sum = new int[2];
        for (String s : l) {
            if (s.length() < 1) {
                continue;
            } else if (s.equals("x")) {
                ++sum[0];
            } else if (s.equals("-x")) {
                --sum[0];
            } else if (s.charAt(s.length()-1) == 'x') {
                sum[0] += Integer.parseInt(s.substring(0, s.length() - 1));
            } else {
                sum[1] += Integer.parseInt(s);
            }
        }
        for (String s : r) {
            if (s.length() < 1) {
                continue;
            } else if (s.equals("x")) {
                --sum[0];
            } else if (s.equals("-x")) {
                ++sum[0];
            } else if (s.charAt(s.length()-1) == 'x') {
                sum[0] -= Integer.parseInt(s.substring(0, s.length() - 1));
            } else {
                sum[1] -= Integer.parseInt(s);
            }
        }
        if (sum[0] == 0) {
            return sum[1] == 0 ? "Infinite solutions" : "No solution";
        }
        //it is -sum[1] we think right sum[1] is correct
        return "x=" + String.valueOf(-sum[1] / sum[0]);
    }
}
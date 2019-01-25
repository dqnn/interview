package hatecode;

public class TaxCaculationFromInterview {
    // 10000, 5000 0.1, 7500, 0.2  750.2
   
    //the problem is calculate the tax by segmenets, so understand each segement is important and 
    // know if we only 1 segment how to handle the edge case, like only one 
    // segment, so we need to check last element so we can get correct answer
    public static double getTax(String income, String[] taxBrackets) {
        double res = 0.0;
        double wage = Double.valueOf(income);
        for(int i = 0; i< taxBrackets.length - 1;i++) {
            String[] cur = taxBrackets[i].split(" ");
            String[] next = taxBrackets[i + 1].split(" ");
            
            double curStart = Double.valueOf(cur[0]);
            double curRate = Double.valueOf(cur[1]);
            double nextStart = Double.valueOf(next[0]);
            double nextRate = Double.valueOf(next[1]);
            
            if (wage < curStart) {
                return res;
            } else if (wage < nextStart) {
                res += curRate * (wage - curStart + 1);
            } else if (wage >= nextStart) {
                res +=  curRate * (nextStart - curStart);
            }
            
            if (i == taxBrackets.length - 2) {
                if (wage >= nextStart) {
                    res += nextRate * (wage - nextStart + 1);
                }
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(getTax("10000", new String[] {"5000 0.1", "7500 0.2"}));

    }

}

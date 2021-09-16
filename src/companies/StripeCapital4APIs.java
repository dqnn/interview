package companies;
import java.util.*;
public class StripeCapital4APIs {
    
/*
 * 4个api，
CREATE_LOAN, ex: merchant_1, loan_1, 1000
PAY_LOAN, ex merchant_1, loan1, 500
INCREASE_LOAN, ex merchant_1, loan2, 1200
TRANSACTION_PROCESS, ex merchant_1, loan1, 1000, 10(This will pay reduce loan1 by 100. (1000 * 10%)
然后把所有人的欠款都加起来然后print出来，
顺序按merchant的lexigraphical order

认真读题，有一些edge case要处理，比如，merchant2没出现过，但是要increase loan，这个直接ignore就好了
Overpay a loan d‍‍‌‍‍‌‍‌‍‍‌‍‌‌‌‍‍‍‍oesn't reduce other loans, etc.
还有，TRANSACTION_PROCESS算完要percentage之后要truncate，比如21 * 5% = 1.05, 这个就直接round down
 */
    private static Map<String, Map<String, Integer>> map = new HashMap<>();
    public static void CREATE_LOAN(String merchant, String loanId, String amount) {
        int loanAmount = Integer.valueOf(amount);
        if (!map.containsKey(merchant)) {
            map.computeIfAbsent(merchant, v->new HashMap<>()).put(loanId, loanAmount);
        } else {
            int newAmount = map.get(merchant).getOrDefault(loanId, 0) + loanAmount;
            map.get(merchant).put(loanId, newAmount);
        }
    }
    public static boolean IsMerchantOrLoanExist(String merchant, String loanId) {
        if (!map.containsKey(merchant) || !map.get(merchant).containsKey(loanId)) {
            return false;
        }
        return true;
    }
    public static void PAY_LOAN(String merchant, String loanId, String amount) {
        if (!IsMerchantOrLoanExist(merchant, loanId)) {
            return;
        }
        int payDebt = Integer.valueOf(amount);
        int loanleft = map.get(merchant).get(loanId);
        if (loanleft <= payDebt) {
            map.get(merchant).put(loanId, 0);
        } else {
            map.get(merchant).put(loanId, loanleft - payDebt);
        }
    }
    public static void INCREASE_LOAN(String merchant, String loanId, String amount) {
        if (!IsMerchantOrLoanExist(merchant, loanId)) {
            return;
        }
        int newDebt = Integer.valueOf(amount);
        int loanleft = map.get(merchant).get(loanId);
        map.get(merchant).put(loanId, loanleft + newDebt);
    }
    
    public static void TRANSACTION_PROCESS(String merchant, String loanId, String amount, String percent) {
        if (!IsMerchantOrLoanExist(merchant, loanId)) {
            return;
        }
        int loanleft = map.get(merchant).get(loanId);
        int paydebt = (int)(Integer.valueOf(amount) * Integer.valueOf(percent)/100.0);
        if (loanleft <= paydebt) {
            map.get(merchant).put(loanId, 0);
        } else {
            map.get(merchant).put(loanId, loanleft - paydebt);
        }
    }
    
    
    public static void main(String[] args) {
        String[] in = {
                "CREATE_LOAN : acct_foobar, loan1, 1000", 
                "CREATE_LOAN : acct_foobar, loan2, 2000",
                "CREATE_LOAN : acct_barfoo, loan1, 3000",
                "TRANSACTION_PROCESS: acct_foobar,loan1, 100, 1",
                "PAY_LOAN : acct_barfoo, loan1, 1000", 
                "INCREASE_LOAN: acct_foobar,loan2,1000"
        };
        
        for(String str: in) {
            String[] temp = str.split(":");
            String api = temp[0].trim();
            String[] detail = 
                    Arrays.stream(temp[1].split(",")).map(String::trim).toArray(String[]::new);
            
            switch(api) {
            case "CREATE_LOAN": 
                CREATE_LOAN(detail[0], detail[1], detail[2]);
                break;
            case "PAY_LOAN":
                PAY_LOAN(detail[0], detail[1], detail[2]);
                break;
            case "INCREASE_LOAN":
                INCREASE_LOAN(detail[0], detail[1], detail[2]);
                break;
            case "TRANSACTION_PROCESS":
                TRANSACTION_PROCESS(detail[0], detail[1], detail[2], detail[3]);
                break;
            default:
                System.out.println("Error, should not reach here");
            }
            //System.out.println(map);
        }
       Map<String, Integer> res =new HashMap<>();
        for(String merchant : map.keySet()) {
            for(String loanId: map.get(merchant).keySet()) {
                res.put(merchant, res.getOrDefault(merchant, 0) +  map.get(merchant).get(loanId));
            }
        }
        
        System.out.println(res);
    }

}

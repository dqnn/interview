package hatecode;
import java.util.*;
public class UniqueEmailAddresses {
    public int numUniqueEmails(String[] emails) {
        if (emails == null || emails.length < 1) return 0;
        Set<String> res = new HashSet<>();
        for(String email: emails) {
            int atIdx = email.indexOf("@");
            if (atIdx == -1) continue;
            String newEmail  = email.substring(0, atIdx).replaceAll(".","");
            int plusIdx = -1;
            for(int i = 0; i < newEmail.length();i++) {
                if (newEmail.charAt(i) == '+') {
                    plusIdx = i;
                    break;
                }
            }
            
            if (plusIdx != -1) {
                res.add(newEmail.substring(0, plusIdx) + email.substring(atIdx));
            } else {
                res.add(newEmail + email.substring(atIdx));
            }
        }
        return res.size();
    }
    
    //another solution
    public int numUniqueEmails2(String[] emails) {
        Set<String> normalized = new HashSet<>();
        for (String email : emails) {
            String[] parts = email.split("@"); // split into local and domain parts.
            String[] local = parts[0].split("\\+"); // split local by '+'.
            //replaceAll is to replace all regex 
            //str = alias[0].replaceAll("\\.", "") + "@"+parts[1];
            normalized.add(local[0].replace(".", "") + "@" + parts[1]); // remove all '.', and concatenate '@' and domain.        
        }
        return normalized.size();
    }
}
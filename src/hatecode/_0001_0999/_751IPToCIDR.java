package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;

public class _751IPToCIDR {
/*
751. IP to CIDR
Given a start IP address ip and a number of ips we need to cover n, return a 
representation of the range as a list (of smallest possible length) of CIDR blocks.

A CIDR block is a string consisting of an IP, followed by a slash, and then the 
prefix length. For example: "123.45.67.89/20". That prefix length "20" represents 
the number of common prefix bits in the specified range.

Example 1:
Input: ip = "255.0.0.7", n = 10
Output: ["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
Explanation:
The initial ip address, when converted to binary, looks like this (spaces added for clarity):
255.0.0.7 -> 11111111 00000000 00000000 00000111
The address "255.0.0.7/32" specifies all addresses with a common prefix of 32 bits to 
the given address,
ie. just this one address.

The address "255.0.0.8/29" specifies all addresses with a common prefix of 29 bits to 
the given address:
255.0.0.8 -> 11111111 00000000 00000000 00001000
Addresses with common prefix of 29 bits are:
11111111 00000000 00000000 00001000
11111111 00000000 00000000 00001001
11111111 00000000 00000000 00001010
11111111 00000000 00000000 00001011
11111111 00000000 00000000 00001100
11111111 00000000 00000000 00001101
11111111 00000000 00000000 00001110
11111111 00000000 00000000 00001111

The address "255.0.0.16/32" specifies all addresses with a common prefix of 32 bits to the given address,
ie. just 11111111 00000000 00000000 00010000.

In total, the answer specifies the range of 10 ips starting with the address 255.0.0.7 .

There were other representations, such as:
["255.0.0.7/32","255.0.0.8/30", "255.0.0.12/30", "255.0.0.16/32"],
but our answer was the shortest possible.

Also note that a representation beginning with say, "255.0.0.7/30" would be incorrect,
because it includes addresses like 255.0.0.4 = 11111111 00000000 00000000 00000100 
that are outside the specified range.
 */
    //O(n)
    public List<String> ipToCIDR(String ip, int range) {
        long x = 0;
        String[] ips = ip.split("\\.");
        for (int i = 0; i < ips.length; ++i) {
            x = Integer.valueOf(ips[i]) + x * 256;
        }

        List<String> ans = new ArrayList<>();
        while (range > 0) {
            // x & (-x) will be the power of 2 corresponding to the location of the first 1
            // in x.
            long step = x & -x; // (low bit)
            while (step > range)
                step /= 2;
            ans.add(longToIP(x, (int) step));
            x += step;
            range -= step;
        }

        return ans;
    }

    String longToIP(long x, int step) {
        int[] ans = new int[4];
        ans[0] = (int) (x & 255);
        x >>= 8;
        ans[1] = (int) (x & 255);
        x >>= 8;
        ans[2] = (int) (x & 255);
        x >>= 8;
        ans[3] = (int) x;
        int len = 33;
        while (step > 0) {
            len--;
            step /= 2;
        }
        return ans[3] + "." + ans[2] + "." + ans[1] + "." + ans[0] + "/" + len;
    }
}
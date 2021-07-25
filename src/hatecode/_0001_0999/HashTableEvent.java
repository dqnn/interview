package hatecode._0001_0999;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class HashTableEvent {

    

    
    static byte[] longToByteArray(long value) {
        return ByteBuffer.allocate(8).putLong(value).array();
    }

    static long byteArrayToLong(byte[] array) {
        return ByteBuffer.wrap(array).getLong();
    }

    public static void main(String[] args) {
        
        String hex16Chars = String.format("%016X", 2L);
        System.out.println(hex16Chars + ", len: " + hex16Chars.length());
        //byte[] b1 = longToByteArray(1563454984001L);
        System.out.println(Long.toHexString(1563454984001L));
        long maxValue = Long.parseUnsignedLong("FFFFFFFFFFFFFFFF", 16);
        byte[] b = longToByteArray(maxValue);
        System.out.println("b = " + Arrays.toString(b));

        long value = byteArrayToLong(b);
        System.out.println("value = " + value);
        System.out.println("hex value = " + Long.toUnsignedString(value, 16));
    }

}

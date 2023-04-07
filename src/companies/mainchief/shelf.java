package companies.mainchief;

import java.util.concurrent.atomic.AtomicInteger;

enum OrderType {
    Hot,
    Frozen,
    Cold
}

public class Shelf {
    AtomicInteger size = new AtomicInteger();
    int cap;

    

    public Shelf(int cap) {
        this.cap= cap;
    }


}

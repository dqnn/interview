package companies.mainchief;

import java.util.concurrent.LinkedBlockingQueue;

public class ShelfLinkedBlockingQueue<E> extends LinkedBlockingQueue<E> {
    private int shelfDecayModifier;

    public ShelfLinkedBlockingQueue(int shelfDecayModifier, int capacity) {
        super(capacity);
        this.shelfDecayModifier = shelfDecayModifier;
    }

    @Override
    public String toString() {
        return "ShelfLinkedBlockingQueue "
                + "["
                + "shelfDecayModifier=" + shelfDecayModifier 
                + " remainingCapacity=" + this.remainingCapacity() 
                + " currentQueueSize=" + this.size()
                + "]";
    }
    
    
}

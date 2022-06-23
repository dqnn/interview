package companies.mainchief;

import java.util.Arrays;
import java.util.logging.Logger;

public class MainKitchenFulfulmentAndDeliveryApplication {

    public static void main(String[] args) {
        
        Logger log = Logger.getLogger(MainKitchenFulfulmentAndDeliveryApplication.class.getName());
        ShelfManager shelfM = new ShelfManager(10, 10, 10, 15);
        DeliveryManager deliveryM = new DeliveryManager(shelfM);
        
        IngestingJsonAndOrderproducer producer = new IngestingJsonAndOrderproducer(shelfM, deliveryM);
        StatusManager statusManager = new StatusManager(Arrays.asList(shelfM,deliveryM, producer));
        
        Thread deliveryTh = new Thread(deliveryM, "deliveryM");
        deliveryTh.start();
        
        Thread statusManagerTH = new Thread(statusManager, "StatusManager");
        statusManagerTH.start();
        
        Thread producerTH = new Thread(producer, "IngestingJsonAndOrderproducer");
        producerTH.start();
        
        try {
            producerTH.join();
            
            Thread.sleep(20000);
            deliveryM.shutdown();
            deliveryTh.join();
            
            statusManager.shutdown();
            statusManagerTH.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
        log.info("exiting main app!!!");
    }
}

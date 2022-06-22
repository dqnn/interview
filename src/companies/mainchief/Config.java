package companies.mainchief;

public final class Config {

    public static final int SHELF_HOT_SHELF_SIZE = 10;
    public static final int SHELF_COLD_SHELF_SIZE = 10;
    public static final int SHELF_FROZEN_SHELF_SIZE = 10;
    public static final int SHELF_OVERFLOW_SHELF_SIZE = 15;
    
    public static final int COMMON_SHELF_DECAYRATE = 1;
    public static final int OVERFLOW_SHELF_DECAYRATE = 2;
    
    public static final int INGESTION_ORDER_RATE = 5;  //ingestion qps

    public Config() {}

}

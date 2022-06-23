package companies.mainchief;

import java.util.Objects;

import org.json.simple.JSONObject;

public class Order {

    /*
     * {
    "id": "a8cfcb76-7f24-4420-a5ba-d46dd77bdffd",
    "name": "Banana Split",
    "temp": "frozen", ​// Preferred shelf storage temperature
    "shelfLife": 20, /​/ Shelf wait max duration (seconds)
    "decayRate": 0.63 /​/ Value deterioration modifier
}
     */
    
    String id;
    String name;
    String temp;
    long shelfLife;
    double decayRate;
    long timestamp;
    int shelfDecayModifier;
    
    public Order(String id, String name, String temp, long shelfLife, double decayRate) {
        this.id = id;
        this.name = name;
        this.temp = temp;
        this.shelfLife = shelfLife;
        this.decayRate = decayRate;
        this.timestamp = System.currentTimeMillis();
    }
    
    public Order(String id, String name, String temp, long shelfLife, double decayRate, int shelfDecayModifier) {
        this.id = id;
        this.name = name;
        this.temp = temp;
        this.shelfLife = shelfLife;
        this.decayRate = decayRate;
        this.timestamp = System.currentTimeMillis();
        this.shelfDecayModifier = shelfDecayModifier;
    }

    public long getTimestamp() {
        return timestamp;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        return Objects.equals(id, other.id);
    }


    @Override
    public String toString() {
        return "Order [id=" + id + ", name=" + name + ", temp=" + temp + ", shelfLife=" + shelfLife + ", decayRate="
                + decayRate + " value=" + calcOrderValue() + "]";
    }
    
    public static Order buildOrderFromJson(JSONObject json) {
        String id = (String) json.get("id");
        String name = (String) json.get("name");
        String temp = (String) json.get("temp");
        long shelfLife = (Long) json.get("shelfLife");
        double decayRate = (double) json.get("decayRate");
        return new Order(id, name, temp, shelfLife, decayRate);
    }
    
    public double calcOrderValue() {
        return 1.0 *(shelfLife - decayRate * (System.currentTimeMillis() - timestamp)/1000.0 * shelfDecayModifier) / shelfLife;
    }
    
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getTemp() {
        return temp;
    }


    public void setTemp(String temp) {
        this.temp = temp;
    }


    public long getShelfLife() {
        return shelfLife;
    }


    public void setShelfLife(long shelfLife) {
        this.shelfLife = shelfLife;
    }


    public double getDecayRate() {
        return decayRate;
    }


    public void setDecayRate(double decayRate) {
        this.decayRate = decayRate;
    }

    
    
}

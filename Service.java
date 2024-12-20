package DCMSpack;

import java.io.Serializable;

public class Service  implements Serializable {
    private int price;
    private String serviceName;

    public Service() {
    }

    public Service(String name, int price) {
        this.serviceName = name;
        this.price = price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.serviceName = name;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return serviceName;
    }

}
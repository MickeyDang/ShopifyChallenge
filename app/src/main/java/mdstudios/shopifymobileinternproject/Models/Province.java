package mdstudios.shopifymobileinternproject.Models;

import java.util.ArrayList;
import java.util.List;

public class Province {

    private String provinceName;
    private int count;

    private List<Order> orders;

    public Province(String name) {
        provinceName = name;
        orders = new ArrayList<>();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void incrimentCount() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}

package mdstudios.shopifymobileinternproject.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderWrapper {

    @SerializedName("orders")
    private List<Order> list;

    public OrderWrapper() {

    }

    public List<Order> getList() {
        return list;
    }

    public void setList(List<Order> list) {
        this.list = list;
    }
}

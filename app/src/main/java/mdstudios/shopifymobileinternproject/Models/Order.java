package mdstudios.shopifymobileinternproject.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {

    private String id;
    private String email;
    private String name;

    @SerializedName("customer")
    private Customer customer;

    @SerializedName("line_items")
    private List<LineItem> lineItems;

    @SerializedName("user_id")
    private String userID;

    @SerializedName("billing_address")
    private Address address;

    @SerializedName("created_at")
    private String timeCreation;

    public Order() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTimeCreation() {
        return timeCreation;
    }

    public void setTimeCreation(String timeCreation) {
        this.timeCreation = timeCreation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    @Override
    public String toString() {
        return address.getProvince() + " : " + timeCreation + " : " + name + " : " + email;
    }
}

package mdstudios.shopifymobileinternproject.Models;

import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("province")
    private String province;
    private String city;
    private String country;

    public Address() {

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}

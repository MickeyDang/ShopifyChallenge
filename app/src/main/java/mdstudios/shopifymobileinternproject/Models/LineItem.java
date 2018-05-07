package mdstudios.shopifymobileinternproject.Models;

public class LineItem {

    private long id;
    private String title;
    private String vendor;

    public LineItem() {
        id = 0;
        title = "";
        vendor = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}

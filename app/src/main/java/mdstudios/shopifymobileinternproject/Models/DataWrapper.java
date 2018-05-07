package mdstudios.shopifymobileinternproject.Models;

import java.util.ArrayList;
import java.util.List;

public class DataWrapper<T> {

    private T t;

    public DataWrapper(T t) {
        this.t = t;
    }

    public T getData() {
        return t;
    }

    public static <T> List<DataWrapper> wrapInformation(List<T> list) {
        List<DataWrapper> wrappers = new ArrayList<>();
        for (T t : list) {
            wrappers.add(new DataWrapper<T>(t));
        }
        return wrappers;
    }

}

package mdstudios.shopifymobileinternproject.REST;

import mdstudios.shopifymobileinternproject.Models.OrderWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShopifyDataRequest {

    @GET("admin/orders.json?")
    Call<OrderWrapper> getData(@Query("page") String pageNumber, @Query("access_token") String accessCode);

}

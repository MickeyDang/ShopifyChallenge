package mdstudios.shopifymobileinternproject.REST;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mdstudios.shopifymobileinternproject.Models.Order;
import mdstudios.shopifymobileinternproject.Models.OrderWrapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private final String TAG = this.getClass().getSimpleName();
    private final String BASE_URL = "https://shopicruit.myshopify.com";
    private final String ACCESS_TOKEN = "c32313df0d0ef512ca64d5b336a0d7c6";
    private final String PAGE_NUMBER = "1";


    public void getData(final OrderCallback<List<Order>> orderCallback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ShopifyDataRequest service = retrofit.create(ShopifyDataRequest.class);
        Call<OrderWrapper> objectCall = service.getData(PAGE_NUMBER, ACCESS_TOKEN);

        objectCall.enqueue(new Callback<OrderWrapper>() {
            @Override
            public void onResponse(Call<OrderWrapper> call, Response<OrderWrapper> response) {

                OrderWrapper wrapper = response.body();

                //assume some data may be incomplete. Filter null data
                List<Order> orders = new ArrayList<>();

                for (Order order : wrapper.getList()) {
                    if (order.getAddress() != null && order.getAddress().getProvince() != null && order.getTimeCreation() != null) {
                        orders.add(order);
                    }
                }

                orderCallback.onOrderCallback(orders);
            }

            @Override
            public void onFailure(Call<OrderWrapper> call, Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());
            }
        });
    }

    public interface OrderCallback<T> {
        void onOrderCallback(T t);
    }

}

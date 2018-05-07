package mdstudios.shopifymobileinternproject.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mdstudios.shopifymobileinternproject.Fragments.OrderByProvinceFragment;
import mdstudios.shopifymobileinternproject.Fragments.OverviewFragment;
import mdstudios.shopifymobileinternproject.Models.Order;
import mdstudios.shopifymobileinternproject.Models.Province;
import mdstudios.shopifymobileinternproject.R;
import mdstudios.shopifymobileinternproject.REST.RestClient;

public class MainActivity extends AppCompatActivity implements RestClient.OrderCallback<List<Order>>,
        OverviewFragment.OnFragmentInteractionListener {

    private final String TAG = this.getClass().getSimpleName();
    private final int PERMISSION_RC = 1;

    private HashMap<String, Province> mProvinces = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check permissions if needed
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET}, PERMISSION_RC);

        } else {
            fetchAPIData();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_RC:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchAPIData();
                }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void fetchAPIData() {
        RestClient restClient = new RestClient();
        restClient.getData(this);
    }

    @Override
    public void onOrderCallback(List<Order> orders) {
        buildMap(orders);
    }

    private void buildMap(List<Order> orders) {
        //holds number of orders created in 2017
        int count2017 = 0;
        //holds list of orders created in 2017
        List<Order> ordersIn2017 = new ArrayList<>();

        for (Order o : orders) {

            //String parse to find year of created order
            String year = o.getTimeCreation().substring(0, 4);
            if (year.equals("2017")) {
                count2017++;
                ordersIn2017.add(o);
            }

            //increment province count
            String provName = o.getAddress().getProvince();
            Province province = mProvinces.get(provName) == null ? new Province(provName) : mProvinces.get(provName);
            province.incrimentCount();
            province.getOrders().add(o);
            mProvinces.put(provName, province);
        }

        goToFragment(OverviewFragment.newInstance(ordersIn2017, new ArrayList<>(mProvinces.values()), count2017));
    }

    private void goToFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHolder, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onGoToOrderByProvince() {
        goToFragment(OrderByProvinceFragment.newInstance(mProvinces));
    }
}

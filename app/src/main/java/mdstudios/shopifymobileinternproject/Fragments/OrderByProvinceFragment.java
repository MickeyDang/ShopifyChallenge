package mdstudios.shopifymobileinternproject.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import mdstudios.shopifymobileinternproject.Adapters.OrderByProvAdapter;
import mdstudios.shopifymobileinternproject.Models.Order;
import mdstudios.shopifymobileinternproject.Models.Province;
import mdstudios.shopifymobileinternproject.R;


public class OrderByProvinceFragment extends Fragment {

    private HashMap<String, Province> mProvinces;

    private RecyclerView mRecyclerView;
    private OrderByProvAdapter mAdapter;

    public OrderByProvinceFragment() {
        // Required empty public constructor
    }

    public static OrderByProvinceFragment newInstance(HashMap<String, Province> provinces) {
        OrderByProvinceFragment fragment = new OrderByProvinceFragment();
        fragment.mProvinces = provinces;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_by_province, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = createAdapterWithSet();
        mRecyclerView = view.findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

    }

    private OrderByProvAdapter createAdapterWithSet() {
        List<String> dividerTitle = new ArrayList<>();

        //stores the divisions between subOrders in allOrders
        List<Integer> dividerIndicies = new ArrayList<>();
        List<Order> allOrders = new ArrayList<>();

        //first divider is at index 0
        int index = 0;

        for (String key : sortKeySet(mProvinces.keySet())) {
            //add key as province name
            dividerTitle.add(key);

            //get orders associated with province
            List<Order> ordersInProv = mProvinces.get(key).getOrders();

            //increment index of next divider appropriately
            dividerIndicies.add(index);
            index += (ordersInProv.size());

            //add orders
            allOrders.addAll(ordersInProv);
        }
        return new OrderByProvAdapter(dividerTitle, dividerIndicies, allOrders);
    }

    //utility functions to alphabetically sort list
    private List<String> sortKeySet(Set<String> unsortedSet) {
        List<String> list = new LinkedList<>();
        list.addAll(unsortedSet);

        for (int i = 0; i < unsortedSet.size(); i++) {
            for (int j = i; j > 0; j--) {
                //condition is true if (j) should be before (j - 1)
                if (list.get(j).compareTo(list.get(j - 1)) < 0) {
                    swapWithPreceeding(list, j);
                }
            }
        }
        return list;
    }

    private void swapWithPreceeding(List<String> list, int j) {
        String temp = list.get(j);
        list.remove(j);
        list.add(j, list.get(j - 1));
        list.remove(j - 1);
        list.add(j - 1, temp);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}

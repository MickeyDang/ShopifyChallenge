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

import java.util.List;

import mdstudios.shopifymobileinternproject.Adapters.OverviewAdapter;
import mdstudios.shopifymobileinternproject.Models.Order;
import mdstudios.shopifymobileinternproject.Models.Province;
import mdstudios.shopifymobileinternproject.R;


public class OverviewFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private List<Province> provinces;
    private List<Order> ordersIn2017;
    private int count2017;
    RecyclerView mRecyclerView;
    OverviewAdapter mAdapter;

    public OverviewFragment() {
        // Required empty public constructor
    }

    public static OverviewFragment newInstance(List<Order> ordersIn2017, List<Province> provinces, int count2017) {
        OverviewFragment fragment = new OverviewFragment();
        fragment.ordersIn2017 = ordersIn2017;
        fragment.count2017 = count2017;
        fragment.provinces = provinces;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.list);
        mAdapter = new OverviewAdapter(mListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        initView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void initView() {
        mAdapter.addOrders2017(ordersIn2017);
        mAdapter.addProvinces(provinces, provinces.size());
        mAdapter.setNumOrdersFrom2017(count2017);

        mAdapter.compileList();
    }

    public interface OnFragmentInteractionListener {
        void onGoToOrderByProvince();
    }
}

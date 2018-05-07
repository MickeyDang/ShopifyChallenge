package mdstudios.shopifymobileinternproject.Adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mdstudios.shopifymobileinternproject.Fragments.OverviewFragment;
import mdstudios.shopifymobileinternproject.Models.DataWrapper;
import mdstudios.shopifymobileinternproject.Models.Order;
import mdstudios.shopifymobileinternproject.Models.Province;
import mdstudios.shopifymobileinternproject.R;
import mdstudios.shopifymobileinternproject.ViewHolders.OrderViewHolder;
import mdstudios.shopifymobileinternproject.ViewHolders.ProvinceViewHolder;
import mdstudios.shopifymobileinternproject.ViewHolders.ViewHolder;
import mdstudios.shopifymobileinternproject.ViewHolders.YearViewHolder;

public class OverviewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final String HEADING_PROVINCE = "Order by Province";
    private final String HEADING_YEAR = "Order by Year";

    private int TYPE_YEAR = 98;
    private int TYPE_DIVIDER = 99;
    private int TYPE_PROV = 100;
    private int TYPE_ORDER = 101;

    private OverviewFragment.OnFragmentInteractionListener mListener;

    private int provinceDividerIndex;
    private int yearDividerIndex;

    List<DataWrapper> data;
    List<Province> provinces;
    Integer count2017;
    List<Order> orders;

    public OverviewAdapter(OverviewFragment.OnFragmentInteractionListener listener) {
        mListener = listener;
        provinceDividerIndex = 0;
        yearDividerIndex = 1;

        data = new ArrayList<>();
        provinces = new ArrayList<>();
        orders = new ArrayList<>();
        count2017 = 0;
    }

    //creating list
    public void setNumOrdersFrom2017(int i) {
        count2017 = i;
    }

    public void addProvinces(List<Province> provinces, int size) {
        this.provinces.addAll(provinces);
        yearDividerIndex += size;
    }

    public void addOrders2017(List<Order> orders) {
        this.orders.addAll(orders);
    }

    public void compileList() {
        data.clear();

        sort(provinces);

        data.add(new DataWrapper<String>(HEADING_PROVINCE));
        data.addAll(DataWrapper.wrapInformation(provinces));
        data.add(new DataWrapper<String>(HEADING_YEAR));
        data.add(new DataWrapper<Integer>(count2017));
        data.addAll(DataWrapper.wrapInformation(orders));

        notifyDataSetChanged();
    }

    //utility functions
    private void sort(List<Province> list) {
        //page 1 is 50 items max so insertion sort is efficient
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j > 0; j--) {
                //condition is true if (j) should be before (j - 1)
                if (list.get(j).getProvinceName().compareTo(list.get(j - 1).getProvinceName()) < 0) {
                    swapWithPreceeding(list, j);
                }
            }
        }
    }

    private void swapWithPreceeding(List<Province> list, int j) {
        Province temp = list.get(j);
        list.remove(j);
        list.add(j, list.get(j - 1));
        list.remove(j - 1);
        list.add(j - 1, temp);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (viewType == TYPE_DIVIDER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_textline, parent, false);
            return new DividerViewHolder(view);
        } else if (viewType == TYPE_YEAR) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_province, parent, false);
            return new YearViewHolder(view);
        } else if (viewType == TYPE_ORDER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_order, parent, false);
            return new OrderViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_province, parent, false);
            return new ProvinceViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (holder instanceof ProvinceViewHolder) {
            Province prov = (Province) data.get(position).getData();
            ((ProvinceViewHolder) holder).onBind(prov);
        } else if (holder instanceof DividerViewHolder) {
            String s = (String) data.get(position).getData();
            ((DividerViewHolder) holder).onBind(s, position);
        } else if (holder instanceof YearViewHolder) {
            int i = (int) data.get(position).getData();
            ((YearViewHolder) holder).onBind(i);
        } else if (holder instanceof OrderViewHolder) {
            Order o = (Order) data.get(position).getData();
            ((OrderViewHolder) holder).onBind(o);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position == provinceDividerIndex || position == yearDividerIndex) {
            return TYPE_DIVIDER;
        } else if (position == yearDividerIndex + 1) {
            return TYPE_YEAR;
        } else if (position > yearDividerIndex + 1) {
            return TYPE_ORDER;
        } else {
            return TYPE_PROV;
        }

    }

    class DividerViewHolder extends mdstudios.shopifymobileinternproject.ViewHolders.DividerViewHolder {
        View v;

        public DividerViewHolder(View view) {
            super(view);
            v = view;
        }

        public void onBind(String s, int pos) {
            onBind(s);
            if (pos == 0) {
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onGoToOrderByProvince();
                    }
                });
            }
        }

        @Override
        public void onBind(String s) {
            super.onBind(s);
        }
    }
}

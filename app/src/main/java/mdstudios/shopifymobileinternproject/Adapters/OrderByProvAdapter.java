package mdstudios.shopifymobileinternproject.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mdstudios.shopifymobileinternproject.Models.DataWrapper;
import mdstudios.shopifymobileinternproject.Models.Order;
import mdstudios.shopifymobileinternproject.R;
import mdstudios.shopifymobileinternproject.ViewHolders.DividerViewHolder;
import mdstudios.shopifymobileinternproject.ViewHolders.OrderViewHolder;
import mdstudios.shopifymobileinternproject.ViewHolders.ViewHolder;

public class OrderByProvAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final String TAG = this.getClass().getSimpleName();

    private final int TYPE_DIVIDER = 99;
    private final int TYPE_ORDER = 101;

    private List<DataWrapper> data;
    private List<Integer> dividerIndicies;
    private List<String> dividers;
    private List<Order> orders;

    public OrderByProvAdapter(List<String> dividers, List<Integer> dividerIndicies, List<Order> allOrders) {
        data = new ArrayList<>();
        this.dividers = dividers;
        this.dividerIndicies = dividerIndicies;
        this.orders = allOrders;
        compileList();
    }

    private void compileList() {
        if (dividerIndicies.size() != dividers.size()) {
            Log.e(TAG, "not matching dividers with indices");
            return;
        }

        for (int i = 0; i < dividerIndicies.size(); i++) {

            data.add(new DataWrapper<String>(dividers.get(i)));

            //adds a sublist of elements from dividerIndicies(i) to dividerIndicies(i+1) or the end of the orders list.
            List<Order> subList = orders.subList(dividerIndicies.get(i),
                    (i + 1 < dividerIndicies.size() ? dividerIndicies.get(i + 1) : orders.size() - 1));

            data.addAll(DataWrapper.wrapInformation(subList));
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if (isDivider(position)) {
            return TYPE_DIVIDER;
        } else {
            return TYPE_ORDER;
        }
    }

    private boolean isDivider(int position) {
        for (int i = 0; i < dividerIndicies.size(); i++) {
            if (dividerIndicies.get(i) + i == position)
                return true;
        }
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_DIVIDER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_textline, parent, false);
            return new DividerViewHolder(view);
        } else if (viewType == TYPE_ORDER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_order, parent, false);
            return new OrderViewHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder instanceof DividerViewHolder) {
            String s = (String) data.get(position).getData();
            ((DividerViewHolder) holder).onBind(s);
        } else if (holder instanceof OrderViewHolder) {
            Order o = (Order) data.get(position).getData();
            ((OrderViewHolder) holder).onBind(o);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

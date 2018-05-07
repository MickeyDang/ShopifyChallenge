package mdstudios.shopifymobileinternproject.ViewHolders;

import android.view.View;
import android.widget.TextView;

import mdstudios.shopifymobileinternproject.Models.Order;
import mdstudios.shopifymobileinternproject.R;

public class OrderViewHolder extends ViewHolder {
    TextView nameView;
    TextView addressView;
    TextView productView;

    public OrderViewHolder(View view) {
        super(view);
        nameView = view.findViewById(R.id.name);
        addressView = view.findViewById(R.id.address);
        productView = view.findViewById(R.id.product);
    }

    public void onBind(Order order) {

        String formattedText = order.getName() + " " + order.getCustomer().getFirstName()
                + " " + order.getCustomer().getLastName();
        nameView.setText(formattedText);

        formattedText = order.getAddress().getCity() + ", "
                + order.getAddress().getProvince() + ", "
                + order.getAddress().getCountry();
        addressView.setText(formattedText);

        formattedText = order.getLineItems().get(0).getId() + ": "
                + order.getLineItems().get(0).getTitle() + "...";
        productView.setText(formattedText);

    }
}

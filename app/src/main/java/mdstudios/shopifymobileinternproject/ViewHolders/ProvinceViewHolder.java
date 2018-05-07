package mdstudios.shopifymobileinternproject.ViewHolders;

import android.view.View;
import android.widget.TextView;

import mdstudios.shopifymobileinternproject.Models.Province;
import mdstudios.shopifymobileinternproject.R;

public class ProvinceViewHolder extends ViewHolder {

    private final String TEXT = " number of orders from ";
    TextView provinceText;

    public ProvinceViewHolder(View view) {
        super(view);
        provinceText = view.findViewById(R.id.text);
    }

    public void onBind(Province prov) {
        String s = prov.getCount() + TEXT + prov.getProvinceName();
        provinceText.setText(s);
    }

}

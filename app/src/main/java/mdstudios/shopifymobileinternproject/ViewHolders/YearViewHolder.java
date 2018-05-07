package mdstudios.shopifymobileinternproject.ViewHolders;

import android.view.View;
import android.widget.TextView;

import mdstudios.shopifymobileinternproject.R;

public class YearViewHolder extends ViewHolder {
    private final String TEXT = " number of orders created in 2017";
    TextView information;

    public YearViewHolder(View view) {
        super(view);
        information = view.findViewById(R.id.text);
    }

    public void onBind(int i) {
        String s = i + TEXT;
        information.setText(s);
    }
}

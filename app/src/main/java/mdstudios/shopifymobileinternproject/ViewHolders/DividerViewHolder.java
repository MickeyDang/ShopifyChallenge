package mdstudios.shopifymobileinternproject.ViewHolders;

import android.view.View;
import android.widget.TextView;

import mdstudios.shopifymobileinternproject.R;

public class DividerViewHolder extends ViewHolder {
    TextView information;

    public DividerViewHolder(View view) {
        super(view);
        information = view.findViewById(R.id.text);
    }

    public void onBind(String s) {
        information.setText(s);
    }
}

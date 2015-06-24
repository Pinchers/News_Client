
package com.pinchers.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;





import java.util.List;
import java.util.Map;

import com.pinchers.news_client.R;


public class CustomSimpleAdapter extends SimpleAdapter {

    public CustomSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource,
            String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        if (position == 0) {
            TextView categoryTitle = (TextView) v;
            categoryTitle.setBackgroundResource(R.drawable.categorybar_item_background);
            categoryTitle.setTextColor(0xffffffff);
        }
        return v;
    }
}

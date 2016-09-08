package com.yju.app.shihui.special.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.yju.app.R;
import com.yju.app.base.BasicAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopupAdapter extends BasicAdapter<String> {

    private LayoutInflater layoutInflater = null;

    public PopupAdapter(Context context) {
        super();
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            convertView = layoutInflater.inflate(
                    R.layout.item_popup_layout, null);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        initItemData(viewHolder, getItem(position));
        return convertView;
    }

    private void initItemData(ViewHolder viewHolder, String item) {
       viewHolder.itemName.setText(item);
    }

    class ViewHolder {
        @BindView(R.id.item_name)
        TextView itemName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }

        public void reset() {
            itemName.setText("");
        }
    }
}

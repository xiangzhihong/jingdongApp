package com.yju.app.shihui.estateservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yju.app.R;
import com.yju.app.base.BasicAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EstateFeeAdapter extends BasicAdapter<String> {

    private Context context = null;

    public EstateFeeAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_estate_payment_layout, null);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        initItemData(viewHolder, getItem(i));
        return view;
    }

    private void initItemData(ViewHolder holder, String bean) {
        holder.estateMonth.setText(bean);
    }

    class ViewHolder {

        @BindView(R.id.estate_image)
        ImageView estateImage;
        @BindView(R.id.estate_month)
        TextView estateMonth;
        @BindView(R.id.estate_price)
        TextView estatePrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }

        public void reset() {

        }
    }
}

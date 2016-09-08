package com.yju.app.shihui.special.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yju.app.R;
import com.yju.app.base.BasicAdapter;
import com.yju.app.shihui.special.bean.ValueSaleEntity;
import com.yju.app.shihui.welfare.bean.SpecialSaleEntity.LiveBeanEityty.LivesBean;
import com.yju.app.utils.TimeUtils;
import com.yju.app.utils.ToastUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ValueSaleAdapter extends BasicAdapter<ValueSaleEntity> {

    private Context context=null;

    public ValueSaleAdapter(Context context) {
        super();
        this.context=context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
             view = LayoutInflater.from(context).inflate(R.layout.item_value_sale_layout,null);
            viewHolder = new ViewHolder(view);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        initItemData(viewHolder, getItem(i));
        return view;
    }

    private void initItemData(ViewHolder holder, ValueSaleEntity bean) {
        holder.itemImage.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(bean.pic).error(R.drawable.milk_icon).into(holder.itemImage);
        holder.itemTime.setText("剩余"+bean.endtime);
        holder.itemContent.setText(bean.name);
        holder.itemDiscount.setText("可使用现金抵用券"+bean.discount+"元");
        holder.itemOriginalPrice.setText("￥"+bean.originPrice);

        holder.itemBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast("立即抢购");
            }
        });
    }

    class ViewHolder {
        @BindView(R.id.item_image)
        ImageView itemImage;
        @BindView(R.id.item_time)
        TextView itemTime;
        @BindView(R.id.item_content)
        TextView itemContent;
        @BindView(R.id.item_discount)
        TextView itemDiscount;
        @BindView(R.id.item_original_price)
        TextView itemOriginalPrice;
        @BindView(R.id.item_buy)
        TextView itemBuy;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }

        public void reset() {
        }
    }
}

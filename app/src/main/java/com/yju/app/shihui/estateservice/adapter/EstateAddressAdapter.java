package com.yju.app.shihui.estateservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yju.app.R;
import com.yju.app.shihui.estateservice.activity.EstatePaymentActivity;
import com.yju.app.shihui.estateservice.entity.EstateAddressEntity;
import com.yju.swipemenu.BaseSwipListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2016/9/5.
 * 缴费地址管理adapter
 */
public class EstateAddressAdapter extends BaseSwipListAdapter {

    private Context mContext = null;
    private List<EstateAddressEntity> addressList = null;

    public EstateAddressAdapter(Context context) {
        super();
        this.mContext = context;
    }

    public void setList(List<EstateAddressEntity> addressList) {
        this.addressList = addressList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (addressList != null)
            return addressList.size();
        return 0;
    }

    @Override
    public EstateAddressEntity getItem(int i) {
        return addressList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_estate_address, null);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        initItemView(viewHolder, getItem(i));
        return view;
    }

    private void initItemView(ViewHolder viewHolder, EstateAddressEntity item) {
        viewHolder.itemName.setText(item.name);
        viewHolder.itemAddress.setText(item.address);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EstatePaymentActivity.open(mContext);
            }
        });
    }

    class ViewHolder {

        @BindView(R.id.item_view)
        LinearLayout itemView;
        @BindView(R.id.item_name)
        TextView itemName;
        @BindView(R.id.item_address)
        TextView itemAddress;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }

        public void reset() {
            itemName.setText("");
            itemAddress.setText("");
        }
    }
}
